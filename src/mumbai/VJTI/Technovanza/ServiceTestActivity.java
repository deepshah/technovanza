package mumbai.VJTI.Technovanza;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class ServiceTestActivity extends BroadcastReceiver{
	
@Override
public void onReceive(Context context, Intent intent) {
	  
		   Intent Nintent = new Intent(context, GetUpdates.class); 
		  
		   context.startService(Nintent);  
		
}
}