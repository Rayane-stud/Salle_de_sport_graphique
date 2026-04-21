/*
Vignes Gabriel
Ouchiha Rayane
Groupe CB
 */
package ptraitements;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe centrale de l'application représentant la salle de sport.
 * Joue le rôle de contrôleur : orchestre toutes les interactions entre
 * les clients, l'administrateur, les cours et les fichiers de sauvegarde.
 * 
 * Responsabilités :
 *  - Création et gestion des comptes clients
 *  - Gestion des inscriptions et désinscriptions aux cours
 *  - Fonctionnalités administrateur (recherche, activation/désactivation, gestion des cours)
 *  - Sauvegarde et chargement des données via fichiers texte (.txt)
 *  - Mise à jour automatique des listes de cours (futurs → passés)
 * 
 * @author rayan
 */
public class Salle {
    
    // _________________________________________________ Attributs :
    private String nom;                           // Nom de la salle de sport
    private ArrayList<Client> listeClients;       // Liste de tous les clients enregistrés
    private Admin admin;                          // Unique administrateur (une liste si plusieurs admins à l'avenir)
    public ArrayList<Cours> listeCoursFuturs;    // Cours à venir, non encore passés
    private ArrayList<Cours> listeCoursPassees;   // Cours dont la date est dépassée
    private String nomFichSauvegarde;             // Nom du fichier de sauvegarde général (non utilisé directement pour l'instant)
    private int prochainIdCours = 1;              // Compteur pour générer des identifiants de cours uniques et croissants
    
    /**
     * Constructeur de la classe Salle.
     * Initialise la salle avec un nom, un admin et les listes vides.
     * Les données réelles sont chargées ensuite via chargerTout().
     * 
     * @param nom                   Nom de la salle
     * @param admin                 Administrateur de la salle
     * @param nomFichierSauvegarde  Nom du fichier de sauvegarde général
     */
    public Salle(String nom, Admin admin, String nomFichierSauvegarde) {
        this.nom = nom;
        this.admin = admin;
        this.nomFichSauvegarde = nomFichierSauvegarde;
        this.listeClients = new ArrayList<>();
        this.listeCoursFuturs = new ArrayList<>();  // initialisées vides : se rempliront via chargerTout()
        this.listeCoursPassees = new ArrayList<>();
    }
    
    
    // _________________________________________________ Méthodes Clients :
    
    // __ Partie 1 : gestion des comptes (console + interface graphique) __
    
    /**
     * Vérifie si un identifiant (email) est déjà utilisé par un client existant.
     * Utilisée dans creerCompte() pour éviter les doublons d'adresse email.
     * Méthode privée car uniquement utilisée en interne.
     * 
     * @param identifiant l'email à vérifier
     * @return true si l'email est déjà associé à un compte
     */
    private boolean idDejaUtilise(String identifiant) {
        for (Client c : listeClients) {
            if (c.getIdentifiant().equals(identifiant)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Génère un numéro client unique en prenant le maximum existant + 1.
     * Garantit l'unicité tant qu'il n'y a pas de suppression de compte.
     * On pourrait enrichir ce numéro avec la date/heure de création pour plus de traçabilité.
     * 
     * @return le prochain numéro client disponible
     */
    private int genererNumeroClient() {
        int max = 0;
        for (Client c : listeClients) {     // parcours for-each : pas besoin d'itérateur ici
            if (c.getNumeroClient() > max) { // car on ne modifie pas la liste pendant le parcours
                max = c.getNumeroClient();
            }
        }
        return max + 1;                  
    }
    
    /**
     * Crée un nouveau compte client et l'ajoute à la liste des clients.
     * Vérifie au préalable que l'email n'est pas déjà utilisé.
     * L'interface graphique gérera l'affichage du message d'erreur si null est retourné.
     * 
     * @param email    Adresse email (identifiant unique)
     * @param mdp      Mot de passe
     * @param nom      Nom de famille
     * @param prenom   Prénom
     * @param tel      Numéro de téléphone
     * @param adresse  Adresse postale
     * @param type     Type d'abonnement choisi
     * @return le Client créé, ou null si l'email est déjà utilisé
     */
    public Client creerCompte(String email, String mdp, String nom, String prenom,
                              String tel, String adresse, TypeAbonnement type, String pp) {
        if (idDejaUtilise(email)) {
            return null; // l'interface gérera le message d'erreur
        }
        Client nouveau = new Client(email, mdp, nom, prenom, tel, adresse, type, pp);
        nouveau.setNumClient(genererNumeroClient()); // attribution du numéro unique
        listeClients.add(nouveau);
        return nouveau; // retourne le client créé, ou null si échec
    }

    
    
    
    /**
     * Met à jour les informations personnelles d'un client.
     * Délègue chaque modification aux modifieurs de la classe Client.
     * 
     * @param client  le client à modifier
     * @param nom     nouveau nom
     * @param prenom  nouveau prénom
     * @param tel     nouveau téléphone
     * @param adresse nouvelle adresse
     */
    public void miseAJourInformationsCompte(Client client, String nom, String prenom,
                                            String tel, String adresse) {
        client.modifNom(nom);
        client.modifPrenom(prenom);
        client.modifTelephone(tel);
        client.modifAdresse(adresse);
    }
    
    /**
     * Inscrit un client à un cours futur.
     * Effectue plusieurs vérifications avant de procéder :
     *  - le client n'est pas déjà inscrit à ce cours
     *  - le cours est bien dans le futur (date postérieure à aujourd'hui)
     *  - l'abonnement du client est actif
     *  - le cours n'est pas complet
     * 
     * @param client le client qui souhaite s'inscrire
     * @param cours  le cours visé
     * @return true si l'inscription a réussi, false sinon
     */
    public boolean sInscrireACours(Client client, Cours cours) {
        LocalDate aujourdHui = LocalDate.now();
        
        // Vérifie que le client n'est pas déjà inscrit
        if (client.getCoursFuturs().contains(cours)) {
            return false;
        }
        
        // Vérifie que le cours est bien dans le futur
        if (cours.getDatecour().isAfter(aujourdHui)) {
            
            if (!client.isAbonnementActif()) {
                return false; // abonnement inactif
            }
            if (!cours.placesDisponibles()) {
                return false; // cours complet
            }
            
            // Inscription effective : mise à jour des deux côtés (cours et client)
            cours.ajouterClient(client);
            client.ajouterCoursFutur(cours);
            return true;
        }
        
        return false; // cours déjà passé
    }
    
    /**
     * Désinscrit un client d'un cours futur.
     * Vérifie que le client est bien inscrit avant de procéder.
     * Met à jour la liste du cours et celle du client.
     * 
     * @param client le client qui souhaite se désinscrire
     * @param cours  le cours concerné
     * @return true si la désinscription a réussi, false si le client n'était pas inscrit
     */
    public boolean seDesinscrireDeCours(Client client, Cours cours) {
        if (!client.getCoursFuturs().contains(cours)) {
            return false; // le client n'est pas inscrit à ce cours
        }
        
        // Mise à jour des deux côtés (cours et client)
        cours.retirerClient(client);
        client.retirerCoursFutur(cours);
        return true;
    }
    
    
    // _________________________________________________ Méthodes Administrateur :
    
    /**
     * Retourne la liste complète des clients de la salle.
     * Utilisée par l'admin pour consulter tous les comptes.
     * 
     * @return la liste de tous les clients
     */
    public ArrayList<Client> getListeClients() {
        return listeClients;
    }
    
    /**
     * Recherche des clients selon un critère et une valeur donnés.
     * Critères acceptés : "nom", "prenom", "telephone", "numero", "abonnement".
     * La comparaison est insensible à la casse pour les champs texte.
     * 
     * @param critere le champ sur lequel filtrer (ex : "nom")
     * @param valeur  la valeur recherchée (ex : "Dupont")
     * @return la liste des clients correspondant au critère, vide si aucun résultat
     */
    public ArrayList<Client> rechercherClient(String critere, String valeur) {
        ArrayList<Client> resultats = new ArrayList<>();
        for (Client c : listeClients) {
            switch (critere) {
                case "nom" -> {
                    if (c.getNom().equalsIgnoreCase(valeur))
                        resultats.add(c);
                }
                case "prenom" -> {
                    if (c.getPrenom().equalsIgnoreCase(valeur))
                        resultats.add(c);
                }
                case "telephone" -> {
                    if (c.getTelephone().equals(valeur))
                        resultats.add(c);
                }
                case "numero" -> {
                    if (c.getNumeroClient() == Integer.parseInt(valeur))
                        resultats.add(c);
                }
                case "abonnement" -> {
                    // comparaison entre la valeur de l'enum en String et la valeur recherchée
                    if (c.getTypeAbonnement().toString().equalsIgnoreCase(valeur))
                        resultats.add(c);
                }
            }
        }
        return resultats;
    }
    
    /**
     * Désactive l'abonnement d'un client.
     * Un client avec un abonnement inactif ne peut plus se connecter ni s'inscrire à des cours.
     * 
     * @param client le client à désactiver
     */
    public void desactiverAbonnement(Client client) {
        client.modifAbonnementActif(false);
    }
    
    /**
     * Réactive l'abonnement d'un client précédemment désactivé.
     * 
     * @param client le client à réactiver
     */
    public void reactiverAbonnement(Client client) {
        client.modifAbonnementActif(true);
    }
    
    
    // _________________ Gestion des cours (admin) :
    
    /**
     * Retourne la liste des cours passés.
     * 
     * @return la liste de tous les cours dont la date est dépassée
     */
    public ArrayList<Cours> getListeCoursPassees() {
        return listeCoursPassees;
    }
    
    /**
     * Crée un nouveau cours et l'ajoute à la liste des cours futurs.
     * L'identifiant du cours est attribué automatiquement via prochainIdCours,
     * qui est incrémenté à chaque création pour garantir l'unicité.
     * 
     * @param activite     Nom de l'activité
     * @param date         Date du cours
     * @param heure        Heure de début
     * @param typeCours    Type de cours
     * @param nombrePlaces Nombre de places disponibles
     * @return le cours nouvellement créé
     */
    public Cours creerCours(String activite, LocalDate date, LocalTime heure,
                            TypeCours typeCours, int nombrePlaces) {
        Cours c = new Cours(activite, date, heure, typeCours, nombrePlaces, prochainIdCours);
        prochainIdCours++; // incrémentation pour le prochain cours
        LocalDate adj = LocalDate.now();
        if(date.isAfter(adj)|| date.isEqual(adj)){ //adj ou demain
            getCoursFuturs().add(c);
        }
        else{
            getListeCoursPassees().add(c);
        }
        return c;
    }
    
    /**
     * Supprime un cours futur, uniquement si aucun client n'est inscrit.
     * Si des clients sont inscrits, la suppression est refusée pour préserver les données.
     * 
     * @param cours le cours à supprimer
     * @return true si la suppression a réussi, false si des clients sont inscrits
     */
    public boolean supprimerCours(Cours cours) {
        if (!cours.aucunInscrit()) {
            return false; // impossible : des clients sont inscrits
        }
        listeCoursFuturs.remove(cours);
        return true;
    }
    
    /**
     * Modifie un cours futur, uniquement si aucun client n'y est inscrit.
     * Si des clients sont déjà inscrits, la modification est refusée
     * pour ne pas les affecter sans leur consentement.
     * 
     * @param cours        le cours à modifier
     * @param activite     nouveau nom d'activité
     * @param date         nouvelle date
     * @param heure        nouvelle heure
     * @param typeCours    nouveau type
     * @param nombrePlaces nouveau nombre de places
     * @return true si la modification a réussi, false si des clients sont inscrits
     */
    public boolean modifierCours(Cours cours, String activite, LocalDate date,
                                 LocalTime heure, TypeCours typeCours, int nombrePlaces) {
        if (cours.getNbreInscrits() == 0) {
            cours.setActivite(activite);
            cours.setDate(date);
            cours.setHeure(heure);
            cours.setTypeCours(typeCours);
            cours.setNombrePlaces(nombrePlaces);
            return true;
        }
        return false; // modification refusée : cours avec inscrits
    }
    
    /**
     * Retourne la liste des cours futurs correspondant à une activité donnée.
     * La comparaison est insensible à la casse (equalsIgnoreCase).
     * 
     * @param activite le nom de l'activité recherchée
     * @return la liste des cours de cette activité
     */
    public ArrayList<Cours> getCoursParActivite(String activite) {
        ArrayList<Cours> resultats = new ArrayList<>();
        for (Cours c : listeCoursFuturs) { // parcours for-each
            if (c.getActivitecour().equalsIgnoreCase(activite)) {
                resultats.add(c);
            }
        }
        return resultats;
    }
    
    /**
     * Retourne les cours futurs triés du plus populaire au moins populaire.
     * Utilise Collections.sort() qui appelle automatiquement compareTo()
     * défini dans la classe Cours (tri décroissant par nombre d'inscrits).
     * Travaille sur un clone de la liste pour ne pas modifier l'ordre original.
     * 
     * @return une nouvelle liste triée du plus au moins populaire
     */
    public ArrayList<Cours> getCoursPlusPopulaires() {
        ArrayList<Cours> trie = new ArrayList<>(listeCoursFuturs); // clone pour ne pas modifier la liste originale
        Collections.sort(trie); // utilise compareTo() défini dans Cours
        return trie;
    }

    /**
     * Retourne les cours futurs triés du moins populaire au plus populaire.
     * Applique un tri croissant en inversant le résultat de getCoursPlusPopulaires().
     * 
     * @return une nouvelle liste triée du moins au plus populaire
     */
    public ArrayList<Cours> getCoursMoinsPopulaires() {
        ArrayList<Cours> trie = new ArrayList<>(listeCoursFuturs);
        Collections.sort(trie);
        Collections.reverse(trie); // inverse le résultat du tri décroissant
        return trie;
    }
    
    
    // _________________________________________________ Méthodes communes (client + admin) :
    
    /**
     * Gère la connexion d'un utilisateur (client ou admin).
     * Vérifie d'abord si les identifiants correspondent à l'admin,
     * puis parcourt la liste des clients.
     * Un client avec un abonnement inactif ne peut pas se connecter.
     * L'interface graphique gérera les messages d'erreur selon le cas de retour null.
     * 
     * @param identifiant l'email de l'utilisateur
     * @param motDePasse  le mot de passe
     * @return l'objet Utilisateur connecté (Admin ou Client), ou null si échec
     */
    public Utilisateur seConnecter(String identifiant, String motDePasse) {
        // Vérification admin en priorité
        if (admin.getIdentifiant().equals(identifiant) && admin.getMot2Passe().equals(motDePasse)) {
            return admin;
        }
        
        // Parcours des clients
        for (Client c : listeClients) {
            if (c.getIdentifiant().equals(identifiant) && c.getMot2Passe().equals(motDePasse)) {
                if (!c.isAbonnementActif()) {
                    return null; // abonnement inactif : connexion refusée, l'interface gérera le message
                }
                return c;
            }
        }
        return null; // aucun utilisateur trouvé avec ces identifiants
    }
    
    /**
     * Met à jour le mot de passe d'un utilisateur après vérification de l'ancien.
     * Fonctionne pour les clients comme pour l'admin (polymorphisme via Utilisateur).
     * 
     * @param utilisateur l'utilisateur qui souhaite changer son mot de passe
     * @param ancienMdp   l'ancien mot de passe (vérification de sécurité)
     * @param nouveauMdp  le nouveau mot de passe souhaité
     * @return true si le changement a réussi, false si l'ancien mot de passe est incorrect
     */
    public boolean miseAJourMotDePasse(Utilisateur utilisateur, String ancienMdp, String nouveauMdp) {
        if (!utilisateur.getMot2Passe().equals(ancienMdp)) {
            return false; // l'interface gérera le message d'erreur
        }
        utilisateur.setMot2passe(nouveauMdp);
        return true;
    }
    
    /**
     * Retourne la liste des cours futurs.
     * 
     * @return la liste des cours à venir
     */
    public ArrayList<Cours> getCoursFuturs() {
        return listeCoursFuturs;
    }
    
    /**
     * Retourne la liste de toutes les activités distinctes proposées dans la salle.
     * Parcourt à la fois les cours futurs et passés pour être exhaustif.
     * Evite les doublons grâce au test contains() avant chaque ajout.
     * 
     * @return la liste des noms d'activités sans doublon
     */
    public ArrayList<String> getListeActivites() {
        ArrayList<String> listeActivite = new ArrayList<>();
        
        // Parcours des cours futurs
        for (Cours c : listeCoursFuturs) {
            if (!listeActivite.contains(c.getActivitecour())) {
                listeActivite.add(c.getActivitecour());
            }
        }
        
        // Parcours des cours passés (pour avoir un historique complet des activités)
        for (Cours d : listeCoursPassees) {
            if (!listeActivite.contains(d.getActivitecour())) {
                listeActivite.add(d.getActivitecour());
            }
        }
        
        return listeActivite;
    }
    
    
    // _________________________________________________ Sauvegarde et chargement :
    // Uniquement via fichiers texte (.txt), conformément aux contraintes du projet.
    // Pas de sérialisation, pas de JSON, pas de fichiers binaires.
    // Appel recommandé : chargerTout() au démarrage, sauvegarderTout() avant fermeture.
    
    /**
     * Sauvegarde tous les clients dans le fichier "clients.txt".
     * Format CSV avec séparateur ";" : numClient;email;mdp;nom;prenom;tel;adresse;typeAbonnement;abonnementActif
     * Un client par ligne.
     */
    public void sauvegarderClients() {
        try {
            FileWriter writer = new FileWriter("clients.txt");
            
            for (Client c : listeClients) {
                writer.write(
                    c.getNumeroClient() + ";" +
                    c.getIdentifiant() + ";" +
                    c.getMot2Passe() + ";" +
                    c.getNom() + ";" +
                    c.getPrenom() + ";" +
                    c.getTelephone() + ";" +
                    c.getAdresse() + ";" +
                    c.getTypeAbonnement() + ";" +     // toString() de l'enum, compatible avec valueOf() au chargement
                    c.isAbonnementActif() + ";" +
                    c.getPP() + "\n"
                );
            }
            
            writer.close();
            System.out.println("Sauvegarde dans : " + new java.io.File("clients.txt").getAbsolutePath());
            
        } catch (Exception e) {
            System.out.println("Erreur sauvegarde clients");
        }
    }
    
    /**
     * Charge les clients depuis le fichier "clients.txt".
     * Reconstruit chaque objet Client à partir d'une ligne du fichier.
     * Vide la liste actuelle avant de recharger pour éviter les doublons.
     */
    public void chargerClients() {
        try {
            listeClients.clear(); // réinitialisation avant rechargement
            
            File fichier = new File("clients.txt");
            Scanner scanner = new Scanner(fichier);
            
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] parts = ligne.split(";"); // découpage selon le séparateur ";"
                
                // Reconstruction du client à partir des champs de la ligne
                Client c = new Client(
                    parts[1], // email
                    parts[2], // mot de passe
                    parts[3], // nom
                    parts[4], // prénom
                    parts[5], // téléphone
                    parts[6], // adresse
                    TypeAbonnement.valueOf(parts[7]), // conversion String → enum
                    parts[9] // nom Photo, position 9 car en 8 c'est l'abbonement actif
                );
                
                c.setNumClient(Integer.parseInt(parts[0]));
                c.modifAbonnementActif(Boolean.parseBoolean(parts[8]));
                
                listeClients.add(c);
            }
            
            scanner.close();
            
        } catch (Exception e) {
            System.out.println("Erreur chargement clients");
        }
    }
    
    /**
     * Sauvegarde tous les cours futurs dans le fichier "cours.txt".
     * Format CSV avec séparateur ";" : id;activite;date;heure;typeCours;nombrePlaces
     * Un cours par ligne. Seuls les cours futurs sont sauvegardés.
     */
    public void sauvegarderCours() {
        try {
            try (FileWriter writer = new FileWriter("cours.txt")) { // Try with ressoruces pour eviter le bloc finally pour refermer le fichier 
                for (Cours c : listeCoursFuturs) {
                    writer.write(
                            c.getIdCours() + ";" +
                                    c.getActivitecour() + ";" +
                                    c.getDatecour() + ";" +     // format ISO par défaut : yyyy-MM-dd
                                    c.getHeurecour() + ";" +    // format ISO par défaut : HH:mm
                                    c.getTypeCours() + ";" +    // toString() de l'enum, compatible avec valueOf() au chargement
                                    c.getNombrePlacescour() + "\n"
                    );
                }
            }
            System.out.println("Sauvegarde dans : " + new java.io.File("cours.txt").getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Erreur sauvegarde cours");
        }
    }
    
    /**
     * Charge les cours depuis le fichier "cours.txt".
     * Reconstruit chaque objet Cours à partir d'une ligne du fichier.
     * Met également à jour prochainIdCours pour éviter les conflits d'identifiants.
     */
    public void chargerCours() {
        try {
            listeCoursFuturs.clear(); // réinitialisation avant rechargement
            
            File fichier = new File("cours.txt");
            try (Scanner scanner = new Scanner(fichier)) { // try with ressources pour eviter le bloc finally pour reclose le Scanner
                while (scanner.hasNextLine()) {
                    String ligne = scanner.nextLine();
                    String[] parts = ligne.split(";"); // découpage selon le séparateur ";"
                    
                    // Reconstruction des champs du cours
                    int id = Integer.parseInt(parts[0]);
                    String activite = parts[1];
                    LocalDate date = LocalDate.parse(parts[2]);  // parsing du format ISO yyyy-MM-dd
                    LocalTime heure = LocalTime.parse(parts[3]); // parsing du format ISO HH:mm
                    TypeCours type = TypeCours.valueOf(parts[4]);
                    int places = Integer.parseInt(parts[5]);
                    
                    Cours c = new Cours(activite, date, heure, type, places, id);
                    listeCoursFuturs.add(c);
                    
                    // Mise à jour du compteur pour éviter les conflits d'ID lors des prochaines créations
                    if (id >= prochainIdCours) {
                        prochainIdCours = id + 1;
                    }
                }
            }
            
        } catch (Exception e) {
            System.out.println("Erreur chargement cours");
        }
    }
    
    /**
     * Sauvegarde l'ensemble des données (clients + cours).
     * À appeler avant la fermeture de l'application.
     */
    public void sauvegarderTout() {
        sauvegarderClients();
        sauvegarderCours();
        sauvegarderInscriptions();
    }

    /**
     * Charge l'ensemble des données (clients + cours).
     * À appeler au démarrage de l'application.
     */
    public void chargerTout() {
        chargerClients();
        chargerCours();
        chargerInscriptions();
    }
    
    
    // _________________________________________________ Méthodes d'affichage (console) :
    // A adapter pour l'interface graphique
    
    /**
     * Affiche le profil d'un client en le recherchant par nom.
     * Méthode à adapter pour l'interface graphique. <== A modifier pour la partie graphique
     * 
     * @param c le client dont on souhaite consulter le profil
     */
    public void ConsulterCompte(Client c) {
        for (Client client : listeClients) {
            if (client.getNom().equals(c.getNom())) {
                client.afficherProfil();
            }
        }
    }
    
    /**
     * Affiche dans la console la liste complète des cours futurs de la salle.
     * Méthode à adapter pour l'interface graphique. <== A modifier pour la partie graphique
     */
    public void ConsulterListeCoursFuturs() {
        System.out.println("_____ Les cours à venir _____");
        if (listeCoursFuturs.isEmpty()) {
            System.out.println("Aucun cours à venir.");
        } else {
            for (Cours c : listeCoursFuturs) { // parcours for-each
                System.out.println("- " + c.getActivitecour() + " le " + c.getDatecour() + " à " + c.getHeurecour());
            }
        }
    }

    /**
     * Affiche dans la console la liste complète des cours passés de la salle.
     * Méthode à adapter pour l'interface graphique. <== A modifier pour la partie graphique
     */
    public void ConsulterListeCoursPasses() {
        System.out.println("______ Les cours passés ______");
        if (listeCoursPassees.isEmpty()) {
            System.out.println("Aucun cours passé.");
        } else {
            for (Cours c : listeCoursPassees) {
                System.out.println("- " + c.getActivitecour() + " le " + c.getDatecour() + " à " + c.getHeurecour());
            }
        }
    }
    
    /**
     * Déplace les cours passés de listeCoursFuturs vers listeCoursPassees.
     * Mise à jour symétrique : déplace également les cours dans les listes de chaque client inscrit.
     * Méthode privée, appelée automatiquement au démarrage ou à une périodicité à définir.
     * 
     * Utilise une liste intermédiaire (aDeplacer) pour éviter les ConcurrentModificationException
     * lors de la modification d'une liste pendant son parcours.
     */
    private void miseAJourCours() {
        LocalDate aujourdHui = LocalDate.now();
        ArrayList<Cours> aDeplacer = new ArrayList<>(); // liste tampon pour éviter les problèmes de modification pendant le parcours
        
        // Identification des cours dont la date est passée
        for (Cours c : listeCoursFuturs) {
            if (c.getDatecour().isBefore(aujourdHui)) {
                aDeplacer.add(c);
            }
        }
        
        // Déplacement effectif des cours identifiés
        for (Cours c : aDeplacer) {
            listeCoursFuturs.remove(c);
            listeCoursPassees.add(c);
            
            // Mise à jour des listes de chaque client inscrit à ce cours
            for (Client cl : c.getClientsInscritscours()) {
                cl.passerCoursEnPasse(c);
            }
        }
    }
    
        
    //___________________________________________________________________________________________________________________
    
    // SAUVEGUARDE ET CHARGEMENT DES LIENS DE LIENS ENTRE COURS ET CLIENT INSCRITS 15/04/2026 
    
   
    public void sauvegarderInscriptions() {
    try (FileWriter writer = new FileWriter("inscriptions.txt")) {
        for (Cours c : listeCoursFuturs) {
            for (Client cl : c.getClientsInscritscours()) {
                writer.write(c.getIdCours() + ";" + cl.getNumeroClient() + "\n");
            }
        }
    } catch (Exception e) {
        System.out.println("Erreur sauvegarde inscriptions");
    }
}

public void chargerInscriptions() {
    try (Scanner scanner = new Scanner(new File("inscriptions.txt"))) {
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(";");
            int idCours = Integer.parseInt(parts[0]);
            int numClient = Integer.parseInt(parts[1]);

            Cours coursTrouve = null;
            for (Cours c : listeCoursFuturs) {
                if (c.getIdCours() == idCours) { coursTrouve = c; break; }
            }

            Client clientTrouve = null;
            for (Client cl : listeClients) {
                if (cl.getNumeroClient() == numClient) { clientTrouve = cl; break; }
            }

            if (coursTrouve != null && clientTrouve != null) {
                coursTrouve.ajouterClient(clientTrouve);
                clientTrouve.ajouterCoursFutur(coursTrouve);
            }
        }
    } catch (Exception e) {
        // Le fichier n'existe pas encore au premier lancement, on ignore.
    }
}
}