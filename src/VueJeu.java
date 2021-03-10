import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class  VueJeu extends JPanel {
    private MainWindow page;
    private final int SIZE_COULOIR = 93;
    private final int SIZE_OBJECTIF = 15;
    private final int SIZE_PION = 32;
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);
    private Jeu modele = new JeuImpl();
    private Plateau plateau = modele.getPlateau();


    private JLabel xText;
    private JLabel yText;
    private JLabel xTextCouloir;
    private JLabel yTextCouloir;
    private JLabel orientationText;

    private JTextField x;
    private JTextField y;
    private JTextField xCouloir;
    private JTextField yCouloir;
    private JTextField orientation;

    private JButton validerMouvement;
    private JButton validerCouloir;


    public VueJeu(MainWindow page){
        this.page=page;

        //Paramétrage de la taille de la fenêtre
        page.setSize(SIZE_COULOIR*7,SIZE_COULOIR*8+107);
        page.setLocationRelativeTo(null);
        MainWindow.instance.requestFocusInWindow(); 
        
        setLayout(null);

        try {
            ajoutCase();
        } catch (IOException e) {
            e.printStackTrace();
        }      
        
        ajoutObjectifActuel();
        ajoutinfo();
        ajoutOptions();
    }

    public void ajoutCase() throws IOException{
        //Position du pion du joueur
        int posX = modele.getJoueur().getPion().getPositionCourante().getX();
        int posY = modele.getJoueur().getPion().getPositionCourante().getY();

        BufferedImage img;
        CouloirImpl [][] mat = plateau.getCouloirImpls();
        
        for(int i = 0; i < Plateau.TAILLE;i++){
            for (int j = 0 ; j < Plateau.TAILLE; j++){
                img = AssetTiles.getCouloirImage(mat[i][j]);
                JLabel picLabel;
                if(i == posX && j == posY){
                    //TODO Modifier pour que l'on affiche tous les pions
                    String[] str1 = modele.getJoueur().toString().split(",");
                    String[] str2 = str1[0].split(" ");
                    BufferedImage img2 = AssetTiles.getPionImage(str2[1]);
                    picLabel = new JLabel(new ImageIcon(AssetTiles.combinerImage(img, img2)));
                }else{
                    picLabel = new JLabel(new ImageIcon(img));
                }
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

    public void ajoutOptions(){
        int offset = SIZE_COULOIR*7+25;
        int offsetHorizontal = SIZE_OBJECTIF + 400;

        validerMouvement = new JButton("Déplacer");
        validerCouloir = new JButton("Placer couloir");
        System.out.println(modele.getJoueur().getPion().toString());
        JLabel position = new JLabel(modele.getJoueur().getPion().getPositionCourante().toString());
        this.xText = new JLabel("X :");
        this.x = new JTextField();
        this.yText = new JLabel("Y :");
        this.y = new JTextField();

        this.xTextCouloir = new JLabel("X :");
        this.xCouloir = new JTextField();
        this.yTextCouloir = new JLabel("Y :");
        this.yCouloir = new JTextField();
        this.orientationText = new JLabel("Orientation :");
        this.orientation = new JTextField();

        position.setFont(fontEntered);
        validerMouvement.setFont(fontEntered);
        validerCouloir.setFont(fontEntered);
        xText.setFont(fontEntered);
        x.setFont(fontEntered);
        yText.setFont(fontEntered);
        y.setFont(fontEntered);
        
        xTextCouloir.setFont(fontEntered);
        xCouloir.setFont(fontEntered);
        yTextCouloir.setFont(fontEntered);
        yCouloir.setFont(fontEntered);
        orientationText.setFont(fontEntered);
        orientation.setFont(fontEntered);
        
        position.setBounds(offsetHorizontal,offset-30,200,50);
        validerMouvement.setBounds(offsetHorizontal,offset+70,125,25);
        xText.setBounds(offsetHorizontal,offset,30,50);
        x.setBounds(offsetHorizontal+35,offset+10,50,30);
        yText.setBounds(offsetHorizontal,offset+30,30,50);
        y.setBounds(offsetHorizontal+35,offset+40,50,30);

        orientation.setBounds((offsetHorizontal-250)+135,offset-20,50,30);
        orientationText.setBounds((offsetHorizontal-250),offset-20,150,30);
        validerCouloir.setBounds((offsetHorizontal-250),offset+70,200,25);
        xTextCouloir.setBounds((offsetHorizontal-250),offset,30,50);
        xCouloir.setBounds((offsetHorizontal-250)+35,offset+10,50,30);
        yTextCouloir.setBounds((offsetHorizontal-250),offset+30,30,50);
        yCouloir.setBounds((offsetHorizontal-250)+35,offset+40,50,30);

        this.add(position);
        this.add(validerMouvement);
        this.add(x);
        this.add(xText);
        this.add(y);
        this.add(yText);   
        this.add(orientation);
        this.add(orientationText);
        this.add(xTextCouloir);    
        this.add(xCouloir);    
        this.add(yTextCouloir);    
        this.add(yCouloir);    
        this.add(validerCouloir);

    }
}