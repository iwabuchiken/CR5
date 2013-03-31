package cr5.listeners.dialog;


import cr5.main.R;
import cr5.utils.CONS;
import cr5.utils.DBUtils;
import cr5.utils.Methods;
import cr5.utils.Tags;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
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
		
		default:
			break;
		}//switch (tag)
		
	}//public void onItemClick(AdapterView<?> parent, View v, int position, long id)

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
			
			
		}//if (item.equals(actv.getString(R.string.dlg_db_admin_item_backup_db)))
		
	}

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
