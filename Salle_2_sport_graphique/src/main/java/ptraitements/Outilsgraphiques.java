/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ptraitements;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon; // pour recup l'icone finale a utiliser
import javax.swing.JLabel;

/**
 * Ici seront mises des methodes d'arrangement ou de personnalisation de composants graphiques 
 * @author rayan
 */
public class Outilsgraphiques {
    
    /* Methode publique et statique ( n'a pas besoin d'objet ) 
    * Prend en attribut le nom de l'image et les dimmension que l'on attend
    * Mise de tout sous try catch pour eviter de faire planter le programme en cas de problème lié au fichier
    * on renvoie un icon car seticon() sur les btns est pour les icons, y'a des differences de gestion de ressources par java aussi qui le rendent plus favorable
    */
    public static ImageIcon redimensionnerIcone(String nomFichier, int largeur, int hauteur){
        try {
            String chemin = "/pimagesR/" + nomFichier; // String qui contiendra le chemin d'acces; pimagesR car creation de dossier ressources
            System.out.println("Tentative de chargement : " + chemin); // TEMPORAIRE PR TESTS
            
            // recherche de la ressource dans le projet. 
            // getResource cherche l'image à l'intérieur du fichier .jar final. ( principe de jar decouvert en faisant des recherche sur flatlap ) 
            java.net.URL imgURL = Outilsgraphiques.class.getResource(chemin);    // On aurait pu ne pas utiliser de get ressources mais des conflits auraient eu lieu si on n'utilisait que le jar pour utiliser l'app
 
            // On vérifie si l'image a bien été trouvée (si l'URL n'est pas vide)( sans les ressources on aurait verifier que icon n'est pas null ) 
            if (imgURL != null) {
                ImageIcon icon = new ImageIcon(imgURL); // image chargée en mémoire
                
                // On extrait l'objet 'Image' de l'icône pour pouvoir le transformer.
                Image img = icon.getImage().getScaledInstance(largeur, hauteur, Image.SCALE_SMOOTH);  // Scalesmooth est pour que ce soit moins pixélisé          
               return new ImageIcon(img); // on le retransforme en icone
            }
            else{
                System.err.println("Fichier introuvable a cette adresse : "+ chemin); // System.err est un Systeme.out pour les erreurs, plus la meme couleur de sortie et sortie imédiate
                return null;
            }
        }
        catch(Exception e){
            System.err.println("Erreur lors du redimensionnement de l'icon");
            return null; // a fin que ca ne plante  pas et qu'il y ait qd mme un retour
        }
        
        
    }
    
    // Methodes permettant de redimensionner et afficher une image dans un label 
    public static void AfficherImageBTaille(JLabel label, String nomImage) {
        try {
            int largeur = label.getWidth();
            int hauteur = label.getHeight();
            if (largeur == 0 || hauteur == 0) throw new Exception("Label taille 0");

            ImageIcon icon = redimensionnerIcone(nomImage, largeur, hauteur);
            if (icon == null) throw new Exception("Image non trouvée : " + nomImage);

            label.setIcon(icon);

        } catch (Exception e) {
            System.err.println("Erreur AfficherImageBTaille : " + e.getMessage());
        }
    }
    
}
