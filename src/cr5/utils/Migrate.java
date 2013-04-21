package cr5.utils;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.widget.Toast;

public class Migrate {

	public static void _20130421_115608_ResetTableTexts(Activity actv, Dialog dlg1) {
		
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
				Log.d("Migrate.java" + "["
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
				Log.d("Migrate.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2].getMethodName()
						+ "]",
						"Sorry. Table creation failed. Now you don't have the table:"
								+ CONS.DB.tname_texts);
				
			}//if (res == true)
		
	}//public static void _20130421_115608_ResetTableTexts()
	
	public static void _20130421_120721_ResetTable_Words(Activity actv, Dialog dlg1) {
		
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
			Log.d("Migrate.java" + "["
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
			Log.d("Migrate.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Sorry. Table creation failed. Now you don't have the table:"
							+ CONS.DB.tname_words);
			
		}//if (res == true)
		
		
	}//public static void _20130421_120721_ResetTable_Words()

	public static void
	_20130421_131922_CreateTable_Word_list
	(Activity actv, Dialog dlg1) {
		// TODO Auto-generated method stub
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);

		boolean res = dbu.createTable(
							CONS.DB.tname_word_list,
							CONS.DB.cols_word_list,
							CONS.DB.col_types_word_list);
		
		if (res == true) {
			
			// debug
			Toast.makeText(actv,
					"Table reset => " + CONS.DB.tname_words,
					Toast.LENGTH_LONG).show();
			
			// Log
			Log.d("Migrate.java" + "["
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
			Log.d("Migrate.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Sorry. Table creation failed. Now you don't have the table:"
							+ CONS.DB.tname_words);
			
		}//if (res == true)
		
	}//_20130421_131922_CreateTable_Word_list

}//public class Migrate
