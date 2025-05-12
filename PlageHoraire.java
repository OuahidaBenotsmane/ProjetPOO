package model.temps;
// Faite par SIBACHIR Adel
import java.time.LocalTime;

public class PlageHoraire {
    private JourSemaine jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private boolean recurrent;
    
    public PlageHoraire(JourSemaine jour, LocalTime heureDebut, LocalTime heureFin, boolean recurrent) {
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.recurrent = recurrent;
    }
    
    public boolean chevauche(PlageHoraire autre) {
        if (this.jour != autre.jour) {
            return false;
        }
        return !(this.heureFin.isBefore(autre.heureDebut) || this.heureDebut.isAfter(autre.heureFin));
    }
    
    public JourSemaine getJour() {
        return jour;
    }
    
    public LocalTime getHeureDebut() {
        return heureDebut;
    }
    
    public LocalTime getHeureFin() {
        return heureFin;
    }
    
    public boolean isRecurrent() {
        return recurrent;
    }
}



