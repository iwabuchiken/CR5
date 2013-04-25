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

import cr5.items.Text;
import cr5.items.Word;
import cr5.listeners.dialog.DialogButtonOnClickListener;
import cr5.listeners.dialog.DialogButtonOnTouchListener;
import cr5.listeners.dialog.DialogOnItemClickListener;
import cr5.main.R;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
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
//			actv.getString(R.string.dlg_db_admin_item_reset_table_texts),
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

	
	public static void dlg_word_list(Activity actv, Text text) {
		// TODO Auto-generated method stub
		/***************************************
		 * Dialog
		 ***************************************/
		Dialog dlg = dlg_word_list__1_GetDialog(actv);
		
		/***************************************
		 * Layout: Window
		 ***************************************/
		// REF	http://y-anz-m.blogspot.jp/2012/05/androiddialog.html
//		DisplayMetrics metrics = actv.getResources().getDisplayMetrics();
//		
//	    int dialogWidth = (int) (metrics.widthPixels * 0.8); 
//		
//	    WindowManager.LayoutParams lp = dlg.getWindow().getAttributes();
//	    
//	    lp.width = dialogWidth;
//	    
//	    dlg.getWindow().setAttributes(lp);  
	    
		/***************************************
		 * Build: Word list
		 ***************************************/
		List<Word> wList = dlg_word_list__2_GetWordList(actv, text);

		// Log
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "wList.size()=" + wList.size());
		
		/***************************************
		 * Sort: Word list
		 ***************************************/
		boolean res = Methods_CR5.sort_WordList(actv, wList);
		
		/***************************************
		 * Modify: Height of the table layout
		 ***************************************/
		TableLayout tl = (TableLayout) dlg.findViewById(R.id.dlg_word_list_xml_tl);
		
		TableLayout.LayoutParams tlParams =
				new TableLayout.LayoutParams(
								LayoutParams.WRAP_CONTENT,
//								100,
								300);
//								LayoutParams.WRAP_CONTENT);

		
		
		/***************************************
		 * Set word list
		 ***************************************/
		// TableLayout => http://www.javadrive.jp/android/tablelayout/index4.html
//		TableLayout tl = (TableLayout) dlg.findViewById(R.id.dlg_word_list_xml_tl);
		
//		for (int i = 0; i < 4; i++) {
		for (int i = 0; i < wList.size(); i++) {
		
			TableRow tr = new TableRow(actv);

			TableLayout.LayoutParams trParams =
					new TableLayout.LayoutParams(
									LayoutParams.WRAP_CONTENT,
//									100,
									LayoutParams.WRAP_CONTENT);

			// REF	http://stackoverflow.com/questions/4814124/how-to-change-margin-of-textview
			trParams.setMargins(10, 1, 10, 1);
			
			tr.setLayoutParams(trParams);
			
			/***************************************
			 * Layout: TableRow
			 ***************************************/
			// Array of views => http://youinfo.sitemix.jp/uncategorized/705.html
			TextView[] tvWs = new TextView[3];

//			TableRow.LayoutParams tvParams =
//					new TableRow.LayoutParams(
//									LayoutParams.WRAP_CONTENT,
////									100,
//									LayoutParams.WRAP_CONTENT);
//			
//			tvParams.setMargins(2, 2, 2, 2);

			for (int j = 0; j < 3; j++) {
				
				tvWs[j] = new TextView(actv);

//				tvWs[j].setTextSize(25);
				tvWs[j].setTextSize(20);
				tvWs[j].setTextColor(Color.BLACK);
				tvWs[j].setBackgroundColor(Color.WHITE);
				tvWs[j].setPadding(3, 5, 3, 5);

//			LinearLayout.LayoutParams params =
//			new LinearLayout.LayoutParams(
	// http://stackoverflow.com/questions/3224193/set-the-layout-weight-of-a-textview-programmatically ## "answered Jul 31 '12 at 16:06"
				TableRow.LayoutParams tvParams =
						new TableRow.LayoutParams(
			//							LayoutParams.WRAP_CONTENT,
										100,
										LayoutParams.WRAP_CONTENT);

				tvParams.setMargins(2, 2, 2, 2);
				
				tvWs[j].setLayoutParams(tvParams);
//				tvWs[i].setLayoutParams(tvParams);
				
				


			}//for (int j = 0; j < 3; j++)
			
			tvWs[0].setText(wList.get(i).getW1());
			tvWs[1].setText(wList.get(i).getW2());
			tvWs[2].setText(wList.get(i).getW3());
			
			for (int j = 0; j < 3; j++) {
				
				tr.addView(tvWs[j]);
				
			}//for (int j = 0; j < 3; j++)
			
			
			
//			TextView tv = new TextView(actv);
			
//			for (int j = 0; j < 3; j++) {
//				
//				TextView tv = new TextView(actv);
//				
//				tv = new TextView(actv);
//		
//				/***************************************
//				 * Layout: TextView
//				 ***************************************/
//				tv.setTextSize(25);
//				tv.setTextColor(Color.BLACK);
//				tv.setBackgroundColor(Color.WHITE);
//				tv.setPadding(3, 5, 3, 5);
//				
////				LinearLayout.LayoutParams params =
////						new LinearLayout.LayoutParams(
//				// http://stackoverflow.com/questions/3224193/set-the-layout-weight-of-a-textview-programmatically ## "answered Jul 31 '12 at 16:06"
//				TableRow.LayoutParams tvParams =
//						new TableRow.LayoutParams(
////										LayoutParams.WRAP_CONTENT,
//										100,
//										LayoutParams.WRAP_CONTENT);
//
//				tv.setLayoutParams(tvParams);
//				
////				tv.setText(wList.get(i).get);
//				tv.setText("j=" + String.valueOf(j));
//				
//				
//				tr.addView(tv);
//				
//			}//for (int j = 0; j < 3; j++)
			
			
			tl.addView(tr);
			
			
		}//for (int i = 0; i < 4; i++)
		
		dlg.show();
		
//		Dialog dlg = new Dialog(actv);
//		
//		//
//		dlg.setContentView(R.layout.dlg_word_list);
//		
//		// Title
//		dlg.setTitle(R.string.dlg_word_list_title);
//		
//		/****************************
//		* 2. Add listeners => OnTouch
//		****************************/
//		//
//		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_word_list_xml_bt_ok);
//		
//		//
//		btn_ok.setTag(Tags.DialogTags.dlg_generic_dismiss);
//		
//		//
//		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
//		
//		/****************************
//		* 3. Add listeners => OnClick
//		****************************/
//		//
//		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));
		
		//
		//dlg.show();
		
	}//public static void dlg_word_list(Activity actv, Text text)

	private static List<Word>
	dlg_word_list__2_GetWordList(Activity actv, Text text) {
		// TODO Auto-generated method stub
		List<Word> wList = new ArrayList<Word>();
		
		/***************************************
		 * Build: Word id list
		 ***************************************/
		long remoteDbId = text.getRemoteDbId();
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();
		
		Cursor c = rdb.query(
				CONS.DB.tname_word_list,
				null,
				"text_id like ?",
				new String[]{String.valueOf(remoteDbId)},
				null, null, null);

		// Log
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "c.getCount()=" + c.getCount());
		
		// Log
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "remoteDbId=" + remoteDbId);
		
		/***************************************
		 * Got any item?
		 ***************************************/
		if (c.getCount() < 1) {
			
			// Log
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "c < 1");
			
			rdb.close();
			
			return null;
			
		}//if (c == null || c.getCount() < 1)
		
		/***************************************
		 * Build: List: Word id list
		 ***************************************/
		c.moveToFirst();
		
		List<Long> wordIdList = new ArrayList<Long>();
		
		for (int i = 0; i < c.getCount(); i++) {
			
			wordIdList.add(Long.valueOf(c.getLong(4)));
			
			c.moveToNext();
			
		}//for (int i = 0; i < c.getCount(); i++)
		
		// Log
		Log.d("Methods_dlg.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "wordIdList.size()=" + wordIdList.size());
		
		/***************************************
		 * Build: List: Word list
		 ***************************************/
//		wList
		for (int i = 0; i < wordIdList.size(); i++) {
			
			long wordId = wordIdList.get(i).longValue();
			
			Word w = dbu.get_WordFromDbId(actv, wordId);
			
			// Log
			Log.d("Methods_dlg.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "w.getW1()=" + w.getW1());
			
			wList.add(w);
			
			
		}//for (int i = 0; i < wordIdList.size(); i++)
		
		/***************************************
		 * Finish
		 ***************************************/
		rdb.close();
		
		return wList;
		
	}//dlg_word_list__2_GetWordList(Activity actv)
	

	private static Dialog
	dlg_word_list__1_GetDialog(Activity actv) {
		// TODO Auto-generated method stub
		Dialog dlg = new Dialog(actv);
		
		//
		dlg.setContentView(R.layout.dlg_word_list);
		
		// Title
		dlg.setTitle(R.string.dlg_word_list_title);
		
		/****************************
		* 2. Add listeners => OnTouch
		****************************/
		//
		Button btn_ok = (Button) dlg.findViewById(R.id.dlg_word_list_xml_bt_ok);
		
		//
		btn_ok.setTag(Tags.DialogTags.dlg_generic_dismiss);
		
		//
		btn_ok.setOnTouchListener(new DialogButtonOnTouchListener(actv, dlg));
		
		/****************************
		* 3. Add listeners => OnClick
		****************************/
		//
		btn_ok.setOnClickListener(new DialogButtonOnClickListener(actv, dlg));

		/***************************************
		 * Return
		 ***************************************/
		return dlg;
		
	}//dlg_word_list__1_GetDialog(Activity actv)

}//public class Methods_dialog
