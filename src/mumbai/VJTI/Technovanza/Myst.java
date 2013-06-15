package mumbai.VJTI.Technovanza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Myst extends Activity {
	
	TextView tv;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myst_main);
		tv=(TextView)findViewById(R.id.tv);
		String text="The new MYST is Mysterious...\nYou decide your faith this time. Be cautious deep trenches await your path to victory. Just one hint to get you across, the bridge is narrower. Touch the rope and the deeper the next trench is. Be wiser and you shall sail through.MYST the only place where your processing speed matters more than the Memory.Overclock your brains and... GET SET GO.\nHere to test you again!";
		tv.setText(text);
		

		/**  Questions! */
		
		Button quest=(Button) findViewById(R.id.question);
		quest.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ddk=new Intent(getApplicationContext(),Quest.class);
				startActivity(ddk);
			}
		});
		

		/**  Rules */
		
		Button rule=(Button) findViewById(R.id.rules);
		rule.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ddk=new Intent(getApplicationContext(),Rules.class);
				startActivity(ddk);
			}
		});
		

		/**  Leaderboard */
		
		Button board=(Button) findViewById(R.id.board);
		board.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ddk=new Intent(getApplicationContext(),Leaderboard.class);
				startActivity(ddk);
			}
		});
	}



public void mainpage(View view)
{
	Intent intent = new Intent(this, MainActivity.class);
	startActivity(intent);
}

}