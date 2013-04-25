package cr5.tasks;

import org.apache.commons.net.ftp.FTPClient;

import cr5.utils.CONS;
import cr5.utils.Methods_CR5;
import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class
Task_FTP extends AsyncTask<Integer, Integer, Integer> {

	Activity	actv;
	Dialog		dlg1;
	
	public Task_FTP(Activity actv, Dialog dlg1) {
		
		this.actv	= actv;
		this.dlg1	= dlg1;
		
	}

	@Override
	protected Integer doInBackground(Integer... params) {
		
		/***************************************
		 * Dispatch
		 ***************************************/
		if (params[0].intValue() == CONS.FTP.TASK_UPLOAD_DB_FILE) {
			
			return Integer.valueOf(task_UploadDbFile());
			
		} else {//if (params[0] == condition)
			
			return Integer.valueOf(CONS.FTP.TASK_RETURN_SUCCEESSFUL);
			
		}//if (params[0] == condition)
		
//		/***************************************
//		 * Connect
//		 ***************************************/
//		FTPClient fc = Methods_CR5.FTP.connect(actv);
//		
//		if (fc == null) {
//			
//			// Log
//			Log.d("Task_FTP.java" + "["
//					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//					+ ":"
//					+ Thread.currentThread().getStackTrace()[2].getMethodName()
//					+ "]", "fc => null");
//			
//			return CONS.FTP.TASK_RETRUN_FAILED;
//			
//			
//		}//if (fc == null)
//		
//		
//		
//		/***************************************
//		 * Disconnect
//		 ***************************************/
//		boolean res = Methods_CR5.FTP.disconnect(actv, fc);
//		
//		// Log
//		Log.d("Task_FTP.java" + "["
//				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
//				+ ":"
//				+ Thread.currentThread().getStackTrace()[2].getMethodName()
//				+ "]", "res=" + res);
//		
//		return CONS.FTP.TASK_RETURN_SUCCEESSFUL;
		
	}//protected Integer doInBackground(String... arg0)

	private int task_UploadDbFile() {
		/***************************************
		 * Connect
		 ***************************************/
		FTPClient fc = Methods_CR5.FTP.connect(actv);
		
		if (fc == null) {
			
			// Log
			Log.d("Task_FTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "fc => null");
			
			return CONS.FTP.TASK_RETRUN_FAILED;
			
			
		}//if (fc == null)
		
		
		
		/***************************************
		 * Disconnect
		 ***************************************/
		boolean res = Methods_CR5.FTP.disconnect(actv, fc);
		
		// Log
		Log.d("Task_FTP.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "res=" + res);
		
		return CONS.FTP.TASK_RETURN_SUCCEESSFUL;
	
	}//private int task_UploadDbFile()
	

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(Integer result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		if (result.intValue() == CONS.FTP.TASK_RETURN_SUCCEESSFUL) {
			
			dlg1.dismiss();
			
			// debug
			Toast.makeText(actv, "Upload => Successful", Toast.LENGTH_LONG).show();
			
		} else {

			// debug
			Toast.makeText(actv, "Upload => Failed", Toast.LENGTH_LONG).show();
			
		}
		
	}//protected void onPostExecute(Integer result)

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	
}//Task_FTP extends AsyncTask<String, Integer, Integer>
