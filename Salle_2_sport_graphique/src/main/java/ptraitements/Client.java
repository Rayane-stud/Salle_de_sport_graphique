    /*
    Vignes Gabriel
    Ouchiha Rayane
    Groupe CB
     */
    package ptraitements;

    import java.util.ArrayList;
    import java.util.List;

    /**
     * Classe représentant un client de la salle de sport.
     * Hérite de la classe Utilisateur (identifiant email + mot de passe).
     * 
     * Un client possède un profil personnel (nom, prénom, téléphone, adresse),
     * un type d'abonnement, un état d'abonnement (actif/inactif),
     * ainsi que deux listes de cours : futurs et passés.
     * 
     * La création d'un client et l'attribution de son numéro
     * sont gérées par la classe Salle via creerCompte().
     * 
     * @author rayan
     */
    public class Client extends Utilisateur {

        // _________________________________________________ Attributs :
        private int numClient;                    // Numéro unique attribué par la Salle lors de la création du compte
        private String nom;
        private String prenom;
        private String tel;
        private String adresse;
        private TypeAbonnement typeAbonnement;    // Type d'abonnement : ANNUEL, TRIMESTRIEL ou SEMAINE
        private boolean abonnementActif;          // Etat de l'abonnement : true = actif, false = désactivé par l'admin
        private String PhotoPP;

        // Listes de cours associées au client :
        private ArrayList<Cours> listeCoursFuturs; // Cours auxquels le client est inscrit et qui n'ont pas encore eu lieu
        private ArrayList<Cours> listeCoursPasses; // Cours déjà passés auxquels le client était inscrit

        /**
         * Constructeur de la classe Client.
         * Le numéro client n'est pas initialisé ici : il est généré et attribué
         * par la méthode genererNumeroClient() de la classe Salle.
         * L'abonnement est actif par défaut à la création du compte.
         * 
         * @param identifiant    Adresse email du client (identifiant de connexion)
         * @param motDePasse     Mot de passe du compte
         * @param nom            Nom de famille du client
         * @param prenom         Prénom du client
         * @param telephone      Numéro de téléphone
         * @param adresse        Adresse postale
         * @param typeAbonnement Type d'abonnement choisi (ANNUEL, TRIMESTRIEL ou SEMAINE)
         */
        public Client(String identifiant, String motDePasse, String nom, String prenom,
                      String telephone, String adresse, TypeAbonnement typeAbonnement, String PP) {
            super(identifiant, motDePasse);  // appelle le constructeur de Utilisateur
            this.nom = nom;
            this.prenom = prenom;
            this.tel = telephone;
            this.adresse = adresse;
            this.typeAbonnement = typeAbonnement;
            this.abonnementActif = true;           // actif par défaut à la création
            this.listeCoursFuturs = new ArrayList<>();
            this.listeCoursPasses = new ArrayList<>();
            this.PhotoPP = PP;
        }

        public void CreerClient(String identifiant, String motDePasse, String nom, String prenom,String telephone, String adresse, TypeAbonnement typeAbonnement, String PP){
            Client c = new Client(identifiant, motDePasse,  nom,  prenom, telephone,  adresse, typeAbonnement, PP);
        }


        // _________________________________________________ Getters :

        /** @return le numéro unique du client */
        public int getNumeroClient() {
            return numClient;
        }

        /** @return le nom de famille du client */
        public String getNom() {
            return this.nom;
        }

        /** @return le prénom du client */
        public String getPrenom() {
            return prenom;
        }

        /** @return le numéro de téléphone du client */
        public String getTelephone() {
            return tel;
        }

        /** @return l'adresse postale du client */
        public String getAdresse() {
            return adresse;
        }

        /** @return le type d'abonnement du client (ANNUEL, TRIMESTRIEL, SEMAINE) */
        public TypeAbonnement getTypeAbonnement() {
            return typeAbonnement;
        }

        /** @return le nom de l'image de pp */
        public String getPP() {
            return PhotoPP;
        }

        /** @return true si l'abonnement est actif, false s'il a été désactivé par l'admin */
        public boolean isAbonnementActif() {
            return abonnementActif;
        }

        /** @return la liste des cours futurs du client (non modifiable directement) */
        public List<Cours> getCoursFuturs() {
            return listeCoursFuturs;
        }

        /** @return la liste des cours passés du client (non modifiable directement) */
        public List<Cours> getCoursPasses() {
            return listeCoursPasses;
        }


        // _________________________________________________ Modifieurs des informations personnelles :
        // Utilisés par la méthode miseAJourInformationsCompte() de la classe Salle

        /** @param nom nouveau nom de famille */
        public void modifNom(String nom) {
            this.nom = nom;
        }

        /** @param prenom nouveau prénom */
        public void modifPrenom(String prenom) {
            this.prenom = prenom;
        }

        /** @param telephone nouveau numéro de téléphone */
        public void modifTelephone(String telephone) {
            this.tel = telephone;
        }

        /** @param adresse nouvelle adresse postale */
        public void modifAdresse(String adresse) {
            this.adresse = adresse;
        }

        /** @param PP nouvelle photo */
        public void modifPP(String pp) {
            this.PhotoPP = pp;
        }

        /**
         * Active ou désactive l'abonnement du client.
         * Appelée par l'admin via desactiverAbonnement() ou reactiverAbonnement() dans Salle.
         * 
         * @param actif true pour activer, false pour désactiver
         */
        public void modifAbonnementActif(boolean actif) {
            this.abonnementActif = actif;
        }

        /**
         * Attribue le numéro unique au client.
         * Appelé uniquement lors de la création du compte dans creerCompte() de la classe Salle.
         * 
         * @param num numéro généré par genererNumeroClient()
         */
        public void setNumClient(int num) {
            this.numClient = num;
        }


        // _________________________________________________ Gestion des listes de cours :

        /**
         * Ajoute un cours à la liste des cours futurs du client.
         * Appelé lors de l'inscription via sInscrireACours() dans Salle.
         * 
         * @param cours le cours auquel le client vient de s'inscrire
         */
        public void ajouterCoursFutur(Cours cours) {
            listeCoursFuturs.add(cours);
        }

        /**
         * Retire un cours de la liste des cours futurs du client.
         * Appelé lors de la désinscription via seDesinscrireDeCours() dans Salle.
         * 
         * @param cours le cours dont le client se désinscrit
         */
        public void retirerCoursFutur(Cours cours) {
            listeCoursFuturs.remove(cours);
        }

        /**
         * Déplace un cours de la liste "futurs" vers la liste "passés".
         * Appelé automatiquement lors de la mise à jour des cours dans miseAJourCours() de Salle,
         * lorsqu'un cours dont la date est dépassée est détecté.
         * 
         * @param cours le cours dont la date est passée
         */
        public void passerCoursEnPasse(Cours cours) {
            listeCoursFuturs.remove(cours);
            listeCoursPasses.add(cours);
        }


        // _________________________________________________ Affichages :
        // NB : pour parcourir les listes dans un but d'affichage, la boucle for-each suffit.
        //      Pour modifier ou supprimer un élément de la liste, on utilisera un itérateur
        //      afin de le faire proprement et éviter les ConcurrentModificationException.

        /**
         * Affiche les informations complètes du profil client dans la console.
         * Méthode à adapter lors du passage à l'interface graphique
         * pour retourner des chaînes à afficher dans les champs graphiques.
         */
        public void afficherProfil() {
            System.out.println("____ Mon Compte ____");
            System.out.println("Numéro client  : " + this.numClient);
            System.out.println("Nom            : " + this.nom);
            System.out.println("Prénom         : " + this.prenom);
            System.out.println("Email          : " + this.id_email);
            System.out.println("Téléphone      : " + this.tel);
            System.out.println("Adresse        : " + this.adresse);
            System.out.println("Abonnement     : " + this.typeAbonnement);
            System.out.println("Etat           : " + (this.abonnementActif ? "Actif" : "Inactif")); // opérateur ternaire : forme simplifiée d'un if-else
        }
        /**
         * Affiche la liste des cours futurs du client dans la console.
         * Méthode à adapter pour l'interface graphique. <== A modifier pour la partie graphique
         */
        public void afficherCoursFuturs() {
            System.out.println("_____ Mes cours à venir _____");
            if (listeCoursFuturs.isEmpty()) {
                System.out.println("Aucun cours à venir.");
            } else {
                for (Cours c : listeCoursFuturs) { // parcours de la liste avec for-each
                    System.out.println("- " + c.getActivitecour() + " le " + c.getDatecour() + " à " + c.getHeurecour());
                }
            }
        }
        /**
         * Affiche la liste des cours passés du client dans la console.
         * Méthode à adapter pour l'interface graphique. <== A modifier pour la partie graphique
         */
        public void afficherCoursPasses() {
            System.out.println("______ Mes cours passés ______");
            if (listeCoursPasses.isEmpty()) {
                System.out.println("Aucun cours passé.");
            } else {
                for (Cours c : listeCoursPasses) {
                    System.out.println("- " + c.getActivitecour() + " le " + c.getDatecour() + " à " + c.getHeurecour());
                }
            }
        }
    }