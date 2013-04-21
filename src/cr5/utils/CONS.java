package cr5.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cr5.adapters.SenAdapter;
import cr5.items.Text;

import android.content.SharedPreferences;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
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
			// 0	1		2			3
			"text", "url", "title", "memo", 
			// 4			5			6
			"genreId", "subGenreId", "langId",	// 2, 3, 4, 5
			// 7
			"word_ids",
			// 8
			"remote_db_id",
			// 9					10
			"created_at_mill", "updated_at_mill",
		};
		
		public static final String[] col_types_texts = {
			// 0	1		2			3
			"TEXT", "TEXT", "TEXT", "TEXT", 
			// 4			5			6
			"INTEGER", "INTEGER", "INTEGER",	// 2, 3, 4, 5
			// 7
			"TEXT",
			// 8
			"INTEGER",
			// 9		10
			"INTEGER", "INTEGER",
		};

		public static final String[] cols_texts_full = {
			// 0
			android.provider.BaseColumns._ID,	// 0
			// 1			2
			"created_at", "modified_at",		// 1, 2
			// 3	4		5			6
			"text", "url", "title", "memo", 
			// 7			8			9
			"genreId", "subGenreId", "langId",	// 2, 3, 4, 5
			// 10
			"word_ids",
			// 11
			"remote_db_id",
			// 12					13
			"created_at_mill", "updated_at_mill",
		};

		/***************************************
		 * Table: words
		 ***************************************/
		public static final String tname_words = "words";
		
		public static final String[] cols_words = {
			// 0	1	2
			"w1", "w2", "w3",
			// 3			4			5
			"text_id", "text_ids", "langId",
			// 6
			"remote_db_id",
			// 7					8
			"created_at_mill", "updated_at_mill"
		};
		
		public static final String[] col_types_words = {
			// 0	1		2
			"TEXT", "TEXT", "TEXT",
			// 3			4			5
			"INTEGER", "TEXT",		"INTEGER",
			// 6
			"INTEGER",
			// 7			8
			"INTEGER", "INTEGER"
		};
		
		public static final String[] cols_words_full = {
			// 0
			android.provider.BaseColumns._ID,
			// 1			2
			"created_at", "modified_at",
			// 3	4	5
			"w1", "w2", "w3",
			// 6			7		8
			"text_id", "text_ids", "langId",
			// 9
			"remote_db_id",
			// 10					11
			"created_at_mill", "updated_at_mill"
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
		public static final int RETURN_EXECUTION_ABORTED = -61;
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

	public static class Admin {
	
		public static Vibrator vib = null;
		
		public static final String remoteUrl = "http://cosmos-cr6.herokuapp.com/texts.json";
	}

	public static class ActvRead {
	
		public static final int GET_INTENT_FAILED = -10;

		public static final int GET_DBID_FAILED = -20;
		
		public static final int GET_TEXT_SUCCESSFUL = 10;
		
		public static final int GET_TEXT_FAILED = -30;
		
		public static Text text = null;
		
		public static SenAdapter adpSen = null;

		public static TextToSpeech tts = null;
		
	}
}//public class CONS
