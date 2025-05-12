package model.utilisateur;
// Faite par LEKHAL Abir
public class ATS extends Utilisateur {
    private int anneeRecrutement;
    private String serviceRattachement;
    
    public ATS(String nom, String prenom, String matricule, 
               int anneeRecrutement, String serviceRattachement) {
        super(nom, prenom, matricule);
        this.anneeRecrutement = anneeRecrutement;
        this.serviceRattachement = serviceRattachement;
    }
    
    public int getAnneeRecrutement() {
        return anneeRecrutement;
    }
    
    public String getServiceRattachement() {
        return serviceRattachement;
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Personnel ATS au service " + serviceRattachement + " depuis " + anneeRecrutement;
    }

	@Override
	public Statut getStatut() {
		return null;
	}
}
