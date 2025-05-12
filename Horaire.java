package model.temps;
//Faite par SIBACHIR Adel
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Horaire {
    private List<PlageHoraire> plages;
    
    public Horaire() {
        this.plages = new ArrayList<>();
    }
    
    public void ajouterPlage(PlageHoraire plage) {
        plages.add(plage);
    }
    
    public boolean estDisponible(LocalDateTime dateTime) {
        // Convertir le jour de la semaine Java en notre enum JourSemaine
        JourSemaine jour = convertirJour(dateTime.getDayOfWeek());
        LocalTime heure = dateTime.toLocalTime();
        
        for (PlageHoraire plage : plages) {
            if (plage.getJour() == jour && 
                (heure.isAfter(plage.getHeureDebut()) || heure.equals(plage.getHeureDebut())) && 
                (heure.isBefore(plage.getHeureFin()) || heure.equals(plage.getHeureFin()))) {
                return true;
            }
        }
        
        return false;
    }
    
    private JourSemaine convertirJour(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY: return JourSemaine.LUNDI;
            case TUESDAY: return JourSemaine.MARDI;
            case WEDNESDAY: return JourSemaine.MERCREDI;
            case THURSDAY: return JourSemaine.JEUDI;
            case FRIDAY: return JourSemaine.VENDREDI;
            case SATURDAY: return JourSemaine.SAMEDI;
            case SUNDAY: return JourSemaine.DIMANCHE;
            default: throw new IllegalArgumentException("Jour non reconnu");
        }
    }
    
    public List<PlageHoraire> getPlages() {
        return new ArrayList<>(plages);
    }
}
