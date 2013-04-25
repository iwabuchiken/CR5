package cr5.main;

import java.util.ArrayList;
import java.util.List;

import cr5.adapters.SenAdapter;
import cr5.items.Text;
import cr5.utils.CONS;
import cr5.utils.DBUtils;
import cr5.utils.Methods;
import cr5.utils.Methods_CR5;
import cr5.utils.Methods_dlg;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ActvRead extends ListActivity implements TextToSpeech.OnInitListener{

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(CONS.ActvRead.tts != null && CONS.ActvRead.tts.isSpeaking()) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "tts != null && tts.isSpeaking()");
			
			CONS.ActvRead.tts.stop();
			
			CONS.ActvRead.tts.shutdown();
//			tts = null;
			
		} else if(CONS.ActvRead.tts != null) {

			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "tts != null");
			
			CONS.ActvRead.tts.shutdown();
//			tts = null;
		}//if(CONS.ActvRead.tts != null && CONS.ActvRead.tts.isSpeaking()) {
		
	}//protected void onDestroy()

	@Override
	protected void
	onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
		/****************************
		 * Vibrate
			****************************/
		CONS.Admin.vib.vibrate(Methods.vibLength_click);

		/***************************************
		 * Start: Speech
		 ***************************************/
		String text = (String) l.getItemAtPosition(position);
		
		Methods_CR5.start_speech(this, text);
		
	}//onListItemClick(ListView l, View v, int position, long id)

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.actv_read);
		
		this.setTitle(this.getClass().getName());
		
		/***************************************
		 * Get: Text object
		 ***************************************/
		int res = _onCreate_getTextItem();
		
		if (res < 0) {
			
			// debug
			Toast.makeText(this, "Can't build text", Toast.LENGTH_LONG).show();
		
			return;
			
		}//if (res < 0)
		
		// Log
		Log.d("ActvRead.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"CONS.ActvRead.text.getDbId()=" + CONS.ActvRead.text.getRemoteDbId());
		
		/***************************************
		 * TTS
		 ***************************************/
		CONS.ActvRead.tts = new TextToSpeech(this, this);
		
		/***************************************
		 * Get: Sentences list
		 ***************************************/
		
//		String[] sens = CONS.ActvRead.text.getText().split("(,|。)");
		String[] sens = CONS.ActvRead.text.getText().split("(，|。)");
		
		if (sens != null) {
			
			List<Spannable> senList = new ArrayList<Spannable>();
//			List<SpannableString> senList = new ArrayList<SpannableString>();
//			List<String> senList = new ArrayList<String>();
			
			for (int i = 0; i < sens.length; i++) {
				
				SpannableString spannedString = Methods_CR5.addSpannable(this, sens[i]);
//				String spannedString = Methods_CR5.addSpannable(this, sens[i]);
				
//				SpannableString ss = new SpannableString((i + 1) + ". " + spannedString);
//				SpannableString ss = new SpannableString(spannedString);
//				SpannableString ss2 = new SpannableString((i + 1) + ". " + spannedString);
				Spannable ss3 = new SpannableString((i + 1) + ". " + spannedString);
				
				
				senList.add(ss3);
//				senList.add(ss2);
//				senList.add((i + 1) + ". " + ss);
//				senList.add((i + 1) + ". " + sens[i]);
				
			}//for (int i = 0; i < sens.length; i++)
			
			CONS.ActvRead.adpSen = new SenAdapter(
					this,
					R.layout.listrow_actv_read,
//					R.layout.actv_al,
					senList
			);
			
			this.setListAdapter(CONS.ActvRead.adpSen);
			
//			// Log
//			Log.d("ActvRead.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "sens.length=" + sens.length);
//			
//			// Log
//			Log.d("ActvRead.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "text=" + CONS.ActvRead.text.getText());
			
		} else {//if (sens != null)
			
			// Log
			Log.d("ActvRead.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "sens => null");
			
			return;
			
		}//if (sens != null)
		
		
	}//protected void onCreate(Bundle savedInstanceState)

	private int _onCreate_getTextItem() {
		// TODO Auto-generated method stub
		/***************************************
		 * Get: Intent data
		 ***************************************/
		Intent i = this.getIntent();
		
		if (i == null) {
			
			// debug
			Toast.makeText(this, "Can't get intent", Toast.LENGTH_SHORT).show();
			
			// Log
			Log.d("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Can't get intent");
			
			return CONS.ActvRead.GET_INTENT_FAILED;
			
		}//if (i == null)
		
		long dbId = i.getLongExtra("dbId", -1);
		
		if (dbId == -1) {
			
			// debug
			Toast.makeText(this, "Can't get db_id", Toast.LENGTH_SHORT).show();
			
			// Log
			Log.d("PlayActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Can't get db_id");
			
			return CONS.ActvRead.GET_DBID_FAILED;
			
		} else {//if (db_id == -1)
			
			// Log
			Log.d("ActvRead.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "dbId=" + dbId);
			
		}//if (db_id == -1)

		/***************************************
		 * Build: Text object
		 ***************************************/
		DBUtils dbu = new DBUtils(this, CONS.DB.dbName);
		
		CONS.ActvRead.text = dbu.get_TextFromDbId(this, dbId);
		
		// Log
		Log.d("ActvRead.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"CONS.ActvRead.text.getRemoteDbId()="
						+ CONS.ActvRead.text.getRemoteDbId());
		
//		CONS.ActvRead.text = Methods_CR5.get_TextFromDbId(this, dbId);

		/***************************************
		 * Return
		 ***************************************/
		if (CONS.ActvRead.text != null) {
		
			return CONS.ActvRead.GET_TEXT_SUCCESSFUL;
			
		} else {//if (CONS.ActvRead.text != null)
			
			return CONS.ActvRead.GET_TEXT_FAILED;
			
		}//if (CONS.ActvRead.text != null)
		
//		return 0;
		
	}//private void _onCreate_getTextItem()

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		/***************************************
		 * Instantiate: vib
		 ***************************************/
		if (CONS.Admin.vib == null) {
			
			CONS.Admin.vib = (Vibrator) this.getSystemService(this.VIBRATOR_SERVICE);
			
		}//if (CONS.Admin.vib == null)
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_actv_read, menu);
		return true;
	}

	
	@Override
	public void onInit(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
		case R.id.opt_menu_actv_read_get_word_list://------------------------------
			
			opt_menu_actv_read_get_word_list();
			
			break;// case R.id.opt_menu_actv_read_get_word_list
			
		case R.id.opt_menu_actv_read_dict://------------------------------
			
			opt_menu_actv_read_dict();
			
			break;// case R.id.opt_menu_actv_read_dict
			
		}//switch (item.getItemId()) {

		return super.onOptionsItemSelected(item);
	}

	private void opt_menu_actv_read_dict() {
		// TODO Auto-generated method stub
		Methods_dlg.dlg_word_list(this, CONS.ActvRead.text);
		
		// Log
		Log.d("ActvRead.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"CONS.ActvRead.text.getRemoteDbId()=" + CONS.ActvRead.text.getRemoteDbId());
	}

	private void opt_menu_actv_read_get_word_list() {
		
		boolean res = Methods_CR5.validateTableExists_Words(this);
		
		if (res == true) {
			
			Methods_CR5.getWords(this, CONS.ActvRead.text.getRemoteDbId());
//			Methods_CR5.getWords(this, CONS.ActvRead.text.getDbId());
			// Log
			Log.d("ActvRead.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "res => " + res);
			
		} else {//if (res == true)
			
			// Log
			Log.d("ActvMain.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Validation: Table \"words\" => Failed");
			
			// debug
			Toast.makeText(this, "Can't prepare the table \"words\"", Toast.LENGTH_LONG).show();
			
		}//if (res == true)
		
		
	}//private void opt_menu_actv_read_get_word_list() {
	
}//public class ActvRead extends ListActivity
