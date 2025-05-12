package model.course;

public class Preferences {
	private String sexe; // "Filles", "Garçons", "Indifférent"
	 private boolean music;
	 private boolean bagages;
	//constructeur
	 public Preferences(String sexe, boolean musique, boolean bagages) {
	 this.sexe = sexe;
	 this.music= musique;
	 this.bagages = bagages;
	 }
	 //methode Vérifie la compatibilité des préférences
	 public boolean estCompatible(Preferences autre) {
	 return this.sexe.equals(autre.sexe) && this.music == autre.music &&
	this.bagages == autre.bagages;
	 }
	 // Getters
	 public String getSexe() { return sexe; }
	 public boolean isMusique() { return music; }
	 public boolean isBagages() { return bagages; }
}