package cr5.utils;



import java.util.ArrayList;
import java.util.List;

import cr5.items.Text;

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
						"Insertion => Successful: Db id=" + t.getDbId());
				
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
		
		val.put("genreId", t.getGenreId());
		val.put("subGenreId", t.getSubGenreId());
		val.put("dbId", t.getDbId());
		val.put("langId", t.getLangId());
		
		val.put("memo", t.getMemo());

		return val;
		
	}//insertData_Text__1_getContentValues(Text t)

}//public class DBUtils

