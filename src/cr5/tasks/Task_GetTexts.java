package cr5.tasks;

import java.io.IOException;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cr5.utils.CONS;
import cr5.utils.Methods_CR5;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class
Task_GetTexts extends AsyncTask<String, Integer, Integer> {

	Activity actv;
	
	
	public Task_GetTexts(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}


	@Override
	protected Integer doInBackground(String... urls) {
		
		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "url=" + urls[0]);

		/***************************************
		 * Get: HttpResponse
		 ***************************************/
//		HttpResponse hr = doInBackground__1__getHttpResponse(urls);
		HttpResponse hr = this.doInBackground__1__getHttpResponse_v2_GET(urls);
		
		if (hr == null) {
		
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "hr == null");
			
			return CONS.Task_GetTexts.HTTP_RESPONSE_NULL;
			
		} else {//if (hr == null)
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "Http response => Obtained");
			
		}//if (hr == null)
		
		/*********************************
		 * Status code
		 *********************************/
		int status = hr.getStatusLine().getStatusCode();
		
		if (status == CONS.HTTP_Response.CREATED
				|| status == CONS.HTTP_Response.OK) {

			// Log
			Log.d("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);

//			return CONS.HTTP_Response.CREATED;
			
		} else {//if (status == CONS.HTTP_Response.CREATED)
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "status=" + status);
			
			return CONS.HTTP_Response.NOT_CREATED;
			
		}//if (status == CONS.HTTP_Response.CREATED)
		
		/*********************************
		 * Entity
		 *********************************/
		HttpEntity entity = hr.getEntity();
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "entity.toString()=" + entity.toString());
		
		/***************************************
		 * Get: Json array
		 ***************************************/
//		JSONObject joRoot = null;
		JSONArray jaRoot = null;
		
		try {

			String entityContent = EntityUtils.toString(entity);
			
//			jaRoot = new JSONArray(jsonString);
			jaRoot = new JSONArray(entityContent);
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "jaRoot.length()=" + jaRoot.length());

		} catch (ParseException e) {
			
			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.Task_GetTexts.EXCEPTION_PARSE_JSON;
			
		} catch (JSONException e) {

			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.Task_GetTexts.EXCEPTION_JSON;

		} catch (IOException e) {

			// Log
			Log.e("Task_GetYomi.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return CONS.Task_GetTexts.EXCEPTION_IO;
			
		}//try
		
		// Log
		Log.d("Task_GetYomi.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "JSONObject => Created");
				+ "]", "JSONArray => Created");

		/***************************************
		 * Store data
		 ***************************************/
		JSONObject joText = null;
				
		for (int i = 0; i < jaRoot.length(); i++) {
			
			try {
				
				joText = jaRoot.getJSONObject(i);
				
			} catch (JSONException e) {
				
				// Log
				Log.d("Task_GetTexts.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"item=" + i + " => " + e.toString());
				
				continue;
				
			}
			
			boolean res = Methods_CR5.storeData_Text(actv, joText);
			
		}//for (int i = 0; i < jaRoot.length(); i++)
		
		/***************************************
		 * Return
		 ***************************************/
		return CONS.ReturnValue.RETURN_OK;
		
	}//protected Integer doInBackground(String... urls)


	private
	JSONArray doInBackground__2__getJsonArray(JSONArray jaRoot) {
		// TODO Auto-generated method stub
		
		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "JSONArray!");

		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "jaRoot.length()=" + jaRoot.length());
		
		JSONObject jo1 = null;
				
		try {
			
			jo1 = jaRoot.getJSONObject(0);
			
		} catch (JSONException e) {
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", e.toString());
			
			return null;
			
		}

		// Get keys
		Iterator keys = jo1.keys();
		
		int count = 0;
		
		while(keys.hasNext()) {
			
			String key = (String) keys.next();
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "key=" + keys.next());
					+ "]", "key=" + key);
			
			try {
//				if (jo1.getString(key).equals("url")) {
				if (key.equals("url")) {
					
					// Log
					Log.d("Task_GetTexts.java"
							+ "["
							+ Thread.currentThread().getStackTrace()[2]
									.getLineNumber()
							+ ":"
							+ Thread.currentThread().getStackTrace()[2]
									.getMethodName() + "]", "url=" + jo1.getString(key));
					
				}
			} catch (JSONException e) {

				// Log
				Log.d("Task_GetTexts.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]", e.toString());
				
			}//if (variable == condition)
			
			count += 1;
			
		}
		
		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "count=" + count);
		
//		String name = null;
//		
//		try {
//			
//			name = jo1.getString("name");
//			
//		} catch (JSONException e) {
//			
//			// Log
//			Log.d("Task_GetTexts.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return null;
//			
//		}
		
//		// Log
//		Log.d("Task_GetTexts.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "name=" + name);
		
		
		return null;
		
	}//JSONArray doInBackground__2__getJsonArray(JSONArray jaRoot)


	private
	JSONArray doInBackground__2__getJsonArray(JSONObject joRoot) {
		// TODO Auto-generated method stub

		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "joRoot.length()=" + joRoot.length());
		
		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "message" + joRoot.keys());
		
		Iterator it = joRoot.keys();
		
		int counter = 0;
		
		while(it.hasNext()) {
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "key=" + (String) it.next());
			
			counter += 1;
		}
		
		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Number of keys => " + counter);
		
		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "joRoot.toString()=" + joRoot.toString());
		
		//
		try {
			String url = joRoot.getString("url");
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "url=" + url);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		joRoot.names()
		
		
//		JSONArray jaTexts = null;
//		
//		jaTexts = joRoot.names();
		
//		try {
//			
////			jaTexts = joRoot.getJSONArray("created_at");
////			jaTexts = joRoot.getJSONArray("");
////			jaTexts = joRoot.names();
//			
//		} catch (JSONException e) {
//			
//			// Log
//			Log.e("Task_GetTexts.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return CONS.Task_GetTexts.EXCEPTION_JSON;
//			
//		}
//		
//		// Log
//		Log.d("Task_GetTexts.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "jaTexts.length()=" + jaTexts.length());
		
////		JSONObject joText = null;
//		String joText = null;
//		
//		try {
//			
//			joText = (String) jaTexts.getJSONObject(0);
//			
//		} catch (JSONException e) {
//			
//			// Log
//			Log.e("Task_GetTexts.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", e.toString());
//			
//			return CONS.Task_GetTexts.EXCEPTION_JSON;
//			
//		}
		
//		if (joText != null) {
//			
//			try {
//				
//				String url1 = joText.getString("url");
//				
//				// Log
//				Log.d("Task_GetTexts.java"
//						+ "["
//						+ Thread.currentThread().getStackTrace()[2]
//								.getLineNumber()
//						+ ":"
//						+ Thread.currentThread().getStackTrace()[2]
//								.getMethodName() + "]", "url=" + url1);
//				
//			} catch (JSONException e) {
//				
//				return CONS.Task_GetTexts.EXCEPTION_JSON;
//				
//			}
//			
//			
//			
//		} else {//if (joText != null)
//			
//			// Log
//			Log.d("Task_GetTexts.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "joText == null");
//			
//		}//if (joText != null)
		
		return null;
		
	}//JSONArray doInBackground__2__getJsonArray(JSONObject joRoot)
	


	private
	HttpResponse doInBackground__1__getHttpResponse(String[] urls) {
		// TODO Auto-generated method stub
		HttpPost httpPost = new HttpPost(urls[0]);
		
		httpPost.setHeader("Content-type", "application/json");
		
		HttpUriRequest postRequest = httpPost;
		
		//debug
		doInBackground__1__getHttpResponse__debug_1(postRequest);
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		/***************************************
		 * Execute: Post
		 ***************************************/
		try {
			
			hr = dhc.execute(postRequest);
			
		} catch (ClientProtocolException e) {
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
		} catch (IOException e) {
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
		}
		
		/***************************************
		 * Validate: Return
		 ***************************************/
		if (hr == null) {
			
//			// debug
//			Toast.makeText(actv, "hr == null", 2000).show();
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
//			return CONS.Task_GetTexts.EXECUTE_POST_NULL;
			return null;
			
		} else {//if (hr == null)
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "hr => Not null");
			
			return hr;
			
		}//if (hr == null)

	}//HttpResponse doInBackground__1__getHttpResponse(String[] urls)

	private
	HttpResponse doInBackground__1__getHttpResponse_v2_GET(String[] urls) {
		// TODO Auto-generated method stub
//		HttpPost httpPost = new HttpPost(urls[0]);
		HttpGet httpGet = new HttpGet(urls[0]);
		
		httpGet.setHeader("Content-type", "application/json");
		
//		HttpUriRequest postRequest = httpGet;
		HttpUriRequest getRequest = httpGet;
		
		//debug
//		doInBackground__1__getHttpResponse__debug_1(postRequest);
		
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpResponse hr = null;
		
		/***************************************
		 * Execute: Post
		 ***************************************/
		try {
			
//			hr = dhc.execute(postRequest);
			hr = dhc.execute(getRequest);
			
		} catch (ClientProtocolException e) {
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
		} catch (IOException e) {
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", e.toString());
		}
		
		/***************************************
		 * Validate: Return
		 ***************************************/
		if (hr == null) {
			
//			// debug
//			Toast.makeText(actv, "hr == null", 2000).show();
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "hr == null");
			
//			return CONS.Task_GetTexts.EXECUTE_POST_NULL;
			return null;
			
		} else {//if (hr == null)
			
			// Log
			Log.d("Task_GetTexts.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "hr => Not null");
			
			return hr;
			
		}//if (hr == null)

	}//HttpResponse doInBackground__1__getHttpResponse_v2_GET(String[] urls)


	private void
	doInBackground__1__getHttpResponse__debug_1(HttpUriRequest postRequest) {
		// TODO Auto-generated method stub
		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "getRawPath=" + postRequest.getURI().getRawPath());

		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "toString()=" + postRequest.getURI().toString());

	}//doInBackground__1__getHttpResponse__debug_1(HttpUriRequest postRequest)
	


	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}


	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		// Log
		Log.d("Task_GetTexts.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "result=" + result.intValue());
		
		// debug
		Toast.makeText(actv, "rsult =>" + result.intValue(), Toast.LENGTH_LONG).show();
		
	}//protected void onPostExecute(Integer result)


	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		// debug
		Toast.makeText(actv, "Getting texts...", Toast.LENGTH_LONG).show();
		
	}


	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	
}//Task_GetTexts extends AsyncTask<String, Integer, Integer>
