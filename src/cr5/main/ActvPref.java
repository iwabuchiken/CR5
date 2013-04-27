package cr5.main;

import cr5.utils.CONS;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class ActvPref extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		/*----------------------------
		 * Steps
		 * 1. Super
		 * 2. Set content
		 * 
		 * 3. Set preferences
		----------------------------*/
		super.onCreate(savedInstanceState);

		//
		setContentView(R.layout.actv_pref);

		this.setTitle(this.getClass().getName());
		
		/*----------------------------
		 * 3. Set preferences
			----------------------------*/
		getPreferenceManager()
			.setSharedPreferencesName(CONS.Pref.ActvPref_main);
//		.setSharedPreferencesName(this.getString(R.string.prefs_shared_prefs_name));
		
		addPreferencesFromResource(R.xml.preferences);
		
		//debug
		SharedPreferences mSP = PreferenceManager.getDefaultSharedPreferences(this);
		int hogeInteger = Integer.parseInt(mSP.getString("hogekey", "2"));
		
		
		
		// Log
		Log.d("ActvPref.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "hogeInteger=" + hogeInteger);
		
		/***************************************
		 * Listener
		 ***************************************/
		ListPreference lp =
				(ListPreference) findPreference("hoge_key");
//		(ListPreference) findPreference(this.getString(R.string.hogekey));
//		(ListPreference) this.findPreference(this.getString(R.string.hogekey));
		
		
		
		// Log
		Log.d("ActvPref.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "R.string.hogekey=" + this.getString(R.string.hogekey));
		
		if (lp == null) {
			
			// Log
			Log.d("ActvPref.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "lp == null");
			
		} else {//if (lp == null)

			// Log
			Log.d("ActvPref.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "lp != null");

		}//if (lp == null)
		
//		
//		SharedPreferences.Editor edit = lp.getEditor();
//		
//		edit.putString("hogekey", "1");
//		
//		edit.commit();
		
		

		hogeInteger = Integer.parseInt(mSP.getString("hogekey", "2"));
		
		// Log
		Log.d("ActvPref.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "hogeInteger=" + hogeInteger);

		
	}//public void onCreate(Bundle savedInstanceState)

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
//		CheckBoxPreference pref_lang = 
//				(CheckBoxPreference) findPreference(
//						this.getString(R.string.actv_pref_choose_lang_key));
//		this.getString(R.string.prefs_history_size_key));
		
		
		
		
//		prefEditText.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
		
//		boolean chosenLang = pref_lang.
		
		SharedPreferences prefs =
				this.getSharedPreferences(
						CONS.Pref.ActvPref_main, 0);
//		this.getString(R.string.prefs_shared_prefs_name), 0);
		
		boolean chosenLang =
				prefs.getBoolean(this.getString(R.string.actv_pref_choose_lang_key), false);
//		prefs.getBoolean(this.getString(R.id.), true);
//		String pref_history_size = prefs.getString(this.getString(R.string.prefs_history_size_key), null);

		// Log
		Log.d("ActvPref.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "chosenLang=" + chosenLang);
		
		//debug
		/***************************************
		 * Listener
		 ***************************************/
		ListPreference lp =
				(ListPreference) findPreference("hogekey");
//		(ListPreference) findPreference("hoge_key");
//		(ListPreference) findPreference(this.getString(R.string.hogekey));
//		(ListPreference) this.findPreference(this.getString(R.string.hogekey));
		
		
		
		// Log
		Log.d("ActvPref.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "R.string.hogekey=" + this.getString(R.string.hogekey));
		
		if (lp == null) {
			
			// Log
			Log.d("ActvPref.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "lp == null");
			
		} else {//if (lp == null)

			// Log
			Log.d("ActvPref.java" + "["
					+ Thread.currentThread().getStackTrace()[2].getLineNumber()
					+ ":"
					+ Thread.currentThread().getStackTrace()[2].getMethodName()
					+ "]", "lp != null");

		}//if (lp == null)
		
		/***************************************
		 * Change listener
		 ***************************************/
		
		// REF http://wada811.blog.fc2.com/?tag=ListPreference
		lp.setOnPreferenceChangeListener(new OnPreferenceChangeListener(){

			@Override
			public boolean onPreferenceChange(Preference pref, Object newVal) {
				String val = (String) newVal;
				
				// Log
				Log.d("ActvPref.java"
						+ "["
						+ Thread.currentThread().getStackTrace()[2]
								.getLineNumber()
						+ ":"
						+ Thread.currentThread().getStackTrace()[2]
								.getMethodName() + "]",
						"pref=" + pref.getClass().getName()
						+ "/"
						+ "new value=" + val);
				
//				SharedPreferences mSP = PreferenceManager.getDefaultSharedPreferences(this);
//				int hogeInteger = Integer.parseInt(mSP.getString("hogekey", "2"));
				
				return false;
			}
			
		});//lp.setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
		
//		
//		SharedPreferences.Editor edit = lp.getEditor();
//		
//		edit.putString("hogekey", "1");
//		
//		edit.commit();
		
		SharedPreferences mSP = PreferenceManager.getDefaultSharedPreferences(this);

		int hogeInteger = Integer.parseInt(mSP.getString("hogekey", "2"));
		
		// Log
		Log.d("ActvPref.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "hogeInteger=" + hogeInteger);
		super.onStart();
		
	}//protected void onStart()

	@Override
	protected void onStop() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		super.onDestroy();
	}
	

}
