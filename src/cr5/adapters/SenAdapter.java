package cr5.adapters;

import java.util.ArrayList;
import java.util.List;

import cr5.items.Text;
import cr5.main.R;
import cr5.utils.Methods;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

// Audio item list => TextL
public class SenAdapter extends ArrayAdapter<String> {

	/****************************
	 * Class fields
		****************************/
	// Context
	Context con;

	// Inflater
	LayoutInflater inflater;

	/****************************
	 * Constructor
		****************************/
	//
	public SenAdapter(Context con, int resourceId, List<String> items) {
		// Super
		super(con, resourceId, items);

		// Context
		this.con = con;

		// Inflater
		inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		

	}//public TextListAdapter(Context con, int resourceId, List<Text> items)

	/****************************
	 * Methods
		****************************/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	/****************************
		 * 0. View
		 * 
		 * 1. Set layout
		 * 2. Get view
		 * 
		 * 3. Get item
		 * 
		 * 4. Set file name
		 * 
		 * 5. Set title
		 * 
		 * 9. Return view
			****************************/

    	/****************************
		 * 0. View
			****************************/
    	View v = null;

    	/*********************************
		 * Set layout
		 *********************************/
    	if (convertView != null) {

    		v = convertView;
    		
		} else {//if (convertView != null)

			v = inflater.inflate(R.layout.listrow_actv_read, null);
			
		}//if (convertView != null)

    	/*********************************
		 * 3. Get item
		 *********************************/
    	String sen = (String) getItem(position);

    	/***************************************
		 * Set: Texts
		 ***************************************/
    	_setText_Sentence(v, sen);
    	
    	/*********************************
		 * 9. Return view
		 *********************************/
		return v;
		
    }//public View getView(int position, View convertView, ViewGroup parent)

	private void _setText_Sentence(View v, String sen) {
		
    	TextView tvSen =
    			(TextView) v.findViewById(R.id.lr_actv_read_tv_sen);
    	
    	tvSen.setText(sen);
    	
	}

}//public class TLAdapter extends ArrayAdapter<Text>
