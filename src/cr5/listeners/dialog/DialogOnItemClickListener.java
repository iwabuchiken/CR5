package cr5.listeners.dialog;


import java.util.ArrayList;
import java.util.List;

import cr5.main.R;
import cr5.utils.CONS;
import cr5.utils.DBUtils;
import cr5.utils.Methods;
import cr5.utils.Methods_CR5;
import cr5.utils.Methods_dlg;
import cr5.utils.Migrate;
import cr5.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class DialogOnItemClickListener implements OnItemClickListener {

	//
	Activity actv;
	Dialog dlg1;
	Dialog dlg2;
	
	//
	Vibrator vib;
	
	//
//	Methods.DialogTags dlgTag = null;

	public DialogOnItemClickListener(Activity actv, Dialog dlg) {
		// 
		this.actv = actv;
		this.dlg1 = dlg;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	public DialogOnItemClickListener(Activity actv, Dialog dlg, Dialog dlg2) {
		// 
		this.actv = actv;
		this.dlg1 = dlg;
		this.dlg2 = dlg2;
		
		vib = (Vibrator) actv.getSystemService(Context.VIBRATOR_SERVICE);
		
	}//public DialogOnItemClickListener(Activity actv, Dialog dlg)

	//	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		/*----------------------------
		 * Steps
		 * 1. Get tag
		 * 2. Vibrate
		 * 3. Switching
			----------------------------*/
		
		Tags.DialogItemTags tag = (Tags.DialogItemTags) parent.getTag();
//		
		vib.vibrate(Methods.vibLength_click);
		
		/*----------------------------
		 * 3. Switching
			----------------------------*/
		switch (tag) {
		
		case dlg_db_admin_lv://-----------------------------
			
			String item = (String) parent.getItemAtPosition(position);
			
			case_dlg_db_admin_lv(item);
			
			break;// case dlg_db_admin_lv
		
		case dlg_GetDataFromRemote_lv://-----------------------------
			
			item = (String) parent.getItemAtPosition(position);
			
			case_dlg_GetDataFromRemote_lv(item);
			
			break;// case dlg_GetDataFromRemote_lv
			
		case migrate_lv://-----------------------------
			
			item = (String) parent.getItemAtPosition(position);
			
			case_dlg_Migrate(item);
			
			break;// case migrate_lv
			
		default:
			break;
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

	private void case_dlg_Migrate(String item) {
		// TODO Auto-generated method stub
		/***************************************
		 * Reset tables: Main
		 ***************************************/
		if (item.equals(actv.getString(
				R.string.migrate_20130421_115608_ResetTableTexts))) {
			
//			migrate_20130421_115608_ResetTableTexts();
			Migrate._20130421_115608_ResetTableTexts(actv, dlg1);

		} else if (item.equals(actv.getString(
				R.string.migrate_20130421_120721_ResetTable_Words))) {

			Migrate._20130421_120721_ResetTable_Words(actv, dlg1);
			
		} else if (item.equals(actv.getString(
				R.string.migrate_ResetTable_WordList))) {
			
			Migrate._ResetTable_WordList(actv, dlg1);
//			migrate_20130421_120721_ResetTable_Words();
		
		/***************************************
		 * Reset tables: Updates
		 ***************************************/
		} else if (item.equals(actv.getString(
				R.string.migrate__ResetTable_Updates_Text))) {

			Migrate.migrate__ResetTable_Updates(
					actv, dlg1,
					CONS.DB.tname_Updates_Texts,
					CONS.DB.cols_Updates_Texts,
					CONS.DB.col_types_Updates_Texts);
			
		} else if (item.equals(actv.getString(
				R.string.migrate_ResetTable_Updates_Words))) {
			
			Migrate.migrate__ResetTable_Updates(
					actv, dlg1,
					CONS.DB.tname_Updates_Words,
					CONS.DB.cols_Updates_Words,
					CONS.DB.col_types_Updates_Words);
			
		} else if (item.equals(actv.getString(
				R.string.migrate_ResetTable_Updates_WordList))) {
			
			Migrate.migrate__ResetTable_Updates(
					actv, dlg1,
					CONS.DB.tname_Updates_WordList,
					CONS.DB.cols_Updates_WordList,
					CONS.DB.col_types_Updates_WordList);
			
		/***************************************
		 * Create tables: Main
		 ***************************************/
		} else if (item.equals(actv.getString(
				R.string.migrate_20130421_131922_CreateTable_Word_list))) {

			Migrate._20130421_131922_CreateTable_Word_list(actv, dlg1);

		/***************************************
		 * Create tables: Updates
		 ***************************************/
		} else if (item.equals(actv.getString(
				R.string.migrate_20130421_135728_CreateTable_Updates_Texts))) {
			
			Migrate._20130421_135728_CreateTable_Updates_Texts(actv, dlg1);
			
		} else if (item.equals(actv.getString(
				R.string.migrate_20130421_135837_CreateTable_Updates_Words))) {
			
			Migrate._20130421_135837_CreateTable_Updates_Words(actv, dlg1);
			
		} else if (item.equals(actv.getString(
				R.string.migrate_20130421_135941_CreateTable_Updates_WordList))) {
			
			Migrate._20130421_135941_CreateTable_Updates_WordList(actv, dlg1);
			
			
		}
		
	}//private void case_dlg_Migrate(String item)

	private void migrate_20130421_120721_ResetTable_Words() {
		// TODO Auto-generated method stub
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		boolean res = dbu.dropTable(actv, CONS.DB.tname_words);
		
//		if (res == true) {
			
		res = dbu.createTable(
				CONS.DB.tname_words,
				CONS.DB.cols_texts,
				CONS.DB.col_types_texts);
		
		if (res == true) {
			
			// debug
			Toast.makeText(actv,
					"Table reset => " + CONS.DB.tname_words,
					Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Table reset => " + CONS.DB.tname_words);
			
			dlg1.dismiss();
			
		} else {//if (res == true)

			// debug
			Toast.makeText(actv,
					"Sorry. Table creation failed. Now you don't have the table:"
						+ CONS.DB.tname_words,
					Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Sorry. Table creation failed. Now you don't have the table:"
							+ CONS.DB.tname_words);
			
		}//if (res == true)
			
	}//private void migrate_20130421_120721_ResetTable_Words()

	private void migrate_20130421_115608_ResetTableTexts() {
		// TODO Auto-generated method stub
		
		case_dlg_db_admin_lv__ResetTableTexts();
		
	}//private void migrate_20130421_115608()

	private void case_dlg_GetDataFromRemote_lv(String item) {
		// TODO Auto-generated method stub
		if (item.equals(actv.getString(
							R.string.dlg_GetDataFromRemote_texts))) {
			
			_GetDataFromRemote_lv_Texts();
			
		} else if (item.equals(actv.getString(
				R.string.dlg_GetDataFromRemote_words))) {
			
			_GetDataFromRemote_lv_Words();
			
		} else if (item.equals(actv.getString(
				R.string.dlg_GetDataFromRemote_word_lists))) {
			
			_GetDataFromRemote_lv_WordList();
			
		}//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db))) {
		
	}//private void case_dlg_GetDataFromRemote_lv(String item)

	private void _GetDataFromRemote_lv_WordList() {
		// TODO Auto-generated method stub
		boolean res = Methods_CR5.validateTableExists(actv, CONS.DB.tname_word_list);
//		boolean res = Methods_CR5.validateTableExists_Words(actv);
		
		if (res == true) {
			
			Methods_CR5.getWordList(actv, CONS.Admin.remoteUrl_WordList);
			
			dlg1.dismiss();
			
		} else {//if (res == true)
			
			// Log
			Log.d("ActvMain.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2]
							.getMethodName() + "]",
					"Validation: Table \"word_list\" => Failed");
			
			// debug
			Toast.makeText(actv, "Can't prepare the table \"word_list\"", Toast.LENGTH_LONG).show();
			
		}//if (res == true)
		
	}//private void _GetDataFromRemote_lv_WordList()

	private void _GetDataFromRemote_lv_Words() {
		// TODO Auto-generated method stub
		boolean res = Methods_CR5.validateTableExists_Words(actv);
		
		if (res == true) {
			
			Methods_CR5.getWords(actv, CONS.Admin.remoteUrl_Words);
			
			dlg1.dismiss();
			
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
			Toast.makeText(actv, "Can't prepare the table \"texts\"", Toast.LENGTH_LONG).show();
			
		}//if (res == true)

	}//private void _GetDataFromRemote_lv_Words()

	private void _GetDataFromRemote_lv_Texts() {
		// TODO Auto-generated method stub
		boolean res = Methods_CR5.validateTableExists_texts(actv);
		
		if (res == true) {
			
			String url = _GetDataFromRemote_lv_Texts__1__BuildUrl(actv);
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "url=" + url);
			
			
			Methods_CR5.getTexts(actv, CONS.Admin.remoteUrl_Texts);
			
			dlg1.dismiss();
			
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
			Toast.makeText(actv, "Can't prepare the table \"texts\"", Toast.LENGTH_LONG).show();
			
		}//if (res == true)

	}//private void _GetDataFromRemote_lv_Texts()

	private String
	_GetDataFromRemote_lv_Texts__1__BuildUrl(Activity actv) {
		// TODO Auto-generated method stub
//		CONS.Admin.remoteUrl_Texts
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		long lastCreatedAt = dbu.get_LastCreatedAt(actv, CONS.DB.tname_Updates_Texts);
		
		// Log
		Log.d("DialogOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "lastCreatedAt=" + lastCreatedAt);
		
		/***************************************
		 * Return
		 ***************************************/
		if (lastCreatedAt > 0) {
			
			Uri.Builder ub = Uri.parse(CONS.Admin.remoteUrl_Texts).buildUpon();
			
			ub.appendQueryParameter("since", String.valueOf(lastCreatedAt));
			
//			// Log
//			Log.d("DialogOnItemClickListener.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "ub.build().toString()=" + ub.build().toString());
			
			return ub.build().toString();
			
		} else {//if (condition)
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "lastCreatedAt <= 0");
			
			return CONS.Admin.remoteUrl_Texts;
			
		}//if (condition)
		
		
//		return null;
	}//_GetDataFromRemote_lv_Texts__1__BuildUrl(Activity actv)

	private void case_dlg_db_admin_lv(String item) {
		// TODO Auto-generated method stub
		if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db))) {
			
//			Methods.db_backup(actv, dlg1, dbPath, dbName, dbBackupPath, dbBackupNameTrunk, dbBackupNameExt)
			Methods.db_backup(
						actv,
						dlg1,
						
						CONS.DB.dpath_db,
						CONS.DB.dbName,
						
						CONS.DB.dpath_dbBackup,
						CONS.DB.fname_dbBackupTrunk,
						CONS.DB.fname_dbBackupExt);
//						dbBackupPath, dbBackupNameTrunk, dbBackupNameExt)
			
		} else if (item.equals(actv.getString(
								R.string.dlg_db_admin_item_restore_db))) {//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db)))
			
//			Methods.restore_db(actv, CONS.DB.dbName, src, dst)
			Methods.restore_db(actv);

		} else if (item.equals(actv.getString(
							R.string.dlg_db_admin_item_create_table_refresh_history))) {//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db)))
			
			case_dlg_db_admin_lv__refresh_history();

		} else if (item.equals(actv.getString(
				R.string.dlg_db_admin_item_reset_table_texts))) {//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db)))

			case_dlg_db_admin_lv__ResetTableTexts();
			
		} else if (item.equals(actv.getString(
				R.string.dlg_db_admin_item_reset_table_history))) {//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db)))
			
			case_dlg_db_admin_lv__ResetTableHistory();
			
		} else if (item.equals(actv.getString(
				R.string.dlg_db_admin_item_add_column_millsec_refresh))) {//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db)))

			case_dlg_db_admin_lv__AddColRefresh();
		
		} else if (item.equals(actv.getString(
				R.string.dlg_db_admin_item_migrate))) {//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db)))
			
			case_dlg_db_admin_lv__Migrate();
			
		}//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db)))
		
	}

	private void case_dlg_db_admin_lv__Migrate() {
		// TODO Auto-generated method stub
		dlg1.dismiss();
		
		Dialog dlg = Methods_dlg.dlg_template_cancel(
				actv, R.layout.dlg_db_admin, 
				R.string.dlg_db_admin_title, 
				R.id.dlg_db_admin_bt_cancel, 
				Tags.DialogTags.dlg_generic_dismiss);

		/****************************
		* 2. Prep => List
		****************************/
		String[] choices = {
					actv.getString(R.string.migrate_20130421_115608_ResetTableTexts),
					actv.getString(R.string.migrate_20130421_120721_ResetTable_Words),
					actv.getString(R.string.migrate_ResetTable_WordList),
					
					actv.getString(R.string.migrate__ResetTable_Updates_Text),
					actv.getString(R.string.migrate_ResetTable_Updates_Words),
					actv.getString(R.string.migrate_ResetTable_Updates_WordList),
					
					actv.getString(R.string.migrate_20130421_131922_CreateTable_Word_list),
					
					actv.getString(
							R.string.migrate_20130421_135728_CreateTable_Updates_Texts),
					actv.getString(
							R.string.migrate_20130421_135837_CreateTable_Updates_Words),
					actv.getString(
							R.string.migrate_20130421_135941_CreateTable_Updates_WordList),
					
		};
		
		List<String> list = new ArrayList<String>();
		
		for (String item : choices) {
		
			list.add(item);
		
		}
		
		// Log
		Log.d("Methods_dlg.java" + "["
		+ Thread.currentThread().getStackTrace()[2].getLineNumber()
		+ ":"
		+ Thread.currentThread().getStackTrace()[2].getMethodName()
		+ "]", "list.size()=" + list.size());
		
		/****************************
		* 3. Adapter
		****************************/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				actv,
				//R.layout.dlg_db_admin,
				android.R.layout.simple_list_item_1,
				list
		);
		
		/****************************
		* 4. Set adapter
		****************************/
		ListView lv = (ListView) dlg.findViewById(R.id.dlg_db_admin_lv);
		
		lv.setAdapter(adapter);
		
		/****************************
		* 5. Set listener to list
		****************************/
		lv.setTag(Tags.DialogItemTags.migrate_lv);
		
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		
		/****************************
		* 6. Show dialog
		****************************/
		dlg.show();

		
	}//private void case_dlg_db_admin_lv__Migrate()

	private void case_dlg_db_admin_lv__ResetTableHistory() {
		// TODO Auto-generated method stub
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		boolean res = dbu.dropTable(actv, CONS.DB.tname_RefreshHistory);
		
		if (res == true) {
			
			res = dbu.createTable(
					CONS.DB.tname_RefreshHistory,
					CONS.DB.cols_RefreshHistory,
					CONS.DB.col_types_RefreshHistory);
			
			if (res == true) {
				
				// debug
				Toast.makeText(actv,
						"Table reset => " + CONS.DB.tname_RefreshHistory,
						Toast.LENGTH_LONG).show();
				
				// Log
				Log.d("DialogOnItemClickListener.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]",
						"Table reset => " + CONS.DB.tname_RefreshHistory);
				
				dlg1.dismiss();
				
			} else {//if (res == true)

				// debug
				Toast.makeText(actv,
						"Sorry. Table creation failed. Now you don't have the table:"
							+ CONS.DB.tname_texts,
						Toast.LENGTH_LONG).show();
				
				// Log
				Log.d("DialogOnItemClickListener.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]",
						"Sorry. Table creation failed. Now you don't have the table:"
								+ CONS.DB.tname_RefreshHistory);
				
			}//if (res == true)
			
		} else {//if (res == true)
			
		}//if (res == true)
		
	}//private void case_dlg_db_admin_lv__ResetTableHistory()

	private void case_dlg_db_admin_lv__AddColRefresh() {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		int res = dbu.addColumn(
						actv,
						CONS.DB.tname_RefreshHistory,
						"createdAt_mill",
						"INTEGER");
		
	}//private void case_dlg_db_admin_lv__AddColRefresh()

	private void case_dlg_db_admin_lv__ResetTableTexts() {
		// TODO Auto-generated method stub
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		boolean res = dbu.dropTable(actv, CONS.DB.tname_texts);
		
//		if (res == true) {
			
			res = dbu.createTable(
					CONS.DB.tname_texts,
					CONS.DB.cols_texts,
					CONS.DB.col_types_texts);
			
			if (res == true) {
				
				// debug
				Toast.makeText(actv,
						"Table reset => " + CONS.DB.tname_texts,
						Toast.LENGTH_LONG).show();
				
				// Log
				Log.d("DialogOnItemClickListener.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]",
						"Table reset => " + CONS.DB.tname_texts);
				
				dlg1.dismiss();
				
			} else {//if (res == true)

				// debug
				Toast.makeText(actv,
						"Sorry. Table creation failed. Now you don't have the table:"
							+ CONS.DB.tname_texts,
						Toast.LENGTH_LONG).show();
				
				// Log
				Log.d("DialogOnItemClickListener.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]",
						"Sorry. Table creation failed. Now you don't have the table:"
								+ CONS.DB.tname_texts);
				
			}//if (res == true)
			
//		} else {//if (res == true)
//			
//		}//if (res == true)
		
	}//private void case_dlg_db_admin_lv__ResetTableTexts()

	private void case_dlg_db_admin_lv__refresh_history() {
		// Log
		Log.d("DialogOnItemClickListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Create table: " + CONS.DB.tname_RefreshHistory);
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		boolean res = dbu.createTable(
							CONS.DB.tname_RefreshHistory,
							CONS.DB.cols_RefreshHistory,
							CONS.DB.col_types_RefreshHistory);
		
		if (res == true) {
			
			// debug
			Toast.makeText(actv,
					"Table created => " + CONS.DB.tname_RefreshHistory,
					Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Table created => " + CONS.DB.tname_RefreshHistory);
			
			dlg1.dismiss();
			
		} else {//if (res == true)
			
			// debug
			Toast.makeText(actv,
					"Create table => Failed: " + CONS.DB.tname_RefreshHistory,
					Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("DialogOnItemClickListener.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Create table => Failed: " + CONS.DB.tname_RefreshHistory);
			
		}//if (res == true)
		

		
	}//private void case_dlg_db_admin_lv(String item)

}//public class DialogOnItemClickListener implements OnItemClickListener
