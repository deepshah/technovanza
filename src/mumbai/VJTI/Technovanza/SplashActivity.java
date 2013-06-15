package mumbai.VJTI.Technovanza;

import java.util.Timer;

import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {
	private long splashdelay=2000;//4 seconds
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		TimerTask task = new TimerTask()
		{
			@Override
			public void run()
			{
				finish();

	    		
				Intent mainIntent =new Intent().setClass(SplashActivity.this, MainActivity.class);
				startActivity(mainIntent);
			}
		};
		Timer timer =new Timer();
		timer.schedule(task, splashdelay);
	}

}
