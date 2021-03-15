import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class MenuCreation extends JPanel{

    /**
     * Nombre de joueurs jouant au jeu, permet de savoir combien de joueurs sont a créer
     */
    private static int nbJoueurs;

    /**
     * Police d'écriture utilisé par le MenuCreation
     */
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);

    /**
     * Champs de saisie pour le nombre de joueurs
     */
    private JFormattedTextField nbJoueurField;

    /**
     * Liste de champs de saisie pour l'âge des joueurs
     */
    private static JFormattedTextField[] listeAge;

    /**
     * Liste de champs de saisie pour la couleur de chaque joueurs
     */
    private static JTextField[] listeCouleurs;

    /**
     * Instance de la fenêtre actuel
     */
    private MainWindow page;

    /**
     * Instance du menu VueJeu
     */
    private VueJeu menu;
    
    /**
     * Format n'acceptant que les entiers
     */
    private NumberFormat format = NumberFormat.getInstance();
    
    /**
     * Formateur de nombre
     */
    private NumberFormatter nff = new NumberFormatter(format);
    
    /**
     * Factory de formatteur de nombre
     */
    private DefaultFormatterFactory factory = new DefaultFormatterFactory(nff);

    /**
     * MenuCreation est un JPanel permettant de créer les joueurs
     * 
     * @param size Taille du JPanel ainsi que la taille de la fenêtre
     * @param page Jframe contenant MenuCreation
     */
    public MenuCreation(Dimension size, MainWindow page){
        this.page = page;
        //Paramétrage de la taille de la fenêtre
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        this.setLayout(null);
        JLabel titre = new JLabel("Création de la partie");
        JLabel nbJoueurText = new JLabel("Nombre de joueurs :");
        nbJoueurField = new JFormattedTextField();
        nbJoueurField.setFormatterFactory(factory);
        JButton valider = new JButton("Valider");

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
                if(! nbJoueurField.getText().equals("")) genererOptionJoueurs(Integer.parseInt(nbJoueurField.getText()));
            }
        });

    }

    /**
     * Génére les champs de saisie pour l'age et la couleur des joueurs.
     * La méthode ne fait rien si x est inférieur ou égal à 0 ou est supérieur ou égal à 4 ou bien si nbJoueur est supérieur à 0
     * @param x Nombre de joueurs choisit par l'utilisateur, passer par le champs nbJoueurField
     */
    private void genererOptionJoueurs(int x){
        if(nbJoueurs > 0 || x <= 0 || x > 4){
            System.out.println("Valeur interdite");
            return;
        }
        nbJoueurs = x;

        JLabel[] listeTitres = new JLabel[x];
        listeAge = new JFormattedTextField[x];
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
            listeAge[i] = new JFormattedTextField();
            listeAge[i].setFormatterFactory(factory);
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

    /**
     * Vérifie si les valeurs entrées dans les champs pour les âges et les couleurs des joueurs sont correct.
     * @return Boolean indiquant si les valeurs entrées sont légal ou non
     */
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

    /**
     * Méthode chargeant le jeu. Créer une nouvelle instance de VueJeu avec la valeur actuel de page. 
     * Puis définit cette nouvelle instance de VueJeu comme étant le JPanel de MainWindow a afficher.
     */
    private void chargerJeu(){
        this.menu = new VueJeu(page);
        this.page.setMenuJeu(menu);
        this.page.setContentPane(menu);
    }

    /**
     * Retourne la valeur de menu.
     * @return valeur de menu, de type VueJeu.
     */
    public VueJeu getMenu(){
        return this.menu;
    }

    /**
     * Donne le nombre de joueurs a créer.
     * @return valeur de nbJoueur, de type int.
     */
    public static int getNbJoueurs(){
        return nbJoueurs;
    }

    /**
     * Vérifie les valeurs de listeAge avant de retourner une liste des âges des joueurs.
     * @return liste des âges des joueurs, de type int[].
     */
    public static int[] listeAgeToInt() {
        int[] ret = new int[nbJoueurs];
        for(int i=0;i<nbJoueurs;i++){
            if(! listeAge[i].getText().equals(""))  ret[i] = Integer.parseInt(listeAge[i].getText());
        }
        return ret;
    }

    /**
     * Vérifie les valeurs de listeCouleurs avant de retourner une liste des couleurs des joueurs.
     * @return liste des couleurs des joueurs, de type String[].
     */
    public static String[] listeCouleursToString() {
        String[] ret = new String[nbJoueurs];
        for(int i=0;i<nbJoueurs;i++){
            ret[i] = listeCouleurs[i].getText().toUpperCase();
        }
        return ret;
    }

}
