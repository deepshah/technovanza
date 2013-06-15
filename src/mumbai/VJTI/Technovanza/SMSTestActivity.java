package mumbai.VJTI.Technovanza;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
public class SMSTestActivity extends Activity 
{
	public void home(View view)
    {
    	Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
    }
    Button btnSendSMS,contacts;
    String phoneNumber;
    EditText txtPhoneNo;
    final int PICK_CONTACT=1;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share);  
        
        final Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
 
        contacts=(Button) findViewById(R.id.SelectContact);
        btnSendSMS = (Button) findViewById(R.id.btnSendSMS);
        txtPhoneNo = (EditText) findViewById(R.id.txtPhoneNo);
       
        
        contacts.setOnClickListener(new View.OnClickListener()
        {
        	public void onClick(View v)
        	{
        		startActivityForResult(intent , PICK_CONTACT);
        	}
        });
 
        btnSendSMS.setOnClickListener(new View.OnClickListener() 
        {
            public void onClick(View v) 
            {                
                //String phoneNo = txtPhoneNo.getText().toString();
            	String fullm=txtPhoneNo.getText().toString();
            	String delims="[,]";
            	String [] phoneNo=fullm.split(delims);
                String message="https://play.google.com/store/apps/details?id=mumbai.VJTI.Technovanza";
                for(int i=0;i<phoneNo.length;i++)
                	{
                		if (phoneNo[i].length()>0 && message.length()>0)                
                		{ message=message+"\n Download the Technovanza app ";
                		sendSMS(phoneNo[i], message);
                		}                
                		else
                			Toast.makeText(getBaseContext(), 
                					"Please enter both phone number and message.", 
                					Toast.LENGTH_SHORT).show();
                	}
            }
        });        
    }
    
    /**									Send SMS 								*/
    
    private void sendSMS(String phoneNumber, String message)
    {        
    	        
    	        PendingIntent pi = PendingIntent.getActivity(this, 0,
    	            new Intent(this, SMSTestActivity.class), 0);                
    	        SmsManager sms = SmsManager.getDefault();
    	        sms.sendTextMessage(phoneNumber, null, message, pi, null);  
    	        this.finish();
    }
    
    /**					Fetch the contact from the Contact list 					*/
    
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
      super.onActivityResult(reqCode, resultCode, data);

      switch (reqCode) {
        case (PICK_CONTACT) :
          if (resultCode == Activity.RESULT_OK) {
        	  Uri contactData = data.getData();
              Cursor c = managedQuery(contactData, null, null, null, null);
              if (c.moveToFirst()) {
                 
                do
                 {	
                	 String ContactId=c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                	 String hasPhone=c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                	 if(hasPhone.compareTo("1")==0)
                	 {
                		 Cursor phone=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                				 ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+ContactId, null , null);
                		 while(phone.moveToNext())
                		 {	/*if(phone.getInt(phone.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.TYPE))==
                			 ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)*/
                			 {phoneNumber=phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                			 //Toast.makeText(this, phoneNumber, Toast.LENGTH_LONG).show();
                			 txtPhoneNo.append(phoneNumber);
                			 txtPhoneNo.append(",");
                			 } 
                		 }
                		 phone.close();
                	 }
                 }
                while(c.moveToNext());
          }
          break;
      }
    }
      
}
    
}