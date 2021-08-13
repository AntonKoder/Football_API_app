package com.example.footballapiapp.screens.preloader

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
import com.example.footballapiapp.R
import com.example.footballapiapp.databinding.PreloaderFragmentBinding


class PreloaderFragment : Fragment() {

    private lateinit var viewModel: PreloaderViewModel

    private var nullableBinding: PreloaderFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var observerOnUrl: Observer<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = PreloaderFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val vm: PreloaderViewModel by viewModels {
            PreloaderViewModelFactory()
        }
        viewModel = vm

        observerOnUrl = Observer {
            binding.webView.loadUrl(it)
        }
        viewModel.getCasino()

        viewModel.urlLiveData.observe(this, observerOnUrl)

        binding.webView.setParameters()

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse?
            ) {
                when (errorResponse?.statusCode) {
                    404 -> APP_ACTIVITY.navController.navigate(R.id.action_preloaderFragment_to_countriesFragment)
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