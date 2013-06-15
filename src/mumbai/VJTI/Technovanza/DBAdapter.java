package mumbai.VJTI.Technovanza;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DBAdapter extends ListActivity
{	
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "update";
    private static final int DATABASE_VERSION = 12;

    private static final String DATABASE_CREATE1 ="create table if not exists updates2 (id INTEGER(3), upd VARCHAR(225),tname VARCHAR(225), eventName VARCHAR(225), cat INTEGER(3));";
    private static final String DATABASE_CREATE3 ="create table if not exists status (id INTEGER(3), notif_count INTEGER(3), email VARCHAR(30), ifLoggedIn INTEGER(1));";
    private static final String DATABASE_CREATE4 ="create table if not exists dyk (fact VARCHAR(255));";
    

    private final Context context;  
    
    private static String CMD;
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        
    }
    
	private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	db.execSQL(DATABASE_CREATE1);
            db.execSQL(DATABASE_CREATE3);
            db.execSQL(DATABASE_CREATE4);

            db.execSQL("INSERT INTO updates2 VALUES(0,'Welcome to Techno','http://www.technovanza.org','hey',2)");            
            db.execSQL("INSERT INTO status VALUES(0,0,'',0)");
            db.execSQL("INSERT INTO dyk VALUES('More than a Billion Transistors are manufactured every second')");
        }
             
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            
            onCreate(db);
        }
    }  
	

    public void createTable(String name){
    	
		String check="SELECT name FROM sqlite_master WHERE type='table' AND name='"+name+"'";
		
		
    	Cursor cur=db.rawQuery(check, null);
    	
    	
    	try
    	{
    		this.open();
    		
    		if(cur.getCount()==0)
    			{
    			CMD="create table "+name+" (name VARCHAR(100),desc VARCHAR(225));";
    				db.execSQL(CMD);
    				int id;
    				
    				if(name.compareToIgnoreCase("iCode")==0)
    					id=0;
    				else if(name.compareToIgnoreCase("Eureka")==0)
    					id=1;
    				else if(name.compareToIgnoreCase("Manageria")==0)
    					id=2;
    				else if(name.compareToIgnoreCase("Autobots")==0)
    					id=3;
    				else if(name.compareToIgnoreCase("iMech")==0)
    					id=4;
    				else if(name.compareToIgnoreCase("iBuild")==0)
    					id=5;
    				else if(name.compareToIgnoreCase("impulse")==0)
    					 id=6;
    				else id=7;
    			String ids[][]={ 
    					{"Crazy Coding","Mission SQL","Cryptext","TechAthlon","Ultimate Coder","CWay","Code Swap","Java Guru","Code in X","Algo Crack","Web Maestro","Code Magnet"}, 
    					{"RCMO", "Mission Mumbai","XCon", "How Stuff Works","Technical Paper Presentation","Poster Presentation","I3"},
    					{"Unicus","HIRE", "Biz Quiz", "Rs 50 venture","Adomania","Web-Preneur","SCAM","Wall Street"}, 
    					{"Robowars", "Monster Arena","Cable Crane","Flying Dutchman","Split Second","Wall E", "Master of Puppets"},
    					{"Hydro Jet","Armageddon","Catapult", "Kinetize"},
    					{"Bridge the Gap","Elevate It ", "B2B-Bid to Build"},
    					{"Criss Cross","Tricky Tronics","Google Junkie","Pantograph","Click-N-Capture","Minute to win it","Tech Quiz","Plumb It"},
    					{"Viwanda"}};
    					
    				
    				desc d=new desc();
    				
    				String vals[][]={{
    					d.crazy,d.sql,d.crypt,d.techathlon,d.ultimate,d.Cway,d.codeSwap,d.Java,d.codex,d.algo,d.WebM,d.magnet
    				},{
    					d.RCMO,d.MissionMumbai,d.Xcon,d.HowStufffWorks,d.TPP,d.PosterPresentation,d.I3
    				},{
    					d.Unicus, d.Hire, d.BizQuiz, d.Rs50V,d.Adomania,d.web,d.scam,d.WallStreet
    				},{
    					d.Robowars,d.MonsterArena,d.CableCrane,d.dutchman,d.SplitSecond,d.WallE,d.MasterOfPuppets
    				},{
    					d.Hydrojet,d.Armageddon,d.Catapult,d.Kinetize
    				},{
    				
    					d.BridgeTheGap,d.Elevate,d.B2B
    				},
    				{"here "," this and that ","  this and that "," this and that  ","  this and that "," this and that  ","  this and that "," this and that  "},
    				{d.viwanda}};
    				
    				
    				for(int i=0;i<ids[id].length;i++)
    					{
    					
    					this.insertQuote(ids[id][i],vals[id][i],name);
    					
    				}
    				
    			}
    			cur.close();
    	}
    	catch(Exception e){
    		cur.close();
    		Log.v("in create table",e.getMessage());
    	}
    	
    	finally {
    		close();
    	}
    }
    
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() 
    {
        DBHelper.close();
    }      
    

    
    
    //session functions

    public void loginUser(String email)
    {
    	this.open();
    	try
    	{
    	db.execSQL("UPDATE status SET email='"+email+"', ifLoggedIn=1");
    	}
    	catch(Exception e){
    	}
    	this.close();
    }
    
    public void logoutUser()
    {
    	this.open();
    	try
    	{
    	db.execSQL("UPDATE status SET email='', ifLoggedIn=0");
    	}
    	catch(Exception e){
    	}
    	this.close();
    }
    
   
    
    
    public int isLoggedIn()
    {
    	open();
    	int is = 0;
    	try{
    	Cursor count_cursor = db.rawQuery(
                "SELECT ifLoggedIn FROM status", null);
            if(count_cursor.moveToFirst()) {
                is = count_cursor.getInt(0);
            }
        	count_cursor.close();

           
    	}
    	catch(Exception e){
    	}	
    	close();
    	return is;
    	 
    }
    
    
    
    public String getEmail()
    {
    	open();
    	String name = "";
    	try{
    	Cursor name_cursor = db.rawQuery(
                "SELECT email FROM status", null);
            if(name_cursor.moveToFirst()) {
                name = name_cursor.getString(0);
            }
        	name_cursor.close();

           
    	}
    	catch(Exception e){
    	}	
    	close();
    	return name;    	 
    }
    
    public void insertQuote(String id, String vals,String name)
    {
    	open();
    	
    	String insert="insert into "+name+" values('"+id+"','"+vals+"')";
    	
    	try{
    	 db.execSQL(insert);
    	 }
    	catch(Exception e){
    		Log.v("inserting quote",e.getMessage());
    	}
    	close();
    	
    }
    
    public int getLastUpdateId()
    {
    	open();
    	int id = 0;
    	try{
    	Cursor id_cursor = db.rawQuery(
                "SELECT id FROM status", null);
            if(id_cursor.moveToFirst()) {
                id = id_cursor.getInt(0);
            }
        	id_cursor.close();

           
    	}
    	catch(Exception e){
    	}	
    	close();
    	return id;
    	 
    }
    
    public int notif_count()
    {
    	open();
    	int count = 0;
    	try{
    	Cursor count_cursor = db.rawQuery(
                "SELECT notif_count FROM status", null);
            if(count_cursor.moveToFirst()) {
                count = count_cursor.getInt(0);
            }
        	count_cursor.close();

    	}
    	catch(Exception e){
    	}	
    	close();
    	return count;
    	 
    }
    

    
    //Update last id received
    public void setLastUpdateId(int id)
    {

    	open();
        try
        {
        	db.execSQL("UPDATE status SET id="+id);
        	close();
        }
        catch(Exception e){
    	}
    }
    
    
        
    //Update notif count
    public void setNotifCount(int count)
    {

    	open();
        try
        {
        	db.execSQL("UPDATE status SET notif_count="+count);
        	close();
        }
        catch(Exception e){
    	}
    }
    
    //insert new update
    public void insertUpdates(int id, String update,String tname, String eventName, int cat) 
    {

    	open();
        try
        {
        	int size=0;
        	db.execSQL("INSERT INTO updates2 VALUES("+id+",'"+update+"','"+tname+"','"+eventName+"',"+cat+")");
        	Cursor size_cursor = db.rawQuery(
                    "SELECT COUNT(upd) FROM updates2", null);
                if(size_cursor.moveToFirst()) {
                    size = size_cursor.getInt(0);
                }
                size = size_cursor.getInt(0);
            if(size>10)
        	db.execSQL("DELETE FROM updates2 WHERE id=(SELECT MIN(id) FROM updates2)");
        	size_cursor.close();
        	close();
        }
        catch(Exception e){
    	}
    }
    
       
    
    //Add new event
    public void newEvent(String event[])
    {
    	//event: sector, name, desc
    	String sector = event[0];
    	String name = event[1];
    	String desc = event[2];
    	
    	open();
    	try
        {
        	db.execSQL("Insert INTO "+sector+" VALUES("+name+","+desc+")");

        }
        catch(Exception e){
    	}
    	close();
    	
    }
    
    //modify an old event
    public void modifyEvent(String event[])
    {
    	//event: sector, name, desc
    	String sector = event[0];
    	String name = event[1];
    	String desc = event[2];
    	
    	open();
    	try
        {
        	db.execSQL("UPDATE "+sector+" SET desc="+desc+" WHERE name="+name);
        }
        catch(Exception e){
    	}
    	close();
    	
    }
    
    //Add Did you know fact
    public void insertFact(String fact)
    {
    	open();
    	try
        {
        	db.execSQL("Update dyk SET fact='"+fact+"'");
        }
        catch(Exception e){
    	}
    	close();
    }
    
    //Extract Did you know fact
    
    public String getFact()
    {
    	open();
    	String fact = "";
    	try{
    	Cursor fact_cursor = db.rawQuery(
                "SELECT fact FROM dyk", null);
            if(fact_cursor.moveToFirst()) {
                fact = fact_cursor.getString(0);
            }
        	fact_cursor.close();

    	}
    	catch(Exception e){
    	}	
    	close();
    	return fact;
    	 
    }
  
    
    
    public String[][] extractupdates() 
    {
    	open();
    	int size = 0;
    	db.execSQL("UPDATE status SET notif_count=0");
    	Cursor size_cursor = db.rawQuery(
                "SELECT COUNT(upd) FROM updates2", null);
            if(size_cursor.moveToFirst()) {
                size = size_cursor.getInt(0);
            }
            size = size_cursor.getInt(0);
            	
    	String title[][] = new String[4][size];
    	 
    	int i = 0;
       Cursor cursor = db.rawQuery(
                    "SELECT upd FROM updates2 ORDER BY id DESC", null);
      
        if(cursor.moveToFirst()) {
            do
            {
            	title[0][i] = cursor.getString(0);
            	i++;
            }
            while(cursor.moveToNext());
        }
        
        i=0;
        cursor = db.rawQuery(
                "SELECT tName FROM updates2 ORDER BY id DESC", null);
        if(cursor.moveToFirst()) {
            do
            {
            	title[1][i] = cursor.getString(0);
            	i++;
            }
            while(cursor.moveToNext());
        }
        i=0;
        cursor = db.rawQuery(
                "SELECT eventName FROM updates2 ORDER BY id DESC", null);
        if(cursor.moveToFirst()) {
            do
            {
            	title[2][i] = cursor.getString(0);
            	i++;
            }
            while(cursor.moveToNext());
        }
        i=0;
        cursor = db.rawQuery(
                "SELECT cat FROM updates2 ORDER BY id DESC", null);
        if(cursor.moveToFirst()) {
            do
            {
            	title[3][i] = cursor.getString(0);
            	i++;
            }
            while(cursor.moveToNext());
        }
        
    	cursor.close();
    	size_cursor.close();
    	close();
        
        return title;
    }
    
    
    //get event description
    public String getDesc(String name,String tName){
		  String all="";
		  open();
		   try{
			   Cursor cursor = db.rawQuery(
              "SELECT desc FROM "+tName+" WHERE name='"+name+"'", null);
	   		if(cursor.moveToFirst()){
	   			all=all+cursor.getString(0);
	   		}
	    	cursor.close();
	    	
	    	close();
	   		return all;
	   }
	   
	   catch(Exception e){
		   return e.getMessage();  
	   }
	}
    

	   
    public String getAll(String name){
		   String all="";
		   open();
		   try{
			   Cursor cursor2 = db.rawQuery("SELECT name FROM "+name, null);
	   		if(!cursor2.moveToFirst()){
	   			return all;
	   		}
	   		do{
	   			all=all+cursor2.getString(0)+",";
	   			}	
	   		while(cursor2.moveToNext());
	    	
	   		cursor2.close();
	   		close();
	   }
	   
	   catch(Exception e){
		   
	   }
		   close();
		   return all;
	   } 
    
    public void fireQuery(String query)
    {
    	open();
    	
    	    	
    	try{
    	 db.execSQL(query);
    	 }
    	catch(Exception e){
    		
    	}
    	close();
    	
    }
	   
   	  
}