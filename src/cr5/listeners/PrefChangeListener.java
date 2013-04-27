package cr5.listeners;

import cr5.main.R;
import cr5.utils.CONS;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.util.Log;

public class
PrefChangeListener implements OnPreferenceChangeListener {

	Activity actv;
	
	public PrefChangeListener(Activity actv) {
		// TODO Auto-generated constructor stub
		this.actv = actv;
	}

	@Override
	public boolean
	onPreferenceChange(Preference pref, Object newVal) {
		String val = (String) newVal;
		
		// Log
		Log.d("PrefChangeListener.java"
				+ "["
				+ Thread.currentThread().getStackTrace()[2]
						.getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2]
						.getMethodName() + "]",
				"pref=" + pref.getClass().getName()
				+ "/"
				+ "new value=" + val);
		
		/***************************************
		 * Set: Summary
		 ***************************************/
		ListPreference lp = (ListPreference) pref;
		
		if (lp != null) {
			
			lp.setSummary("choice=" + val);
			
		}//if (lp != null)
		
		/***************************************
		 * Set: Pref value
		 ***************************************/
//		SharedPreferences prefs =
//				actv.getSharedPreferences(
//								CONS.Pref.ActvPref_main,
//								Context.MODE_PRIVATE);
//		
//		SharedPreferences.Editor editor = prefs.edit();
//		
//		editor.putString(actv.getString(R.string.langKey), val);
//		editor.commit();

		lp.setValue(val);
		
		// Log
		Log.d("PrefChangeListener.java" + "["
				+ Thread.currentThread().getStackTrace()[2].getLineNumber()
				+ ":"
				+ Thread.currentThread().getStackTrace()[2].getMethodName()
				+ "]", "Pref for lang => Set: " + val);
		
//		SharedPreferences mSP = PreferenceManager.getDefaultSharedPreferences(this);
//		int hogeInteger = Integer.parseInt(mSP.getString("hogekey", "2"));
		
		return false;
	}//onPreferenceChange(Preference pref, Object newVal)

}//PrefChangeListener implements OnPreferenceChangeListener
