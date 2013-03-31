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
		
		public static final String tname_texts = "texts";
		
		public static final String[] cols_texts = {
			"text", "url",
			"genreId", "subGenreId", "dbId", "langId",
			"memo"
			
		};
		
		public static final String[] col_types_texts = {
			"TEXT", "TEXT",
			"INTEGER", "INTEGER", "INTEGER", "INTEGER",
			"TEXT"
		};

		public static final String[] cols_texts_full = {
			android.provider.BaseColumns._ID,
			"created_at", "modified_at",
			"text", "url",
			"genreId", "subGenreId", "dbId", "langId",
			"memo"
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
		
	}
	
	public static class HTTP_Response {
		
		public static final int OK = 200;
		
		public static final int CREATED = 201;
		
		public static final int NOT_CREATED = -201;
		
		
		
	}
	
}//public class CONS
