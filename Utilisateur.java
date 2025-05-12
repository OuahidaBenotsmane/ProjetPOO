package model.utilisateur;
//Faite par CHOUIA Adel
import java.util.ArrayList;
import java.util.List;

import model.course.Evaluation;

public abstract class Utilisateur {
 protected String nom;
 protected String prenom;
 protected String matricule;
 protected double reputation;
 protected Profil profil;
 protected List<Evaluation> evaluationsRecues;
 //Constructeur 
 public Utilisateur(String nom, String prenom, String matricule) {
 this.nom = nom;
 this.prenom = prenom;
 this.matricule = matricule;
 this.reputation = 5.0;
 this.evaluationsRecues = new ArrayList<>();
 this.profil = new Profil();
 }
 public double getReputation() {
 return reputation;
 }
 public void evaluer(Evaluation evaluation) {
 if (evaluation == null) return;
 evaluationsRecues.add(evaluation);
 double somme = 0;
 for (Evaluation eval : evaluationsRecues) {
 somme += eval.getNote();
 }
 this.reputation = evaluationsRecues.isEmpty() ? 5.0 : somme /
evaluationsRecues.size();
 }
 public String getNom() {
 return nom;
 }
 public String getPrenom() {
 return prenom;
 }
 public String getMatricule() {
 return matricule;
 }
 public Profil getProfil() {
 return profil;}
 public double getNoteMoyenne() {
	    return reputation;
	}
 public void setProfil(Profil profil) {
 this.profil = profil;
 }
 @Override
 public String toString() {
 return prenom + " " + nom + " (Matricule: " + matricule + ", Réputation: " +
reputation + ")";
 }
 public void mettreAJourReputation(double note) {
	    evaluationsRecues.add(new Evaluation(note, "", null, this));
	    double somme = 0;
	    for (Evaluation eval : evaluationsRecues) {
	        somme += eval.getNote();
	    }
	    this.reputation = evaluationsRecues.isEmpty() ? 5.0 : somme / evaluationsRecues.size();
	}

	public int getNombreEvaluations() {
	    return evaluationsRecues.size();
	}


public void ajouterEvaluation(Evaluation evaluation) {
    if (evaluation != null && evaluation.getEvalue() == this) {
        evaluationsRecues.add(evaluation);
        double somme = 0;
        for (Evaluation eval : evaluationsRecues) {
            somme += eval.getNote();
        }
        this.reputation = evaluationsRecues.isEmpty() ? 5.0 : somme / evaluationsRecues.size();
    }
}
public abstract Statut getStatut();
}
