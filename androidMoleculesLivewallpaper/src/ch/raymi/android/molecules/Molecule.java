package ch.raymi.android.molecules;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class Molecule {
	private final String mName;
	private final String mFilename;
	private final String mInitScript;


	public final static class Molecules {
		private static SortedMap<String, Molecule> mMolecules = new ConcurrentSkipListMap<String, Molecule>();

		static {
			Molecules.registerMolecule(Molecule.createMolecule("Testosterone", "testosterone.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Vitamine C", "vitamine_c.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("TNT", "tnt.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Water", "water.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Nitroglycerin", "nitroglycerin.sdf", null));
			//Molecules.registerMolecule(Molecule.createMolecule("FullereneB", "fullerene.pdb", null));
			Molecules.registerMolecule(Molecule.createMolecule("Caffeine", "caffeine.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Capsaicin", "capsaicin.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Adrenaline", "adrenaline.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Aspirin", "aspirin.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Penicillin", "penicillin.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Oestrogen", "estradiol.sdf", null));
			Molecules.registerMolecule(Molecule.createMolecule("Sulfuric Acid", "sulfuric_acid.sdf", null));
		}

		private Molecules() {
			//prevent initialization
		}

		public static void registerMolecule(Molecule molecule) {
			mMolecules.put(molecule.mName, molecule);
		}

		public static List<Molecule> getMolecules() {
			return new ArrayList<Molecule>(mMolecules.values());
		}

		public static Molecule getMolecule(String name) {
			if (name == null) {
				return null;
			}
			return mMolecules.get(name);
		}
	}

	private Molecule(String name, String filename, String initScript) {
		if (name == null) {
			throw new IllegalArgumentException("molecules must have a name");
		}
		this.mName = name;
		this.mFilename = filename;
		this.mInitScript = initScript;
	}

	public static Molecule createMolecule(String name, String filename, String initScript) {
		return new Molecule(name, filename, initScript);
	}

	public String getFilename() {
		return mFilename;
	}

	public String getInitScript() {
		return mInitScript;
	}

	public String getName() {
		return mName;
	}

}
