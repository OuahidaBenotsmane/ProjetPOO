package model.course;
//Faite par BERKANI Elissa
public class Itineraire {
	 private String pointDepart;
	 private String pointArrivee;
	//constructeur
	 public Itineraire(String pointDepart, String pointArrivee) {
	 this.pointDepart = pointDepart;
	 this.pointArrivee = pointArrivee;
	 }
	 //methode qui vérifie la compatibilité entre deux itinéraires
	 public boolean estCompatible(Itineraire autre) {
	 return this.pointDepart.equals(autre.pointDepart) &&
	this.pointArrivee.equals(autre.pointArrivee);
	 }
	 // Getters
	 public String getPointDepart() {return pointDepart; }
	 public String getPointArrivee() { return pointArrivee; }
	}