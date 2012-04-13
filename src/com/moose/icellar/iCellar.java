package com.moose.icellar;

import java.io.*;
import android.app.Dialog;
import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class iCellar extends ListActivity {
	
	String[] mycellar;
	String[] myclubs;
	private Dialog splashDialog;
	
    
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	MyStateSaver data = (MyStateSaver) getLastNonConfigurationInstance(); //Saves current state in a MyStateSaver
    	if(data != null) { //If it's not null then display the splash screen and build program
    		if(data.showSplashScreen) {
    			showSplashScreen();
    		}
	    	setContentView(R.layout.main);
	    	
	    	
	    	TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
	    	tabHost.setup();
	    	tabHost.setBackgroundColor(Color.parseColor("#f8e4aa"));
	    	
	    	
	   		TabSpec spec1=tabHost.newTabSpec("Tab 1");
	   		spec1.setIndicator("My Cellar", getResources().getDrawable(R.drawable.onecellar_icon));
	   		spec1.setContent(R.id.tab1);
	
	   		TabSpec spec2=tabHost.newTabSpec("Tab 2");
	    	spec2.setIndicator("My Clubs" , getResources().getDrawable(R.drawable.clubcellar_icon));
	    	spec2.setContent(R.id.tab2);
	
	    	tabHost.addTab(spec1);
	    	tabHost.addTab(spec2);
	    	
	    	tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#46618e"));
	    	tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#46618e"));
	    	
	    	

    	}
    	else {
    		showSplashScreen();
    		setContentView(R.layout.main);
        	
        	
        	TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
        	tabHost.setup();
        	tabHost.setBackgroundColor(Color.parseColor("#f8e4aa"));
        	
        	
       		TabSpec spec1=tabHost.newTabSpec("Tab 1");
       		spec1.setIndicator("My Cellar", getResources().getDrawable(R.drawable.onecellar_icon));
       		spec1.setContent(R.id.tab1);

       		TabSpec spec2=tabHost.newTabSpec("Tab 2");
        	spec2.setIndicator("My Clubs" , getResources().getDrawable(R.drawable.clubcellar_icon));
        	spec2.setContent(R.id.tab2);

        	tabHost.addTab(spec1);
        	tabHost.addTab(spec2);
        	
        	tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#46618e"));
        	tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#46618e"));
        	
        	String[] tickerSymbols = new String[] { "MSFT", "ORCL", "AMZN", "ERTS", "A", "A", "A", "A", "A", "A", "A", "A", "A" };
        	setListAdapter(new ArrayAdapter<String>(this,
        	                        R.layout.list_item,
        	                        tickerSymbols));
        	
        	

    	}
    }
    
    public String readMyCellar() {
    	InputStream is = getResources().openRawResource(R.raw.mycellar);
    	InputStreamReader ir = new InputStreamReader(is);
    	BufferedReader br = new BufferedReader(ir);
    	
    	String line;
    	StringBuilder wines = new StringBuilder();
    	
    	int arsize = 0;
    	
    	try {
    		while(br.readLine() != null) {
    			arsize++;
    		}
    		while((line = br.readLine()) != null) {
    			wines.append(line);
    			wines.append('\n');
    		}
    	}
    	catch(IOException e) {
    		System.out.println("IOException Stack Trace: ");
    		e.printStackTrace();
    	}
    	return wines.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void showSplashScreen() {
    	splashDialog = new Dialog(this, R.style.SplashScreen);
    	splashDialog.setContentView(R.layout.splash);
    	splashDialog.setCancelable(false);
    	splashDialog.show();
    	
    	//This Handler is essentially a contingency plan that simply keeps the splash screen around for 5 seconds
    	final Handler h = new Handler();
    	h.postDelayed(new Runnable() {
    		public void run() {
    			removeSplashScreen();
    		}
    	}, 6000);
    }
    
    public void removeSplashScreen() {
    	if(splashDialog != null) {
    		splashDialog.dismiss();
    		splashDialog = null;
    	}
    }
    
  //A simple inner class containing only a boolean specifying whether or not the splash screen should be shown
    private class MyStateSaver {
    	public boolean showSplashScreen = false;
    	
    }
}