package mumbai.VJTI.Technovanza;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Did_you_know extends Activity {
	 DBAdapter dd = new DBAdapter(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.didyouknow);
        Typeface font1 = Typeface.createFromAsset(getAssets(),"fonts/FEASFBI_.TTF");
        dd.open();
        TextView fact = (TextView) findViewById(R.id.fact);
        fact.setTypeface(font1);
        fact.setTextSize(25.f);
        fact.setText(dd.getFact());
        
        dd.close();
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.did_you_know, menu);
        return true;
    }
*/
    
    public void mainpage(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
    
    
}
