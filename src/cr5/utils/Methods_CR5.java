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
import cr5.tasks.Task_GetTexts;

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
		
//		HttpPost httpPost = new HttpPost(url);
//		
//		httpPost.setHeader("Content-type", "application/json");
//		
//		HttpUriRequest postRequest = httpPost;
//		
//		DefaultHttpClient dhc = new DefaultHttpClient();
//		
//		HttpResponse hr = null;
//		
//		/***************************************
//		 * Execute: Post
//		 ***************************************/
//		try {
//			
//			hr = dhc.execute(postRequest);
//			
//		} catch (ClientProtocolException e) {
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", e.toString());
//		} catch (IOException e) {
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", e.toString());
//		}
//		
//		/***************************************
//		 * Validate: Return
//		 ***************************************/
//		if (hr == null) {
//			
////			// debug
////			Toast.makeText(actv, "hr == null", 2000).show();
//			
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ "]", "hr == null");
//			
//			return;
//			
//		} else {//if (hr == null)
//			
//			// Log
//			Log.d("Methods_CR5.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "hr => Not null");
//			
//		}//if (hr == null)
//
//		/*********************************
//		 * Status code
//		 *********************************/
//		int status = hr.getStatusLine().getStatusCode();
//		
//		// Log
//		Log.d("Task_GetYomi.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "status=" + status);
//		


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
	


	public static boolean
	validateTableExists_texts(Activity actv) {
		
		DBUtils dbu = new DBUtils(actv, CONS.DB.dbName);
		
		return dbu.createTable(
					CONS.DB.tname_texts,
					CONS.DB.cols_texts,
					CONS.DB.col_types_texts);
		
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
