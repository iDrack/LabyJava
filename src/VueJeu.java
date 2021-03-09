
import java.awt.GridLayout;
import java.awt.Container;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.Timer;
import javax.swing.Action;


/**
 * classe qui s'occupe de la vue du memory
 */


public class  VueJeu extends JPanel {
    private MainWindow page;
    public static final int nbPaire = 8; 

    private Jeu modele = new JeuImpl();
    private Plateau plateau = modele.getPlateau();
    
    
    private Action actionQuitter;
    private Action actionRecommencer;
    private Action actionAPropos;
    
    private int cpt =0;
    private int ind =0; // prendra les valeurs de 3 pour desactiver qd les image sont differentre/ 5 pour identique et 15 pour afficher les image au debut
    private Dimension size;
    
    /**
     * contructeur
     * @param modele : modele du memory
     */
    public VueJeu(Dimension s,MainWindow page){
        this.page=page;
        this.size = s;
        

        setSize(size);
        setLayout(new GridLayout(7,7));
        
       
        rempli_case();
        
        
    }

    /**
     * Reinitialise la vue du memory
     */

    public void reinit(){
        
       
    }


    public void rempli_case(){
        Image img;
        CouloirImpl [][] mat = plateau.getCouloirImpls();
        for(int i = 0; i < Plateau.TAILLE;i++){
            for (int j = 0 ; j < Plateau.TAILLE; j++){
                if (mat[i][j].getForme() == Forme.COUDE) {
                    if (mat[i][j].getOrientation() == Orientation.NORD){
                        img= new ImageIcon("media/img/sprites/angle4.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }else if (mat[i][j].getOrientation() == Orientation.SUD) { 
                        img= new ImageIcon("media/img/sprites/angle2.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }else if (mat[i][j].getOrientation() == Orientation.OUEST) { 
                        img= new ImageIcon("media/img/sprites/angle3.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }else {
                        img= new ImageIcon("media/img/sprites/angle.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }
                }
                else if (mat[i][j].getForme() == Forme.TE){ 
                    if (mat[i][j].getOrientation() == Orientation.NORD){
                        img= new ImageIcon("media/img/sprites/formet3.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }else if (mat[i][j].getOrientation() == Orientation.SUD) { 
                        img= new ImageIcon("media/img/sprites/formet.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }else if (mat[i][j].getOrientation() == Orientation.OUEST) { 
                        img= new ImageIcon("media/img/sprites/formet2.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }else {
                        img= new ImageIcon("media/img/sprites/formet4.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }
                }
                else if (mat[i][j].getForme() == Forme.DROIT){
                    if (mat[i][j].getOrientation() == Orientation.NORD || mat[i][j].getOrientation() == Orientation.SUD){
                        img= new ImageIcon("media/img/sprites/ligne.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }else{
                        img= new ImageIcon("media/img/sprites/ligne2.png").getImage().getScaledInstance((int)size.getWidth()/7, (int)size.getHeight()/7, java.awt.Image.SCALE_SMOOTH);
                        add(new JButton(new ImageIcon(img)));
                    }
                }
             
            }
        }
        
    }

   
  
    public static void main(String[] args){
        
    }
}