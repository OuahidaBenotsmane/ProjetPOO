package model.utilisateur;
//Faite par CHOUIA Adel
public class Enseignant extends Utilisateur {
	 private int anneeRecrutement;
	 private String faculte;

	 public Enseignant(String nom, String prenom, String matricule,
	 int anneeRecrutement, String faculte) {
	 super(nom, prenom, matricule);
	 this.anneeRecrutement = anneeRecrutement;
	 this.faculte = faculte;
	 }

	 public int getAnneeRecrutement() {
	 return anneeRecrutement;
	 }

	 public String getFaculte() {
	 return faculte;
	 }

	 @Override
	 public String toString() {
	 return super.toString() + " - Enseignant à " + faculte + " depuis " + anneeRecrutement;
	 }


}
