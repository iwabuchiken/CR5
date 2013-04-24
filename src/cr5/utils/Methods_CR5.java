package cr5.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import cr5.items.Text;
import cr5.items.Word;
import cr5.items.WordList;
import cr5.tasks.Task_GetTexts;
import cr5.tasks.Task_GetWordList;
import cr5.tasks.Task_GetWords;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class Methods_CR5 {

	public static void getTexts(Activity actv) {
		// TODO Auto-generated method stub
		
		String url = "http://cosmos-cr5.herokuapp.com/texts.json";
//		String url = "http://cosmos-cr5.herokuapp.com/texts/1";
//		String url = "http://cosmos-cr5.herokuapp.com/texts/1.json";
		
		Task_GetTexts task = new Task_GetTexts(actv);
		
		task.execute(url);
	
	}//public static void getTexts(Activity actv)

	public static void getWords(Activity actv, long dbId) {
		// TODO Auto-generated method stub
		
		String url = "http://cosmos-cr5.herokuapp.com/texts/get_word_list/"
					+ String.valueOf(dbId);
		
		// Log
		Log.d("Methods_CR5.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "url=" + url);
		
		Task_GetWords task = new Task_GetWords(actv);
		
		task.execute(url);
		
	}//public static void getTexts(Activity actv)

	public static void getTexts(Activity actv, String remoteUrl) {
		// TODO Auto-generated method stub
		
		String url = remoteUrl;
//		String url = "http://cosmos-cr5.herokuapp.com/texts.json";
//		String url = "http://cosmos-cr5.herokuapp.com/texts/1";
//		String url = "http://cosmos-cr5.herokuapp.com/texts/1.json";
		
		Task_GetTexts task = new Task_GetTexts(actv);
		
		task.execute(url);
		

	}//public static void getTexts(Activity actv)

	
	public static boolean
	storeData_Text(Activity actv, JSONObject joText) {
	
//		// Log
//		Log.d("Methods_CR5.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "joText" + joText);
		
		/***************************************
		 * Build a Text instance
		 ***************************************/
		Text t = storeData_Text__1__buildTextInstance(joText);
		
		// Log
		Log.d("Methods_CR5.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "t.getCreatedAt_mill()=" + t.getCreatedAt_mill());
		
		/***************************************
		 * Store text
		 ***************************************/
		return storeData_Text__2__StoreText(actv, t);
		
//		boolean res = storeData_Text__2__StoreText(actv, t);
//		
//		return false;
		
	}//storeData_Text(Activity actv, JSONObject joText)

	private static boolean
	storeData_Text__2__StoreText(Activity actv, Text t) {
		
		DBUtils_CR5 dbu = new DBUtils_CR5(actv, CONS.DB.dbName);
		
		return dbu.insertData_Text(actv, t);
//		boolean res = dbu.insertData_Text(actv, t);
//		
//		return false;
	}

	private static boolean
	storeData_Word__2__StoreWord(Activity actv, Word w) {
		
		DBUtils_CR5 dbu = new DBUtils_CR5(actv, CONS.DB.dbName);
		
		return dbu.insertData_Word(actv, w);
//		return dbu.insertData_Text(actv, w);

	}//storeData_Text__2__StoreText(Activity actv, Word w)


	private static
	Text storeData_Text__1__buildTextInstance(JSONObject joText) {
		
		Text t = null;
		
		try {
			
			String	createdAt		= joText.getString("created_at");
			String	modifiedAt		= joText.getString("updated_at");
			
			String	text			= joText.getString("text");
			String	url				= joText.getString("url");
			String	title			= joText.getString("title");
			String	memo			= joText.getString("memo");
			
			long	genreId			= joText.getLong("genre_id");
			long	subGenreId		= joText.getLong("subgenre_id");
			long	langId			= joText.getLong("lang_id");

			String	wordIds			= joText.getString("word_ids");
			
			long	remoteDbId		= joText.getLong("id");
			long	createdAt_mill	= joText.getLong("created_at_mill");
			long	updatedAt_mill	= joText.getLong("updated_at_mill");
			
			t = new Text.Builder()
						.setCreatedAt(Methods.convert_railsTimeLabel2MilSec(createdAt))
						.setModifiedAt(Methods.convert_railsTimeLabel2MilSec(modifiedAt))
						
						.setText(text)
						.setUrl(url)
						.setTitle(title)
						.setMemo(memo)
						
						.setGenreId(genreId)
						.setSubGenreId(subGenreId)
						.setLangId(langId)
						
						.setWordIds(wordIds)
						
						.setRemoteDbId(remoteDbId)
						.setCreatedAt_mill(createdAt_mill)
						.setUpdatedAt_mill(updatedAt_mill)
						
						.build();
			
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "createdAt=" + createdAt);
			
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "time=" + Methods.convert_railsTimeLabel2MilSec(createdAt));
			
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]",
//					"Time label="
//					+ Methods.get_TimeLabel(Methods.convert_railsTimeLabel2MilSec(createdAt)));

			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "t.getUpdatedAt_mill()=" + t.getUpdatedAt_mill());
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "joText=" + joText);
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"joText.getLong(\"updated_at_mill\")="
							+ joText.getLong("updated_at_mill"));
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "updatedAt_mill=" + updatedAt_mill);
			
			if (t != null) {
				
				// Log
				Log.d("Methods_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"t.getUrl()=" + t.getUrl());
				
			}
//			else {//if (t != null)
//				
//				// Log
//				Log.d("Methods_CR5.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "t == null");
//				
//			}//if (t != null)
			
			
			return t;
			
		} catch (JSONException e) {
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
//		return null;
	}//Text storeData_Text__1__buildTextInstance(JSONObject joText)

	private static
	Word storeData_Word__1__buildWordInstance(JSONObject joWord) {
		
		Word w = null;
		
		try {
			
			String	createdAt		= joWord.getString("created_at");
			String	modifiedAt		= joWord.getString("updated_at");
			
			String	w1				= joWord.getString("w1");
			String	w2				= joWord.getString("w2");
			String	w3				= joWord.getString("w3");
			
			String	text_ids		= joWord.getString("text_ids");
			long	text_id			= joWord.getLong("text_id");
			long	lang_id			= joWord.getLong("lang_id");
			

			long	remoteDbId		= joWord.getLong("id");
			
			long	createdAt_mill	= joWord.getLong("created_at_mill");
			long	updatedAt_mill	= joWord.getLong("updated_at_mill");
			
			w = new Word.Builder()
					.setCreatedAt(Methods.convert_railsTimeLabel2MilSec(createdAt))
					.setModifiedAt(Methods.convert_railsTimeLabel2MilSec(modifiedAt))
					
					.setW1(w1)
					.setW2(w2)
					.setW3(w3)
					
					.setText_ids(text_ids)
					.setText_id(text_id)
					.setLang_id(lang_id)
					
					.setRemoteDbId(remoteDbId)
					.setCreatedAt_mill(createdAt_mill)
					.setUpdatedAt_mill(updatedAt_mill)
					
					.build();
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "createdAt=" + createdAt);
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "time=" + Methods.convert_railsTimeLabel2MilSec(createdAt));
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Time label="
							+ Methods.get_TimeLabel(Methods.convert_railsTimeLabel2MilSec(createdAt)));
			
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "joWord=" + joWord);
			
			if (w != null) {
				
				// Log
				Log.d("Methods_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
								+ ":"
								+ Thread.currentThread().getStackTrace()[2]
										.getMethodName() + "]",
										"w.getW1()=" + w.getW1());
				
			} else {//if (w != null)
				
				// Log
				Log.d("Methods_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
								+ ":"
								+ Thread.currentThread().getStackTrace()[2]
										.getMethodName() + "]", "w == null");
				
			}//if (w != null)
			
			
			return w;
			
		} catch (JSONException e) {
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
//		return null;
	}//Word storeData_Word__1__buildWordInstance(JSONObject joWord)

	private static
	WordList storeData_WordList__1__buildWordListInstance(JSONObject joWordList) {
		
		WordList wl = null;
		
		try {
			
			String	createdAt		= joWordList.getString("created_at");
			String	modifiedAt		= joWordList.getString("updated_at");
			
			
			long	text_id			= joWordList.getLong("text_id");
			long	word_id			= joWordList.getLong("word_id");
			long	lang_id			= joWordList.getLong("lang_id");
			
			
			long	remoteDbId		= joWordList.getLong("id");
			
			long	createdAt_mill	= joWordList.getLong("created_at_mill");
			long	updatedAt_mill	= joWordList.getLong("updated_at_mill");
			
			wl = new WordList.Builder()
						.setCreated_at(
								Methods.convert_railsTimeLabel2MilSec(createdAt))
			//			.setCreatedAt(Methods.convert_railsTimeLabel2MilSec(createdAt))
						.setUpdated_at_mill(
								Methods.convert_railsTimeLabel2MilSec(modifiedAt))
			//			.setModifiedAt(Methods.convert_railsTimeLabel2MilSec(modifiedAt))
						
						.setText_id(text_id)
						.setWord_id(word_id)
						.setLang_id(lang_id)
						
						.setRemote_db_id(remoteDbId)
						.setCreated_at_mill(createdAt_mill)
						.setUpdated_at_mill(updatedAt_mill)
			//			.setCreatedAt_mill(createdAt_mill)
			//			.setUpdatedAt_mill(updatedAt_mill)
						
						.build();
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "createdAt=" + createdAt);
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "time=" + Methods.convert_railsTimeLabel2MilSec(createdAt));
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Time label="
							+ Methods.get_TimeLabel(Methods.convert_railsTimeLabel2MilSec(createdAt)));
			
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "joWordList=" + joWordList);
			
			if (wl != null) {
				
				// Log
				Log.d("Methods_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
								+ ":"
								+ Thread.currentThread().getStackTrace()[2]
										.getMethodName() + "]",
								"wl.getRemote_db_id()=" + wl.getRemote_db_id());
				
			} else {//if (w != null)
				
				// Log
				Log.d("Methods_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
								+ ":"
								+ Thread.currentThread().getStackTrace()[2]
										.getMethodName() + "]", "w == null");
				
			}//if (w != null)
			
			
			return wl;
			
		} catch (JSONException e) {
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
//		return null;
	}//Word storeData_Word__1__buildWordInstance(JSONObject joWord)
	
	private static
	Word storeData_Word__1__BuildInstance_Word(JSONObject joWord) {
		
		Word w = null;
		
		try {
			
			String	createdAt		= joWord.getString("created_at");
			String	modifiedAt		= joWord.getString("updated_at");

			String	textIds			= joWord.getString("text_ids");

//			long	langId			= joWord.getLong("lang_id");
			
			w = new Word.Builder()
						.setCreatedAt(Methods.convert_railsTimeLabel2MilSec(createdAt))
						.setModifiedAt(Methods.convert_railsTimeLabel2MilSec(modifiedAt))
						
//						.setLangId(langId)
						.build();
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "textIds=" + textIds);
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "createdAt=" + createdAt);
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "time=" + Methods.convert_railsTimeLabel2MilSec(createdAt));
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]",
					"Time label="
					+ Methods.get_TimeLabel(Methods.convert_railsTimeLabel2MilSec(createdAt)));

			return w;
			
		} catch (JSONException e) {
			
			// Log
			Log.d("Methods_CR5.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}//try
//		return null;
	}//Text storeData_Text__1__buildTextInstance(JSONObject joWord)


	public static boolean
	validateTableExists_texts(Activity actv) {
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		return dbu.createTable(
					CONS.DB.tname_texts,
					CONS.DB.cols_texts,
					CONS.DB.col_types_texts);
		
	}//validateTableExists_texts(Activity actv)

	public static boolean
	validateTableExists_Words(Activity actv) {
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		return dbu.tableExists(CONS.DB.tname_Words);
		
//		return dbu.createTable(
//					CONS.DB.tname_texts,
//					CONS.DB.cols_texts,
//					CONS.DB.col_types_texts);
		
	}//validateTableExists_texts(Activity actv)


	public static List<Text> get_TextList(Activity actv) {
		
//		List<Text> textList = new ArrayList<Text>();
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		return dbu.getTexts(actv);
		
//		SQLiteDatabase rdb = dbu.getReadableDatabase();
//		return null;
	}


	public static void
	start_speech(Activity actv, String text) {
		// TODO Auto-generated method stub
		String textTrunk = Methods_CR5.find_text_trunk(text);
		
		if (textTrunk == null) {
			
			textTrunk = text;
			
		}//if (text_new == null)
		
		/*----------------------------
		 * 2. Speak
			----------------------------*/
        if (CONS.ActvRead.tts.isSpeaking()) {
        	
        	CONS.ActvRead.tts.stop();
        	
        }

        CONS.ActvRead.tts.speak(textTrunk, TextToSpeech.QUEUE_FLUSH, null);

	}//start_speech(Activity actv, String text)

	public static String find_text_trunk(String text) {
		/*----------------------------
		 * memo
			----------------------------*/
		String reg1 = "^\\d+\\.(.+)$";
		Pattern p = Pattern.compile(reg1);
		Matcher m = p.matcher(text);

		return m.find() ? m.group(1) : null;
		
		
	}//public static void find_text_trunk(String text)

	public static boolean validateTableExists(Activity actv, String tableName) {
		// TODO Auto-generated method stub
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		return dbu.tableExists(tableName);
		
//		return false;
	}

	public static void getWords(Activity actv, String remoteUrl) {
		// TODO Auto-generated method stub

		String url = remoteUrl;
		
		Task_GetWords task = new Task_GetWords(actv);
		
		task.execute(url);

	}

	public static boolean
	storeData_Word(Activity actv, JSONObject joWord) {
		// TODO Auto-generated method stub
		
//		// Log
//		Log.d("Methods_CR5.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "joText" + joText);
		
		/***************************************
		 * Build a Text instance
		 ***************************************/
		Word w = storeData_Word__1__buildWordInstance(joWord);
		
		// Log
		Log.d("Methods_CR5.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "t.getCreatedAt_mill()=" + w.getCreatedAt_mill());
		
		/***************************************
		 * Store text
		 ***************************************/
		return storeData_Word__2__StoreWord(actv, w);
		
	}//storeData_Word(Activity actv, JSONObject joWord)


	public static void getWordList(Activity actv, String remoteUrl) {
		// TODO Auto-generated method stub
		String url = remoteUrl;
		
		Task_GetWordList task = new Task_GetWordList(actv);
		
		task.execute(url);
		
	}

	public static boolean
	storeData_WordList(Activity actv, JSONObject joWordList) {
		// TODO Auto-generated method stub
		/***************************************
		 * Build a Text instance
		 ***************************************/
		WordList wl = storeData_WordList__1__buildWordListInstance(joWordList);
		
		// Log
		Log.d("Methods_CR5.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "t.getCreatedAt_mill()=" + wl.getCreated_at_mill());
		
		/***************************************
		 * Store text
		 ***************************************/
		return storeData_WordList__2__StoreWordList(actv, wl);
		
	}//storeData_WordList(Activity actv, JSONObject joWordList)

	private static boolean
	storeData_WordList__2__StoreWordList
	(Activity actv, WordList wl) {

		DBUtils_CR5 dbu = new DBUtils_CR5(actv, CONS.DB.dbName);
		
		return dbu.insertData_WordList(actv, wl);
		
	}//storeData_WordList__2__StoreText


	
//	public static Text get_TextFromDbId(Activity actv, long dbId) {
//		
//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
//		
//		return dbu.get_TextFromDbId(actv, dbId);
//
//	}//public static Text get_TextFromDbId(Activity actv, long dbId)
	
}//public class Methods_CM5
