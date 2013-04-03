package cr5.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CONS {
	
	public static class DB {
		
		public static final String dbName = "cr5.db";
		
		/***************************************
		 * Table: texts
		 ***************************************/
		public static final String tname_texts = "texts";
		
		public static final String[] cols_texts = {
			"text", "url",
			"genreId", "subGenreId", "dbId", "langId",
			"memo", "createdAt_mill"
			
		};
		
		public static final String[] col_types_texts = {
			"TEXT", "TEXT",
			"INTEGER", "INTEGER", "INTEGER", "INTEGER",
			"TEXT",	"INTEGER"
		};

		public static final String[] cols_texts_full = {
			android.provider.BaseColumns._ID,
			"created_at", "modified_at",
			"text", "url",
			"genreId", "subGenreId", "dbId", "langId",
			"memo"
		};

		/***************************************
		 * Table: refresh_history
		 ***************************************/
		public static final String tname_RefreshHistory = "refresh_history";
		
		public static final String[] cols_RefreshHistory = {
			"num_of_items", "item_ids", "createdAt_mill"
		};
		
		public static final String[] col_types_RefreshHistory = {
			"INTEGER",		"TEXT",		"INTEGER"
		};

		public static final String[] cols_RefreshHistory_full = {
			android.provider.BaseColumns._ID,
			"created_at", "modified_at",
			"num_of_items", "item_ids", "createdAt_mill"
		};
		
		
		/***************************************
		 * Backup
		 ***************************************/
		public static String dpath_storageSdcard = "/mnt/sdcard-ext";
		
		public static String dpath_db = "/data/data/cr5.main/databases";
		
		public static String dpath_dbBackup = 
				dpath_storageSdcard + "/cr5_backup";

		public static String fname_dbBackupTrunk = "cr5_backup";
		public static String fname_dbBackupExt = ".bk";

		
	}
	
	public static class ReturnValue {
		
		public static final int RETURN_OK = 60;
		public static final int RETURN_ERROR = -60;
	}
	
	public static class Task_GetTexts {
		
		public static final int EXECUTE_POST_NULL = -1;
		
		public static final int HTTP_RESPONSE_NULL = -2;
		
		public static final int EXCEPTION_PARSE_JSON = -10;
		public static final int EXCEPTION_JSON = -11;
		
		public static final int EXCEPTION_IO = -20;
		
		public static final int BUILD_JSONARRAY_FAILED = -30;
		
		public static final int JSONARRAY_LENGTH_0 = -40;
		
//		public static final int STORE_DATA_SUCCESSFUL = 10;
		public static final int STORE_DATA_SUCCESSFUL_WITH_HISTORY = 10;
		public static final int STORE_DATA_SUCCESSFUL_NO_HISTORY = 11;
		
		public static final int STORE_DATA_PARTIAL_WITH_HISTORY = 15;
		public static final int STORE_DATA_PARTIAL_NO_HISTORY = 16;
		
		public static final int STORE_DATA_FAILED = -50;
		
	}
	
	public static class HTTP_Response {
		
		public static final int OK = 200;
		
		public static final int CREATED = 201;
		
		public static final int NOT_CREATED = -201;
		
		
		
	}

}//public class CONS
