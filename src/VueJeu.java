import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class  VueJeu extends JPanel {
    private MainWindow page;
    private final int SIZE_COULOIR = 93;
    private final int SIZE_OBJECTIF = 15;
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);
    private Jeu modele = new JeuImpl();
    private Plateau plateau = modele.getPlateau();

    public VueJeu(MainWindow page){
        this.page=page;

        //Paramétrage de la taille de la fenêtre
        page.setSize(SIZE_COULOIR*7,SIZE_COULOIR*8+107);
        page.setLocationRelativeTo(null);
        MainWindow.instance.requestFocusInWindow(); 
        
        setLayout(null);

        try {
            ajoutCase();
            ajoutObjectifActuel();
        } catch (IOException e) {
            e.printStackTrace();
        }      
        ajoutinfo();
    }

    public void ajoutCase() throws IOException{
        BufferedImage img;
        CouloirImpl [][] mat = plateau.getCouloirImpls();
        
        for(int i = 0; i < Plateau.TAILLE;i++){
            for (int j = 0 ; j < Plateau.TAILLE; j++){
                img = AssetTiles.getCouloirImage(mat[i][j]);
                JLabel picLabel = new JLabel(new ImageIcon(img));
                picLabel.setBounds(SIZE_COULOIR*i,SIZE_COULOIR*j,SIZE_COULOIR,SIZE_COULOIR);
                add(picLabel);
            }
        }
    }

    public void ajoutObjectifActuel(){
        int offset = SIZE_COULOIR*7+25;
        Joueur joueur = modele.getJoueur();

        //Affichage de son objectif
        JLabel obj = new JLabel("Objectif :");
        obj.setBounds(SIZE_OBJECTIF,offset,100,50);
        obj.setFont(fontEntered);
        this.add(obj);

        //Image de l'objectif
        BufferedImage imgObj = AssetTiles.getObjectifImage(joueur.getStack().peek());
        JLabel picLabel = new JLabel(new ImageIcon(imgObj));
        picLabel.setBounds(SIZE_OBJECTIF+105,SIZE_OBJECTIF+offset,SIZE_OBJECTIF,SIZE_OBJECTIF);
        add(picLabel);
    }

    public void ajoutinfo(){
        int offset = SIZE_COULOIR*7;
        Joueur joueur = modele.getJoueur();
        String[] str1 = joueur.toString().split(",");

        JLabel info = new JLabel(str1[0]);
        info.setBounds(SIZE_OBJECTIF,offset,250,50);
        info.setFont(fontEntered);
        this.add(info);    
    }

}