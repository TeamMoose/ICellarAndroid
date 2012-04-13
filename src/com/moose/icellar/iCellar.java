package com.moose.icellar;

import java.io.*;
import java.util.ArrayList;

import android.R.id;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class iCellar extends Activity implements OnItemClickListener{
	
	private Dialog splashDialog;
	private Cellar myCellar;
	
	private TabHost tabHost;
	private TabSpec spec1;
	private TabSpec spec2;
	
	
	private ArrayList<Bottle> myCell;
	
	private ListView lv1;
	private ListView lv2;
	
	private Button add;
	
	private TextView mkrtit;
	private TextView mkr;
	private TextView typtit;
	private TextView typ;
	private TextView yeatit;
	private TextView yea;
	private TextView regtit;
	private TextView reg;
	private TextView vintit;
	private TextView vin;
	
	private TextView mkrtitadd;
	private TextView mkradd;
	private TextView typtitadd;
	private TextView typadd;
	private TextView yeatitadd;
	private TextView yeaadd;
	private TextView regtitadd;
	private TextView regadd;
	private TextView vintitadd;
	private TextView vinadd;
	
	private TextView comtit;
	private ListView comLS;
	
	private TextView userfield;
	private TextView passfield;
	private Button logconfirm;
	
	private Button ret;
	private Button retadd;
	private Button edit;
	private Button delete;
	private Button save;
	private Button saveadd;
	
	private ButtonClicked click;
	
	private int delInt;
	private int posInt;
    
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	MyStateSaver data = (MyStateSaver) getLastNonConfigurationInstance(); //Saves current state in a MyStateSaver
    	if(data != null) { //If it's not null then display the splash screen and build program
    		if(data.showSplashScreen) {
    			showSplashScreen();
    		}
	    	

    	}
    	else {
    		showSplashScreen();
    		setContentView(R.layout.main);
        	
    		click = new ButtonClicked();
    		
        	//InputStream is = FTPHelper.getAndroidInputStream("Users/gja8Bottles.txt");
        	//myCellar = new Cellar(is);
        	myCellar = new Cellar(this.getResources().openRawResource(R.raw.bottles));
        	populateButtonsandList();
        	
        	
        	tabHost=(TabHost)findViewById(R.id.tabHost);
        	tabHost.setup();
        	tabHost.setBackgroundColor(Color.parseColor("#f8e4aa"));
        	
        	
       		spec1=tabHost.newTabSpec("Tab 1");
       		spec1.setIndicator("My Cellar", getResources().getDrawable(R.drawable.onecellar_icon));
       		spec1.setContent(R.id.tab1);

       		spec2=tabHost.newTabSpec("Tab 2");
        	spec2.setIndicator("My Clubs" , getResources().getDrawable(R.drawable.clubcellar_icon));
        	spec2.setContent(R.id.tab2);

        	tabHost.addTab(spec1);
        	tabHost.addTab(spec2);
        	
        	tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#46618e"));
        	tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#46618e"));	
    	}
    }
    
	public void populateButtonsandList() {
		delInt = 0;
		myCell = myCellar.toAndroidStringArray();
		
		lv1 = (ListView) findViewById(R.id.lv1);
		setListAdapters();
		lv1.setOnItemClickListener(this);
		
		add = (Button) findViewById(R.id.add);
		save = (Button) findViewById(R.id.save);
		add.setOnClickListener(click);
		save.setOnClickListener(click);
		
		mkrtit = (TextView) findViewById(R.id.mkrtit);
		mkr = (TextView) findViewById(R.id.mkr);
		typtit = (TextView) findViewById(R.id.typtit);
		typ = (TextView) findViewById(R.id.typ);
		yeatit = (TextView) findViewById(R.id.yeatit);
		yea = (TextView) findViewById(R.id.yea);
		regtit = (TextView) findViewById(R.id.regtit);
		reg = (TextView) findViewById(R.id.reg);
		vintit = (TextView) findViewById(R.id.vintit);
		vin = (TextView) findViewById(R.id.vin);
		comtit = (TextView) findViewById(R.id.comtit);
		comLS = (ListView) findViewById(R.id.comLS);
		
    	ret = (Button) findViewById(R.id.ret);
    	edit = (Button) findViewById(R.id.edit);
    	delete = (Button) findViewById(R.id.delete);
    	
    	ret.setOnClickListener(click);
    	edit.setOnClickListener(click);
    	delete.setOnClickListener(click);
    	
    	mkrtitadd = (TextView) findViewById(R.id.mkrtitadd);
		mkradd = (TextView) findViewById(R.id.mkradd);
		typtitadd = (TextView) findViewById(R.id.typtitadd);
		typadd = (TextView) findViewById(R.id.typadd);
		yeatitadd = (TextView) findViewById(R.id.yeatitadd);
		yeaadd = (TextView) findViewById(R.id.yeaadd);
		regtitadd = (TextView) findViewById(R.id.regtitadd);
		regadd = (TextView) findViewById(R.id.regadd);
		vintitadd = (TextView) findViewById(R.id.vintitadd);
		vinadd = (TextView) findViewById(R.id.vinadd);
		
		retadd = (Button) findViewById(R.id.retadd);
		saveadd = (Button) findViewById(R.id.saveadd);
		retadd.setOnClickListener(click);
		saveadd.setOnClickListener(click);
		
		userfield = (TextView) findViewById(R.id.userfield);
		passfield = (TextView) findViewById(R.id.passfield);
		logconfirm = (Button) findViewById(R.id.logconfirm);
		logconfirm.setOnClickListener(click);
    	
		lv1.setVisibility(View.GONE);
		add.setVisibility(View.GONE);
    	
    	View tView = findViewById(R.id.bott);
		tView.setVisibility(View.GONE);
		
		tView = findViewById(R.id.addbott);
		tView.setVisibility(View.GONE);
	}
	
	public void setListAdapters() {
		lv1.setAdapter(new WineBottleAdapter(this, R.layout.list_item, myCell));
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		posInt = position;
		
		lv1.setVisibility(View.GONE);
		add.setVisibility(View.GONE);
		
		comLS.setAdapter(new CommentAdapter(this, R.layout.list_item, myCell.get(position).getComments()));
	
		mkr.setText(myCell.get(position).getMaker());
		typ.setText(myCell.get(position).getType());
		yea.setText(myCell.get(position).getYear());
		reg.setText(myCell.get(position).getRegion());
		vin.setText(myCell.get(position).getVineyard());
		
		View tView = findViewById(R.id.bott);
		tView.setVisibility(View.VISIBLE);

		
	}
	
	private class ButtonClicked implements OnClickListener {
		
		public void onClick(View v) {
			if(v.getId() == R.id.logconfirm) {
				lv1.setVisibility(View.VISIBLE);
				add.setVisibility(View.VISIBLE);
				
				logconfirm.setVisibility(View.GONE);
				View temp = findViewById(R.id.userinfo);
				temp.setVisibility(View.GONE);
				temp = findViewById(R.id.passinfo);
				temp.setVisibility(View.GONE);
				
		    	
		    	delete.setText("Delete Bottle");
		    	delInt = 0;
			}
			if(v.getId() == R.id.ret) {
				
				lv1.setVisibility(View.VISIBLE);
				add.setVisibility(View.VISIBLE);
				
				View temp = findViewById(R.id.bott);
				temp.setVisibility(View.GONE);
		    	
		    	delete.setText("Delete Bottle");
		    	delInt = 0;
			}
			if(v.getId() == R.id.retadd) {
				
				lv1.setVisibility(View.VISIBLE);
				add.setVisibility(View.VISIBLE);
				
				View temp = findViewById(R.id.addbott);
				temp.setVisibility(View.GONE);
				temp = findViewById(R.id.saveadd);
				temp.setVisibility(View.VISIBLE);
				temp = findViewById(R.id.save);
				temp.setVisibility(View.GONE);
		    	
		    	delete.setText("Delete Bottle");
		    	delInt = 0;
		    	
				mkradd.setText("");
				typadd.setText("");
				yeaadd.setText("");
				regadd.setText("");
				vinadd.setText("");
				
			}
			if(v.getId() == R.id.delete) {
				if(delInt == 0) {
					edit.setText("Cancel Delete");
					delete.setText("Confirm Delete");
					delInt++;
				}
				else {
					myCell.remove(posInt);
					
					lv1.setVisibility(View.VISIBLE);
					add.setVisibility(View.VISIBLE);
					
					View temp = findViewById(R.id.bott);
					temp.setVisibility(View.GONE);
			    	
			    	setListAdapters();
			    	
			    	delete.setText("Delete Bottle");
			    	edit.setText("Edit Bottle");
			    	delInt = 0;
				}
			}
			if(v.getId() == R.id.add) {
				lv1.setVisibility(View.GONE);
				add.setVisibility(View.GONE);
				
				View temp = findViewById(R.id.addbott);
				temp.setVisibility(View.VISIBLE);
				temp = findViewById(R.id.save);
				temp.setVisibility(View.GONE);
				
			}
			if(v.getId() == R.id.edit) {
				if(delInt == 1) {
					delete.setText("Delete Bottle");
			    	edit.setText("Edit Bottle");
			    	delInt = 0;
				}
				else {
					lv1.setVisibility(View.GONE);
					add.setVisibility(View.GONE);
					
					View temp = findViewById(R.id.bott);
					temp.setVisibility(View.GONE);
					
					temp = findViewById(R.id.addbott);
					temp.setVisibility(View.VISIBLE);
					
					temp = findViewById(R.id.saveadd);
					temp.setVisibility(View.GONE);
					
					temp = findViewById(R.id.save);
					temp.setVisibility(View.VISIBLE);
					
			    	mkradd.setText(myCell.get(posInt).getMaker());
			    	typadd.setText(myCell.get(posInt).getType());
			    	yeaadd.setText(myCell.get(posInt).getYear());
			    	regadd.setText(myCell.get(posInt).getRegion());
			    	vinadd.setText(myCell.get(posInt).getVineyard());
				}
			}
			if(v.getId() == R.id.saveadd) {
				if(mkradd.getText().toString().trim().length() > 0 && 
						typadd.getText().toString().trim().length() > 0 && 
						yeaadd.getText().toString().trim().length() > 0) {
					
					myCellar.addBottle(mkradd.getText().toString(), 
										typadd.getText().toString(), 
										yeaadd.getText().toString(), 
										regadd.getText().toString(), 
										vinadd.getText().toString(), 
										0.0, 
										null);
					
					lv1.setVisibility(View.VISIBLE);
					add.setVisibility(View.VISIBLE);
					
					mkradd.setText("");
					typadd.setText("");
					yeaadd.setText("");
					regadd.setText("");
					vinadd.setText("");
					
					View temp = findViewById(R.id.addbott);
					temp.setVisibility(View.GONE);
			    	
			    	setListAdapters();
				}
				else {
					mkradd.setText("No Maker!");
				}
			}
			if(v.getId() == R.id.save) {
				if(mkradd.getText().toString().trim().length() > 0 && typadd.getText().toString().trim().length() > 0 && yeaadd.getText().toString().trim().length() > 0) {
					myCell.get(posInt).setMaker(mkradd.getText().toString());
					myCell.get(posInt).setType(typadd.getText().toString());
					myCell.get(posInt).setYear(yeaadd.getText().toString());
					myCell.get(posInt).setRegion(regadd.getText().toString());
					myCell.get(posInt).setVineyard(vinadd.getText().toString());
					
					lv1.setVisibility(View.VISIBLE);
					add.setVisibility(View.VISIBLE);
					
					mkradd.setText("");
					typadd.setText("");
					yeaadd.setText("");
					regadd.setText("");
					vinadd.setText("");
					
					View temp = findViewById(R.id.addbott);
					temp.setVisibility(View.GONE);
			    	
			    	setListAdapters();
				}
			}
		}
	}

    
    
    
    
//==========================================================================================
//Splash Screen Methods Below
//==========================================================================================
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