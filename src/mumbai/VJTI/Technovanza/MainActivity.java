package mumbai.VJTI.Technovanza;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {
    DBAdapter dd = new DBAdapter(this);


    @Override
    public void onCreate(Bundle savedInstanceState) {
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        Intent intent=new Intent(this,GetUpdates.class);
		startService(intent);
       
	/** Registration ! */
    	final Button login=(Button) findViewById(R.id.button2);
    	if(dd.isLoggedIn()==0)
        {
       	
        		login.setText("Login");
        }
    	else
        	login.setText("Logout");
        
        
    	login.setOnClickListener(new View.OnClickListener() {
    		
    		public void onClick(View arg0) {
    			// TODO Auto-generated method stub
    			Intent ddk;
    			if(dd.isLoggedIn()==0)
    			{
    				ddk=new Intent(getApplicationContext(),Login.class);
    				startActivity(ddk);

    			}
    			else
    			{
    				dd.logoutUser();
    			}
    			  			
    			
    		}
    	});

	/**  Did You Know ! */
	
	Button did_u_know=(Button) findViewById(R.id.button3);
	did_u_know.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent ddk=new Intent(getApplicationContext(),Did_you_know.class);
			startActivity(ddk);
		}
	});
	
	/** Events  */
	Button events = (Button) findViewById(R.id.button1);
	events.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent events=new Intent(getApplicationContext(),EventsCategory.class);
			startActivity(events);
		}
	});
	
	/**  Gallery -- working */
	
	Button gallery = (Button) findViewById(R.id.button5);
	gallery.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intgallery=new Intent(getApplicationContext(),PhotoGallery.class);
			startActivity(intgallery);
		}
	});
    
	/** Share the app */
	Button exhi = (Button) findViewById(R.id.button6);
	exhi.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent exhibit=new Intent(getApplicationContext(),SMSTestActivity.class);
			startActivity(exhibit);
		}
	});
	
	/** updates  */
	Button updat = (Button) findViewById(R.id.button4);
	updat.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent up=new Intent(getApplicationContext(),Updates.class);
			startActivity(up);
			
		}
	});

    	
    /** tech updates  */
	Button techupdat = (Button) findViewById(R.id.button7);
	techupdat.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent up=new Intent(getApplicationContext(),RSSReader.class);
			startActivity(up);
			
		}
	});
	
	Button myst = (Button) findViewById(R.id.button8);
	myst.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent up=new Intent(getApplicationContext(),Myst.class);
			startActivity(up);
			
		}
	});



    }
}

