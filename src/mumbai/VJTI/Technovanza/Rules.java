package mumbai.VJTI.Technovanza;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Rules extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rules);
        
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://www.technovanza.org/mysteday_a/rules.php");
    }

    
}
