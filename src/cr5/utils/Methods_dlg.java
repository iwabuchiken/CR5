package cr5.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cr5.listeners.dialog.DialogButtonOnClickListener;
import cr5.listeners.dialog.DialogButtonOnTouchListener;
import cr5.listeners.dialog.DialogOnItemClickListener;
import cr5.main.R;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Methods_dlg {

	public static void dlg_db_activity(Activity actv) {
		/****************************
		 * 1. Dialog
		 * 2. Prep => List
		 * 3. Adapter
		 * 4. Set adapter
		 * 
		 * 5. Set listener to list
		 * 6. Show dialog
			****************************/
		Dialog dlg = Methods_dlg.dlg_template_cancel(
									actv, R.layout.dlg_db_admin, 
									R.string.dlg_db_admin_title, 
									R.id.dlg_db_admin_bt_cancel, 
									Tags.DialogTags.dlg_generic_dismiss);
		
		/****************************
		 * 2. Prep => List
			****************************/
		String[] choices = {
			actv.getString(R.string.dlg_db_admin_item_backup_db),
			actv.getString(R.string.dlg_db_admin_item_refresh_db),
			actv.getString(R.string.dlg_db_admin_item_restore_db),
			actv.getString(R.string.dlg_db_admin_item_create_table_refresh_history),
			actv.getString(R.string.dlg_db_admin_item_reset_table_texts),
			actv.getString(R.string.dlg_db_admin_item_add_column_millsec_refresh),
			actv.getString(R.string.dlg_db_admin_item_reset_table_history),
			actv.getString(R.string.dlg_db_admin_item_migrate),
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
//				R.layout.dlg_db_admin,
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
		lv.setTag(Tags.DialogItemTags.dlg_db_admin_lv);
		
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		
		/****************************
		 * 6. Show dialog
			****************************/
		dlg.show();
		
		
	}//public static void dlg_db_activity(Activity actv)

	public static
	Dialog dlg_template_cancel
	(Activity actv, 
			int layoutId, int titleStringId,
			int cancelButtonId, Tags.DialogTags cancelTag) {
		/****************************
		* Steps
		* 1. Set up
		* 2. Add listeners => OnTouch
		* 3. Add listeners => OnClick
		****************************/
		
		// 
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(layoutId);
		
		// Title
		dlg.setTitle(titleStringId);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_cancel = (Button) dlg.findViewById(cancelButtonId);
		
		//
		btn_cancel.setTag(cancelTag);
		
		//
		btn_cancel.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_cancel.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		//
		//dlg.show();
		
		return dlg;
	
	}//public static Dialog dlg_template_okCancel()

	
	public static void dlg_GetDataFromRemote(Activity actv) {
		// TODO Auto-generated method stub
		/****************************
		 * 1. Dialog
		 * 2. Prep => List
		 * 3. Adapter
		 * 4. Set adapter
		 * 
		 * 5. Set listener to list
		 * 6. Show dialog
			****************************/
		Dialog dlg = Methods_dlg.dlg_template_cancel(
									actv, R.layout.dlg_db_admin, 
									R.string.dlg_GetDataFromRemote_title, 
									R.id.dlg_db_admin_bt_cancel, 
									Tags.DialogTags.dlg_generic_dismiss);
		
		/****************************
		 * 2. Prep => List
			****************************/
		String[] choices = {
			actv.getString(R.string.dlg_GetDataFromRemote_texts),
			actv.getString(R.string.dlg_GetDataFromRemote_words),
			actv.getString(R.string.dlg_GetDataFromRemote_word_lists),
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
//				R.layout.dlg_db_admin,
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
		lv.setTag(Tags.DialogItemTags.dlg_GetDataFromRemote_lv);
		
		lv.setOnItemClickListener(new DialogOnItemClickListener(actv, dlg));
		
		/****************************
		 * 6. Show dialog
			****************************/
		dlg.show();
		
	}//public static void dlg_GetDataFromRemote(Activity actv)

}//public class Methods_dialog
