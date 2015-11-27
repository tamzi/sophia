package com.collabera.labs.sai.db;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class CRUDonDB extends ListActivity {
	
	private final String SAMPLE_DB_NAME = "myFriendsDb";
	private final String SAMPLE_TABLE_NAME = "friends";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        ArrayList<String> results = new ArrayList<String>();
        SQLiteDatabase sampleDB = null;
        
        try {
        	sampleDB =  this.openOrCreateDatabase(SAMPLE_DB_NAME, MODE_PRIVATE, null);
        	
        	sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " +
        			SAMPLE_TABLE_NAME +
        			" (LastName VARCHAR, FirstName VARCHAR," +
        			" Country VARCHAR, Age INT(3));");
        	
        	sampleDB.execSQL("INSERT INTO " +
        			SAMPLE_TABLE_NAME +
        			" Values ('Makam','Sai Geetha','India',25);");
        	sampleDB.execSQL("INSERT INTO " +
        			SAMPLE_TABLE_NAME +
        			" Values ('Chittur','Raman','India',25);");
        	sampleDB.execSQL("INSERT INTO " +
        			SAMPLE_TABLE_NAME +
        			" Values ('Solutions','Collabera','India',20);");
        	
        	Cursor c = sampleDB.rawQuery("SELECT FirstName, Age FROM " +
        			SAMPLE_TABLE_NAME +
        			" where Age > 10 LIMIT 5", null);
        	
        	if (c != null ) {
        		if  (c.moveToFirst()) {
        			do {
        				String firstName = c.getString(c.getColumnIndex("FirstName"));
        				int age = c.getInt(c.getColumnIndex("Age"));
        				results.add("" + firstName + ",Age: " + age);
        			}while (c.moveToNext());
        		} 
        	}
        	
        	this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));
        	
        } catch (SQLiteException se ) {
        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
        	if (sampleDB != null) 
        		sampleDB.execSQL("DELETE FROM " + SAMPLE_TABLE_NAME);
        		sampleDB.close();
        }
    }
}