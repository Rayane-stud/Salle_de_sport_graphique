/*
Vignes Gabriel
Ouchiha Rayane
Groupe CB
 */
package ptraitements;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe principale de l'application Salle de Sport.
 * Contient la méthode main() qui sert de point d'entrée au programme.
 * 
 * Dans cette version console (1er semestre), la méthode main() est utilisée
 * comme banc de tests : elle instancie les objets principaux et valide
 * le bon fonctionnement des méthodes clés de chaque classe.
 * 
 * @author rayan
 */
public class Salle_2_sport_graphique {

    /**
     * Point d'entrée du programme.
     * Réalise une suite de tests unitaires en mode console :
     *  - Création de l'admin et de la salle
     *  - Création de clients et affichage de leurs profils
     *  - Création de cours et affichage
     *  - Inscription et désinscription d'un client
     *  - Modification d'un cours
     *  - Sauvegarde et rechargement des données depuis les fichiers texte
     * 
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        
        System.out.println("===== TEST PROJET SALLE DE SPORT =====\n");
        
        // ── Test 1 : Création de l'administrateur et de la salle ─────────────────────
        Admin admin = new Admin("admin@salle.fr", "admin123");
        Salle salle = new Salle("Fitness Club", admin, "data.txt");
        
        // ── Test 2 : Création de deux clients ─────────────────────────────────────────
        // Vérifie que creerCompte() ajoute bien les clients et leur attribue un numéro
        Client c1 = salle.creerCompte("rayane@mail.com", "1234",
                "Ouchiha", "Rayane", "0600000000",
                "Paris", TypeAbonnement.ANNUEL, "Photo");
        
        Client c2 = salle.creerCompte("gabriel@mail.com", "abcd",
                "Vignes", "Gabriel", "0700000000",
                "Lyon", TypeAbonnement.TRIMESTRIEL, "Photo");
        
        System.out.println("Clients créés : ");
        c1.afficherProfil();
        c2.afficherProfil();
        
        // ── Test 3 : Création de deux cours futurs ────────────────────────────────────
        // Les dates sont définies dans le futur (plusDays) pour passer les vérifications d'inscription
        Cours cours1 = salle.creerCours(
                "Cardio Training",
                LocalDate.now().plusDays(2),  // dans 2 jours
                LocalTime.of(18, 0),
                TypeCours.Individuel,
                10
        );
        
        Cours cours2 = salle.creerCours(
                "Yoga Relax",
                LocalDate.now().plusDays(3),  // dans 3 jours
                LocalTime.of(10, 0),
                TypeCours.Collectif,
                5
        );
        
        System.out.println("\nCours créés :");
        cours1.afficherCours();
        cours2.afficherCours();
        
        // ── Test 4 : Inscription des clients aux cours ────────────────────────────────
        // Doit retourner true si toutes les conditions sont remplies (actif, places dispo, futur)
        System.out.println("\nInscription Rayane au Cardio : "
                + salle.sInscrireACours(c1, cours1)); // attendu : true
        System.out.println("Inscription Gabriel au Yoga : "
                + salle.sInscrireACours(c2, cours2)); // attendu : true
        
        // ── Test 5 : Affichage des cours futurs du client ─────────────────────────────
        System.out.println("\nCours futurs Rayane :");
        c1.afficherCoursFuturs(); // doit afficher "Cardio Training"
        
        // ── Test 6 : Désinscription ───────────────────────────────────────────────────
        // Doit retourner true et vider la liste des cours futurs de Rayane
        System.out.println("\nDésinscription Rayane du Cardio : "
                + salle.seDesinscrireDeCours(c1, cours1)); // attendu : true
        c1.afficherCoursFuturs(); // doit afficher "Aucun cours à venir"
        
        // ── Test 7 : Modification d'un cours ─────────────────────────────────────────
        // Possible car plus aucun client inscrit au cours Yoga (Gabriel toujours inscrit)
        // Ici on modifie uniquement le nombre de places
        System.out.println("\nModification Yoga (places = 20)");
        salle.modifierCours(cours2,
                "Yoga Relax",
                cours2.getDatecour(),
                cours2.getHeurecour(),
                TypeCours.Collectif,
                20); // attendu : false car Gabriel est encore inscrit au cours2
        cours2.afficherCours(); // le nombre de places ne doit pas avoir changé
        
        // ── Test 8 : Sauvegarde des données ──────────────────────────────────────────
        // Ecrit les clients et cours dans clients.txt et cours.txt
        System.out.println("\nSauvegarde...");
        salle.sauvegarderTout();
        
        // ── Test 9 : Rechargement des données (simulation d'un redémarrage) ──────────
        // Vide les listes et les recharge depuis les fichiers
        System.out.println("Chargement...");
        salle.chargerTout();
        
        // Vérification que les données ont bien été restaurées
        System.out.println("\nListe clients après chargement :");
        for (Client c : salle.getListeClients()) {
            c.afficherProfil();
        }
        
        System.out.println("\nListe cours après chargement :");
        salle.ConsulterListeCoursFuturs(); // doit afficher Cardio Training et Yoga Relax
        
        System.out.println("\n===== FIN DES TESTS =====");
    }
}

//Ajout pour test de commit 