package cr5.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cr5.adapters.SenAdapter;
import cr5.adapters.TLAdapter;
import cr5.items.Text;
import cr5.items.Word;

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
			"text_id", "text_ids", "lang_id",
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
			"text_id", "text_ids", "lang_id",
			// 9
			"remote_db_id",
			// 10					11
			"created_at_mill", "updated_at_mill"
		};
		
		/***************************************
		 * Table: word_list
		 ***************************************/
		public static final String tname_word_list = "word_list";
		
		public static final String[] cols_word_list = {
			// 0		1			2
			"text_id", "word_id", "lang_id",
			// 3
			"remote_db_id",
			// 4					5
			"created_at_mill", "updated_at_mill"
		};
		
		public static final String[] col_types_word_list = {
			// 0		1			2
			"INTEGER", "INTEGER", "INTEGER",
			// 3
			"INTEGER",
			// 4			5
			"INTEGER", "INTEGER"
		};
		
		public static final String[] cols_word_list_full = {
			// 0
			android.provider.BaseColumns._ID,
			// 1			2
			"created_at", "modified_at",
			// 3			4		5
			"text_id", "word_id", "lang_id",
			// 6
			"remote_db_id",
			// 7					8
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
		 * Table: updates_texts
		 ***************************************/
		public static final String tname_Updates_Texts= "updates_texts";
		
		public static final String[] cols_Updates_Texts = {
			// 0				1
			"num_of_items", "item_ids",
			// 2					3
			"created_at_mill", "updated_at_mill"
		};
		
		public static final String[] col_types_Updates_Texts = {
			// 0		1
			"INTEGER", "INTEGER",
			// 2		3
			"INTEGER", "INTEGER"
		};
		
		public static final String[] cols_Updates_Texts_full = {
			// 0
			android.provider.BaseColumns._ID,
			// 1				2
			"created_at", "modified_at",
			// 3				4
			"num_of_items", "item_ids",
			// 5					6
			"created_at_mill", "updated_at_mill"

		};
		
		/***************************************
		 * Table: updates_words
		 ***************************************/
		public static final String tname_Updates_Words= "updates_words";
		
		public static final String[] cols_Updates_Words = {
			// 0				1
			"num_of_items", "item_ids",
			// 2					3
			"created_at_mill", "updated_at_mill"
		};
		
		public static final String[] col_types_Updates_Words = {
			// 0		1
			"INTEGER", "INTEGER",
			// 2		3
			"INTEGER", "INTEGER"
		};
		
		public static final String[] cols_Updates_Words_full = {
			// 0
			android.provider.BaseColumns._ID,
			// 1				2
			"created_at", "modified_at",
			// 3				4
			"num_of_items", "item_ids",
			// 5					6
			"created_at_mill", "updated_at_mill"
			
		};
		
		
		/***************************************
		 * Table: updates_word_list
		 ***************************************/
		public static final String tname_Updates_WordList= "updates_word_list";
		
		public static final String[] cols_Updates_WordList = {
			// 0				1
			"num_of_items", "item_ids",
			// 2					3
			"created_at_mill", "updated_at_mill"
		};
		
		public static final String[] col_types_Updates_WordList = {
			// 0		1
			"INTEGER", "INTEGER",
			// 2		3
			"INTEGER", "INTEGER"
		};
		
		public static final String[] cols_Updates_WordList_full = {
			// 0
			android.provider.BaseColumns._ID,
			// 1				2
			"created_at", "modified_at",
			// 3				4
			"num_of_items", "item_ids",
			// 5					6
			"created_at_mill", "updated_at_mill"
			
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

		/***************************************
		 * Table: words
		 ***************************************/
		public static final String tname_Words = "words";
		
//	    t.integer  "text_id"
//	    t.string   "text_ids"
//	    t.integer  "lang_id"
//	    t.datetime "created_at", :null => false
//	    t.datetime "updated_at", :null => false

		public static final String[] cols_Words = {
			"w1",	"w2",	"w3",
			"text_id", "text_ids", "lang_id",
			"remoteDBId"
			
		};
		
		public static final String[] col_types_Words = {
			"TEXT", "TEXT", "TEXT",
			"INTEGER", "TEXT",		"INTEGER",
			"INTEGER"
			
		};

		public static final String[] cols_Words_full = {
			android.provider.BaseColumns._ID,
			"created_at", "modified_at",
			"w1", "w2", "w3",
			"text_id", "text_ids", "lang_id",
			"remoteDBId"
		};
		
	}//public static class DB
	
	public static class ReturnValue {
		
		public static final int RETURN_OK = 60;

		public static final int RETURN_ERROR = -60;
		public static final int RETURN_EXECUTION_ABORTED = -61;
		public static final int RETURN_EXCEPTION = -62;
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
		
		public static final String remoteUrl_Texts =
								"http://cosmos-cr6.herokuapp.com/texts.json";
		public static final String remoteUrl_Words =
								"http://cosmos-cr6.herokuapp.com/words.json";
		public static final String remoteUrl_WordList =
								"http://cosmos-cr6.herokuapp.com/word_lists.json";
	}

	public static class ActvMain {
		
		public static List<Text> textList = null;
		
		public static TLAdapter adpTL = null;
		
	}
	public static class ActvRead {
	
		public static final int GET_INTENT_FAILED	= -10;

		public static final int GET_DBID_FAILED		= -20;
		
		public static final int GET_TEXT_SUCCESSFUL	= 10;
		
		public static final int GET_TEXT_FAILED		= -30;
		
		public static Text text			= null;
		
		public static SenAdapter adpSen	= null;

		public static TextToSpeech tts	= null;
		
		public static List<Word> wList	= null;
		
	}
	
	public static class FTP {
		/***************************************
		 * Return values: Erros and exceptions
		 ***************************************/
		public static final int CONNECT_EXCEPTION			= -1;
		
		public static final int TASK_RETRUN_FAILED			= -10;
		public static final int TASK_RETRUN_LOGIN_FAILED	= -20;
		
		public static final int TASK_DOWNLOAD_FAILED		= -11;
		public static final int TASK_DOWNLOAD_LOGIN_FAILED	= -21;
		
		/***************************************
		 * Return values: Successfuls
		 ***************************************/
		public static final int TASK_RETURN_SUCCEESSFUL		= 1;
		public static final int TASK_DOWNLOAD_SUCCEESSFUL	= 2;
		
		/***************************************
		 * Task ID codes
		 ***************************************/
		public static final int TASK_UPLOAD_DB_FILE		= 20;
		public static final int TASK_DOWNLOAD_DB_FILE	= 21;
	}
}//public class CONS
