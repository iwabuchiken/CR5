package cr5.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import cr5.tasks.Task_GetTexts;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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
	
}//public class Methods_CM5
