package mumbai.VJTI.Technovanza;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.util.Linkify;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


@SuppressWarnings("deprecation")//deprecated after android version3.1..so will this do for normal android phones(2.0-2.3) as very few people have 3.1
public class EventName extends Activity
{
	private static TextView tv;
    DBAdapter dd=new DBAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.event3);
        tv=(TextView)findViewById(R.id.tv);
        Intent intent=getIntent();
        final String eventName=intent.getStringExtra("item");
        final String tName=intent.getStringExtra("id");
        
        DBAdapter db=new DBAdapter(this);

    	db.open();
		Button submit=(Button) findViewById(R.id.register);
       	submit.setOnClickListener(new View.OnClickListener() {
   			
   			public void onClick(View arg0) {
   				if(isOnline())
   					send(tName);
   			}
   		});
       	
    	tv.setText("");
    	Log.v(eventName+tName,db.getDesc(eventName,tName));
    	tv.append(db.getDesc(eventName,tName));
    	MatchFilter matchFilter = new MatchFilter() {
    		public final boolean acceptMatch(CharSequence s, int start, int end) {
    		
    		return true;
    		}
    		};
    		TransformFilter transformFilter = new TransformFilter() {
    		public final String transformUrl(final Matcher match, String url) {
    		
    		return "http://technovanza.org/sectors/"+tName.toLowerCase()+"/"+(eventName.toLowerCase().replace(" ",""))+"/";
    		}
    		};

    		Pattern pattern = Pattern.compile("MORE");
    		String scheme = "http://";
    		Linkify.addLinks(tv, pattern, scheme, matchFilter, transformFilter);
    		
       db.close(); 
    }  
    
    public void mainpage(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
    
	public void send(String tName)
	{
	
        String url="http://technovanza.org/registeruser_event_a.php";
        String response="";
        try{
        dd.open();	
    	HttpPost httppost;
        DefaultHttpClient httpclient;
        ResponseHandler <String> res=new BasicResponseHandler();
        List<NameValuePair> nameValuePairs;
        httppost = new HttpPost(url);
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        httpclient = new DefaultHttpClient(params);
        nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair("key", "value"));
        nameValuePairs.add(new BasicNameValuePair("email", dd.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("event", tName));
        nameValuePairs.add(new BasicNameValuePair("submit", "Register"));

        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  
        response=httpclient.execute(httppost, res);
        
        Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
        
        
        }
	    catch(ClientProtocolException e)
	    {
	    }
	    catch(IOException e)
	    {
	
	    }
	    finally {
	    	dd.close();
	    }
    
	}
	
	public boolean isOnline()
	   {
	            try {
	        ConnectivityManager cm =
	            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo netInfo = cm.getActiveNetworkInfo();
	        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	            return true;
	        }
	        }catch(Exception e)
	        {
	         }
	        return false;
	    }

    
}