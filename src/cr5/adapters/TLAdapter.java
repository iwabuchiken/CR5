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
public class TLAdapter extends ArrayAdapter<Text> {

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
	public TLAdapter(Context con, int resourceId, List<Text> items) {
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
//    	// Log
//		Log.d("TLAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "getView()");
		
    	
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

			v = inflater.inflate(R.layout.listrow_text_list, null);
			
		}//if (convertView != null)

    	/*********************************
		 * 3. Get item
		 *********************************/
    	Text text = (Text) getItem(position);

    	/***************************************
		 * Set: Texts
		 ***************************************/
    	_setText_Title(v, text);
    	
    	_setText_CreatedAt(v, text);
    	
//    	TextView tvTitle =
//    			(TextView) v.findViewById(R.id.lr_text_list_tv_title);
//    	
//    	String title = "";
//    	
//    	if (text.getTitle() == null
//    			|| text.getTitle().equals("")) {
//			
//    		if (text.getText().length() > 30) {
//				
//    			title = text.getText().substring(0, 30);
//    			
//			} else {//if (text.getText().length() > 30)
//				
//				title = text.getText();
//				
//			}//if (text.getText().length() > 30)
//			
//    		
//		} else {//if (text.getTitle().equals(""))
//			
//			title = text.getTitle();
//			
//		}//if (text.getTitle().equals(""))
//		
//    	tvTitle.setText(title);
//    	
    	/*********************************
		 * 9. Return view
		 *********************************/
		return v;
		
    }//public View getView(int position, View convertView, ViewGroup parent)

	private void _setText_CreatedAt(View v, Text text) {
		// TODO Auto-generated method stub
    	TextView tvCreatedAt =
    			(TextView) v.findViewById(R.id.lr_text_list_tv_created_at);
//    	(TextView) v.findViewById(R.id.lr_text_list_tv_title);
    	
    	String createdAt = "";
    	
    	if (text.getCreatedAt_mill() == 0) {
    			
    		createdAt = "No data";
    		
		} else {//if (text.getTitle().equals(""))
			
//			createdAt = Methods.con
			// Log
			Log.d("TLAdapter.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Date=" + Methods.convert_MillSec2DateLabel(text.getCreatedAt_mill())
					+ "/"
					+ "millSec=" + text.getCreatedAt_mill());
			
			createdAt = Methods.getTimeLabel_Japanese(text.getCreatedAt_mill());
			
		}//if (text.getTitle().equals(""))
		
    	tvCreatedAt.setText(createdAt);

	}//private void _setText_CreatedAt(View v, Text text)

	private void _setText_Title(View v, Text text) {
		// TODO Auto-generated method stub
    	TextView tvTitle =
    			(TextView) v.findViewById(R.id.lr_text_list_tv_title);
    	
    	String title = "";
    	
    	if (text.getTitle() == null
    			|| text.getTitle().equals("")) {
			
    		if (text.getText().length() > 30) {
				
    			title = text.getText().substring(0, 30);
    			
			} else {//if (text.getText().length() > 30)
				
				title = text.getText();
				
			}//if (text.getText().length() > 30)
			
    		
		} else {//if (text.getTitle().equals(""))
			
			title = text.getTitle();
			
		}//if (text.getTitle().equals(""))
		
    	tvTitle.setText(title);
    	
	}//private void _setText_title(View v, Text text)

}//public class TLAdapter extends ArrayAdapter<Text>
