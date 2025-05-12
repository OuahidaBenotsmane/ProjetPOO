package model.course;
//Faite par BENOTSMANE Wahida
import java.util.ArrayList;
import java.util.List;
import model.temps.PlageHoraire;
import model.utilisateur.Utilisateur;

public class GestionnaireApplication {
    private List<Utilisateur> utilisateurs;
    private List<Utilisateur> utilisateursBannis;
    private GestionnaireCourses gestionnaireCourses;
    
    public GestionnaireApplication() {
        this.utilisateurs = new ArrayList<>();
        this.utilisateursBannis = new ArrayList<>();
        this.gestionnaireCourses = new GestionnaireCourses();
    }
    
    // Méthodes pour gérer les utilisateurs
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        if (!utilisateurs.contains(utilisateur) && !utilisateursBannis.contains(utilisateur)) {
            utilisateurs.add(utilisateur);
            System.out.println("Nouvel utilisateur enregistré: " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        } else if (utilisateursBannis.contains(utilisateur)) {
            System.out.println("Impossible d'ajouter un utilisateur banni: " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        } else {
            System.out.println("Cet utilisateur existe déjà: " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        }
    }
    //Méthode pour supprimer un utilisateur 
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        if (utilisateurs.remove(utilisateur)) {
            System.out.println("Utilisateur supprimé: " + utilisateur.getNom() + " " + utilisateur.getPrenom());
        } else { System.out.println("Utilisateur non trouvé dans le système");}
    }
 //Méthode pour bannir un utilisateur problématique
    public void bannirUtilisateur(Utilisateur utilisateur) {
        if (utilisateurs.contains(utilisateur) && !utilisateursBannis.contains(utilisateur)) {
            utilisateurs.remove(utilisateur);
            utilisateursBannis.add(utilisateur);
            System.out.println("Utilisateur banni: " + utilisateur.getNom() + " " + utilisateur.getPrenom());}
    }
    
    public boolean estBanni(Utilisateur utilisateur) {return utilisateursBannis.contains(utilisateur);}
    
    // Méthodes pour gérer les courses
    public void ajouterCourse(Course course) {gestionnaireCourses.ajouterCourse(course);}
    
    public void supprimerCourse(Course course) { gestionnaireCourses.supprimerCourse(course);}
    
    public List<Course> rechercherCoursesDisponibles(Itineraire itineraire, PlageHoraire plageHoraire, Preferences preference) {
    	return gestionnaireCourses.rechercherCoursesDisponibles(itineraire, plageHoraire, preference); }
    
    public void terminerCourse(Course course, Evaluation[] evaluations) {
        //  Marquer la course comme terminée
        course.terminerCourse();
         // Enregistrer les évaluations
        if (evaluations != null) {
            for (Evaluation evaluation : evaluations) {
                if (evaluation != null) {
                    Utilisateur evalue = evaluation.getEvalue();
                    evalue.evaluer(evaluation);}
            }
        }
    }
    
    // Méthodes pour visualiser les données
    public List<Course> visualiserCoursesEnCours() {return gestionnaireCourses.getCoursesPlanifiees(); }
    
    public void afficherCoursesEnCours() {
        List<Course> coursesEnCours = visualiserCoursesEnCours();
        System.out.println("=== COURSES EN COURS (" + coursesEnCours.size() + ") ===");
        if (coursesEnCours.isEmpty()) {
            System.out.println("Aucune course n'est en cours actuellement.");
            return; }
        for (Course course : coursesEnCours) {
            System.out.println(course);}
    }
    
    public void afficherPlanningJournalier() { gestionnaireCourses.afficherPlanningJournalier();}
    
    public void afficherHistoriqueCourses() { gestionnaireCourses.afficherHistorique();}
    
    public void afficherStatistiques() { Statistiques.afficherStatistiques(utilisateurs, gestionnaireCourses);}
    
    // Getters
    public List<Utilisateur> getUtilisateurs() {return new ArrayList<>(utilisateurs);}
    
    public List<Utilisateur> getUtilisateursBannis() { return new ArrayList<>(utilisateursBannis);}
    
    public GestionnaireCourses getGestionnaireCourses() { return gestionnaireCourses;}
    
    // Méthode principale pour démarrer l'application
    public void demarrer() {
        System.out.println("Application de covoiturage démarrée!");
    }
}