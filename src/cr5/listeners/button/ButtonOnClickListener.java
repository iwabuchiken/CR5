package cr5.listeners.button;

import java.io.File;

//import cm5.main.ImageActv;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ButtonOnClickListener implements OnClickListener {
	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	//
	int position;
	
	//
	ListView lv;
	
	public ButtonOnClickListener(Activity actv) {
		//
		this.actv = actv;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

	public ButtonOnClickListener(Activity actv, int position) {
		//
		this.actv = actv;
		this.position = position;
		
		//
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
		
		
		
	}//public ButtonOnClickListener(Activity actv, int position)

	public ButtonOnClickListener(Activity actv, ListView lv) {
		// 
		this.actv = actv;
		this.lv = lv;
		
		vib = (Vibrator) actv.getSystemService(actv.VIBRATOR_SERVICE);
	}

public void onClick(View v) {
//		//
//		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
//
//		vib.vibrate(Methods.vibLength_click);
		
		/*********************************
		 * 1. actv_play.xml
		 * 
		 * 2. main.xml
		 *********************************/
		//
//		switch (tag) {
//
//		default:
//			break;
//		}//switch (tag)
		
	}//public void onClick(View v)

	private void thumb_activity_ib_back() {
		/*********************************
		 * memo
		 *********************************/
		actv.finish();
		
	}//private void thumb_activity_ib_back()

	private void image_activity_next() {
//		/*********************************
//		 * 1. Get prefs => current position
//		 * 2. No more prev?
//		 * 
//		 * 3. Get the previous item in the ti list
//		 * 4. New image file path
//		 * 
//		 * 5. Show the previous image
//		 * 
//		 * 6. Set new pref value
//		 * 
//		 * 7. Update the file name label
//		 * 
//		 * 8. Save history
//		 * 
//		 *********************************/
//		SharedPreferences prefs = actv.getSharedPreferences(
//					MainActv.prefName_tnActv,
//					actv.MODE_PRIVATE);
//		
// 
//		//Methods.PrefenceLabels.thumb_actv.name()
//		
//		//int savedPosition = prefs.getInt("chosen_list_item", -1);
//		int savedPosition = prefs.getInt(
//							MainActv.prefName_tnActv_current_image_position,
//							-1);
//		
//		// Log
//		Log.d("ButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "savedPosition=" + savedPosition);
//		
//		/*********************************
//		 * 2. No more prev?
//		 *********************************/
////		if (savedPosition == 0) {
//		if (savedPosition >= TNActv.tiList.size() - 1) {
//			
//			// debug
//			Toast.makeText(actv, "No next images", Toast.LENGTH_SHORT).show();
//			
//			return;
//			
//		}//if (savedPosition == 0)
//
//		/*********************************
//		 * 3. Get the previous item in the ti list
//		 *********************************/
//		TI ti_prev = TNActv.tiList.get(savedPosition + 1);
////		
////		
//		/*********************************
//		 * 4. New image file path
//		 *********************************/
//		String image_file_path_new = ti_prev.getFile_path();
//	
//		// Log
//		Log.d("ButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "image_file_path_new=" + image_file_path_new);
////		
//		/*********************************
//		 * 5. Show the next image
//		 *********************************/
//		ImageActv.bm = BitmapFactory.decodeFile(image_file_path_new);
//		
//		ImageActv.LL.removeView(ImageActv.v);
//		
//		ImageActv.v.setImageBitmap(ImageActv.bm);
//		
//		ImageActv.LL.addView(ImageActv.v);
////		
//		// Log
//		Log.d("ButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "New image added");
////		
//		/*********************************
//		 * 6. Set new pref value
//		 *********************************/
//		SharedPreferences.Editor editor = prefs.edit();
//		
//		editor.putInt(MainActv.prefName_tnActv_current_image_position, savedPosition + 1);
//		editor.commit();
//
//		// Log
//		Log.d("ButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Prefs set: " + (savedPosition + 1));
//
//		/*********************************
//		 * 7. Update the file name label
//		 *********************************/
//		ImageActv.tv_file_name.setText(ti_prev.getFile_name());
//
//		/*********************************
//		 * 8. Save history
//		 *********************************/
//		boolean res = Methods.record_history(actv, ti_prev);
		
	}//private void image_activity_next()

	private void image_activity_prev() {
//		/*********************************
//		 * 1. Get prefs => current position
//		 * 2. No more prev?
//		 * 
//		 * 3. Get the previous item in the ti list
//		 * 4. New image file path
//		 * 
//		 * 5. Show the previous image
//		 * 
//		 * 6. Set new pref value
//		 * 
//		 * 7. Update the file name label
//		 * 
//		 * 8. Save history
//		 *********************************/
//		SharedPreferences prefs = actv.getSharedPreferences(
//					MainActv.prefName_tnActv,
//					actv.MODE_PRIVATE);
//		
// 
//		//Methods.PrefenceLabels.thumb_actv.name()
//		
//		//int savedPosition = prefs.getInt("chosen_list_item", -1);
//		int savedPosition = prefs.getInt(
//							MainActv.prefName_tnActv_current_image_position,
//							-1);
//		
//		// Log
//		Log.d("ButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "savedPosition=" + savedPosition);
//		
//		/*********************************
//		 * 2. No more prev?
//		 *********************************/
//		if (savedPosition == 0) {
//			
//			// debug
//			Toast.makeText(actv, "No previous images", Toast.LENGTH_SHORT).show();
//			
//			return;
//			
//		}//if (savedPosition == 0)
//
//		/*********************************
//		 * 3. Get the previous item in the ti list
//		 *********************************/
//		TI ti_prev = TNActv.tiList.get(savedPosition - 1);
//		
//		
//		/*********************************
//		 * 4. New image file path
//		 *********************************/
//		String image_file_path_new = ti_prev.getFile_path();
//	
//		// Log
//		Log.d("ButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "image_file_path_new=" + image_file_path_new);
//		
//		/*********************************
//		 * 5. Show the previous image
//		 *********************************/
//		ImageActv.bm = BitmapFactory.decodeFile(image_file_path_new);
//		
//		ImageActv.LL.removeView(ImageActv.v);
//		
//		ImageActv.v.setImageBitmap(ImageActv.bm);
//		
//		ImageActv.LL.addView(ImageActv.v);
//		
//		// Log
//		Log.d("ButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "New image added");
//		
//		/*********************************
//		 * 6. Set new pref value
//		 *********************************/
//		SharedPreferences.Editor editor = prefs.edit();
//		
//		editor.putInt(MainActv.prefName_tnActv_current_image_position, savedPosition - 1);
//		editor.commit();
//
//		// Log
//		Log.d("ButtonOnClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Prefs set: " + (savedPosition - 1));
//		
//		/*********************************
//		 * 7. Update the file name label
//		 *********************************/
//		ImageActv.tv_file_name.setText(ti_prev.getFile_name());
//		
//		/*********************************
//		 * 8. Save history
//		 *********************************/
//		boolean res = Methods.record_history(actv, ti_prev);
		
	}//private void image_activity_prev()

}//public class ButtonOnClickListener implements OnClickListener

	