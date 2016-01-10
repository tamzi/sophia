package com.simple;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import android.content.Context;
import android.content.res.AssetManager;

public class TextFilesReader {

	Context g;
	public TextFilesReader(Context c){
		this.g = c;
		
	}

	
	   public String readTxt(String a) throws IOException{

	    //InputStream inputStream = g.getResources().openRawResource(R.raw.hym1);
	    InputStream inputStream = g.getResources().getAssets().open("raw/"+a);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		     
		     int i;
		  try {
		   i = inputStream.read();
		   while (i != -1)
		      {
		       byteArrayOutputStream.write(i);
		       i = inputStream.read();
		      }
		      inputStream.close();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		     return byteArrayOutputStream.toString();
		    }	
	
	
}
