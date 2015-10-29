package apps101.co.za.picassoandrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */
public class WebActivity extends Activity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webview = new WebView(this);

        setContentView(R.layout.progressbar);

        String url = getIntent().getStringExtra("articleUrl");
        if (url == null) {
            url = "http://www.101apps.co.za/";
        }
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                setContentView(webview);
            }
        });
    }
}
