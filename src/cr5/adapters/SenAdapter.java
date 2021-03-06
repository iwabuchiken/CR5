package cr5.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cr5.items.Text;
import cr5.items.Word;
import cr5.main.R;
import cr5.utils.CONS;
import cr5.utils.Methods;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
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
//public class SenAdapter extends ArrayAdapter<Spannable> {
//public class SenAdapter extends ArrayAdapter<SpannableString> {
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
//	public SenAdapter(Context con, int resourceId, List<Spannable> items) {
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
//    	Spannable sen = (Spannable) getItem(position);
//    	SpannableString sen = (SpannableString) getItem(position);
    	String sen = (String) getItem(position);

    	/***************************************
		 * Set: Texts
		 ***************************************/
    	_setText_Sentence(v, sen, position);
    	
    	/*********************************
		 * 9. Return view
		 *********************************/
		return v;
		
    }//public View getView(int position, View convertView, ViewGroup parent)

	private void _setText_Sentence(View v, String origString, int position) {
//	private void _setText_Sentence(View v, String sen) {
//	private void _setText_Sentence(View v, SpannableString sen) {
//	private void _setText_Sentence(View v, Spannable sen) {
		
    	TextView tvSen =
    			(TextView) v.findViewById(R.id.lr_actv_read_tv_sen);
    	
//    	tvSen.setMovementMethod(LinkMovementMethod.getInstance());
    	
//    	// Log
//		Log.d("SenAdapter.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "MovementMethod => Set");
		
		//debug
//		SpannableString ss = new SpannableString(origString);
//		SpannableString ss = _setText_Sentence__1_SetSpannable(v, origString);
		SpannableString ss = _setText_Sentence__1_SetSpannable(v, origString, position);

//		sen.setSpan(new ForegroundColorSpan(0xFF4444FF), 
//		ss.setSpan(new ForegroundColorSpan(0xFF4444FF), 
////				0, sen.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//				0, ss.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    	
//		tvSen.setText(sen);
    	tvSen.setText(ss);
//    	tvSen.setText((position + 1) + "." + ss);
    	
	}//private void _setText_Sentence(View v, Spannable sen)

	private SpannableString
//	_setText_Sentence__1_SetSpannable(View v, String origString) {
	_setText_Sentence__1_SetSpannable(View v, String origString, int position) {
		
//		SpannableString ss = new SpannableString((position + 1) + ". " + origString);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.valueOf((position + 1)));
		sb.append(". ");
		sb.append(origString);
		
		SpannableString ss = 
//				new SpannableString(String.valueOf((position + 1)) + ". " + origString);
				new SpannableString(sb.toString());
		
		/***************************************
		 * Matcher
		 ***************************************/
		if (CONS.ActvRead.wList == null) {
			
			// Log
			Log.d("SenAdapter.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "CONS.ActvRead.wList == null");
			
			return ss;
			
		}//if (variable == condition)

		for (int i = 0; i < CONS.ActvRead.wList.size(); i++) {
			
			Word w = CONS.ActvRead.wList.get(i);
			
			String regex = w.getW1();
			
			Pattern p = Pattern.compile(regex);
			
			//
//			Matcher m = p.matcher(origString);
			Matcher m = p.matcher(sb.toString());
	
			if (m.find()) {
	
				ss.setSpan(new ForegroundColorSpan(0xFF4444FF), 
	//					0, sen.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
						m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				
			} else {//if (m.find())
				
//				// Log
//				Log.d("SenAdapter.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2].getMethodName()
//						+ "]", "No match");
				
			}//if (m.find())
			
		}//for (int i = 0; i < CONS.ActvRead.wList.size(); i++)
		
//			Word w = CONS.ActvRead.wList.get(0);
//			
//			String regex = w.getW1();
//			
//			Pattern p = Pattern.compile(regex);
//			
//			//
//			Matcher m = p.matcher(origString);
//	
//			if (m.find()) {
//	
//				ss.setSpan(new ForegroundColorSpan(0xFF4444FF), 
//	//					0, sen.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//						m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//				
//			} else {//if (m.find())
//				
//				// Log
//				Log.d("SenAdapter.java" + "["
//						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2].getMethodName()
//						+ "]", "No match");
//				
//			}//if (m.find())
		
//		ss.setSpan(new ForegroundColorSpan(0xFF4444FF), 
////				0, sen.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//				0, ss.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		
		return ss;
		
	}//_setText_Sentence__1_SetSpannable(View v, String origString)

}//public class SenAdapter extends ArrayAdapter<String>
