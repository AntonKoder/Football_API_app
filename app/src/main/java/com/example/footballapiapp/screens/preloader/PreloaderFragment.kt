package com.example.footballapiapp.screens.preloader

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.footballapiapp.APP_ACTIVITY
import com.example.footballapiapp.MyApplication
import com.example.footballapiapp.R
import com.example.footballapiapp.databinding.PreloaderFragmentBinding
import com.example.footballapiapp.di.components.ApplicationComponent
import com.example.footballapiapp.models.local.UserDB
import com.example.footballapiapp.repository.local.LocalRepository
import javax.inject.Inject


class PreloaderFragment : Fragment() {

    @Inject
    lateinit var localRepository: LocalRepository

    private lateinit var viewModel: PreloaderViewModel

    private var nullableBinding: PreloaderFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var observerOnUrl: Observer<String>
    private lateinit var observerOnUser: Observer<UserDB>

    lateinit var application: MyApplication

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = PreloaderFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun getAppRoomComponent(): ApplicationComponent {
        application = activity?.application as MyApplication
        return application.appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null)
            binding.webView.restoreState(savedInstanceState);

    }

    override fun onStart() {
        super.onStart()
        val appRoomComponent = getAppRoomComponent()
        appRoomComponent.inject(this)

        initViewModel()

        initWebViewObserver()

        binding.webView.setParameters()

        viewModel.getUser()

        viewModel.getCasino()

        initUserObserver()

        initWebViewClient()


    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    private fun initUserObserver() {
        observerOnUser = Observer {
            if (it != null) {
                if (!it.valid) {
                    APP_ACTIVITY.navController.navigate(R.id.action_preloaderFragment_to_countriesFragment)
                }
            }
        }
        viewModel.userLiveData.observe(this, observerOnUser)
    }

    private fun initWebViewClient() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                when (errorResponse?.statusCode) {
                    404 -> {
                        APP_ACTIVITY.navController.navigate(R.id.action_preloaderFragment_to_countriesFragment)
                        viewModel.saveUser(false)
                    }
                }
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                Log.d("WW", error.errorCode.toString())

                super.onReceivedError(view, request, error)
            }
        }
    }

    private fun initWebViewObserver() {
        observerOnUrl = Observer {
            binding.webView.loadUrl(it)
        }
        viewModel.urlLiveData.observe(this, observerOnUrl)
    }

    private fun initViewModel() {
        val vm: PreloaderViewModel by viewModels {
            PreloaderViewModelFactory(localRepository)
        }
        viewModel = vm
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun WebView.setParameters() {
        this.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.databaseEnabled = true
            settings.minimumFontSize = 1
            settings.minimumLogicalFontSize = 1
        }
    }
}