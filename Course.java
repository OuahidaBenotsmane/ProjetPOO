package model.course;
//Faite par BERKANI Elissa
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.course.GestionnaireCourses.StatutCourse;
import model.utilisateur.Utilisateur;

public class Course {
private Utilisateur chauffeur;
private List<Utilisateur> passagers;
private Itineraire itineraire;
private LocalDateTime horaire;
private int typeCourse;
private String dateHeure; // format attendu : "yyyy-MM-dd HH:mm"
private boolean estTerminee;
private model.course.GestionnaireCourses.StatutCourse statutCourse;

public enum StatutCourse {
    PLANIFIEE,  // Course créée mais pas encore commencée
    EN_COURS,   // Course en cours de réalisation
    TERMINEE}
//constructeur
public Course(Utilisateur chauffeur, Itineraire itineraire, LocalDateTime
mardi10h, int i) {
this.chauffeur = chauffeur;
this.passagers = new ArrayList<>();
this.itineraire = itineraire;
this.horaire = mardi10h;
this.typeCourse = i;
this.estTerminee = false;
}
// methode Ajouter un passager
public boolean ajouterPassager(Utilisateur passager) {
if (!estTerminee) {
passagers.add(passager);
}
return estTerminee;
}
// methode Noter le chauffeur
public void noterChauffeur(Evaluation evaluation) {
if (estTerminee) {
chauffeur.mettreAJourReputation(evaluation.getNote());
}
}
// methode Noter un passager
public void noterPassager(Utilisateur passager, Evaluation evaluation) {
if (estTerminee) {
passager.mettreAJourReputation(evaluation.getNote());
}
}
// methode Terminer la course
public void terminerCourse() {
this.estTerminee = true;
}
// Getters
public Utilisateur getChauffeur() { return chauffeur; }
public List<Utilisateur> getPassagers() { return passagers; }
public Itineraire getItineraire() { return itineraire; }
public LocalDateTime getHoraire() { return horaire; }
public int getTypeCourse() { return typeCourse; }
public String getDateHeure() {return dateHeure;}
public void setDateHeure(String dateHeure) {this.dateHeure = dateHeure;}
public model.course.GestionnaireCourses.StatutCourse getStatutCourse() {
	return statutCourse;}
public String getId() { return "C" + chauffeur.getMatricule() + "-" + System.currentTimeMillis();}

public void setStatutCourse(model.course.GestionnaireCourses.StatutCourse planifiee) {
	this.statutCourse = planifiee;
}

}
