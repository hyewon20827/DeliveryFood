package com.example.deliveryfood.view.fragment

import android.app.Activity
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.activityViewModels
import com.example.deliveryfood.R
import com.example.deliveryfood.base.BaseFragment
import com.example.deliveryfood.constant.BASE_URL
import com.example.deliveryfood.databinding.FragmentFirstBinding
import com.example.deliveryfood.utils._enum.WebviewPageType
import com.example.deliveryfood.viewmodel.DeliveryAddressViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebviewFragment : BaseFragment<FragmentFirstBinding, DeliveryAddressViewModel>() {

    private lateinit var handler : Handler

    override val layoutResourceId: Int
        get() = R.layout.fragment_first
    override val viewmodel: DeliveryAddressViewModel by activityViewModels<DeliveryAddressViewModel>()

    private var hashMap = HashMap<String, String>()

    private val client by lazy{
        object : WebViewClient(){
            override fun onReceivedSslError( view: WebView?, handler: SslErrorHandler?, error: SslError? ) {
                handler?.proceed()
            }

            override fun shouldOverrideUrlLoading( view: WebView?, request: WebResourceRequest? ): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }

//            override fun onPageFinished(view: WebView?, url: String?) {
//                view?.loadUrl("javascript:execKakaoPostcode();")
//            }
        }
    }

    private val webviewURL by lazy { BASE_URL + "daum.html" }

    companion object{
        fun newInstance(title : String) = WebviewFragment().apply {
            arguments = Bundle().apply {
                putString("title", title)
            }
        }
    }

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun FragmentFirstBinding.onCreateView() {
        handler = Handler(Looper.getMainLooper())
        setWebview()
    }

    override fun FragmentFirstBinding.onViewCreated() {

    }

    private fun setWebview(){
        viewDataBinding.webview.apply {
            settings.apply {
                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                setSupportMultipleWindows(true)
                defaultTextEncodingName = "UTF-8"
            }

            addJavascriptInterface(MyJavaScriptInterface(), "deliveryfood")
            webViewClient = client
            webChromeClient = WebChromeClient()
            loadUrl("http://192.168.43.76:8090/daum.html")
        }
    }

    private inner class MyJavaScriptInterface{
        @JavascriptInterface
        fun processDATA(userSelctedType : String, address : String, code : String){
            handler.post(object : Runnable {
                override fun run() {
                    hashMap.apply {
                        put("userSelectedType", userSelctedType)
                        put("address", address)
                        put("code", code)
                    }
                    viewmodel.putApiResult(hashMap)
                    viewmodel.changeWebviewFragment(WebviewPageType.WEBVIEW2)
                }
            })

        }
    }
}

