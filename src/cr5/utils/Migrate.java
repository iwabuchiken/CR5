package cr5.utils;

import android.app.Activity;
import android.util.Log;
import cr5.main.ActvMain;

public class Migrate {

	public static boolean
	_20130419_100053_CreateTableWord(Activity actv) {
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		boolean res = dbu.createTable(
							CONS.DB.tname_Words,
							CONS.DB.cols_Words,
							CONS.DB.col_types_Words);
		
		// Log
		Log.d("Migrate.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		return res;
		
	}//_20130419_100053_CreateTableWord(Activity actv)

	public static boolean
	_20130419_102817_DropTableWord(Activity actv) {

		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);

		boolean res = dbu.dropTable(actv, CONS.DB.tname_Words);

		// Log
		Log.d("Migrate.java" + "["
			+ Thread.currentThread().getStackTrace()[2].getLineNumber()
			+ ":"
			+ Thread.currentThread().getStackTrace()[2].getMethodName()
			+ "]", "res=" + res);

		return res;

	}//_20130419_102817_DropTableWord(Activity actv)
	
}//public class Migrate
