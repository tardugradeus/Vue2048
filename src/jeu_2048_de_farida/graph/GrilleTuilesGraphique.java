                          


/**
 *
 * @author B. KHAFIF
 * Panneau graphique permettant de représenter le plateau de jeu 2048
 * V.1.0
 *
 * reproduction interdite sans l'autorisation de l'auteur
 */
package jeu_2048_de_farida.graph;
import  java.awt.Color;
import java.awt.Cursor;
import  java.awt.Font;
import  java.awt.FontMetrics;
import  java.awt.Graphics;
import  java.awt.Image;
import java.awt.Point;
import  java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import  java.awt.geom.Rectangle2D;
import  java.util.Random;
import javax.swing.JPanel;
import moteur.*;;


public class GrilleTuilesGraphique extends JPanel  {
    /**
     * Espacement entre le dessin de la tuile et les
     * bord de la case
     */
    public final static int MARGE=15;
    public static final int FORME_RONDE = 0;
    public static final int FORME_CARRE = 1;
    public static final int EPAISSEUR_DELIMITATION_CASE = 2;
    /**
     * Forme de dessin de la tuile
     */
    private int fomeTuile;
    private GrilleTuiles grille = null;
    /**
     * Image de fond de la grille
     */
    private Image image;
    /**
     * Constructer
     * @param grilleTuiles (GrilleTuiles) : Grille de tuiles
     */
    public GrilleTuilesGraphique(GrilleTuiles grilleTuiles) {
         /**
          * Chargement du fond de la grille de jeu
          */
         this.image =Toolkit.getDefaultToolkit().getImage("images/jeu_2048_fond_ecran.png");
         /**
          * Forme par dÃ©faut pour les tuiles carre
          */
         this.fomeTuile = GrilleTuilesGraphique.FORME_CARRE;
         this.grille = grilleTuiles;
    }
    public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	/**
      * Fixe la forme de dessin des tuiles
      * <BR> Damier.FORME_RONDE pour des tuiles rondes
      * <BR> Damier.FORME_RONDE pour des tuiles carrÃ©es
      * @param fomeTuile (int) : forme de dessin de la tuile
      */
    public void setFormeTuile(int fomeTuile) {
         this.fomeTuile = fomeTuile;
    }
    public void paintComponent(Graphics g) {
         super.paintComponent(g);
         
         
         
         /**
          * DÃ©termination de la largeur et de la hauteur
          * du panel en pixels (ne pas confondre avec la rÃ©solution de l'Ã©cran)
          */
         int largeurEcran = (int) this.getWidth();
         int hauteurEcran = this.getHeight();
         int tailleJeu = this.grille.getSize();
         /**
          * DÃ©termination de la largeur et de la hauteur en pixels de
          * chaque tuile
          */
         int hauteurCase = hauteurEcran/tailleJeu;
         int largeurCase = largeurEcran/tailleJeu;
         /**
          * Dessin de l'image de fond d ela grille de jeu
          */
         g.drawImage(this.image,0,0,largeurEcran,hauteurEcran,this);
         /**
          * Dessin des dÃ©limiations des tuiles
          */
         g.setColor(Color.black);
         /**
          * DÃ©limitation des lignes
          */
         for(int i = 0; i <= tailleJeu; i++)
             if (i == 0)
                 g.fillRect(0, i*hauteurCase, largeurEcran,
 GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE);
             else if (i>=tailleJeu)
             g.fillRect(0,
 tailleJeu*hauteurCase-GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE ,
                 largeurEcran, GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE);
             else
                 g.fillRect(0, i*(hauteurCase-GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE) ,
                             largeurEcran,
 GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE*2);
         /**
          * DÃ©limitation des colonnes
          */
         for(int j = 0; j <= tailleJeu; j++)
             if (j == 0)
                 g.fillRect(j*largeurCase, 0,
 GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE,hauteurEcran);
             else if (j>=tailleJeu)
             g.fillRect(
 tailleJeu*largeurCase-GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE , 0,
                 GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE, hauteurEcran);
             else
                 g.fillRect(j*
 (largeurCase-GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE) , 0,
                             GrilleTuilesGraphique.EPAISSEUR_DELIMITATION_CASE*2,
 hauteurEcran);

         /**
          * Dessin des tuiles
          */
         for(int i = 0; i < tailleJeu; i++)
             for(int j = 0; j < tailleJeu; j++) {
                 Tuile tuile = this.grille.getUneTuile(j, i);
                 if (tuile != null){
                     /**
                       * Remplissage de la tuile avec la couleur adaptÃ©e
                       */
                     g.setColor(GrilleTuiles.getCouleurTuile(tuile));
                     switch(this.fomeTuile){
                     case GrilleTuilesGraphique.FORME_CARRE :
                          g.setColor(Color.BLACK);
                          g.drawRect(i*largeurCase+1+MARGE, j*hauteurCase+1+MARGE,
 largeurCase-1-2*MARGE, hauteurCase-1-2*MARGE);
                          /**
                           * Remplissage de la tuile avec la couleur adaptÃ©e
                           */
                          g.setColor(GrilleTuiles.getCouleurTuile(tuile));
                          g.fillRect(i*largeurCase+1+MARGE, j*hauteurCase+1+MARGE,
 largeurCase-1-2*MARGE, hauteurCase-1-2*MARGE);
                          break;
                     case GrilleTuilesGraphique.FORME_RONDE :
                          g.setColor(Color.BLACK);
                          g.drawOval(i*largeurCase+1+MARGE, j*hauteurCase+1+MARGE,
 largeurCase-1-2*MARGE, hauteurCase-1-2*MARGE);
                          /**
                           * Remplissage de la tuile avec la couleur adaptÃ©e
                           */
                          g.setColor(GrilleTuiles.getCouleurTuile(tuile));
                          g.fillOval(i*largeurCase+1+MARGE, j*hauteurCase+1+MARGE,
 largeurCase-1-2*MARGE, hauteurCase-1-2*MARGE);
                          break;
                     }
                     String s = ""+tuile.getValue();
                     /**
                       * On fixe la font de caractÃ¨res
                       */
                     g.setFont(new Font("Arial", Font.BOLD, 24));
                     // DÃ©termination de la dimension de la fonte utilisÃ©e
                     FontMetrics fm = getFontMetrics(g.getFont());
                     // DÃ©termination du rectangle qui permet de circonscrire le texte
                     Rectangle2D textsize = fm.getStringBounds(s, g);
                     /**
                       * Dessine le texte centrÃ© dans la tuile
                       */
                     g.setColor(Color.black);
                     int xPos = i*largeurCase+1 + (int) (largeurCase-textsize.getWidth()) / 2;
                     int yPos =j*hauteurCase+1 + (int)(hauteurCase - textsize.getHeight()) / 2 + fm.getAscent();
                    		                     g.drawString(s, xPos, yPos);
                    		                 }
                    		             }
                    		     }
    
    
    
}

