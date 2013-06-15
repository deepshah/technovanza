package mumbai.VJTI.Technovanza;


import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Updates extends ListActivity{
	 DBAdapter dd = new DBAdapter(this);
	 public String title[][],titlereverse[],link1[],link;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.updates);
	        try
	        {
	        	 display();    	  
		        }
	        catch(Exception e)
		        {
		    		 Toast.makeText(getBaseContext(),"Content not avaiable", Toast.LENGTH_SHORT).show();
		        }
	        	}
	  	    public void display()
	  	    {
	    	try
	    	{
	    	dd.open();
	    	title = dd.extractupdates();
	    	
	    	{
	    		 setListAdapter(new ArrayAdapter<String>(this,R.layout.simplerow, title[0])); 
	    	}
	    	}
	    	catch(Exception e)
	    	{
	   	    	Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
	    	}
	    }
	  	  public void mainpage(View view)
	      {
	      	Intent intent = new Intent(this, MainActivity.class);
	      	startActivity(intent);
	      }
	  	@Override
	  	protected void onListItemClick(ListView l, View v, int position, long id) {
	  		try
	  		{
				//String item = (String) getListAdapter().getItem(position);
				Intent intent;
				
				if(Integer.parseInt(title[3][position])== 4)
					{
					intent=new Intent();
					intent.setClass(getBaseContext(), EventName.class);
					
					intent.putExtra("item",title[2][position]);
					intent.putExtra("id", title[1][position]);
					startActivity(intent);
					
					}
				else if(Integer.parseInt(title[3][position])== 3)
				{
					intent=new Intent();
					intent.setClass(getBaseContext(), Class.forName("mumbai.VJTI.Technovanza."+title[2][position]));
					startActivity(intent);
					
				}
				else if(Integer.parseInt(title[3][position])== 2)
				{
					 
					 intent = new Intent(Intent.ACTION_VIEW, Uri.parse(title[1][position]));
					 startActivity(intent);
					
				}
				
			 	}
			catch(ClassNotFoundException e){
				 Log.v(position+" "+title[1][position],e.getMessage());
			 }
	  		catch(Exception e)
	  		{
	  			Log.v(position+" "+title[3][position],e.getMessage());	
	  		}
	  	}
	  	  
	  	  
}
