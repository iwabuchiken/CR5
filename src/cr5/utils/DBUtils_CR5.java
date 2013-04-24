package cr5.utils;



import java.util.ArrayList;
import java.util.List;

import cr5.items.Text;
import cr5.items.Word;
import cr5.items.WordList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
//import android.view
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/****************************************
 * Copy & pasted from => C:\WORKS\WORKSPACES_ANDROID\ShoppingList\src\shoppinglist\main\DBUtils.java
 ****************************************/
public class DBUtils_CR5 extends SQLiteOpenHelper{

	/*****************************************************************
	 * Class fields
	 *****************************************************************/
	 // DB name
	static String dbName = null;
	
	// Activity
	Activity activity;
	
	//
	Context context;

	/*********************************
	 * DB
	 *********************************/
	// Database
	SQLiteDatabase db = null;

	/*****************************************************************
	 * Constructor
	 *****************************************************************/
	public DBUtils_CR5(Context context, String dbName) {
		super(context, dbName, null, 1);
		
		// Initialize activity
		this.activity = (Activity) context;
		
		this.context = context;
		
		this.dbName = dbName;
		
	}//public DBUtils(Context context)

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	public boolean insertData_Text(Activity actv, Text t) {
		// TODO Auto-generated method stub
		/*----------------------------
		* 1. Insert data
		----------------------------*/
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// ContentValues
//			ContentValues val = new ContentValues();
			ContentValues val = insertData_Text__1_getContentValues(t);

			
//			val.put(android.provider.BaseColumns._ID, ai.getDb_id());
			
//			val.put("created_at", t.getCreatedAt());
//			val.put("modified_at", t.getModifiedAt());

			
			
			// Insert data
			long res = wdb.insert(CONS.DB.tname_texts, null, val);
			
			if (res != -1) {
			
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Insertion => Successful: Db id=" + t.getRemoteDbId());
				
				// Set as successful
				wdb.setTransactionSuccessful();
				
				// End transaction
				wdb.endTransaction();
				
//				// Log
//				Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Data inserted => " + "(file_name => " + val.getAsString("file_name") + "), and others");

				wdb.close();
				
				return true;

			} else {//if (res != -1)
				
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Insertion failed");
				
				wdb.close();
				
				return false;
				
			}//if (res != -1)
//			
//			
//			// Set as successful
//			wdb.setTransactionSuccessful();
//			
//			// End transaction
//			wdb.endTransaction();
//			
////			// Log
////			Log.d("DBUtils.java" + "["
////				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
////				+ "]", "Data inserted => " + "(file_name => " + val.getAsString("file_name") + "), and others");
//			
//			return true;
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", e.toString());
			
			return false;
		}//try
		
	}//public boolean insertData_Text(Activity actv, Text t)

	private ContentValues
	insertData_Text__1_getContentValues(Text t) {
		// TODO Auto-generated method stub
		ContentValues val = new ContentValues();
		
//		android.provider.BaseColumns._ID,
//		"created_at", "modified_at",
//		"text", "url",
//		"genreId", "subGenreId", "dbId", "langId",
//		"memo"
		
		val.put("created_at", t.getCreatedAt());
		val.put("modified_at", t.getModifiedAt());

		val.put("text", t.getText());
		val.put("url", t.getUrl());
		val.put("title", t.getTitle());
		val.put("memo", t.getMemo());
		
		val.put("genreId", t.getGenreId());
		val.put("subGenreId", t.getSubGenreId());
		val.put("langId", t.getLangId());
		
		val.put("word_ids", t.getWordIds());
		
		val.put("remote_db_id", t.getRemoteDbId());
		
		
		val.put("created_at_mill", t.getCreatedAt_mill());
		val.put("updated_at_mill", t.getUpdatedAt_mill());
		

		
		return val;
		
	}//insertData_Text__1_getContentValues(Text t)

	
	public boolean
	storeData_History
	(Activity actv, 
			int numOfStoredItems, String idsString,
			long lastCreatedAt, long lastUpdatedAt) {
		// TODO Auto-generated method stub
		/*----------------------------
		* 1. Insert data
		----------------------------*/
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// ContentValues
//			ContentValues val = new ContentValues();
			ContentValues val =
					insertData_History__1_getContentValues(
								numOfStoredItems,
								idsString,
								lastCreatedAt,
								lastUpdatedAt
					);
			
//			val.put(android.provider.BaseColumns._ID, ai.getDb_id());
			
//			val.put("created_at", t.getCreatedAt());
//			val.put("modified_at", t.getModifiedAt());

			
			
			// Insert data
//			long res = wdb.insert(CONS.DB.tname_texts, null, val);
//			long res = wdb.insert(CONS.DB.tname_RefreshHistory, null, val);
			long res = wdb.insert(CONS.DB.tname_Updates_Texts, null, val);
			
			if (res != -1) {
			
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Insertion => Successful: idsString=" + idsString);
				
				// Set as successful
				wdb.setTransactionSuccessful();
				
				// End transaction
				wdb.endTransaction();
				
				wdb.close();
				
				return true;

			} else {//if (res != -1)
				
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Insertion failed");
				
				wdb.close();
				
				return false;
				
			}//if (res != -1)

		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", e.toString());
			
			return false;
		}//try
	}//storeData_History()

	public boolean
	storeData_History
	(Activity actv,
			String tableName,
			int numOfStoredItems, String idsString,
			long lastCreatedAt, long lastUpdatedAt) {
		// TODO Auto-generated method stub
		/*----------------------------
		 * 1. Insert data
		----------------------------*/
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// ContentValues
//			ContentValues val = new ContentValues();
			ContentValues val =
					insertData_History__1_getContentValues(
							numOfStoredItems,
							idsString,
							lastCreatedAt,
							lastUpdatedAt
							);
			
//			val.put(android.provider.BaseColumns._ID, ai.getDb_id());
			
//			val.put("created_at", t.getCreatedAt());
//			val.put("modified_at", t.getModifiedAt());
			
			
			
			// Insert data
//			long res = wdb.insert(CONS.DB.tname_texts, null, val);
//			long res = wdb.insert(CONS.DB.tname_RefreshHistory, null, val);
//			long res = wdb.insert(CONS.DB.tname_Updates_Texts, null, val);
			long res = wdb.insert(tableName, null, val);
			
			if (res != -1) {
				
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
								+ ":"
								+ Thread.currentThread().getStackTrace()[2]
										.getMethodName() + "]",
										"Insertion => Successful: idsString=" + idsString);
				
				// Set as successful
				wdb.setTransactionSuccessful();
				
				// End transaction
				wdb.endTransaction();
				
				wdb.close();
				
				return true;
				
			} else {//if (res != -1)
				
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
								+ ":"
								+ Thread.currentThread().getStackTrace()[2]
										.getMethodName() + "]", "Insertion failed");
				
				wdb.close();
				
				return false;
				
			}//if (res != -1)
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
			
			return false;
		}//try
	}//storeData_History()

	private ContentValues
	insertData_History__1_getContentValues
	(int numOfStoredItems, String idsString, long lastCreatedAt, long lastUpdatedAt) {
		ContentValues val = new ContentValues();

//		android.provider.BaseColumns._ID,
//		"created_at", "modified_at",
//		"num_of_items", "item_ids", "createdAt_mill"
		
		val.put("created_at", Methods.getMillSeconds_now());
		val.put("modified_at", Methods.getMillSeconds_now());

		val.put("num_of_items", numOfStoredItems);
		val.put("item_ids", idsString);
		
		val.put("created_at_mill", lastCreatedAt);
		val.put("updated_at_mill", lastUpdatedAt);

		return val;

	}//insertData_History__1_getContentValues

	public long getLastRefreshedDate(Activity actv) {
		// TODO Auto-generated method stub
		SQLiteDatabase rdb = this.getReadableDatabase();
		
		String sql = "SELECT * FROM " + CONS.DB.tname_RefreshHistory;
		
		Cursor c = null;
		
		try {
			
			c = rdb.rawQuery(sql, null);
			
		} catch (Exception e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return -1;
		}

		/***************************************
		 * Search: The largest
		 ***************************************/
		long largestDate = -1;
		
		while(c.moveToNext()) {
		
			long createdAt_mill = c.getLong(c.getColumnIndex("createdAt_mill"));
			
			if (createdAt_mill > largestDate) {
				
				largestDate = createdAt_mill;
				
			}//if (createdAt_mill == condition)
			
		}
		
		/***************************************
		 * Finish
		 ***************************************/
		rdb.close();
		
		return largestDate;
		
	}//public long getLastRefreshedDate(Activity actv)

	public long getLastRefreshedDate(Activity actv, String tableName) {
		// TODO Auto-generated method stub
		SQLiteDatabase rdb = this.getReadableDatabase();
		
//		String sql = "SELECT * FROM " + CONS.DB.tname_RefreshHistory;
		String sql = "SELECT * FROM " + tableName;
		
		Cursor c = null;
		
		try {
			
			c = rdb.rawQuery(sql, null);
			
		} catch (Exception e) {
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return -1;
		}
		
		/***************************************
		 * Search: The largest
		 ***************************************/
		long largestDate = -1;
		
		while(c.moveToNext()) {
			
			long createdAt_mill = c.getLong(c.getColumnIndex("created_at_mill"));
//			long createdAt_mill = c.getLong(c.getColumnIndex("createdAt_mill"));
			
			if (createdAt_mill > largestDate) {
				
				largestDate = createdAt_mill;
				
			}//if (createdAt_mill == condition)
			
		}
		
		/***************************************
		 * Finish
		 ***************************************/
		rdb.close();
		
		return largestDate;
		
	}//public long getLastRefreshedDate(Activity actv)

	
	public boolean insertData_Word(Activity actv, Word w) {
		/*----------------------------
		* 1. Insert data
		----------------------------*/
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// ContentValues
//			ContentValues val = new ContentValues();
			ContentValues val = insertData_Word__1_getContentValues(w);

			// Insert data
			long res = wdb.insert(CONS.DB.tname_words, null, val);
//			long res = wdb.insert(CONS.DB.tname_texts, null, val);
			
			if (res != -1) {
			
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Insertion => Successful: Db id=" + w.getRemoteDbId());
				
				// Set as successful
				wdb.setTransactionSuccessful();
				
				// End transaction
				wdb.endTransaction();
				
//				// Log
//				Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Data inserted => " + "(file_name => " + val.getAsString("file_name") + "), and others");

				wdb.close();
				
				return true;

			} else {//if (res != -1)
				
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Insertion failed");
				
				wdb.close();
				
				return false;
				
			}//if (res != -1)
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", e.toString());
			
			return false;
		}//try
		
	}//public boolean insertData_Word(Activity actv, Word w)

	private ContentValues
	insertData_Word__1_getContentValues(Word w) {
		ContentValues val = new ContentValues();
		
//		android.provider.BaseColumns._ID,
//		"created_at", "modified_at",
//		"text", "url",
//		"genreId", "subGenreId", "dbId", "langId",
//		"memo"
		
		val.put("created_at", w.getCreatedAt());
		val.put("modified_at", w.getModifiedAt());
		
		val.put("w1", w.getW1());
		val.put("w2", w.getW2());
		val.put("w3", w.getW3());
		
		val.put("text_ids", w.getText_ids());
		val.put("text_id", w.getText_id());
		val.put("lang_id", w.getLang_id());
		
		
		val.put("remote_db_id", w.getRemoteDbId());
		
		
		val.put("created_at_mill", w.getCreatedAt_mill());
		val.put("updated_at_mill", w.getUpdatedAt_mill());
		

		
		return val;	
		
	}//insertData_Word__1_getContentValues(Word w)

	
	public boolean
	insertData_WordList(Activity actv, WordList wl) {
		SQLiteDatabase wdb = this.getWritableDatabase();
		
		try {
			// Start transaction
			wdb.beginTransaction();
			
			// ContentValues
//			ContentValues val = new ContentValues();
			ContentValues val = insertData_WordList__1_getContentValues(wl);

			// Insert data
			long res = wdb.insert(CONS.DB.tname_word_list, null, val);
//			long res = wdb.insert(CONS.DB.tname_texts, null, val);
			
			if (res != -1) {
			
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"Insertion => Successful: Remote db id=" + wl.getRemote_db_id());
				
				// Set as successful
				wdb.setTransactionSuccessful();
				
				// End transaction
				wdb.endTransaction();
				
//				// Log
//				Log.d("DBUtils.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Data inserted => " + "(file_name => " + val.getAsString("file_name") + "), and others");

				wdb.close();
				
				return true;

			} else {//if (res != -1)
				
				// Log
				Log.d("DBUtils_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "Insertion failed");
				
				wdb.close();
				
				return false;
				
			}//if (res != -1)
			
		} catch (Exception e) {
			// Log
			Log.e("DBUtils.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", e.toString());
			
			return false;
		}//try	
		
	}//insertData_WordList(Activity actv, WordList wl)

	private ContentValues
	insertData_WordList__1_getContentValues(WordList wl) {
		ContentValues val = new ContentValues();
		
		val.put("created_at", wl.getCreated_at());
		val.put("modified_at", wl.getUpdated_at());
		
		val.put("text_id", wl.getText_id());
		val.put("word_id", wl.getWord_id());
		val.put("lang_id", wl.getLang_id());
		
		
		val.put("remote_db_id", wl.getRemote_db_id());
		val.put("created_at_mill", wl.getCreated_at_mill());
		val.put("updated_at_mill", wl.getUpdated_at_mill());
				
		return val;
	}//insertData_WordList__1_getContentValues(WordList wl)

}//public class DBUtils

