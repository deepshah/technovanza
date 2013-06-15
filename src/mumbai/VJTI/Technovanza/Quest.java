package mumbai.VJTI.Technovanza;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Quest extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest);
        
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://www.technovanza.org/mysteday_a/quest.php");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }


    
}
