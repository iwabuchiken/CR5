package cr5.main;

import java.util.List;

import cr5.adapters.TLAdapter;
import cr5.items.Text;
import cr5.utils.CONS;
import cr5.utils.DBUtils;
import cr5.utils.Methods_CR5;
import cr5.utils.Methods_dlg;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ActvMain extends ListActivity {

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
			
			Methods_dlg.dlg_GetDataFromRemote(this);
//			
//			boolean res = Methods_CR5.validateTableExists_texts(this);
//			
//			if (res == true) {
//				
//				Methods_CR5.getTexts(this);
//				
//			} else {//if (res == true)
//				
//				// Log
//				Log.d("ActvMain.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]",
//						"Validation: Table \"texts\" => Failed");
//				
//				// debug
//				Toast.makeText(this, "Can't prepare the table \"texts\"", Toast.LENGTH_LONG).show();
//				
//			}//if (res == true)
//			
////			Methods_CR5.getTexts(this);
//			
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
		
		/***************************************
		 * Build: Text list
		 ***************************************/
		List<Text> textList = Methods_CR5.get_TextList(this);
		
		// Log
		Log.d("ActvMain.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "textList.size()=" + textList.size());
		
		TLAdapter adpTL = new TLAdapter(
				this,
				R.layout.listrow_text_list,
//				R.layout.actv_al,
				textList
				);

		/*********************************
		 * Set adapter
		 *********************************/
		this.setListAdapter(adpTL);

		//test
//		_test();
		
	}//protected void onStart()

	private void _test() {
		
		List<Text> textList = Methods_CR5.get_TextList(this);
		
		_test_20130416_150452_list_titles(textList);
		
	}

	private void _test_20130416_150452_list_titles(List<Text> textList) {
		// TODO Auto-generated method stub
		for (int i = 0; i < textList.size(); i++) {
			
			// Log
			Log.d("ActvMain.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "title=" + textList.get(i).getTitle());
			
		}//for (int i = 0; i < textList.size(); i++)
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void
	onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
//		super.onListItemClick(l, v, position, id);
		
		/***************************************
		 * Get: Item
		 ***************************************/
		Text text = (Text) l.getItemAtPosition(position);
		
		// Log
		Log.d("ActvMain.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "title=" + text.getTitle());
		
		// Log
		Log.d("ActvMain.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dbId=" + text.getRemoteDbId());
		
		/****************************
		 * 2. Intent
		 * 		2.1. Set data
			****************************/
		Intent i = new Intent();
		
		i.setClass(this, ActvRead.class);
		
		i.putExtra("dbId", text.getRemoteDbId());
		
		i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		
		/*********************************
		 * 9. Start intent
		 *********************************/
		startActivity(i);

	}//onListItemClick(ListView l, View v, int position, long id)

}
