package cr5.listeners.button;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class ButtonOnTouchListener implements OnTouchListener {

	/*----------------------------
	 * Fields
		----------------------------*/
	//
	Activity actv;

	//
	Vibrator vib;
	
	public ButtonOnTouchListener(Activity actv) {
		//
		this.actv = actv;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
	}

//	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
//		Tags.ButtonTags tag = (Tags.ButtonTags) v.getTag();
		
		switch (event.getActionMasked()) {
		case MotionEvent.ACTION_DOWN:
			
//			switch (tag) {
//
//			}//switch (tag)
			
			break;//case MotionEvent.ACTION_DOWN:

			
		case MotionEvent.ACTION_UP:
//			switch (tag) {
//
//			}//switch (tag)
			
			break;//case MotionEvent.ACTION_UP:
		}//switch (event.getActionMasked())
		return false;
	}

	private void case_bcWhite_tcBlack(View v) {
		// TODO Auto-generated method stub
		v.setBackgroundColor(Color.WHITE);
		
		TextView tv = (TextView) v;
		
		tv.setTextColor(Color.BLACK);
		
	}

	private void case_bcBlack_tcWhite(View v) {
		// TODO Auto-generated method stub
		v.setBackgroundColor(Color.BLACK);

		((TextView)v).setTextColor(Color.WHITE);

	}

}
