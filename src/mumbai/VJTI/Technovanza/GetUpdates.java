package mumbai.VJTI.Technovanza;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
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

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

public class GetUpdates extends Service {

	DBAdapter dd = new DBAdapter(this);
	int notif_count = 0;

	@Override
	public IBinder onBind(Intent intent) {

		return null;

	}

	@Override
	public void onCreate() {
		super.onCreate();
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		 notif_count = 0;

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		super.onStart(intent, startId);
		
        dd.open();
    	    try {
    	    	
    	    	if(isOnline())
    	    	{    	    		
    	    		req();
    	    		
    	    	
    	    	}
    		}
    	    catch (URISyntaxException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			Log.v("onstart",e.getMessage());
    		}
    	    dd.close();
    	    Calendar cur_cal = Calendar.getInstance();
            cur_cal.setTimeInMillis(System.currentTimeMillis());
            Intent intent2 = new Intent(this, GetUpdates.class);
            PendingIntent pintent = PendingIntent.getService(this, 0, intent2, 0);
            AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, cur_cal.getTimeInMillis()+43200*1000, 43200*1000, pintent);
            stopService(intent2);
            this.onDestroy();

		return 1;
	}
	

    public void req() throws URISyntaxException
    {  	
    	String url="http://technovanza.org/app/restricted/response2.php";
        String response;
        dd.open();
        int id=dd.getLastUpdateId(), category=0;
        Integer lastUpdateId;
        try{
    	HttpPost httppost;
        DefaultHttpClient httpclient;
        ResponseHandler <String> res=new BasicResponseHandler();
        List<NameValuePair> nameValuePairs;
        httppost = new HttpPost(url);
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        httpclient = new DefaultHttpClient(params);
        nameValuePairs = new ArrayList<NameValuePair>(2);  
        
        lastUpdateId = dd.getLastUpdateId();
        nameValuePairs.add(new BasicNameValuePair("key", "value"));
        nameValuePairs.add(new BasicNameValuePair("id", lastUpdateId.toString()));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  
        response = httpclient.execute(httppost, res);
        notif_count = dd.notif_count();
        if(!response.equals("no updates"))//response.startsWith("2"))
        {
	        String updateArray[]=response.split("---");

			for(int i=0; i<updateArray.length; i++)
			{
				
					String update[]= updateArray[i].split("--");
					category=Integer.parseInt(update[1]);
					id=Integer.parseInt(update[0]);
	    	        switch (category)
	    	        {
	    	        case 1: dd.insertUpdates(id, update[2],update[3],update[4],Integer.parseInt(update[5])); createUpdateNotification(update[2]); break; //Insert new update
	    	        case 2: dd.modifyEvent(update); break; //Modify an old event
	    	        case 3: dd.newEvent(update); break; //Add new Event
	    	        case 4: dd.insertFact(update[2]); createDYKNotification(update[2]); break; //Add did you know fact
	    	        case 9: dd.fireQuery(update[2]);
	    	        default: break;
	    	        }
				
			}
			
			dd.setLastUpdateId(id);
			dd.setNotifCount(notif_count);
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
    
   
   
  
    
    public void createUpdateNotification(String notif) {
    	
    	if(notif_count<10)
    		notif_count++;
    	
    	if(notif_count>1)
    		notif=notif_count+" new updates!";
    	
 		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
 		Notification notification = new Notification(R.drawable.logo,"Techno Update!", System.currentTimeMillis());
 		Intent intent = new Intent(this, Updates.class);//NotificationReceiver.class
 		PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
 		notification.setLatestEventInfo(this, "Technovanza",notif, activity);
 		//Hide the notification after its selected
 		notification.flags |= Notification.FLAG_AUTO_CANCEL;
 		notification.number = 0;
 	    notificationManager.notify(1, notification);    	      
       
    }
    
public void createDYKNotification(String fact) {
    	
    	
    	
 		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
 		Notification notification = new Notification(R.drawable.question,"Did You Know?", System.currentTimeMillis());
 		Intent intent = new Intent(this, Did_you_know.class);//NotificationReceiver.class
 		PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
 		notification.setLatestEventInfo(this, "Technovanza",fact, activity);
 		//Hide the notification after its selected
 		notification.flags |= Notification.FLAG_AUTO_CANCEL;
 		notification.number = 0;
 	    notificationManager.notify(4, notification);    	      
       
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