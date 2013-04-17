package cr5.main;

import java.util.ArrayList;
import java.util.List;

import cr5.adapters.SenAdapter;
import cr5.items.Text;
import cr5.utils.CONS;
import cr5.utils.DBUtils;
import cr5.utils.Methods;
import cr5.utils.Methods_CR5;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ActvRead extends ListActivity {

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		
		
	}

	@Override
	protected void
	onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
		/****************************
		 * Vibrate
			****************************/
		CONS.Admin.vib.vibrate(Methods.vibLength_click);

		
	}//onListItemClick(ListView l, View v, int position, long id)

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.actv_read);
		
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
				"CONS.ActvRead.text.getDbId()=" + CONS.ActvRead.text.getDbId());
		
		/***************************************
		 * Get: Sentences list
		 ***************************************/
		
//		String[] sens = CONS.ActvRead.text.getText().split("(,|。)");
		String[] sens = CONS.ActvRead.text.getText().split("(，|。)");
		
		if (sens != null) {
			
			List<String> senList = new ArrayList<String>();
			
			for (int i = 0; i < sens.length; i++) {
				
				senList.add(sens[i]);
				
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
		getMenuInflater().inflate(R.menu.actv_main, menu);
		return true;
	}
	
}//public class ActvRead extends ListActivity
