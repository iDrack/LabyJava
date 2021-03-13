import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueJeu extends JPanel {
    private MainWindow page;
    private final int SIZE_COULOIR = 93;
    private final int SIZE_OBJECTIF = 15;
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);
    private Jeu modele = new JeuImpl();
    private Plateau plateau = modele.getPlateau();

    private JLabel xText;
    private JLabel yText;
    private JLabel posTextCouloir;
    private JLabel orientationText;

    private JTextField x;
    private JTextField y;
    private JTextField posCouloir;
    private JTextField orientation;

    private JButton validerMouvement;


    public VueJeu(MainWindow page){
        this.page=page;

        //Paramétrage de la taille de la fenêtre
        Dimension d = new Dimension(SIZE_COULOIR*9+400,SIZE_COULOIR*9);
        page.setSize(d);
        page.setLocationRelativeTo(null);
        MainWindow.instance.requestFocusInWindow(); 
        
        ajoutFleche();
        setLayout(null);
        try {
            ajoutCase();
        } catch (IOException e) {
            e.printStackTrace();
        }             
        ajoutObjectifActuel();
        ajoutinfo();
        ajoutOptions();
        ajoutCouloir();
        ajoutGuide();

    }

    public void ajoutCase() throws IOException{
        //Position du pion du joueur

        BufferedImage img;
        CouloirImpl [][] mat = plateau.getCouloirImpls();
        Objectif obj;

        ArrayList<Joueur> listeJoueurs = modele.getJoueurs();
        
        for(int i = 0; i < Plateau.TAILLE;i++){
            for (int j = 0 ; j < Plateau.TAILLE; j++){
                img = AssetTiles.getCouloirImage(mat[i][j]);
                JLabel picLabel;

                obj = plateau.getObjectifCase(new Position(i, j));

                for(int k=0; k< listeJoueurs.size();k++){
                    int posX = listeJoueurs.get(k).getPion().getPositionCourante().getX();
                    int posY = listeJoueurs.get(k).getPion().getPositionCourante().getY();

                    if((obj != Objectif.VIDE) && i == posX && j == posY){
                        //Si on a objectif et joueur
                        String[] str1 = listeJoueurs.get(k).toString().split(",");
                        String[] str2 = str1[0].split(" ");
    
                        BufferedImage img3 = AssetTiles.getPionImage(str2[1]);
                        BufferedImage img2 = AssetTiles.getObjectifImage(obj);
    
                        img2 = AssetTiles.redimensionner(img2, SIZE_OBJECTIF*3, SIZE_OBJECTIF*3);
    
                        picLabel = new JLabel(new ImageIcon(AssetTiles.combinerImage(img, AssetTiles.combinerImage(img2, img3, true), false)));
    
                    }else if(obj != Objectif.VIDE){
                        //Si on a objectif
                        BufferedImage img2 = AssetTiles.getObjectifImage(obj);
    
                        img2 = AssetTiles.redimensionner(img2, SIZE_OBJECTIF*3, SIZE_OBJECTIF*3);
                        picLabel = new JLabel(new ImageIcon(AssetTiles.combinerImage(img, img2, true)));
    
                    }else if(i == posX && j == posY){
                        //Si on a joueur
                        String[] str1 = listeJoueurs.get(k).toString().split(",");
                        String[] str2 = str1[0].split(" ");
                        BufferedImage img2 = AssetTiles.getPionImage(str2[1]);
                        picLabel = new JLabel(new ImageIcon(AssetTiles.combinerImage(img, img2, false)));
                    }else{
                        picLabel = new JLabel(new ImageIcon(img));
                    }
                    //hORIZONTAL PUI VERTICAL
                    picLabel.setBounds(SIZE_COULOIR*(j+1),SIZE_COULOIR*(i+1),SIZE_COULOIR,SIZE_COULOIR);
                    add(picLabel);
                }

                
            }
        }
    }

    public void ajoutObjectifActuel(){
        int offset = SIZE_COULOIR*9;
        Joueur joueur = modele.getJoueur();

        //Affichage de son objectif
        JLabel obj = new JLabel("Objectif :");
        obj.setBounds(offset,SIZE_COULOIR+275,100,50);
        obj.setFont(fontEntered);
        this.add(obj);

        //Image de l'objectif
        BufferedImage imgObj = AssetTiles.getObjectifImage(joueur.getStack().peek());
        imgObj = AssetTiles.redimensionner(imgObj, SIZE_OBJECTIF*2, SIZE_OBJECTIF*2);

        JLabel picLabel = new JLabel(new ImageIcon(imgObj));
        picLabel.setBounds(offset+100,SIZE_COULOIR+285,SIZE_OBJECTIF*2,SIZE_OBJECTIF*2);
        add(picLabel);
    }

    public void ajoutinfo(){
        int offset = SIZE_COULOIR*9;
        Joueur joueur = modele.getJoueur();
        String[] str1 = joueur.toString().split(",");

        JLabel info = new JLabel(str1[0]+"      "+modele.getJoueur().getPion().getPositionCourante().toString());

        info.setBounds(offset,SIZE_COULOIR+250,400,50);

        info.setFont(fontEntered);

        this.add(info);
    }

    public void ajoutOptions(){
        int offset = SIZE_COULOIR*9;
        int offset2 = SIZE_COULOIR+475;        

        validerMouvement = new JButton("Déplacer");
        System.out.println(modele.getJoueur().getPion().toString());
        this.xText = new JLabel("X :");
        this.x = new JTextField();
        this.yText = new JLabel("Y :");
        this.y = new JTextField();       

        validerMouvement.setFont(fontEntered);
        xText.setFont(fontEntered);
        x.setFont(fontEntered);
        yText.setFont(fontEntered);
        y.setFont(fontEntered);
        
        validerMouvement.setBounds(offset+145,offset2+10,125,29);
        xText.setBounds(offset,offset2,30,50);
        x.setBounds(offset+30,offset2+10,30,30);
        yText.setBounds(offset+70,offset2,30,50);
        y.setBounds(offset+100,offset2+10,30,30);
       
        this.add(validerMouvement);
        this.add(x);
        this.add(xText);
        this.add(y);
        this.add(yText);   

        this.validerMouvement.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(getPosX() >= 0 && getPosX() <= 6 && getPosY() >= 0 && getPosY() <= 6 && verifierOrientation() && verifierPos()){
                    if(modele.getPlateau().estAtteignable(modele.getJoueur().getPion().getPositionCourante(), new Position(getPosX(), getPosY()))){
                        modele.getJoueur().joue();
                        System.out.println("Le joueur a joué");
                        modele.jouer();
                    }
                }
            }
        });
    }

    private void ajoutFleche(){
        //Ajout du Nord
        JLabel Nord1 = new JLabel("N1");
        JLabel Nord2 = new JLabel("N2");
        JLabel Nord3 = new JLabel("N3");

        Nord1.setFont(fontEntered);
        Nord2.setFont(fontEntered);
        Nord3.setFont(fontEntered);

        Nord1.setBounds(SIZE_COULOIR*2+30,SIZE_COULOIR/4,SIZE_COULOIR,SIZE_COULOIR);
        Nord2.setBounds(SIZE_COULOIR*4+30,SIZE_COULOIR/4,SIZE_COULOIR,SIZE_COULOIR);
        Nord3.setBounds(SIZE_COULOIR*6+30,SIZE_COULOIR/4,SIZE_COULOIR,SIZE_COULOIR);

        this.add(Nord1);
        this.add(Nord2);
        this.add(Nord3);

        //Ajout de l'Ouest
        JLabel Ouest1 = new JLabel("O1");
        JLabel Ouest2 = new JLabel("O2");
        JLabel Ouest3 = new JLabel("O3");

        Ouest1.setFont(fontEntered);
        Ouest2.setFont(fontEntered);
        Ouest3.setFont(fontEntered);

        Ouest1.setBounds(SIZE_COULOIR-(30+SIZE_COULOIR/4),SIZE_COULOIR*2,SIZE_COULOIR,SIZE_COULOIR);
        Ouest2.setBounds(SIZE_COULOIR-(30+SIZE_COULOIR/4),SIZE_COULOIR*4,SIZE_COULOIR,SIZE_COULOIR);
        Ouest3.setBounds(SIZE_COULOIR-(30+SIZE_COULOIR/4),SIZE_COULOIR*6,SIZE_COULOIR,SIZE_COULOIR);

        this.add(Ouest1);
        this.add(Ouest2);
        this.add(Ouest3);

        //Ajout du Sud 
        JLabel Sud1 = new JLabel("S1");
        JLabel Sud2 = new JLabel("S2");
        JLabel Sud3 = new JLabel("S3");

        Sud1.setFont(fontEntered);
        Sud2.setFont(fontEntered);
        Sud3.setFont(fontEntered);

        Sud1.setBounds(SIZE_COULOIR*2+30,SIZE_COULOIR*7+SIZE_COULOIR/4+SIZE_COULOIR/2,SIZE_COULOIR,SIZE_COULOIR);
        Sud2.setBounds(SIZE_COULOIR*4+30,SIZE_COULOIR*7+SIZE_COULOIR/4+SIZE_COULOIR/2,SIZE_COULOIR,SIZE_COULOIR);
        Sud3.setBounds(SIZE_COULOIR*6+30,SIZE_COULOIR*7+SIZE_COULOIR/4+SIZE_COULOIR/2,SIZE_COULOIR,SIZE_COULOIR);

        this.add(Sud1);
        this.add(Sud2);
        this.add(Sud3);

        //Ajout de l'Est
        JLabel Est1 = new JLabel("E1");
        JLabel Est2 = new JLabel("E2");
        JLabel Est3 = new JLabel("E3");

        Est1.setFont(fontEntered);
        Est2.setFont(fontEntered);
        Est3.setFont(fontEntered);

        Est1.setBounds(SIZE_COULOIR*7+SIZE_COULOIR/4+SIZE_COULOIR/2+SIZE_COULOIR-(30+SIZE_COULOIR/4),SIZE_COULOIR*2,SIZE_COULOIR,SIZE_COULOIR);
        Est2.setBounds(SIZE_COULOIR*7+SIZE_COULOIR/4+SIZE_COULOIR/2+SIZE_COULOIR-(30+SIZE_COULOIR/4),SIZE_COULOIR*4,SIZE_COULOIR,SIZE_COULOIR);
        Est3.setBounds(SIZE_COULOIR*7+SIZE_COULOIR/4+SIZE_COULOIR/2+SIZE_COULOIR-(30+SIZE_COULOIR/4),SIZE_COULOIR*6,SIZE_COULOIR,SIZE_COULOIR);

        this.add(Est1);
        this.add(Est2);
        this.add(Est3);
    }

    private void ajoutCouloir(){
        int offset = SIZE_COULOIR*9;
        int offset2 = SIZE_COULOIR+350;

        this.posTextCouloir = new JLabel("Position :");
        this.posCouloir = new JTextField();
        this.orientationText = new JLabel("Orientation :");
        this.orientation = new JTextField();
        JLabel exemple = new JLabel("Exemple : N1");

        posTextCouloir.setFont(fontEntered);
        posCouloir.setFont(fontEntered);
        orientationText.setFont(fontEntered);
        orientation.setFont(fontEntered);
        exemple.setFont(new Font(Font.DIALOG, Font.ROMAN_BASELINE, 12));

        orientationText.setBounds(offset+117,offset2+50,150,50);
        posTextCouloir.setBounds(offset+117,offset2-10,150,50);

        exemple.setBounds(offset+117,offset2+10,150,50);

        orientation.setBounds(offset+252,offset2+60,60,30);
        posCouloir.setBounds(offset+217,offset2,50,30);

        Couloir couloir = modele.getSupplementaire();
        BufferedImage img = AssetTiles.getCouloirImage(couloir);
        JLabel picLabel = new JLabel(new ImageIcon(img));
        picLabel.setBounds(offset,offset2,SIZE_COULOIR,SIZE_COULOIR);

        this.add(picLabel);
        this.add(orientation);
        this.add(orientationText);
        this.add(posTextCouloir);    
        this.add(posCouloir);
        this.add(exemple);
    }

    private void ajoutGuide(){
        BufferedImage img = AssetTiles.getImage("guide.png");
        JLabel picLabel = new JLabel(new ImageIcon(img));
        picLabel.setBounds(SIZE_COULOIR*9,SIZE_COULOIR,345,248);
        add(picLabel);
    }

    public Boolean verifierOrientation(){
        return (getOrientation().equals("NORD") || getOrientation().equals("OUEST") || getOrientation().equals("SUD") || getOrientation().equals("EST"));
    }

    public Boolean verifierPos(){
        PositionInsertion[] tab = PositionInsertion.values();
        for(int i=0; i<tab.length; i++){
            if(getPosCouloir().equals(tab[i].toString()))return true;
        }
        return false;
    }

    public int getPosX(){
        if(x.getText().equals(""))return -1;
        return Integer.parseInt(x.getText());
    }

    public int getPosY(){
        if(y.getText().equals(""))return -1;
        return Integer.parseInt(y.getText());
    }

    public String getOrientation(){
        return orientation.getText().toUpperCase();
    }

    public String getPosCouloir(){
        return posCouloir.getText().toUpperCase();
    }

    public void reset(){
        this.x.setText("");
        this.y.setText("");
        this.posCouloir.setText("");
        this.orientation.setText("");

        this.removeAll();    //Supprimme les elements graphique
        ajoutFleche();
        try {
            ajoutCase();
        } catch (IOException e) {
            e.printStackTrace();
        }             
        ajoutObjectifActuel();
        ajoutinfo();
        ajoutOptions();
        ajoutCouloir();
        ajoutGuide();
    }

}