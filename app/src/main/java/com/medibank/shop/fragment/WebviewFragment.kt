package com.medibank.shop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.medibank.shop.databinding.FragmentWebviewBinding

class WebviewFragment : Fragment() {

    private lateinit var binding: FragmentWebviewBinding
    private val args: WebviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observer
        loadWebView()
    }

    private fun loadWebView(){
        binding.webView.loadUrl(args.url)

        binding.webView.settings.setJavaScriptEnabled(true)

// Force links and redirects to open in the WebView instead of in a browser
        binding.webView.setWebViewClient(WebViewClient())

    }
}