package com.example.footballapiapp.screens.preloader

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.util.Log
import android.view.KeyEvent.ACTION_UP
import android.view.KeyEvent.KEYCODE_BACK
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
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

    lateinit var application: MyApplication

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = PreloaderFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun getAppRoomComponent(): ApplicationComponent {
        application = activity?.application as MyApplication
        return application.appComponent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appRoomComponent = getAppRoomComponent()
        appRoomComponent.inject(this)

        initViewModel()

        if (savedInstanceState != null) {
            binding.webView.restoreState(savedInstanceState)
        } else {
            binding.webView.setParameters()
            viewModel.getCasinoRootUrl()
        }
        initWebViewObserver()
    }

    override fun onStart() {
        super.onStart()
        initWebViewClient()
    }

    private fun initWebViewClient() {
        binding.webView.webViewClient = object : WebViewClient() {

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                when (errorResponse?.statusCode) {
                    @Suppress("MagicNumber") // Ошибка 404, можно вынести в const для красоты
                    404 -> {
                        // Ловим 404 во время оплаты. Поэтому если ссылка содержит "pay" -> игнорируем
                        val url = view?.url
                        if (url != null) {
                            if (!url.contains("pay", ignoreCase = true)) {
                                Log.d("HTML", "catch 404 error")
                                //тут нужный перехват не сработает
                            }
                        }
                    }
                    else -> {
                        Log.d("err", "catch another error!")
                    }
                }
                super.onReceivedHttpError(view, request, errorResponse)
            }

            override fun onPageFinished(view: WebView?, url: String) {
                super.onPageFinished(view, url)
                Handler(getMainLooper()).postDelayed({
                    view!!.evaluateJavascript(
                        "(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();",
                        ValueCallback<String?> { html ->
                            if (html != null) {
                                Log.d("HTML", html.length.toString() + " post")
                                if (html.contains("ERR_HTTP") || (html.length < 1000)) {
                                    view.visibility = INVISIBLE
                                    APP_ACTIVITY.navController.navigate(R.id.action_preloaderFragment_to_countriesFragment)
                                } else {
                                    view.visibility = VISIBLE
                                    binding.progressBar.visibility = GONE
                                }
                            }
                        })
                }, 2000)


            }

            override fun onReceivedError(
                view: WebView,
                request: WebResourceRequest,
                error: WebResourceError
            ) {
                super.onReceivedError(view, request, error)
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (URLUtil.isNetworkUrl(url)) {
                    return false
                }
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.d("err", "ERROR! $e")
                }
                return true
            }
        }
    }

    private fun initWebViewObserver() {
        observerOnUrl = Observer {
            binding.webView.loadUrl(it)
        }
        viewModel.urlLiveData.observe(this, observerOnUrl)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webView.saveState(outState)
    }

    private fun initViewModel() {
        val vm: PreloaderViewModel by viewModels {
            PreloaderViewModelFactory(localRepository, application)
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
            setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                if (keyCode == KEYCODE_BACK && event.action == ACTION_UP) {
                    if (this.canGoBack()) {
                        this.goBack()
                    } else {
                        APP_ACTIVITY.navController.popBackStack()
                        APP_ACTIVITY.finish()
                    }
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webView.destroy()
        nullableBinding = null
    }
}
