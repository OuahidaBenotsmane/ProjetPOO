package main;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
//Faite par LEKHAL Abir
public class Main {
    public static void main(String[] args) {
        Administration admin = new Administration();
        
        Point universite = new Point(36.7058, 3.1717, "Université de Bab-Ezzouar");
        Point alger = new Point(36.7538, 3.0588, "Alger Centre");
        Point kouba = new Point(36.7173, 3.0874, "Kouba");
        Point birkhadem = new Point(36.7173, 3.0519, "Bir Khadem");
        
        Etudiant etudiant1 = new Etudiant("Benali", "Karim", "E12345", 2020, "Informatique", "Génie Logiciel");
        Etudiant etudiant2 = new Etudiant("Hadj", "Amina", "E23456", 2021, "Informatique", "Réseaux");
        Enseignant enseignant1 = new Enseignant("Boudiaf", "Mohamed", "P12345", 2015, "Informatique");
        ATS ats1 = new ATS("Kaci", "Samira", "A12345", 2018, "Administration");
        
        admin.ajouterUtilisateur(etudiant1);
        admin.ajouterUtilisateur(etudiant2);
        admin.ajouterUtilisateur(enseignant1);
        admin.ajouterUtilisateur(ats1);
        
        Profil profilEtudiant1 = etudiant1.getProfil();
        profilEtudiant1.setStatut(Statut.CHAUFFEUR);
        Itineraire itineraireEtudiant1 = new Itineraire(alger, universite);
        profilEtudiant1.setItineraire(itineraireEtudiant1);
        profilEtudiant1.ajouterPreference(Preference.INDIFFERENT);
        profilEtudiant1.ajouterPreference(Preference.AVEC_MUSIQUE);
        Horaire horaireEtudiant1 = new Horaire();
        horaireEtudiant1.ajouterPlage(new PlageHoraire(JourSemaine.LUNDI, LocalTime.of(8, 0), LocalTime.of(9, 0), true));
        horaireEtudiant1.ajouterPlage(new PlageHoraire(JourSemaine.MERCREDI, LocalTime.of(8, 0), LocalTime.of(9, 0), true));
        profilEtudiant1.setHoraires(horaireEtudiant1);
        profilEtudiant1.setTypeCourse(TypeCourse.ALLER_RETOUR);
        
        Profil profilEtudiant2 = etudiant2.getProfil();
        profilEtudiant2.setStatut(Statut.PASSAGER);
        Itineraire itineraireEtudiant2 = new Itineraire(kouba, universite);
        profilEtudiant2.setItineraire(itineraireEtudiant2);
        profilEtudiant2.ajouterPreference(Preference.FILLES_UNIQUEMENT);
        profilEtudiant2.ajouterPreference(Preference.AVEC_MUSIQUE);
        Horaire horaireEtudiant2 = new Horaire();
        horaireEtudiant2.ajouterPlage(new PlageHoraire(JourSemaine.LUNDI, LocalTime.of(8, 0), LocalTime.of(9, 0), true));
        horaireEtudiant2.ajouterPlage(new PlageHoraire(JourSemaine.MERCREDI, LocalTime.of(8, 0), LocalTime.of(9, 0), true));
        profilEtudiant2.setHoraires(horaireEtudiant2);
        profilEtudiant2.setTypeCourse(TypeCourse.ALLER_SIMPLE);
        
        Profil profilEnseignant = enseignant1.getProfil();
        profilEnseignant.setStatut(Statut.CHAUFFEUR);
        Itineraire itineraireEnseignant = new Itineraire(birkhadem, universite);
        profilEnseignant.setItineraire(itineraireEnseignant);
        profilEnseignant.ajouterPreference(Preference.INDIFFERENT);
        profilEnseignant.ajouterPreference(Preference.SANS_MUSIQUE);
        Horaire horaireEnseignant = new Horaire();
        horaireEnseignant.ajouterPlage(new PlageHoraire(JourSemaine.MARDI, LocalTime.of(10, 0), LocalTime.of(11, 0), true));
        horaireEnseignant.ajouterPlage(new PlageHoraire(JourSemaine.JEUDI, LocalTime.of(14, 0), LocalTime.of(15, 0), true));
        profilEnseignant.setHoraires(horaireEnseignant);
        profilEnseignant.setTypeCourse(TypeCourse.ALLER_RETOUR);
        
        LocalDateTime lundi8h = LocalDateTime.now().withHour(8).withMinute(0).withSecond(0);
        Course course1 = new Course(etudiant1, itineraireEtudiant1, lundi8h, 3);
        
        LocalDateTime mardi10h = LocalDateTime.now().withHour(10).withMinute(0).withSecond(0);
        Course course2 = new Course(enseignant1, itineraireEnseignant, mardi10h, 2);
        
        admin.ajouterCourse(course1);
        admin.ajouterCourse(course2);
        
        boolean ajoutOk = course1.ajouterPassager(etudiant2);
        System.out.println("Ajout de " + etudiant2.getPrenom() + " à la course de " + etudiant1.getPrenom() + ": " + 
                          (ajoutOk ? "Réussi" : "Échoué"));
        
        course1.terminerCourse();
        
        Evaluation evalChauffeur = new Evaluation(etudiant2, etudiant1, 4, "Très bon trajet, à l'heure");
        Evaluation evalPassager = new Evaluation(etudiant1, etudiant2, 5, "Passagère ponctuelle et agréable");
        
        course1.ajouterEvaluation(evalChauffeur);
        course1.ajouterEvaluation(evalPassager);
        
        Statistiques stats = admin.genererStatistiques();
        System.out.println("\n--- Statistiques ---");
        System.out.println("Nombre d'étudiants: " + stats.getNbEtudiants());
        System.out.println("Nombre d'enseignants: " + stats.getNbEnseignants());
        System.out.println("Nombre d'ATS: " + stats.getNbATS());
        System.out.println("Nombre d'utilisateurs actifs: " + stats.getNbUtilisateursActifs());
        
        System.out.println("\nCourses par catégorie:");
        stats.getCourseParCategorie().forEach((categorie, nombre) -> 
            System.out.println("- " + categorie + ": " + nombre)
        );
        
        System.out.println("\nTop chauffeurs:");
        stats.getTopChauffeurs().forEach(chauffeur -> 
            System.out.println("- " + chauffeur.getPrenom() + " " + chauffeur.getNom() + 
                              " (Réputation: " + chauffeur.getReputation() + ")")
        );
        
        GestionnaireRecherche gestionnaire = new GestionnaireRecherche();
        gestionnaire.ajouterCourse(course1);
        gestionnaire.ajouterCourse(course2);
        
        System.out.println("\n--- Recherche de courses pour " + etudiant2.getPrenom() + " ---");
        List<Course> coursesDisponibles = gestionnaire.rechercherCourses(etudiant2);
        if (coursesDisponibles.isEmpty()) {
            System.out.println("Aucune course disponible correspondant aux critères.");
        } else {
            System.out.println("Courses disponibles:");
            coursesDisponibles.forEach(course -> 
                System.out.println("- " + course)
            );
        }
    }
}
