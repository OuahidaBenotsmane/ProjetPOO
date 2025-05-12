package model.course;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.utilisateur.Utilisateur;

public class GestionnaireReservation {
    private List<Reservation> reservations;
    
    public GestionnaireReservation() {
        this.reservations = new ArrayList<>();
    }
    
    public Reservation creerReservation(Course course, Utilisateur passager, 
                                       String pointRamassage, boolean bagageSupplementaire, 
                                       String commentaire) {
        if (passager.getProfil().getStatut() != fr.univ.covoiturage.profil.Statut.PASSAGER) {
            throw new IllegalArgumentException("L'utilisateur doit être un passager pour faire une réservation");
        }
        
        if (course.isTerminee() || course.getPlacesDisponibles() <= 0) {
            throw new IllegalArgumentException("La course n'est pas disponible pour réservation");
        }
        
        Reservation reservation = new Reservation(course, passager, pointRamassage, 
                                                bagageSupplementaire, commentaire);
        reservations.add(reservation);
        return reservation;
    }
    
    public List<Reservation> getReservationsUtilisateur(Utilisateur utilisateur) {
        return reservations.stream()
                .filter(r -> r.getPassager().equals(utilisateur))
                .collect(Collectors.toList());
    }
    
    public List<Reservation> getReservationsCourse(Course course) {
        return reservations.stream()
                .filter(r -> r.getCourse().equals(course))
                .collect(Collectors.toList());
    }
    
    public List<Reservation> getReservationsEnAttente(Course course) {
        return reservations.stream()
                .filter(r -> r.getCourse().equals(course) && 
                       r.getStatut() == Reservation.StatutReservation.EN_ATTENTE)
                .collect(Collectors.toList());
    }
    
    public int confirmerReservationsEnAttente(Course course) {
        List<Reservation> enAttente = getReservationsEnAttente(course);
        int placesDisponibles = course.getPlacesDisponibles();
        int confirmees = 0;
        
        for (Reservation reservation : enAttente) {
            if (placesDisponibles > 0 && reservation.confirmer()) {
                placesDisponibles--;
                confirmees++;
            }
        }
        
        return confirmees;
    }
    
    public int annulerReservationsEnAttente(Course course) {
        List<Reservation> enAttente = getReservationsEnAttente(course);
        int annulees = 0;
        
        for (Reservation reservation : enAttente) {
            if (reservation.annuler()) {
                annulees++;
            }
        }
        
        return annulees;
    }
    
    public void terminerReservations(Course course) {
        if (course.isTerminee()) {
            reservations.stream()
                    .filter(r -> r.getCourse().equals(course) && 
                           r.getStatut() == Reservation.StatutReservation.CONFIRMEE)
                    .forEach(Reservation::terminer);
        }
    }
}
