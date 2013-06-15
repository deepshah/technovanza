package mumbai.VJTI.Technovanza;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RSSReader extends ListActivity {
	
	List headlines;
	List links;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
    	if(isOnline())
    	{
    	setContentView(R.layout.updates);
     // Initializing instance variables
       headlines = new ArrayList();
        links = new ArrayList();

        try {
        	URL url = new URL("https://news.google.com/news/feeds?pz=1&cf=all&ned=in&hl=en&topic=tc&output=rss");

        	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        	factory.setNamespaceAware(false);
        	XmlPullParser xpp = factory.newPullParser();

                // We will get the XML from an input stream
        	xpp.setInput(getInputStream(url), "UTF_8");

               
        	boolean insideItem = false;

                // Returns the type of current event: START_TAG, END_TAG, etc..
        	int eventType = xpp.getEventType();
        	while (eventType != XmlPullParser.END_DOCUMENT) {
        		if (eventType == XmlPullParser.START_TAG) {

        			if (xpp.getName().equalsIgnoreCase("item")) {
        				insideItem = true;
        			} else if (xpp.getName().equalsIgnoreCase("title")) {
        				if (insideItem)
        					headlines.add(xpp.nextText()); //extract the headline
        			} else if (xpp.getName().equalsIgnoreCase("link")) {
        				if (insideItem)
        					links.add(xpp.nextText()); //extract the link of article
        			}
        		}else if(eventType==XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")){
        			insideItem=false;
        		}

        		eventType = xpp.next(); //move to next element

                // Binding data
                ArrayAdapter adapter = new ArrayAdapter(this,
                	R.layout.simplerow, headlines);

                setListAdapter(adapter);
        	}

        } catch (MalformedURLException e) {
        	e.printStackTrace();
        } catch (XmlPullParserException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        catch(Exception e){
        	Log.v("Tech updates",e.getMessage());
        }
    	}
    	else {
    		Toast.makeText(getApplicationContext(), "You cannot receive feeds. You are not connected to the internet!", Toast.LENGTH_LONG).show();
    		finish();
    	}

    }
    

public InputStream getInputStream(URL url) {
   try {
       return url.openConnection().getInputStream();
   } catch (IOException e) {
       return null;
     }
}


@Override
protected void onListItemClick(ListView l, View v, int position, long id) {
   Uri uri = Uri.parse((String) links.get(position));
   Intent intent = new Intent(Intent.ACTION_VIEW, uri);
   startActivity(intent);
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

