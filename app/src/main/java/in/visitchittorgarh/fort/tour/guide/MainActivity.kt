package `in`.visitchittorgarh.fort.tour.guide

import `in`.visitchittorgarh.fort.tour.guide.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar: ProgressBar
    private lateinit var layoutOffline: LinearLayout
    private lateinit var splashOverlay: FrameLayout
    private lateinit var btnRetry: Button

    private var backPressedTime: Long = 0
    private var isFirstLoad = true

    companion object {
        const val TARGET_URL = "https://www.visitchittorgarh.in/"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupSwipeRefresh()
        setupWebView()
        setupBackNavigation()

        loadApp()
    }

    private fun initViews() {
        webView = findViewById(R.id.webView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        progressBar = findViewById(R.id.progressBar)
        layoutOffline = findViewById(R.id.layoutOffline)
        splashOverlay = findViewById(R.id.splashOverlay)
        btnRetry = findViewById(R.id.btnRetry)

        btnRetry.setOnClickListener {
            loadApp()
        }
    }

    private fun setupSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(this, R.color.accent_gold),
            ContextCompat.getColor(this, R.color.gold_light),
            ContextCompat.getColor(this, R.color.primary)
        )
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(this, R.color.surface_card)
        )
        swipeRefreshLayout.setOnRefreshListener {
            if (isNetworkAvailable()) {
                webView.reload()
            } else {
                swipeRefreshLayout.isRefreshing = false
                showOfflineScreen()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.databaseEnabled = true
        settings.allowFileAccess = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        settings.cacheMode = if (isNetworkAvailable()) {
            WebSettings.LOAD_DEFAULT
        } else {
            WebSettings.LOAD_CACHE_ELSE_NETWORK
        }

        val defaultUserAgent = settings.userAgentString
        settings.userAgentString = "$defaultUserAgent VisitChittorgarhApp/1.0.0 (Android; Mobile)"

        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress < 100) {
                    progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                } else {
                    progressBar.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = false
                }
            }

            override fun onGeolocationPermissionsShowPrompt(
                origin: String?,
                callback: GeolocationPermissions.Callback?
            ) {
                callback?.invoke(origin, true, false)
            }
        }

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                showOnlineScreen()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                swipeRefreshLayout.isRefreshing = false
                if (isFirstLoad) {
                    isFirstLoad = false
                    splashOverlay.animate()
                        .alpha(0f)
                        .setDuration(400)
                        .withEndAction {
                            splashOverlay.visibility = View.GONE
                        }
                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                if (request?.isForMainFrame == true && !isNetworkAvailable()) {
                    showOfflineScreen()
                }
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                val url = request?.url?.toString() ?: return false
                return handleSpecialIntents(url)
            }
        }
    }

    private fun handleSpecialIntents(url: String): Boolean {
        try {
            val uri = Uri.parse(url)

            if (url.startsWith("whatsapp://") || url.contains("api.whatsapp.com") || url.contains("wa.me")) {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                return true
            }

            if (url.startsWith("tel:")) {
                val intent = Intent(Intent.ACTION_DIAL, uri)
                startActivity(intent)
                return true
            }

            if (url.startsWith("mailto:")) {
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                startActivity(intent)
                return true
            }

            if (url.startsWith("geo:") || url.contains("maps.google.com") || url.contains("google.com/maps")) {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                return true
            }

            if (url.startsWith("upi://")) {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                return true
            }

            if (url.contains("visitchittorgarh.in")) {
                return false
            }

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
            return true

        } catch (e: Exception) {
            Toast.makeText(this, "Could not open external app", Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun loadApp() {
        if (isNetworkAvailable()) {
            showOnlineScreen()
            webView.loadUrl(TARGET_URL)
        } else {
            showOfflineScreen()
        }
    }

    private fun showOfflineScreen() {
        splashOverlay.visibility = View.GONE
        swipeRefreshLayout.visibility = View.GONE
        layoutOffline.visibility = View.VISIBLE
    }

    private fun showOnlineScreen() {
        layoutOffline.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return false
        val activeNetwork = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(activeNetwork) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    private fun setupBackNavigation() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    if (System.currentTimeMillis() - backPressedTime < 2000) {
                        finish()
                    } else {
                        backPressedTime = System.currentTimeMillis()
                        Toast.makeText(this@MainActivity, "Press back again to exit", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}
