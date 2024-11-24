package br.senai.sp.jandira.touccanuser.component

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun StripeWebView(accountLink: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient() // Garante que os links abram na mesma WebView
            settings.javaScriptEnabled = true
            loadUrl(accountLink)
        }
    })
}