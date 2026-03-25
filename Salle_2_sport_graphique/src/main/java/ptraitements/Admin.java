/*
Vignes Gabriel
Ouchiha Rayane
Groupe CB
 */
package ptraitements;

/**
 * Classe représentant l'administrateur de la salle de sport.
 * Hérite de la classe Utilisateur dont elle reprend les attributs et méthodes d'authentification.
 * 
 * Un seul administrateur est prévu dans cette version de l'application.
 * La logique métier propre à l'admin est centralisée dans la classe Salle
 * pour garder cette classe simple et cohérente.
 * 
 * Pourrait être enrichie si l'on souhaitait gérer plusieurs administrateurs
 * avec des niveaux d'accès différents.
 * 
 * @author rayan
 */
public class Admin extends Utilisateur {
    
    // Aucun attribut supplémentaire à ceux hérités de la classe Utilisateur :
    // l'admin est identifié uniquement par son email et son mot de passe.
    
    /**
     * Constructeur de la classe Admin.
     * Délègue l'initialisation à la classe mère Utilisateur.
     * 
     * @param identifiant Adresse email de l'administrateur
     * @param motDePasse  Mot de passe de l'administrateur
     */
    public Admin(String identifiant, String motDePasse) {
        super(identifiant, motDePasse); // appelle le constructeur de Utilisateur
    }
    
    // Hérite des méthodes getIdentifiant(), getMot2Passe() et setMot2passe() de Utilisateur.
    // La logique des actions admin est centralisée dans la classe Salle, pas besoin d'autres méthodes ici.
}