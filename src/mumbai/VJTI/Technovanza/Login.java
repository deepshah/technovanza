package mumbai.VJTI.Technovanza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {
	DBAdapter dd = new DBAdapter(this);
    TextView err;


	@Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.login); 
       

       err=(TextView)findViewById(R.id.textView2);
        
        
        if(!isOnline())
        	err.setText("You need to be connected to the internet to login.");
		Button login=(Button) findViewById(R.id.button1);
       	login.setOnClickListener(new View.OnClickListener() {
   			
   			public void onClick(View arg0) {
   				if(isOnline())
   					send();
   			}
   		});
       	
		Button register=(Button) findViewById(R.id.button2);
       	register.setOnClickListener(new View.OnClickListener() {
   			
   			public void onClick(View arg0) {
   				Intent reg=new Intent(getApplicationContext(),Register.class);
   				startActivity(reg);
   			}
   		});
    }

	
	public void send()
	{
		String email = ((EditText) findViewById(R.id.editText1)).getText().toString();
		String password = ((EditText) findViewById(R.id.editText2)).getText().toString();

        String url="http://technovanza.org/checklogin_a.php";
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
        nameValuePairs.add(new BasicNameValuePair("email", email));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("submit", "Login"));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  
        response=httpclient.execute(httppost, res);
        if(response.startsWith("You are now logged in!"))
        {
        	dd.loginUser(email);
        	err.setText(response);
        	System.out.println(response);
        	//finish();        	
        }
        
        else
        {
        	err.setText(response);
        	System.out.println(response);

        }
        
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
