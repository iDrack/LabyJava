import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCreation extends JPanel{
    private static final long serialVersionUID = 1L;
    private JLabel titre;
    private JLabel nbJoueurText;
    private JTextField nbJoueurField;
    private JButton valider;
    private static int nbJoueurs;
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);
    private Dimension size;
    private static JTextField[] listeAge;
    private static JTextField[] listeCouleurs;
    private MainWindow page;
    private VueJeu menu;

    public MenuCreation(Dimension s,MainWindow page){
        this.size = s;
        this.page = page;
        //Paramétrage de la taille de la fenêtre
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        this.setLayout(null);
        titre = new JLabel("Création de la partie");
        nbJoueurText = new JLabel("Nombre de joueurs :");
        nbJoueurField = new JTextField();
        valider = new JButton("Valider");

        titre.setBounds(351,50,205,50);
        nbJoueurText.setBounds(150,100,225,50);
        nbJoueurField.setBounds(375,112,50,30);
        valider.setBounds(450,111,125,30);

        titre.setFont(fontEntered);
        nbJoueurText.setFont(fontEntered);
        nbJoueurField.setFont(fontEntered);
        valider.setFont(fontEntered);

        this.add(titre);
        this.add(nbJoueurText);
        this.add(nbJoueurField);
        this.add(valider);

        valider.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                genererOptionJoueurs(Integer.parseInt(nbJoueurField.getText()));
            }
        });

    }

    private void genererOptionJoueurs(int x){
        if(nbJoueurs > 0 || x <= 0 || x > 4){
            System.out.println("Valeur interdite");
            return;
        }
        nbJoueurs = x;

        JLabel[] listeTitres = new JLabel[x];
        listeAge = new JTextField[x];
        listeCouleurs = new JTextField[x];
        JLabel couleur, instructionAge, instructionCouleur;
        JButton validerJoueurs;

        validerJoueurs = new JButton("Valider les joueurs");
        instructionAge = new JLabel("Vous devez avoir plus de 8 ans pour jouer.");
        instructionCouleur = new JLabel("Couleurs disponibles : Rouge, Vert, Bleu, Jaune.");
        
        validerJoueurs.setBounds(301,175+(x+1)*(30),305,30);
        instructionAge.setBounds(150,130,1000,50);
        instructionCouleur.setBounds(150,160,1000,50);
        
        validerJoueurs.setFont(fontEntered);
        instructionAge.setFont(fontEntered);
        instructionCouleur.setFont(fontEntered);
        
        this.add(validerJoueurs);
        this.add(instructionAge);
        this.add(instructionCouleur);

        for(int i=0;i<x;i++){
            //Création des labels
            couleur = new JLabel("Couleur :");
            String tmpString = "Joueur n°"+(i+1)+"    Âge :";
            listeTitres[i] = new JLabel(tmpString);
            listeAge[i] = new JTextField();
            listeCouleurs[i] = new JTextField();

            //Positionnement
            listeTitres[i].setBounds(150,160+((i+1)*30),225,50);
            listeAge[i].setBounds(335,172+((i+1)*30),50,30);
            couleur.setBounds(390,160+((i+1)*30),100,50);
            listeCouleurs[i].setBounds(485,172+((i+1)*30),100,30);

            //Polices
            listeTitres[i].setFont(fontEntered);
            listeAge[i].setFont(fontEntered);
            listeCouleurs[i].setFont(fontEntered);
            couleur.setFont(fontEntered);

            //Ajout
            this.add(listeTitres[i]);
            this.add(listeAge[i]);
            this.add(listeCouleurs[i]);
            this.add(couleur);
        }

        validerJoueurs.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(verification()) chargerJeu();
            }
        });
    }

    private boolean verification(){
        int[] lAge = listeAgeToInt();
        String[] lCouleur = listeCouleursToString();

        for(int i=0; i<nbJoueurs; i++){
            if(lAge[i] < 8)return false;
            if((! lCouleur[i].equals("JAUNE")) && (! lCouleur[i].equals("BLEU")) && (! lCouleur[i].equals("VERT")) && (! lCouleur[i].equals("ROUGE")))return false;
            for(int j=0; j<nbJoueurs; j++){
                if( i != j && lCouleur[i].equals(lCouleur[j]))return false;
            }
        }       
        return true;
    }

    private void chargerJeu(){
        //Jeu jeu = new JeuImpl();
        //System.out.println(jeu);
        this.menu = new VueJeu(page);
        this.page.setMenuJeu(menu);
        this.page.setContentPane(menu);
    }

    public VueJeu getMenu(){
        return this.menu;
    }

    public static int getNbJoueurs(){
        return nbJoueurs;
    }

    public static int[] listeAgeToInt() {
        int[] ret = new int[nbJoueurs];
        for(int i=0;i<nbJoueurs;i++){
            ret[i] = Integer.parseInt(listeAge[i].getText());
        }
        return ret;
    }

    public static String[] listeCouleursToString() {
        String[] ret = new String[nbJoueurs];
        for(int i=0;i<nbJoueurs;i++){
            ret[i] = listeCouleurs[i].getText().toUpperCase();
        }
        return ret;
    }

}
