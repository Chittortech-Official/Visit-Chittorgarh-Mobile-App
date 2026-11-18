-keepattributes JavascriptInterface
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(..);
}
-keepclassmembers class * extends android.webkit.WebChromeClient {
    public void *(..);
}
-dontwarn android.webkit.**
