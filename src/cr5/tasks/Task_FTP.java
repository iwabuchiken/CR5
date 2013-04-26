package cr5.tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
		 * Login
		 ***************************************/
		String uname = "chips.jp-benfranklin";

		String passwd = "9x9jh4";

		try {
			
			boolean res = fc.login(uname, passwd);
			
			if(res == false) {
				
				int reply_code = fc.getReplyCode();
				
				// Log
				Log.e("Task_FTP.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber() + "]", "Log in failed => " + reply_code);
				
				fc.disconnect();
				
				return CONS.FTP.TASK_RETRUN_LOGIN_FAILED;
				
			} else {
				
				int reply_code = fc.getReplyCode();
				
				// Log
				Log.d("Task_FTP.java" + "["
						+ Thread.currentThread().getStackTrace()[2].getLineNumber()
						+ "]", "Log in => Succeeded: " + reply_code);

			}//if(res == false)

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/***************************************
		 * FTP
		 ***************************************/
		/*********************************
		 * FTP files
		 *********************************/
		
		FileInputStream is;
		
		String fpath_DbFileBackup = _task_UploadDbFile__1_GetSourceDbFilePath();
		
		// Log
		Log.d("Task_FTP.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "fpath_DbFileBackup=" + fpath_DbFileBackup);
		
		String fpath_Remote = "./" + "cr5.db";
//		String fpath_Remote = "./android_app_data/" + "cr5.db";
//		String fpath_Remote = "./android_app_data" + "cr5.db";
		
		try {
			
			is = new FileInputStream(fpath_DbFileBackup);
//			is = new FileInputStream(fpath_audio);
			
//			fp.storeFile("./" + MainActv.fileName_db, is);// �T�[�o�[��
			fc.storeFile(fpath_Remote, is);// �T�[�o�[��
			
//			fp.makeDirectory("./ABC");
			
			
			// Log
			Log.d("MethodsFTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "File => Stored");
			
			is.close();

		} catch (FileNotFoundException e) {

			// Log
			Log.e("MethodsFTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());
			
		} catch (IOException e) {
			
			// Log
			Log.e("MethodsFTP.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "Exception: " + e.toString());

		}
		
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
	

	private String _task_UploadDbFile__1_GetSourceDbFilePath() {
		String src_dir = CONS.DB.dpath_dbBackup;
		
		File f_dir = new File(src_dir);
		
		File[] src_dir_files = f_dir.listFiles();
		
		// If no files in the src dir, quit the method
		if (src_dir_files.length < 1) {
			
			// Log
			Log.d("Methods.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ "]", "No files in the dir: " + src_dir);
			
			return null;
			
		}//if (src_dir_files.length == condition)
		
		// Latest file
		File f_src_latest = src_dir_files[0];
		
		
		for (File file : src_dir_files) {
			
			if (f_src_latest.lastModified() < file.lastModified()) {
						
				f_src_latest = file;
				
			}//if (variable == condition)
			
		}//for (File file : src_dir_files)
		
		// Show the path of the latest file
		// Log
		Log.d("Methods.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ "]", "f_src_latest=" + f_src_latest.getAbsolutePath());
		
		/*********************************
		 * Restore file
		 *********************************/
		return f_src_latest.getAbsolutePath();
		
	}//private String _task_UploadDbFile__1_GetSourceDbFilePath()
	

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
