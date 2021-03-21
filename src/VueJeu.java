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

/**
 * Classe servant à l'affichage du plateau lors du tour d'un joueur.
 * Affiche les information sur le plateau, les pions des joueurs, l'objectif du joueur, le couloir à insérer.
 * Permet au joueur en train de jouer de choisir où insérer le-dit couloir ainsi que choisir où se déplacer.
 */
public class VueJeu extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * JFrame actuel définissant la fenêtre de l'application.
     */
    private MainWindow page;

    /**
     * Taille des images des couloirs.
     */
    private final int SIZE_COULOIR = 93;

    /**
     * Taille des images des objectifs.
     */
    private final int SIZE_OBJECTIF = 32;

    /**
     * Police d'écriture utilisé par la classe VueJeu.
     */
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);

    /**
     * Instance du Jeu. C'est ici que l'on créer le jeu.
     */
    private Jeu modele = new JeuImpl();

    /**
     * Instance du plateau du jeu définit ar JeuImpl.
     */
    private Plateau plateau = modele.getPlateau();

    /**
     * Liste déroulante des nouvelles coordonnées du joueur (x).
     */
    private JComboBox<String> x;

    /**
     * Liste déroulante des nouvelles coordonnées du joueur (y).
     */
    private JComboBox<String> y;

    /**
     * Liste déroulante de la nouvelle position du couloir a insérer.
     */
    private JComboBox<String> posCouloir;

    /**
     * Liste déroulante de l'orientation du couloir a insérer.
     */
    private JComboBox<String> orientation;

    /**
     * Boolean permet de savoir si le joueur a insérer ou non le couloir.
     * Il permet aussi de savoir où nousnous trouvons dans le tou du joueur.
     */
    private Boolean dejaInserer;

    /**
     * VueJeu correspond au GUI du jeu, affichant le plateau, les coordonnées du joueur actuel, 
     * le couloirs a insérer, un guide d'insertion ainsi que les champs de saisie du joueur.
     * 
     * @param page MainWindow définissant la fenêtre du jeu.
     */
    public VueJeu(MainWindow page){
        this.page=page;
        this.dejaInserer = false;

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
        ajoutCouloir();
        ajoutGuide();

    }

    /**
     * Méthode affichant le plateau du jeu (couloirs, joueurs et objectifs), selon les coordonnés des joueurs ainsi que les coordonnées des objectifs,
     * cette méthode pourra les combiner en appelant la méthode combinerImage de AssetTiles.
     * Les Joueur se trouvant au dessus des objectifs qui se trouve au dessus des couloirs.
     * 
     * @throws IOException.
     */
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
    
                        picLabel = new JLabel(new ImageIcon(AssetTiles.combinerImage(AssetTiles.combinerImage(img, img2, true), img3, false)));
    
                    }else if(obj != Objectif.VIDE){
                        //Si on a objectif
                        BufferedImage img2 = AssetTiles.getObjectifImage(obj);
    
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
                    
                    picLabel.setBounds(SIZE_COULOIR*(j+1),SIZE_COULOIR*(i+1),SIZE_COULOIR,SIZE_COULOIR);
                    add(picLabel);
                }

                
            }
        }
    }

    /**
     * Méthode affichant l'image de l'objectif du joueur actuel.
     */
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

        JLabel picLabel = new JLabel(new ImageIcon(imgObj));
        picLabel.setBounds(offset+100,SIZE_COULOIR+285,SIZE_OBJECTIF,SIZE_OBJECTIF);
        add(picLabel);
    }

    /**
     * Méthode affichant les informations du jouuer (Coordonnées, couleur).
     */
    public void ajoutinfo(){
        int offset = SIZE_COULOIR*9;
        Joueur joueur = modele.getJoueur();
        String[] str1 = joueur.toString().split(",");

        JLabel info = new JLabel(str1[0]+"      "+modele.getJoueur().getPion().getPositionCourante().toString());

        info.setBounds(offset,SIZE_COULOIR+250,400,50);

        info.setFont(fontEntered);

        this.add(info);
    }

    /**
     * Permet d'ajouter les champs demandant les nouvelles coordonnées du joueur.
     * Cette méthode est appelé après que le joueurs ait insérer un couloir sur le plateau.
     * Le bouton validerMouvement vérifie les valeurs des champs x et y avant de finir le tour du joueur actuel.
     */
    public void ajoutOptions(){
        int offset = SIZE_COULOIR*9;
        int offset2 = SIZE_COULOIR+475;        

        JButton validerMouvement = new JButton("Déplacer");
        System.out.println(modele.getJoueur().getPion().toString());

        String[] valeursXY = {"0","1","2","3","4","5","6"};

        JLabel xText = new JLabel("X :");
        this.x = new JComboBox<String>(valeursXY);

        x.setSelectedIndex(modele.getJoueur().getPion().getPositionCourante().getX());

        JLabel yText = new JLabel("Y :");
        this.y = new JComboBox<String>(valeursXY);
        
        y.setSelectedIndex(modele.getJoueur().getPion().getPositionCourante().getY());

        JLabel guideCoor = new JLabel("Ligne : x (0~6), Colonne : y (0~6).");

        validerMouvement.setFont(fontEntered);
        xText.setFont(fontEntered);
        x.setFont(fontEntered);
        yText.setFont(fontEntered);
        y.setFont(fontEntered);
        guideCoor.setFont(new Font(Font.DIALOG, Font.ROMAN_BASELINE, 12));
        
        validerMouvement.setBounds(offset+145,offset2+10,125,29);
        xText.setBounds(offset,offset2,30,50);
        x.setBounds(offset+30,offset2+10,30,30);
        yText.setBounds(offset+70,offset2,30,50);
        y.setBounds(offset+100,offset2+10,30,30);
        guideCoor.setBounds(offset,offset2+25,250,50);
       
        this.add(validerMouvement);
        this.add(x);
        this.add(xText);
        this.add(y);
        this.add(yText);   
        this.add(guideCoor);

        validerMouvement.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(getPosX() >= 0 && getPosX() <= 6 && getPosY() >= 0 && getPosY() <= 6){
                    if(modele.getPlateau().estAtteignable(modele.getJoueur().getPion().getPositionCourante(), new Position(getPosX(), getPosY()))){
                        modele.getJoueur().joue();
                        modele.jouer();
                    }
                }
            }
        });
    }

    /**
     * Affoche les nom des colonnes et lignes de couloirs mobiles sur le plateau.
     */
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

    /**
     * Permet d'afficher les information relatives au couloir a insérer.
     * Le bouton moveCouloir vérifie la valeurs des champs position et orientation avant de mettre à jour le plateau.
     * Afin de mettre à jour le plateau, il est nécessaire de faire appel à removeAll() ainsi que reset() et les fonctions d'ajout de JLabel.
     */
    private void ajoutCouloir(){
        int offset = SIZE_COULOIR*9;
        int offset2 = SIZE_COULOIR+350;

        JLabel posTextCouloir = new JLabel("Position :");
        String[] valeursPosition = {"N1","N2","N3","S1","S2","S3","O1","O2","O3","E1","E2","E3"};
        this.posCouloir = new JComboBox<>(valeursPosition);
        this.posCouloir.setSelectedIndex(0);

        JLabel orientationText = new JLabel("Orientation :");
        String[] valeursOrientation = {"Nord","Est","Sud","Ouest"};
        this.orientation = new JComboBox<>(valeursOrientation);
        this.orientation.setSelectedIndex(0);

        JButton moveCouloirs = new JButton("Insérer");

        posTextCouloir.setFont(fontEntered);
        posCouloir.setFont(fontEntered);
        orientationText.setFont(fontEntered);
        orientation.setFont(fontEntered);
        moveCouloirs.setFont(fontEntered);

        posTextCouloir.setBounds(offset+117,offset2-15,150,50);
        orientationText.setBounds(offset+117,offset2+25,150,50);

        posCouloir.setBounds(offset+252,offset2-5,80,30);
        orientation.setBounds(offset+252,offset2+35,80,30);

        moveCouloirs.setBounds(offset+117,offset2+70,215,29);

        moveCouloirs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(!dejaInserer && verifierOrientation() && verifierPos()){
                    modele.modifierCouloirs(modele.getJoueur().choisirPositionInsertionCouloir(), modele.getJoueur().choisirOrientationCouloir());    //Modification du couloir
                    removeAll();
                    reset(true);
                    ajoutOptions();
                }
            }
        });

        if(! this.dejaInserer){
            Couloir couloir = modele.getSupplementaire();
            BufferedImage img;
    
            if(couloir.getObjectif() != Objectif.VIDE){
                img = AssetTiles.getCouloirImage(couloir);
                BufferedImage img2 = AssetTiles.getObjectifImage(couloir.getObjectif());
                img = AssetTiles.combinerImage(img, img2, true);
            }else{
                img = AssetTiles.getCouloirImage(couloir);
            }
    
            JLabel picLabel = new JLabel(new ImageIcon(img));
            picLabel.setBounds(offset,offset2,SIZE_COULOIR,SIZE_COULOIR);
            this.add(picLabel);

        }

        this.add(orientation);
        this.add(orientationText);
        this.add(posTextCouloir);    
        this.add(posCouloir);
        this.add(moveCouloirs);

    }

    /**
     * Affiche l'image guide d'insertion.
     */
    private void ajoutGuide(){
        BufferedImage img = AssetTiles.getImage("guide.png");
        JLabel picLabel = new JLabel(new ImageIcon(img));
        picLabel.setBounds(SIZE_COULOIR*9,SIZE_COULOIR,345,248);
        add(picLabel);
    }

    /**
     * Vérifie la valeur du champs de saisie de l'orientation.
     * @return Booelan vérifiant que la valeur du champs orientation est légal.
     */
    public Boolean verifierOrientation(){
        return (getOrientation().equals("NORD") || getOrientation().equals("OUEST") || getOrientation().equals("SUD") || getOrientation().equals("EST"));
    }

    /**
     * Vérifie la valeur du champs position.
     * @return Boolean vérifiant que la valeur du champs position est légal.
     */
    public Boolean verifierPos(){
        PositionInsertion[] tab = PositionInsertion.values();
        for(int i=0; i<tab.length; i++){
            if(getPosCouloir().equals(tab[i].toString()))return true;
        }
        return false;
    }

    /**
     * Permet de remettre à zéro les élèments de VueJeu et ainsi de réinitialiser l'affichage du plateau.
     * @param deja Boolean permettant de vérifier si nous somme à la fin du tour, si oui alors toutes les valeur des JLabel de VueJeu sont remisent à = sinon, nous gardons les valeurs de la position et lòrientation du couloir à insérer.
     */
    public void reset(Boolean deja){
        int p = 0;
        int o = 0;
        if( deja){
            p = this.posCouloir.getSelectedIndex();
            o = this.orientation.getSelectedIndex();
        }
        this.dejaInserer = deja;

        this.removeAll();    //Supprimme les elements graphique
        ajoutFleche();
        try {
            ajoutCase();
        } catch (IOException e) {
            e.printStackTrace();
        }             
        ajoutObjectifActuel();
        ajoutinfo();
        ajoutCouloir();
        ajoutGuide();
        this.posCouloir.setSelectedIndex(p);
        this.orientation.setSelectedIndex(o);
    }

    /**
     * Permet d'accéder à la valeur du champs x de VueJeu.
     * @return int, -1 si le champs x est vide.
     */
    public int getPosX(){
        if(x.getSelectedItem().toString().equals(""))return -1;
        return Integer.parseInt(x.getSelectedItem().toString());
    }

    /**
     * Permet d'accéder à la valeur du champs y de VueJeu.
     * @return int, -1 si le champs y est vide.
     */
    public int getPosY(){
        if(y.getSelectedItem().toString().equals(""))return -1;
        return Integer.parseInt(y.getSelectedItem().toString());
    }

    /**
     * Permet d'accéder à la valeur du champs orientation.
     * @return String corespondant au texte dans le champs orientation.
     */
    public String getOrientation(){
        return orientation.getSelectedItem().toString().toUpperCase();
    }

    /**
     * Permet d'accéder à la valeur du champs position.
     * @return String correspondant au texte dans le champs position.
     */
    public String getPosCouloir(){
        return posCouloir.getSelectedItem().toString().toUpperCase();
    }

    /**
     * Permet d'accéder à la valeur de page.
     * @return MainWindow correspondant à la fenêtre actuel du jeu.
     */
    public MainWindow getPage(){
        return page;
    }
}