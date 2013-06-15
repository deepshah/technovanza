package mumbai.VJTI.Technovanza;

/** 
 * The activity displays the list of events in the category
 */


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Category extends ListActivity{
	private String tName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        try{
		super.onCreate(savedInstanceState);
        
        setContentView(R.layout.eventlist);
        
        tName=getIntent().getExtras().getString("item");
        
        DBAdapter db=new DBAdapter(this);
    	db.open();
		db.createTable(tName);
			
    	String Clist=db.getAll(tName);
    	
    	String list[]=Clist.split(",");
    	 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simplerow, list);

 	        setListAdapter(adapter);
 	      
 	        db.close();}
        catch(Exception e){
        }
        
    	}
	
	@Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		 	try{
			String item = (String) getListAdapter().getItem(position);
			 if(tName.compareToIgnoreCase("impulse")==0)
	 	       {
	 	    	   v.setEnabled(false);
	 	    	   Toast.makeText(getApplicationContext(),"Register and play on the spot during Technovanza	!", Toast.LENGTH_LONG).show();
	 	       }
			 else {
			Intent intent=new Intent();
			intent.setClass(getBaseContext(), EventName.class);
			intent.putExtra("item",item);
			intent.putExtra("id", tName);
			startActivity(intent);
			
			 }
		 	}
		 catch(Exception e){
		 }
		 }
	
	  public void mainpage(View view)
	    {
	    	Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
	    }
	    
	
}