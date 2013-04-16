package cr5.main;

import cr5.utils.CONS;
import cr5.utils.DBUtils;
import cr5.utils.Methods_CR5;
import cr5.utils.Methods_dlg;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ActvMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actv_main);
		
		//debug
//		_migrate();
		
	}

	private void _migrate() {
		// TODO Auto-generated method stub
//		_migrate_20130416_132606_AddColumnToText();
	}

	private void _migrate_20130416_132606_AddColumnToText() {
		// TODO Auto-generated method stub
		DBUtils dbu = new DBUtils(this, CONS.DB.dbName);
		
		int res = dbu.addColumn(
							this,
							CONS.DB.tname_texts,
							CONS.DB.cols_texts[8],
							CONS.DB.col_types_texts[8]);
		
		// Log
		Log.d("ActvMain.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res => " + res);
		
//		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
	}//_migrate_20130416_132606_AddColumnToText_()

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actv_main, menu);
		return true;
	}

	/***************************************
	 * memo
	 ***************************************/
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
		case R.id.opt_actvmain_get_texts://------------------------------
			
			boolean res = Methods_CR5.validateTableExists_texts(this);
			
			if (res == true) {
				
				Methods_CR5.getTexts(this);
				
			} else {//if (res == true)
				
				// Log
				Log.d("ActvMain.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Validation: Table \"texts\" => Failed");
				
				// debug
				Toast.makeText(this, "Can't prepare the table \"texts\"", Toast.LENGTH_LONG).show();
				
			}//if (res == true)
			
//			Methods_CR5.getTexts(this);
			
			break;// case R.id.opt_actvmain_get_texts
			
		case R.id.opt_menu_actv_main_db_activity://----------------------------
			
			Methods_dlg.dlg_db_activity(this);
			
			break;// case R.id.opt_menu_actv_main_db_activity
			
		}//switch (item.getItemId())
		
		return super.onOptionsItemSelected(item);
		
	}//public boolean onOptionsItemSelected(MenuItem item)

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
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}
