/*
Vignes Gabriel
Ouchiha Rayane
Groupe CB
 */
package ptraitements;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un cours proposé dans la salle de sport.
 * Un cours est caractérisé par une activité, une date, une heure, un type,
 * un nombre de places et une liste d'inscrits.
 * 
 * Implémente Comparable pour permettre le tri des cours par popularité
 * (nombre de clients inscrits), utilisé dans getCoursPlusPopulaires() et
 * getCoursMoinsPopulaires() de la classe Salle.
 * 
 * L'identifiant id_cours est généré et attribué par la classe Salle
 * lors de l'appel à creerCours().
 * 
 * @author rayan
 */
public class Cours implements Comparable<Cours> {
    
    // _________________________________________________ Attributs :
    private String activite;                  // Nom de l'activité (ex : "Yoga Relax")
    private LocalDate date;                   // Date du cours
    private LocalTime heure;                  // Heure de début du cours
    private TypeCours typeCours;              // Type du cours : Individuel (1 place) ou Collectif (n places)
    private ArrayList<Client> listeInscrits;  // Liste des clients inscrits à ce cours
    private int nbre_places;                  // Nombre maximum de places (forcé à 1 si Individuel)
    private int id_cours;                     // Identifiant unique du cours, utile pour la recherche,
                                              // le stockage, la suppression précise et l'identification
                                              // sans ambiguïté (évite les doublons)
    
    /**
     * Constructeur de la classe Cours.
     * L'identifiant id_cours est généré dans la classe Salle via prochainIdCours
     * et passé en paramètre lors de la création du cours.
     * 
     * Si le type est Individuel, le nombre de places est automatiquement forcé à 1,
     * quelle que soit la valeur passée en paramètre.
     * 
     * @param activite     Nom de l'activité
     * @param date         Date du cours
     * @param heure        Heure de début
     * @param typeCours    Type de cours : Individuel ou Collectif
     * @param nombrePlaces Nombre maximum de participants (ignoré si Individuel)
     * @param id           Identifiant unique attribué par la Salle
     */
    public Cours(String activite, LocalDate date, LocalTime heure, TypeCours typeCours, int nombrePlaces, int id) {
        this.activite = activite;
        this.date = date;
        this.heure = heure;
        this.typeCours = typeCours;
        if (this.typeCours == TypeCours.Individuel) {
            nombrePlaces = 1; // un cours individuel n'accepte qu'un seul client
        }
        this.nbre_places = nombrePlaces;
        this.listeInscrits = new ArrayList<>();
        this.id_cours = id;
    }
    
    /**
     * Méthode de comparaison pour le tri par popularité (grand → petit).
     * Utilisée par Collections.sort() dans getCoursPlusPopulaires() de Salle.
     * Un cours avec plus d'inscrits sera considéré "plus grand" (priorité haute dans le tri).
     * 
     * @param autre le cours avec lequel comparer
     * @return valeur positive si autre a plus d'inscrits, négative sinon
     */
    @Override
    public int compareTo(Cours autre) {
        return autre.getNbreInscrits() - this.getNbreInscrits(); // tri décroissant : grand → petit
    }
    
    
    // _________________________________________________ Getters :

    /** @return l'identifiant unique du cours */
    public int getIdCours() {
        return id_cours;
    }

    /** @return le nom de l'activité du cours */
    public String getActivitecour() {
        return activite;
    }

    /** @return la date du cours */
    public LocalDate getDatecour() {
        return date;
    }

    /** @return l'heure de début du cours */
    public LocalTime getHeurecour() {
        return heure;
    }

    /** @return le type du cours (Individuel ou Collectif) */
    public TypeCours getTypeCours() {
        return typeCours;
    }

    /** @return le nombre total de places disponibles dans ce cours */
    public int getNombrePlacescour() {
        return nbre_places;
    }

    /** @return la liste des clients inscrits à ce cours */
    public List<Client> getClientsInscritscours() {
        return listeInscrits;
    }
    
    
    // _________________________________________________ Modifieurs :
    // Pas de modifieur direct pour la liste des inscrits :
    // toute modification passe par ajouterClient() ou retirerClient()
    // pour garantir les vérifications de cohérence (places disponibles, doublons).
    
    /** @param idCours nouvel identifiant du cours */
    public void setIdCours(int idCours)           { this.id_cours = idCours; }

    /** @param activite nouveau nom de l'activité */
    public void setActivite(String activite)      { this.activite = activite; }

    /** @param date nouvelle date du cours */
    public void setDate(LocalDate date)           { this.date = date; }

    /** @param heure nouvelle heure de début */
    public void setHeure(LocalTime heure)         { this.heure = heure; }

    /** @param typeCours nouveau type de cours */
    public void setTypeCours(TypeCours typeCours) { this.typeCours = typeCours; }

    /** @param nombrePlaces nouveau nombre maximum de places */
    public void setNombrePlaces(int nombrePlaces) { this.nbre_places = nombrePlaces; }
    
    
    // _________________________________________________ Méthodes de gestion des inscriptions :
    // Ces méthodes, bien que "triviales", rendent le code de la classe Salle plus lisible
    // et allègent les méthodes d'inscription / désinscription.

    /**
     * Vérifie s'il reste des places disponibles dans ce cours.
     * Comparaison entre le nombre d'inscrits actuels et le maximum autorisé.
     * 
     * @return true s'il reste au moins une place libre
     */
    public boolean placesDisponibles() {
        return listeInscrits.size() < nbre_places;
    }

    /**
     * Vérifie si aucun client n'est inscrit à ce cours.
     * Utilisé dans supprimerCours() de Salle pour autoriser la suppression.
     * 
     * @return true si la liste des inscrits est vide
     */
    public boolean aucunInscrit() {
        return this.listeInscrits.isEmpty();
    }

    /**
     * Retourne le nombre de clients actuellement inscrits.
     * Utilisé pour le tri par popularité via compareTo().
     * 
     * @return le nombre d'inscrits
     */
    public int getNbreInscrits() {
        return listeInscrits.size();
    }

    /**
     * Ajoute un client à la liste des inscrits.
     * Vérifie au préalable que le client n'est pas déjà inscrit
     * et qu'il reste des places disponibles.
     * 
     * @param client le client à inscrire
     */
    public void ajouterClient(Client client) {
        if (!listeInscrits.contains(client) && placesDisponibles()) {
            listeInscrits.add(client);
        }
    }

    /**
     * Retire un client de la liste des inscrits.
     * Appelé lors d'une désinscription via seDesinscrireDeCours() dans Salle.
     * 
     * @param client le client à désinscrire
     */
    public void retirerClient(Client client) {
        listeInscrits.remove(client);
    }
    
    
    // _________________________________________________ Comparaison de dates :
    // Ces méthodes sont utilisées dans miseAJourCours() de la classe Salle
    // pour déplacer les cours dont la date est passée vers listeCoursPassees.
    // Peuvent également être appelées à chaque lancement de l'application.

    /**
     * Vérifie si le cours est déjà passé.
     * Combine date et heure en un LocalDateTime pour une comparaison précise.
     * On aurait pu faire la comparaison en deux temps (date puis heure),
     * mais LocalDateTime simplifie et fiabilise la vérification.
     * 
     * @return true si le cours a déjà eu lieu
     */
    public boolean estPasse() {
        LocalDateTime dateHeure = LocalDateTime.of(this.date, this.heure); // combinaison date + heure
        return dateHeure.isBefore(LocalDateTime.now());
    }

    /**
     * Vérifie si le cours est à venir.
     * Même principe que estPasse() avec LocalDateTime.
     * 
     * @return true si le cours n'a pas encore eu lieu
     */
    public boolean estFutur() {
        LocalDateTime dateHeure = LocalDateTime.of(date, heure);
        return dateHeure.isAfter(LocalDateTime.now());
    }
    
    
    // _________________________________________________ Affichage :

    /**
     * Affiche les informations complètes d'un cours dans la console.
     * Méthode à adapter pour l'interface graphique. <== A modifier pour la partie graphique
     */
    public void afficherCours() {
        System.out.println("________ Cours n°" + id_cours + " __________");
        System.out.println("Activité       : " + activite);
        System.out.println("Date           : " + date);
        System.out.println("Heure          : " + heure);
        System.out.println("Type           : " + typeCours);
        System.out.println("Places         : " + listeInscrits.size() + "/" + nbre_places);
        System.out.println("Disponible     : " + (this.placesDisponibles() ? "Oui" : "Non")); // opérateur ternaire : forme simplifiée d'un if-else
    }
}
