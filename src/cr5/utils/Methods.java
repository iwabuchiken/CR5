package cr5.utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import android.os.AsyncTask;

// Apache
import org.apache.commons.lang.StringUtils;

public class Methods {

	static int counter;		// Used => sortFileList()
	
	
	/****************************************
	 * Vars
	 ****************************************/
	public static final int vibLength_click = 35;

	static int tempRecordNum = 20;

	/****************************************
	 * Methods
	 ****************************************/
	public static void sort_list_files(File[] files) {
		// REF=> http://android-coding.blogspot.jp/2011/10/sort-file-list-in-order-by-implementing.html
		/****************************
		 * 1. Prep => Comparator
		 * 2. Sort
			****************************/
		
		/****************************
		 * 1. Prep => Comparator
			****************************/
		Comparator<? super File> filecomparator = new Comparator<File>(){
			
			public int compare(File file1, File file2) {
				/****************************
				 * 1. Prep => Directory
				 * 2. Calculate
				 * 3. Return
					****************************/
				
				int pad1=0;
				int pad2=0;
				
				if(file1.isDirectory())pad1=-65536;
				if(file2.isDirectory())pad2=-65536;
				
				int res = pad2-pad1+file1.getName().compareToIgnoreCase(file2.getName());
				
				return res;
			} 
		 };//Comparator<? super File> filecomparator = new Comparator<File>()
		 
		/****************************
		 * 2. Sort
			****************************/
		Arrays.sort(files, filecomparator);

	}//public static void sort_list_files(File[] files)

	public static boolean
	clearPref (Activity actv,String prefName) {

		SharedPreferences prefs = 
						actv.getSharedPreferences(
								prefName,
								actv.MODE_PRIVATE);
		
		/****************************
		* 2. Get editor
		****************************/
		SharedPreferences.Editor editor = prefs.edit();
		
		/****************************
		* 3. Clear
		****************************/
		try {
		
			editor.clear();
			editor.commit();
			
			// Log
			Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Prefs cleared");
			
			return true;
		
		} catch (Exception e) {
		
			// Log
			Log.e("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Excption: " + e.toString());
			
			return false;
		}

	}//public static boolean clear_prefs_current_path(Activity actv, Strin newPath)

	
	/*********************************
	 * <Return>
	 * -1	=> Not detected
	 *********************************/
	private static int get_position_from_array(String[] base_array,
			String target_string) {
		/*********************************
		 * memo
		 *********************************/
		int position;
		
		for (position = 0; position < base_array.length; position++) {
			
			if (base_array[position].equals(target_string)) {
				
				return position;
				
			}//if (base_array[i] == condition)
			
		}//for (int position = 0; position < base_array.length; position++)
		
		
		return -1;
	}//private static int get_position_from_array()

	public static String convert_path_into_table_name(Activity actv, String newPath) {
		/****************************
		 * Steps
		 * 1. Get table name => Up to the current path
		 * 2. Add name => Target folder name
			****************************/
		String tableName = null;
		StringBuilder sb = new StringBuilder();

			
//		String[] currentPathArray = convert_prefs_into_path_label(actv).split(File.separator);
		String[] currentPathArray = newPath.split(File.separator);
		
		if (currentPathArray.length > 1) {
			
			tableName = StringUtils.join(currentPathArray, "__");
			
		} else {//if (currentPathArray.length > 1)
			
			sb.append(currentPathArray[0]);
			
			tableName = sb.toString();
			
		}//if (currentPathArray.length > 1)
		
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "tableName => " + tableName);
		
		
		return tableName;
	}//public static String convert_path_into_table_name(Activity actv)

	private static long getFileLength(String fileFullPath) {
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "Methods: " + Thread.currentThread().getStackTrace()[2].getMethodName());
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "File path=" + fileFullPath);
		
		MediaPlayer mp = new MediaPlayer();
		
//		int len = 0;
		long len = 0;
		
		try {
			mp.setDataSource(fileFullPath);
			
			mp.prepare();
			
//			len = mp.getDuration() / 1000;
			len = mp.getDuration();
			
			// REF=> http://stackoverflow.com/questions/9609479/android-mediaplayer-went-away-with-unhandled-events
			mp.reset();
			
			// REF=> http://stackoverflow.com/questions/3761305/android-mediaplayer-throwing-prepare-failed-status-0x1-on-2-1-works-on-2-2
			mp.release();
			
		} catch (IllegalArgumentException e) {
			
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());
			
		} catch (IllegalStateException e) {
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());

		} catch (IOException e) {
			// Log
			Log.d("Methods.java"
					+ "["
					+ Thread.currentThread().getStackTrace()[2]
							.getLineNumber() + "]", "Exception=" + e.toString());
		}//try
		
		return len;
	}//private static long getFileLength(String fileFullPath)

	/****************************
	 * deleteDirectory(File target)()
	 * 
	 * 1. REF=> http://www.rgagnon.com/javadetails/java-0483.html
		****************************/
	public static boolean deleteDirectory(File target) {
		
		if(target.exists()) {
			//
			File[] files = target.listFiles();
			
			//
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					
					deleteDirectory(files[i]);
					
				} else {//if (files[i].isDirectory())
					
					String path = files[i].getAbsolutePath();
					
					files[i].delete();
					
					// Log
					Log.d("Methods.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber() + "]", "Removed => " + path);
					
					
				}//if (files[i].isDirectory())
				
			}//for (int i = 0; i < files.length; i++)
			
		}//if(target.exists())
		
		return (target.delete());
	}//public static boolean deleteDirectory(File target)

	public static long getMillSeconds(int year, int month, int date) {
		// Calendar
		Calendar cal = Calendar.getInstance();
		
		// Set time
		cal.set(year, month, date);
		
		// Date
		Date d = cal.getTime();
		
		return d.getTime();
		
	}//private long getMillSeconds(int year, int month, int date)

	/****************************************
	 *	getMillSeconds_now()
	 * 
	 * <Caller> 
	 * 1. ButtonOnClickListener # case main_bt_start
	 * 
	 * <Desc> 1. <Params> 1.
	 * 
	 * <Return> 1.
	 * 
	 * <Steps> 1.
	 * 
	 ****************************************/
	public static long getMillSeconds_now() {
		
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime().getTime();
		
	}//private long getMillSeconds_now(int year, int month, int date)

	public static String get_TimeLabel(long millSec) {

		//		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.JAPAN);
		 
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

	public static boolean restore_db(Activity actv, String dbName,
				String src, String dst) {
		/*********************************
		 * 1. Setup db
		 * 2. Setup: File paths
		 * 3. Setup: File objects
		 * 4. Copy file
		 * 
		 *********************************/
    	// Setup db
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();

		wdb.close();

		/*********************************
		 * 2. Setup: File paths
		 *********************************/
//    	String src = 
//    			"/mnt/sdcard-ext/ShoppingList_backup/shoppinglist_backup_20120906_201402.bk";
//    			"/mnt/sdcard-ext/CR4_backup/cr4_backup_20120907_184555.bk";

    	
//    	String dst =
////    			"/data/data/test.main/databases/shoppinglist.db";
//    			"/data/data/cr4.main/databases/cr4.db";

    	/*********************************
		 * 3. Setup: File objects
		 *********************************/
		File f_src = new File(src);
		File f_dst = new File(dst);

		/*********************************
		 * 4. Copy file
		 *********************************/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied: " + src);
			
			// debug
			Toast.makeText(actv, "DB restoration => Done", Toast.LENGTH_LONG).show();
			
			return true;

		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
			return false;
			
		}//try
		
	}//private boolean restore_db()

	public static void restore_db(Activity actv) {
    	
//    	// Log
//		Log.d("MainActv.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Starting: restore_db()");

		/*********************************
		 * Get the absolute path of the latest backup file
		 *********************************/
		// Get the most recently-created db file
//		String src_dir = "/mnt/sdcard-ext/IFM9_backup";
		String src_dir = CONS.DB.dpath_dbBackup;
		
		File f_dir = new File(src_dir);
		
		File[] src_dir_files = f_dir.listFiles();
		
		// If no files in the src dir, quit the method
		if (src_dir_files.length < 1) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "No files in the dir: " + src_dir);
			
			return;
			
		}//if (src_dir_files.length == condition)
		
		// Latest file
		File f_src_latest = src_dir_files[0];
		
		
		for (File file : src_dir_files) {
			
			if (f_src_latest.lastModified() < file.lastModified()) {
						
				f_src_latest = file;
				
			}//if (variable == condition)
			
		}//for (File file : src_dir_files)
		
		// Show the path of the latest file
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "f_src_latest=" + f_src_latest.getAbsolutePath());
		
		/*********************************
		 * Restore file
		 *********************************/
		String src = f_src_latest.getAbsolutePath();
		String dst = StringUtils.join(
				new String[]{CONS.DB.dpath_db, CONS.DB.dbName},
				File.separator);
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "dst=" + dst);
		
		boolean res = Methods.restore_db(actv, CONS.DB.dbName, src, dst);
		
		// Log
		Log.d("MainActv.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "res=" + res);
		
	}//private void restore_db()

	public static String[] get_column_list(Activity actv, String dbName, String tableName) {
		/*********************************
		 * 1. Set up db
		 * 2. Cursor null?
		 * 3. Get names
		 * 
		 * 4. Close db
		 * 5. Return
		 *********************************/
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase rdb = dbu.getReadableDatabase();

		//=> source: http://stackoverflow.com/questions/4681744/android-get-list-of-tables : "Just had to do the same. This seems to work:"
		String q = "SELECT * FROM " + tableName;
		
		/*********************************
		 * 2. Cursor null?
		 *********************************/
		Cursor c = null;
		
		try {
			c = rdb.rawQuery(q, null);
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "c.getCount(): " + c.getCount());

		} catch (Exception e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception => " + e.toString());
			
			rdb.close();
			
			return null;
		}
		
		/*********************************
		 * 3. Get names
		 *********************************/
		String[] column_names = c.getColumnNames();
		
		/*********************************
		 * 4. Close db
		 *********************************/
		rdb.close();
		
		/*********************************
		 * 5. Return
		 *********************************/
		return column_names;
		
//		return null;
	}//public static String[] get_column_list(Activity actv, String tableName)

    public static void drop_table(Activity actv, String dbName, String tableName) {
    	// Setup db
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		boolean res = 
				dbu.dropTable(actv, wdb, tableName);
		
		if (res == true) {
			// Log
			Log.d("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Table dropped: " + tableName);
		} else {//if (res == true)

			// Log
			Log.e("MainActv.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Drop table => Failed: " + tableName);
			
		}//if (res == true)
		

		wdb.close();
		
		
	}//private void drop_table(String tableName)

	public static boolean add_column_to_table(Activity actv, String dbName,
			String tableName, String column_name, String data_type) {
		/*********************************
		 * 1. Column already exists?
		 * 2. db setup
		 * 
		 * 3. Build sql
		 * 4. Exec sql
		 * 
		 * 5. Close db
		 *********************************/
		/*********************************
		 * 1. Column already exists?
		 *********************************/
		String[] cols = Methods.get_column_list(actv, dbName, tableName);
		
//		//debug
//		for (String col_name : cols) {
//
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "col: " + col_name);
//			
//		}//for (String col_name : cols)

		
		for (String col_name : cols) {
			
			if (col_name.equals(column_name)) {
				
//				// debug
//				Toast.makeText(actv, "Column exists: " + column_name, Toast.LENGTH_SHORT).show();
				
//				// Log
//				Log.d("Methods.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber() + "]", "Column exists: " + column_name);
				
				return false;
				
			}
			
		}//for (String col_name : cols)
		
		// debug
		Toast.makeText(actv, "Column doesn't exist: " + column_name, Toast.LENGTH_SHORT).show();
		
		/*********************************
		 * 2. db setup
		 *********************************/
		DBUtils dbu = new DBUtils(actv, dbName);
		
		SQLiteDatabase wdb = dbu.getWritableDatabase();
		
		/*********************************
		 * 3. Build sql
		 *********************************/
		// REF[20121001_140817] => http://stackoverflow.com/questions/8291673/how-to-add-new-column-to-android-sqlite-database
		
		String sql = "ALTER TABLE " + tableName + 
					" ADD COLUMN " + column_name + 
					" " + data_type;
		
		/*********************************
		 * 4. Exec sql
		 *********************************/
		try {
//			db.execSQL(sql);
			wdb.execSQL(sql);
			
			// Log
			Log.d(actv.getClass().getName() + 
					"["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Column added => " + column_name);

			/*********************************
			 * 5. Close db
			 *********************************/
			wdb.close();
			
			return true;
			
		} catch (SQLException e) {
			// Log
			Log.d(actv.getClass().getName() + 
					"[" + Thread.currentThread().getStackTrace()[2].getLineNumber() + "]", 
					"Exception => " + e.toString());
			
			/*********************************
			 * 5. Close db
			 *********************************/
			wdb.close();

			return false;
		}//try

		/*********************************
		 * 5. Close db
		 *********************************/


		
	}//public static boolean add_column_to_table()

	/*********************************
	 * <Notes>
	 * 1. File names => Sorted alphabetico-ascendantly
	 * 
	 * <Return>
	 * null		=> File "dpath" doesn't exist
	 *********************************/
	public static List<String> get_file_list(File dpath) {
		/*********************************
		 * 1. Directory exists?
		 * 2. Build list
		 * 2-1. Sort list
		 * 
		 * 3. Return
		 *********************************/
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Methods.get_file_list(File dpath)");
		
		/*********************************
		 * 1. Directory exists?
		 *********************************/
		if (!dpath.exists()) {
			
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Dir doesn't exist");
			
			return null;
			
		} else {//if (!dpath.exists() == condition)
			
//			// Log
//			Log.d("Methods.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "Dir exists: " + dpath.getAbsolutePath());
			
		}//if (!dpath.exists() == condition)
		
		/*********************************
		 * 2. Build list
		 *********************************/
		List<String> list_dir = new ArrayList<String>();
		
		File[] files_list = dpath.listFiles();
		
		/*********************************
		 * 2-1. Sort list
		 *********************************/
		Methods.sort_list_files(files_list);
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "Starts => get_file_list()");
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]",
//				"dpath=" + dpath.getAbsolutePath()
//				+ " // size=" + files_list.length);
		
		
		for (File f : files_list) {
			
			list_dir.add(f.getName());
			
		}//for (File f : files_list)
		
		/*********************************
		 * 3. Return
		 *********************************/
		return list_dir;
		
	}//public static List<String> get_file_list(File dpath)

	public static String convert_intSec2Digits(int t) {
		
		int sec = t % 60;
		
		if (t / 60 < 1) {
			
//			return "00:00:" + String.valueOf(sec);
			return "00:00:" + Methods.convert_sec2digits(sec, 2);
			
		}//if (t / 60 < 1)
		
//		int min = (t - sec) % 60;
		int min = ((t - sec) % (60 * 60)) / 60;
		
		if ((t - sec) / (60 * 60) < 1) {
			
//			return "00:" + String.valueOf(min) + ":" + String.valueOf(sec);
			return "00:"
				+ Methods.convert_sec2digits(min, 2) + ":"
				+ Methods.convert_sec2digits(sec, 2);
			
		}//if (variable == condition)
		
//		int hour = (t - min) / 60;
		int hour = (t - sec) / (60 * 60);
				
//		return String.valueOf(hour) + ":"
//				+ String.valueOf(min) + ":"
//				+ String.valueOf(sec);

		return Methods.convert_sec2digits(min, 2) + ":"
		+ Methods.convert_sec2digits(min, 2) + ":"
		+ Methods.convert_sec2digits(sec, 2);

		
	}//public static String convert_intSec2Digits(int time)

	/***************************************
	 * 20130320_120437<br>
	 * @param t ... Value in seconds, <i>not</i> in mill seconds
	 ***************************************/
	public static String convert_intSec2Digits_lessThanHour(int t) {
		
		int sec = t % 60;
		
		if (t / 60 < 1) {
			
//			return "00:00:" + String.valueOf(sec);
//			return "00:00:" + Methods.convert_sec2digits(sec, 2);
			return "00:" + Methods.convert_sec2digits(sec, 2);
			
		}//if (t / 60 < 1)
		
//		int min = (t - sec) % 60;
		int min = ((t - sec) % (60 * 60)) / 60;
		
		return Methods.convert_sec2digits(min, 2) + ":"
			+ Methods.convert_sec2digits(sec, 2);
			
	}//public static String convert_intSec2Digits(int time)

	private static String convert_sec2digits(int sec, int i) {
		
		int current_len = String.valueOf(sec).length();
		
		if (current_len < i) {
			
			StringBuilder sb = new StringBuilder();
			
			for (int j = 0; j < i - current_len; j++) {
				
				sb.append("0");
			}
			
			sb.append(String.valueOf(sec));
			
			return sb.toString();
			
		}//if (current_len == condition)
		
		return String.valueOf(sec);
		
	}//private static String convert_sec2digits(int sec, int i)

	public static int getArrayIndex(String[] targetArray, String targetString) {
		int index = -1;
		
		for (int i = 0; i < targetArray.length; i++) {
			
			if (targetArray[i].equals(targetString)) {
				
				index = i;
				
				break;
				
			}//if (targetArray[i] == )
			
		}//for (int i = 0; i < targetArray.length; i++)
		
		return index;
	}//public static int getArrayIndex(String[] targetArray, String targetString)
	
	public static long
	convert_railsTimeLabel2MilSec(String createdAt) {
		/***************************************
		 * Get data
		 ***************************************/
		String[] dateTime = createdAt.split("T");
		
		String[] dateData = dateTime[0].split("-");
		
		String[] timeLabel = dateTime[1].split("Z");
		
		String[] timeData = timeLabel[0].split(":");
		
		/***************************************
		 * Calendar
		 ***************************************/
		Calendar cal = Calendar.getInstance();
		
		cal.set(
				Integer.parseInt(dateData[0]),
				Integer.parseInt(dateData[1]) - 1,
				Integer.parseInt(dateData[2]),
				Integer.parseInt(timeData[0]),
				Integer.parseInt(timeData[1]),
				Integer.parseInt(timeData[2])
				);
		
		return cal.getTimeInMillis();
		
	}//convert_railsTimeLabel2MilSec(String createdAt)

	public static void
	db_backup
	(Activity actv,
			Dialog dlg,
			String dbPath, String dbName,
			String dbBackupPath,
			String dbBackupNameTrunk,
			String dbBackupNameExt) {
		/****************************
		 * 1. Prep => File names
		 * 2. Prep => Files
		 * 2-2. Folder exists?
		 * 
		 * 2-3. Dst folder => Files within the limit?
		 * 3. Copy
			****************************/
		String time_label = Methods.get_TimeLabel(Methods.getMillSeconds_now());
		
		String db_src = StringUtils.join(
					new String[]{
							dbPath,
							dbName},
					File.separator);
		
		String db_dst_folder = StringUtils.join(
					new String[]{
							dbBackupPath,
							dbBackupNameTrunk},
					File.separator);
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "db_src: " + db_src + " * " + "db_dst: " + db_dst);
//		
		String db_dst = db_dst_folder + "_"
				+ time_label
//				+ MainActv.fileName_db_backup_ext;
				+ dbBackupNameExt;
//				+ MainActv.fname_db_backup_trunk;
		
//		// Log
//		Log.d("Methods.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ "]", "db_src: " + db_src + " * " + "db_dst: " + db_dst);
		
		/****************************
		 * 2. Prep => Files
			****************************/
		File src = new File(db_src);
		File dst = new File(db_dst);
		
		/****************************
		 * 2-2. Folder exists?
			****************************/
		File db_backup = new File(dbBackupPath);
		
		if (!db_backup.exists()) {
			
			try {
				db_backup.mkdir();
				
				// Log
				Log.d("Methods.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Folder created: " + db_backup.getAbsolutePath());
			} catch (Exception e) {
				
				// Log
				Log.e("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Create folder => Failed");
				
				return;
				
			}
			
		} else {//if (!db_backup.exists())
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Folder exists: ");
			
		}//if (!db_backup.exists())
		
		/*********************************
		 * 2-3. Dst folder => Files within the limit?
		 *********************************/
		File[] files_dst_folder = new File(dbBackupPath).listFiles();
		
		int num_of_files = files_dst_folder.length;
		
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "num_of_files=" + num_of_files);
		
		/****************************
		 * 3. Copy
			****************************/
		try {
			FileChannel iChannel = new FileInputStream(src).getChannel();
			FileChannel oChannel = new FileOutputStream(dst).getChannel();
			iChannel.transferTo(0, iChannel.size(), oChannel);
			iChannel.close();
			oChannel.close();
			
			// Log
			Log.d("ThumbnailActivity.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File copied");
			
			// debug
			Toast.makeText(actv, "DB backup => Done", Toast.LENGTH_LONG).show();

			dlg.dismiss();
			
		} catch (FileNotFoundException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
		} catch (IOException e) {
			// Log
			Log.e("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
		}//try
		
	}//public static void db_backup(Activity actv, Dialog dlg, String item)

	public static boolean
	columnExists(Activity actv, String dbName, String tableName, String colName) {
		
		String[] cols = Methods.get_column_list(actv, dbName, tableName);
		
		for (String col_name : cols) {
			
			if (col_name.equals(colName)) {
				
				// debug
				Toast.makeText(actv, "Column exists: " + colName, Toast.LENGTH_SHORT).show();
				
				// Log
				Log.d("Methods.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Column exists: " + colName);
				
				return true;
				
			}
			
		}//for (String col_name : cols)

		return false;
		
	}//columnExists(Activity actv, String dbName, String tableName, String colName)

	/***************************************
	 * REF=> http://www.coderanch.com/t/401142/java/java/check-String-numeric
	 ***************************************/
	public static boolean isNumeric(String label) {
	
		if (label.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
			
            return true;
            
        } else {  
            
        	return false;
        	
        } 
		
	}//public static boolean isNumeric(String label)

	public static String convert_MillSec2DateLabel(long millSec) {
		
		Date d = new Date(millSec);
		
		Calendar cal = Calendar.getInstance();
		
//		cal.setTime(d);
		cal.setTimeInMillis(millSec);
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.DAY_OF_MONTH);
		int day = cal.get(Calendar.DATE);

		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]",
				"year=" + year
				+ "/"
				+ "month=" + month
				+ "/"
				+ "day=" + day);
		
		return String.format(Locale.JAPAN, "%d-%2d-%2d", year, month, day);
		
//		return null;
	}

	public static String getTimeLabel_Japanese(long millSec) {
		
		
//		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd_HHmmss");
//		SimpleDateFormat sdf1 = new SimpleDateFormat("MM月dd日", Locale.JAPAN);
//		SimpleDateFormat sdf1 = new SimpleDateFormat("M月d日(E)", Locale.JAPAN);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yy-MM-dd", Locale.JAPAN);
		
		return sdf1.format(new Date(millSec));
		
	}//public static String get_TimeLabel(long millSec)

}//public class Methods

