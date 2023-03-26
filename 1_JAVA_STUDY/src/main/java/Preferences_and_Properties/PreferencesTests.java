package Preferences_and_Properties;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferencesTests {
	public static void main(String[] args) throws BackingStoreException {
		PreferenceTest test = new PreferenceTest();
		
		test.setPreference();
		// test.Clear();
	}
}

class PreferenceTest {
	private static final String VERSION = "version";
	private static final String NAME = "name";
	private static final String IS_STABLE_RELEASE = "isStableRelease";

	private static void displayPreferences(Preferences prefs) {
		/* Try to read preference before setting */
		int versionNum = prefs.getInt(VERSION, 1);
		String productName = prefs.get(NAME, "MySports");
		boolean isStableRelease = prefs.getBoolean(IS_STABLE_RELEASE, false);

		System.out.println("versionNum : " + versionNum);
		System.out.println("productName : " + productName);
		System.out.println("isStableRelease : " + isStableRelease);
		System.out.println();
	}
	
	public void Clear() throws BackingStoreException {
		Preferences preferences  = Preferences.userRoot().node(this.getClass().getName());
		preferences.clear();
	}

	public void setPreference() {
		// This will define a node in which the preferences can be stored
		Preferences preferences = Preferences.userRoot().node(this.getClass().getName());

		displayPreferences(preferences);

		/* Set the Preferences */
		preferences.putInt(VERSION, 123);
		preferences.put(NAME, "MyCityInfo");
		preferences.putBoolean(IS_STABLE_RELEASE, true);

		displayPreferences(preferences);
	}
}