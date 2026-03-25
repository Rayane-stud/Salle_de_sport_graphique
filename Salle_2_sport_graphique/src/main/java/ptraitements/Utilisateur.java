/*
Vignes Gabriel
Ouchiha Rayane
Groupe CB
 */
package ptraitements;

/**
 * Classe abstraite représentant un utilisateur de l'application.
 * Sert de base commune aux classes Client et Admin.
 * Regroupe les informations d'authentification partagées par les deux types d'utilisateurs.
 * 
 * @author rayan
 */
public abstract class Utilisateur {
    
    // Attributs en protected pour être accessibles directement dans les classes filles
    // sans passer par des getters, tout en restant encapsulés vis-à-vis de l'extérieur
    protected String id_email;   // Identifiant de connexion (adresse email)
    protected String motDepasse; // Mot de passe de connexion
    
    /**
     * Constructeur de la classe Utilisateur.
     * Initialise l'identifiant email et le mot de passe.
     * 
     * @param id  Adresse email servant d'identifiant unique
     * @param mdp Mot de passe associé au compte
     */
    public Utilisateur(String id, String mdp) {
        id_email = id;
        motDepasse = mdp;
    }
    
    // _________________________________________________ Getters :

    /**
     * Retourne l'identifiant (email) de l'utilisateur.
     * 
     * @return l'adresse email utilisée comme identifiant
     */
    public String getIdentifiant() {
        return id_email;
    }
    
    /**
     * Retourne le mot de passe de l'utilisateur.
     * Utilisé pour la vérification lors de la connexion ou du changement de mot de passe.
     * 
     * @return le mot de passe actuel
     */
    public String getMot2Passe() {
        return motDepasse;
    }
    
    // _________________________________________________ Modifieur :

    /**
     * Modifie le mot de passe de l'utilisateur.
     * Appelée uniquement après vérification de l'ancien mot de passe dans la classe Salle.
     * 
     * @param mdp le nouveau mot de passe à appliquer
     */
    public void setMot2passe(String mdp) {
        this.motDepasse = mdp;
    }
}