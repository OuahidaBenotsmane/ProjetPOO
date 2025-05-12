package model.utilisateur;
//Faite par CHOUIA Adel
public class Etudiant extends Utilisateur {
 private int anneeAdmission;
 private String faculte;
 private String specialite;
 public Etudiant(String nom, String prenom, String matricule, int anneeAdmission,
String faculte, String specialite) {
 super(nom, prenom, matricule);
 this.anneeAdmission = anneeAdmission;
 this.faculte = faculte;
 this.specialite = specialite;
 }
 public int getAnneeAdmission() {
 return anneeAdmission;
 }
 public String getFaculte() {
 return faculte;
 }
 public String getSpecialite() {
 return specialite;
 }
 @Override
 public String toString() {
 return super.toString() + " - Étudiant en " + specialite + " à " + faculte + 
"depuis " + anneeAdmission;
 }
@Override
public Statut getStatut() {
	return null;
}
}
