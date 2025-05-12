package model.course;
//Faite par BERKANI Elissa
import model.utilisateur.Utilisateur;

public class Evaluation {
	 private double note; // Sur 5
	 private String commentaire;
	 private Utilisateur evaluateur;
	 private Utilisateur evalue;
	//constructeur
	 public Evaluation(double note, String commentaire, Utilisateur evaluateur,
	Utilisateur evalue) {
	 this.note = note;
	 this.commentaire = commentaire;
	 this.evaluateur = evaluateur;
	 this.evalue = evalue;
	 }
	//getter
	 public double getNote() { return note; }
	 public String getCommentaire() { return commentaire; }
	 public Utilisateur getEvaluateur() { return evaluateur; }
	 public Utilisateur getEvalue() { return evalue; }
	}