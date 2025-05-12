package model.utilisateur;
//Faite par SIBACHIR Adel
import java.util.ArrayList;
import java.util.List;

import model.course.Itineraire;
import model.course.Preferences;
import model.temps.PlageHoraire;

public class Profil {
    private Statut statut;
    private Itineraire itineraire;
    private List<Preferences> preferences;
    private List<PlageHoraire> horaires;
    private String typeCourse;
    
    public Profil() {
        this.statut = Statut.PASSAGER; // Par défaut
        this.preferences = new ArrayList<>();
        this.horaires = new ArrayList<>();
        this.typeCourse = "ALLER_RETOUR"; // Par défaut
    }
    
    public Statut getStatut() {
        return statut;
    }
    
    public void setStatut(Statut statut) {
        this.statut = statut;
    }
    
    public Itineraire getItineraire() {
        return itineraire;
    }
    
    public void setItineraire(Itineraire itineraire) {
        this.itineraire = itineraire;
    }
    
    public List<Preferences> getPreferences() {
        return new ArrayList<>(preferences);
    }
    
    public void ajouterPreference(Preferences preference) {
        preferences.add(preference);
    }
    
    public List<PlageHoraire> getHoraires() {
        return new ArrayList <>(horaires);
    }
    
    public void ajouterPlageHoraires(PlageHoraire horaires) {
        horaires.add(horaires);
    }
    
    public String getTypeCourse() {
        return typeCourse;
    }
    
    public void setTypeCourse(String typeCourse) {
        this.typeCourse = typeCourse;
    }
}
