/*
Vignes Gabriel
Ouchiha Rayane
Groupe CB
 */
package ptraitements;

/**
 * Enumération des types de cours proposés dans la salle de sport.
 * Associée à chaque cours lors de sa création par l'administrateur.
 * Permet de catégoriser et filtrer les cours par discipline.
 * Stockée sous forme de String dans les fichiers de sauvegarde via .toString() / .valueOf().
 * 
 * @author rayan
 */
public enum TypeCours {
    Individuel,  // Cours individuel : 1 seule place disponible, attribuée automatiquement dans le constructeur de Cours
    Collectif    // Cours collectif : nombre de places défini lors de la création du cours
}