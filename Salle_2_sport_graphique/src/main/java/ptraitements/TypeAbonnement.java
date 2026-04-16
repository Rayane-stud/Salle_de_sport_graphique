/*
Vignes Gabriel
Ouchiha Rayane
Groupe CB
 */
package ptraitements;

/**
 * Enumération des types d'abonnement disponibles dans la salle de sport.
 * Utilisée lors de la création d'un compte client et lors des recherches par type.
 * Stockée sous forme de String dans les fichiers de sauvegarde via .toString() / .valueOf().
 * 
 * @author rayan
 */
public enum TypeAbonnement {
    ANNUEL,       // Abonnement valable 12 mois
    TRIMESTRIEL,  // Abonnement valable 3 mois
    SEMAINE     // Abonnement valable 1 semaine (accès court terme)
}
