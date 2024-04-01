package com.example.myapp.ui.apilist.activity.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.myapp.R
import com.example.myapp.databinding.FragmentAPIDetailBinding

class APIDetailFragment : Fragment(R.layout.fragment_a_p_i_detail) {
    var url_api=""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAPIDetailBinding.bind(view)
        arguments?.let {
            url_api = it.getString("ApiURL").toString()
        }
        binding.apply {
            incHeader.tvTitle.text = "API Details"
            webView.apply {
                settings.builtInZoomControls = true
                settings.javaScriptEnabled = true
                settings.useWideViewPort = true
                settings.loadWithOverviewMode = true
                if (url_api != null && url_api !=""){
                    loadUrl(url_api)
                    setupWebView(binding)
                }
            }
        }

    }
    private fun setupWebView(binding : FragmentAPIDetailBinding) {
        try{
            val webViewClient: WebViewClient = object: WebViewClient() {

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return super.shouldOverrideUrlLoading(view, request)
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }
            }
            binding.apply {
                webView.webViewClient = webViewClient
                webView.settings.javaScriptEnabled = true
                webView.settings.defaultTextEncodingName = "utf-8"
            }

        }catch (e : java.lang.Exception){
            e.printStackTrace()
        }
    }
}