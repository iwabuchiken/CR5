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
import cr5.tasks.Task_GetTexts;
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

	public static boolean
	storeData_Word(Activity actv, JSONObject joWord) {
		
//		// Log
//		Log.d("Methods_CR5.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "joWord" + joWord);
		
		/***************************************
		 * Build a Text instance
		 ***************************************/
//		Text t = storeData_Text__1__buildTextInstance(joWord);
		Word w = storeData_Word__1__BuildInstance_Word(joWord);
		
		// Log
		Log.d("Methods_CR5.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "w.getCreatedAt_mill()=" + w.getCreatedAt_mill());
		
		/***************************************
		 * Store text
		 ***************************************/
//		return storeData_Text__2__StoreText(actv, w);
		
		return false;
		
//		boolean res = storeData_Text__2__StoreText(actv, t);
//		
//		return false;
		
	}//storeData_Word(Activity actv, JSONObject joWord)


	
	private static boolean
	storeData_Text__2__StoreText(Activity actv, Text t) {
		
		DBUtils_CR5 dbu = new DBUtils_CR5(actv, CONS.DB.dbName);
		
		return dbu.insertData_Text(actv, t);
//		boolean res = dbu.insertData_Text(actv, t);
//		
//		return false;
	}


	private static
	Text storeData_Text__1__buildTextInstance(JSONObject joText) {
		
		Text t = null;
		
		try {
			
			String	createdAt		= joText.getString("created_at");
			String	modifiedAt		= joText.getString("updated_at");
			String	text			= joText.getString("text");
			String	url				= joText.getString("url");
			long	genreId			= joText.getLong("genre_id");
			long	subGenreId		= joText.getLong("subgenre_id");
			long	dbId			= joText.getLong("id");
			long	langId			= joText.getLong("lang_id");
			String	memo			= joText.getString("memo");
			long	createdAt_mill	= joText.getLong("created_at_mill");
			String	title			= joText.getString("title");
			
			t = new Text.Builder()
						.setCreatedAt(Methods.convert_railsTimeLabel2MilSec(createdAt))
						.setModifiedAt(Methods.convert_railsTimeLabel2MilSec(modifiedAt))
						.setText(text)
						.setUrl(url)
						.setGenreId(genreId)
						.setSubGenreId(subGenreId)
						.setDbId(dbId)
						.setLangId(langId)
						.setMemo(memo)
						.setCreatedAt_mill(createdAt_mill)
						.setTitle(title)
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
//					+ "]", "joText=" + joText);
			
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
				
			} else {//if (t != null)
				
				// Log
				Log.d("Methods_CR5.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", "t == null");
				
			}//if (t != null)
			
			
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

	
//	public static Text get_TextFromDbId(Activity actv, long dbId) {
//		
//		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
//		
//		return dbu.get_TextFromDbId(actv, dbId);
//
//	}//public static Text get_TextFromDbId(Activity actv, long dbId)
	
}//public class Methods_CM5
