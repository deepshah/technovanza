package mumbai.VJTI.Technovanza;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class EventsCategory extends Activity {
	
	Button icode, imech, ibuild, manageria,autobots, eureka;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_category);
		
	}
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int id=v.getId();
				String item="";
				
				Boolean flag=true;
				try{
				switch(id)
				{
				case R.id.code: item="iCode" ;break;
				case R.id.ibuild: item="iBuild" ;break;
				case R.id.imech: item="iMech" ;break;
				case R.id.manageria: item="Manageria" ;break;
				case R.id.eureka: item="Eureka" ;break;
				case R.id.autobots: item="Autobots" ;break;
				case R.id.conference: item="viwanda";break;
				case R.id.impulse:item="impulse";break;
				default: break;
				
				}
				
				Intent intent=new Intent();
				if(flag){
				intent.setClass(getBaseContext(), Category.class);
				intent.putExtra("item",item);
				}
				else {
					intent.setClass(getBaseContext(), EventName.class);
					intent.putExtra("item",item);
					intent.putExtra("id", "conference");
					DBAdapter db=new DBAdapter(this);
			    	db.open();
					db.createTable("conference");
					db.close();
				}
				startActivity(intent);
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
	
