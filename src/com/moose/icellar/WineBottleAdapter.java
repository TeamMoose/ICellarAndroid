package com.moose.icellar;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WineBottleAdapter extends ArrayAdapter<Bottle> {
    private ArrayList<Bottle> bottles;

    public WineBottleAdapter(Context context, int textViewResourceId, ArrayList<Bottle> bottles) {
        super(context, textViewResourceId, bottles);
        this.bottles = bottles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View v = convertView;
    	if (v == null) {
    		LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		v = vi.inflate(R.layout.list_item, null);
    	}
      
    	Bottle bott = bottles.get(position);
	  
    	TextView maker = (TextView) v.findViewById(R.id.maker);
	    TextView type = (TextView) v.findViewById(R.id.type);
	    TextView year = (TextView) v.findViewById(R.id.year);
	      
		maker.setText(bott.getMaker());
		type.setText(bott.getType());
		year.setText(bott.getYear());
		
    	return v;
    }
}