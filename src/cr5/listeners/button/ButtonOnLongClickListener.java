package cr5.listeners.button;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;

public class ButtonOnLongClickListener implements OnLongClickListener {

	Activity actv;
	
	Vibrator vib;

	public boolean onLongClick(View v) {
		/*********************************
		 * 0. Vibrate
		 * 1. Get tag
		 *********************************/
//		vib.vibrate(Methods.vibLength_click);
		
//		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
		
//		// Log
//		Log.d("ButtonOnLongClickListener.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "tag.name()=" + tag.name());
//		
//		switch (tag) {
//		
//
//		default:
//			break;
//		
//		}//switch (tag)
		
		return false;
	}//public boolean onLongClick(View v)

}//public class ButtonOnLongClickListener implements OnLongClickListener
