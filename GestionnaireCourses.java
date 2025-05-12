package model.course;
//Faite par BENOTSMANE Wahida
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.temps.PlageHoraire;

public class GestionnaireCourses {
    private List<Course> courses;
   
    public enum StatutCourse {
        PLANIFIEE, EN_COURS, TERMINEE
    }
    
    public GestionnaireCourses() {
        this.courses = new ArrayList<>();
    }
    
    public void ajouterCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.setStatutCourse(StatutCourse.PLANIFIEE);
        }
    }
    
    public void supprimerCourse(Course course) {
        courses.remove(course);
    }
    
    public void demarrerCourse(Course course) {
        if (courses.contains(course) && course.getStatutCourse() == StatutCourse.PLANIFIEE) {
            course.setStatutCourse(StatutCourse.EN_COURS);
        }
    }
    
    public void terminerCourse(Course course) {
        if (courses.contains(course) && course.getStatutCourse() != StatutCourse.TERMINEE) {
            course.setStatutCourse(StatutCourse.TERMINEE);
        }
    }
    
    // Méthodes de recherche simplifiées
    public List<Course> getCoursesPlanifiees() {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatutCourse() == StatutCourse.PLANIFIEE) {
                result.add(c);
            }
        }
        return result;
    }
    
    public List<Course> getCoursesTerminees() {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getStatutCourse() == StatutCourse.TERMINEE) {
                result.add(c);
            }
        }
        return result;
    }
    
    public List<Course> getCoursesParDate(String date) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses) {
            if (c.getDateHeure().startsWith(date)) {
                result.add(c);
            }
        }
        return result;
    }

    public void afficherPlanningJournalier() {
        String today = LocalDate.now().toString();
        System.out.println("=== PLANNING DU JOUR (" + today + ") ===");
        
        List<Course> coursesJour = getCoursesParDate(today);
        if (coursesJour.isEmpty()) {
            System.out.println("Aucune course planifiée aujourd'hui.");
            return;
        }
        
        for (Course course : coursesJour) {
            if (course.getStatutCourse() != StatutCourse.TERMINEE) {
                System.out.println(course.getDateHeure().substring(11, 16) + " - " +
                        course.getChauffeur().getNom() + " (" + 
                        course.getItineraire().getPointDepart() + " -> " +
                        course.getItineraire().getPointArrivee() + ")");
            }
        }
    }
    //Méthode pour afficher l'historique des courses
    public void afficherHistorique() {
        System.out.println("=== HISTORIQUE DES COURSES ===");
        List<Course> terminees = getCoursesTerminees();
        
        if (terminees.isEmpty()) {
            System.out.println("Aucune course terminée.");
            return;
        }
        
        for (Course course : terminees) {
            System.out.println("- " + course.getDateHeure() + ": " + 
                    course.getChauffeur().getNom() + " avec " + 
                    course.getPassagers().size() + " passager(s)");
        }
    }

	public List<Course> rechercherCoursesDisponibles(Itineraire itineraire, PlageHoraire plageHoraire,
			Preferences preference) {
		return courses;
	}
}