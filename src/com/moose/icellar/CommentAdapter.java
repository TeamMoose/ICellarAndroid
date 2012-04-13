package com.moose.icellar;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CommentAdapter extends ArrayAdapter<Comment> {
    private ArrayList<Comment> comments;

    public CommentAdapter(Context context, int textViewResourceId, ArrayList<Comment> comments) {
        super(context, textViewResourceId, comments);
        this.comments = comments;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View v = convertView;
    	if (v == null) {
    		LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    		v = vi.inflate(R.layout.comment_item, null);
    	}
      
    	Comment comm = comments.get(position);
	  
    	TextView text = (TextView) v.findViewById(R.id.text);
	    TextView user = (TextView) v.findViewById(R.id.user);
	    TextView date = (TextView) v.findViewById(R.id.date);
	      
		text.setText(comm.getText());
		user.setText(comm.getUser());
		date.setText(comm.getDate());
		
    	return v;
    }
}