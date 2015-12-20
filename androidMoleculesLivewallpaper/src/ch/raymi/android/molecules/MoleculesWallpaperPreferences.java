package ch.raymi.android.molecules;

import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;

public class MoleculesWallpaperPreferences extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
	static final String SHARED_PREFERENCES_NAME = "ch.raymi.android.molecules.liveWallpaper";
	private static final String PREFERENCE_MOLECULE_KEY = "molecule";
	private static final String PREFERENCE_OVERLAY_COLOR_KEY = "overlayColor";
	private static final String PREFERENCE_RANDOM_MOLECULE_KEY = "randomMolecule";
	private static final String PREFERENCE_STYLE_KEY = "visualStyle";
	private static final String PREFERENCE_SPINX_KEY = "spinx";
	private static final String PREFERENCE_SPINY_KEY = "spiny";
	private static final String PREFERENCE_SPINZ_KEY = "spinz";
	private static final String PREFERENCE_SHOW_NAME_ON_TOUCH_KEY = "showNameOnTouch";


	public static String getMoleculeName(SharedPreferences prefs) {
		return prefs.getString(MoleculesWallpaperPreferences.PREFERENCE_MOLECULE_KEY, null);
	}

	public static String getOverlayColor(SharedPreferences prefs) {
		return prefs.getString(MoleculesWallpaperPreferences.PREFERENCE_OVERLAY_COLOR_KEY, null);
	}

	public static String getStyle(SharedPreferences prefs) {
		return prefs.getString(MoleculesWallpaperPreferences.PREFERENCE_STYLE_KEY, "ballnstick");
	}

	public static boolean isRandomMolecule(SharedPreferences prefs) {
		return prefs.getBoolean(MoleculesWallpaperPreferences.PREFERENCE_RANDOM_MOLECULE_KEY, true);
	}

	public static boolean isSpinX(SharedPreferences prefs) {
		return prefs.getBoolean(MoleculesWallpaperPreferences.PREFERENCE_SPINX_KEY, false);
	}

	public static boolean isSpinY(SharedPreferences prefs) {
		return prefs.getBoolean(MoleculesWallpaperPreferences.PREFERENCE_SPINY_KEY, true);
	}

	public static boolean isSpinZ(SharedPreferences prefs) {
		return prefs.getBoolean(MoleculesWallpaperPreferences.PREFERENCE_SPINZ_KEY, false);
	}

	public static boolean isShowNameOnTouch(SharedPreferences prefs) {
		return prefs.getBoolean(MoleculesWallpaperPreferences.PREFERENCE_SHOW_NAME_ON_TOUCH_KEY, true);
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getPreferenceManager().setSharedPreferencesName(SHARED_PREFERENCES_NAME);
		createPreferenceScreen();
		getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(
				this);
	}

	private PreferenceScreen createPreferenceScreen() {
		PreferenceScreen root = getPreferenceManager().createPreferenceScreen(this);
		root.setTitle(R.string.settings_title);
		setPreferenceScreen(root);

		PreferenceCategory data = new PreferenceCategory(this);
		data.setTitle(R.string.settings_category_data_title);
		root.addPreference(data);

		CheckBoxPreference randomMolecule = new CheckBoxPreference(this);
		randomMolecule.setKey(PREFERENCE_RANDOM_MOLECULE_KEY);
		randomMolecule.setTitle(R.string.settings_random_title);
		randomMolecule.setDisableDependentsState(true);
		randomMolecule.setPersistent(true);
		randomMolecule.setDefaultValue(true);
		data.addPreference(randomMolecule);

		ListPreference moleculesPref = new ListPreference(this);
		moleculesPref.setTitle(R.string.settings_molecules_title);
		List<Molecule> molecules = Molecule.Molecules.getMolecules();
		String[] names = new String[molecules.size()];
		for (int i = 0 ; i < molecules.size(); i++) {
			names[i] = molecules.get(i).getName();
		}
		moleculesPref.setEntries(names);
		moleculesPref.setEntryValues(names);
		moleculesPref.setKey(PREFERENCE_MOLECULE_KEY);
		moleculesPref.setPersistent(true);
		data.addPreference(moleculesPref);

		PreferenceCategory appearance = new PreferenceCategory(this);
		appearance.setTitle(R.string.settings_category_appearance_title);
		root.addPreference(appearance);

		ListPreference overlayPref = new ListPreference(this);
		overlayPref.setTitle(R.string.settings_overlay_title);
		overlayPref.setEntries(R.array.overlay);
		overlayPref.setEntryValues(R.array.overlay_values);
		overlayPref.setKey(PREFERENCE_OVERLAY_COLOR_KEY);
		overlayPref.setPersistent(true);
		overlayPref.setDefaultValue("");
		appearance.addPreference(overlayPref);

		ListPreference style = new ListPreference(this);
		style.setTitle(R.string.settings_visualStyle_title);
		style.setEntries(R.array.visualStyle);
		style.setEntryValues(R.array.visualStyle_values);
		style.setKey(PREFERENCE_STYLE_KEY);
		style.setDefaultValue("ballnstick");
		style.setPersistent(true);
		appearance.addPreference(style);

		CheckBoxPreference onTouch = new CheckBoxPreference(this);
		onTouch.setKey(PREFERENCE_SHOW_NAME_ON_TOUCH_KEY);
		onTouch.setTitle(R.string.settings_ontouch_title);
		onTouch.setPersistent(true);
		onTouch.setDefaultValue(true);
		appearance.addPreference(onTouch);

		PreferenceCategory rotation = new PreferenceCategory(this);
		rotation.setTitle(R.string.settings_category_movement_title);
		root.addPreference(rotation);

		CheckBoxPreference spinx = new CheckBoxPreference(this);
		spinx.setTitle(R.string.settings_spinx_title);
		spinx.setPersistent(true);
		spinx.setKey(PREFERENCE_SPINX_KEY);
		spinx.setDefaultValue(false);
		rotation.addItemFromInflater(spinx);

		CheckBoxPreference spiny = new CheckBoxPreference(this);
		spiny.setTitle(R.string.settings_spiny_title);
		spiny.setPersistent(true);
		spiny.setKey(PREFERENCE_SPINY_KEY);
		spiny.setDefaultValue(true);
		rotation.addItemFromInflater(spiny);

		CheckBoxPreference spinz = new CheckBoxPreference(this);
		spinz.setTitle(R.string.settings_spinz_title);
		spinz.setPersistent(true);
		spinz.setKey(PREFERENCE_SPINZ_KEY);
		spinz.setDefaultValue(false);
		rotation.addItemFromInflater(spinz);

		moleculesPref.setDependency(randomMolecule.getKey());

		return root;

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		super.onDestroy();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
	}

}
