package model.course;
//Faite par BENOTSMANE Wahida
import java.util.List;
import model.utilisateur.ATS;
import model.utilisateur.Enseignant;
import model.utilisateur.Etudiant;
import model.utilisateur.Statut;
import model.utilisateur.Utilisateur;

public class Statistiques {
    public static void afficherStatistiques(List<Utilisateur> utilisateurs, GestionnaireCourses gestionnaireCourses) {
        System.out.println("=== STATISTIQUES ===");
        
        // Compter les utilisateurs par type
        int etudiants = 0, enseignants = 0, ats = 0, actifs = 0;
        for (Utilisateur u : utilisateurs) {
            if (u instanceof Etudiant) etudiants++;
            else if (u instanceof Enseignant) enseignants++;
            else if (u instanceof ATS) ats++;
            if (u.getNombreEvaluations() > 0) actifs++;
        }
        
        System.out.println("Étudiants: " + etudiants + " | Enseignants: " + enseignants +  " | ATS: " + ats + " | Actifs: " + actifs);
        
        // Courses par catégorie
        int coursesEtudiants = 0, coursesEnseignants = 0, coursesATS = 0;
        for (Course c : gestionnaireCourses) {
            Utilisateur chauffeur = c.getChauffeur();
            if (chauffeur instanceof Etudiant) coursesEtudiants++;
            else if (chauffeur instanceof Enseignant) coursesEnseignants++;
            else if (chauffeur instanceof ATS) coursesATS++;
        }
         System.out.println("Courses: Étudiants=" + coursesEtudiants +  ", Enseignants=" + coursesEnseignants + ", ATS=" + coursesATS);
        
        // Top chauffeurs et utilisateurs à bannir
        afficherTopChauffeurs(utilisateurs);
        afficherUtilisateursABannir(utilisateurs);
    }
    
    // Affiche les meilleurs chauffeurs
    private static void afficherTopChauffeurs(List<Utilisateur> utilisateurs) {
        System.out.println("\nTop chauffeurs:");
        
        // Filtrer et trier les chauffeurs
        List<Utilisateur> chauffeurs = new java.util.ArrayList<>();
        for (Utilisateur u : utilisateurs) {
            if (u.getProfil() != null && u.getProfil().getStatut() == Statut.CHAUFFEUR) {
                chauffeurs.add(u);
            }
        }
      
        for (int i = 0; i < chauffeurs.size() - 1; i++) {
            for (int j = 0; j < chauffeurs.size() - i - 1; j++) {
                if (chauffeurs.get(j).getNoteMoyenne() < chauffeurs.get(j + 1).getNoteMoyenne()) {
                    Utilisateur temp = chauffeurs.get(j);
                    chauffeurs.set(j, chauffeurs.get(j + 1));
                    chauffeurs.set(j + 1, temp);}
                }
            }
        }
     
    // Affiche les utilisateurs à bannir
    private static void afficherUtilisateursABannir(List<Utilisateur> utilisateurs) {
        System.out.println("\nUtilisateurs à bannir:");
        boolean aucun = true;
        
        for (Utilisateur u : utilisateurs) {
            if (u.getNoteMoyenne() < 2.0 && u.getNombreEvaluations() >= 5) {
                System.out.println("- " + u.getNom() + " (Note: " + u.getNoteMoyenne() + ")");
                aucun = false; }
        }
        if (aucun) System.out.println("Aucun");}
}