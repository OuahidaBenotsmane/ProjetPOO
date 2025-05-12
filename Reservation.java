package model.course;

import model.utilisateur.Utilisateur;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {
    
    public enum StatutReservation {
        EN_ATTENTE("En attente de confirmation"),
        CONFIRMEE("Confirmée"),
        ANNULEE("Annulée"),
        TERMINEE("Terminée");
        
        private final String libelle;
        
        StatutReservation(String libelle) {
            this.libelle = libelle;
        }
        
        public String getLibelle() {
            return libelle;
        }
    }
    
    private String id;
    private Course course;
    private Utilisateur passager;
    private StatutReservation statut;
    private LocalDateTime dateReservation;
    private LocalDateTime dateConfirmation;
    private String commentaire;
    private boolean bagageSupplementaire;
    private String pointRamassage;
    
    public Reservation(Course course, Utilisateur passager, String pointRamassage, 
                      boolean bagageSupplementaire, String commentaire) {
        this.id = UUID.randomUUID().toString();
        this.course = course;
        this.passager = passager;
        this.statut = StatutReservation.EN_ATTENTE;
        this.dateReservation = LocalDateTime.now();
        this.pointRamassage = pointRamassage;
        this.bagageSupplementaire = bagageSupplementaire;
        this.commentaire = commentaire;
    }
    
    public boolean confirmer() {
        if (statut == StatutReservation.EN_ATTENTE) {
            if (course.getPlacesDisponibles() > 0) {
                boolean ajoutOk = course.ajouterPassager(passager);
                if (ajoutOk) {
                    this.statut = StatutReservation.CONFIRMEE;
                    this.dateConfirmation = LocalDateTime.now();
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean annuler() {
        if (statut == StatutReservation.EN_ATTENTE || statut == StatutReservation.CONFIRMEE) {
            if (statut == StatutReservation.CONFIRMEE && !course.isTerminee()) {
                // course.retirerPassager(passager);
            }
            
            this.statut = StatutReservation.ANNULEE;
            return true;
        }
        return false;
    }
    
    public void terminer() {
        if (statut == StatutReservation.CONFIRMEE && course.isTerminee()) {
            this.statut = StatutReservation.TERMINEE;
        }
    }
    
    public boolean estCompatibleAvecChauffeur() {
        if (bagageSupplementaire && course.getChauffeur().getProfil().getPreferences()
                .contains(fr.univ.covoiturage.profil.Preference.SANS_BAGAGES)) {
            return false;
        }
        return true;
    }
    
    public String getId() {
        return id;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public Utilisateur getPassager() {
        return passager;
    }
    
    public StatutReservation getStatut() {
        return statut;
    }
    
    public LocalDateTime getDateReservation() {
        return dateReservation;
    }
    
    public LocalDateTime getDateConfirmation() {
        return dateConfirmation;
    }
    
    public String getCommentaire() {
        return commentaire;
    }
    
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public boolean hasBagageSupplementaire() {
        return bagageSupplementaire;
    }
    
    public void setBagageSupplementaire(boolean bagageSupplementaire) {
        this.bagageSupplementaire = bagageSupplementaire;
    }
    
    public String getPointRamassage() {
        return pointRamassage;
    }
    
    public void setPointRamassage(String pointRamassage) {
        this.pointRamassage = pointRamassage;
    }
    
    @Override
    public String toString() {
        return "Réservation #" + id.substring(0, 8) + " - " +
               "Passager: " + passager.getPrenom() + " " + passager.getNom() + " - " +
               "Course: " + course.getId() + " - " +
               "Statut: " + statut.getLibelle() + " - " +
               "Date: " + dateReservation;
    }
}
