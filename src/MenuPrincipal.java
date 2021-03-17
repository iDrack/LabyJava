import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe affichant le menu principal du jeu, laisse le choix au joueur entre jouer ou quitter le jeu.
 */
public class MenuPrincipal extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Police d'écriture utilisé par le MenuPrincipal.
     */
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);

    /**
     * Image de fond de MenuPrincipal.
     */
    private Image img;

    /**
     * Fenêtre actuel de l'application, de type MainWindow.
     */
    private MainWindow fenetre;

    /**
     * Taille de menuPrincipal.
     */
    private Dimension size;

    /**
     * MenuPrincipal est un JPanel contenant deux boutons ainsi qu'une image de fond.
     * 
     * @param fenetre Fenêtre actuel de l'application, de type MainWindow.
     */
    public MenuPrincipal(MainWindow fenetre){
        this.fenetre = fenetre;

        //Accés à l'image de fond
        this.img = new ImageIcon("media/img/labyrinthe.png").getImage();

        //Détection de la taille de l'image de fond
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        this.size = size;
        this.setLayout(null);

        //Crréation des label jouer et quitter
        JButton jouer = new JButton("Jouer");
        JButton quitter = new JButton("Quitter");

        //Positionnement des boutons
        jouer.setBounds(225,500,150,50);
        quitter.setBounds(531,500,150,50);

        //Choix des polices d'écriture
        jouer.setFont(fontEntered);
        quitter.setFont(fontEntered);

        //ajout des boutons 
        this.add(jouer);
        this.add(quitter);

        //Ajout des fonctionnalité des boutons
        jouer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                changerMenu();
            }
        });
        quitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                MainWindow.instance.dispose();  //Ferme proprement la fenêtre ouverte
                System.exit(0);                 //Quitte le programme
            }
        });

    }

    /**
     * Méthode affichant les composant graphique de MenuPrincipal
     * 
     * @param g Composant graphique de type Graphics
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    /**
     * Méthode permettant de changer le JPanel de MainWindow (De MenuPrinciapl à MenuCreation)
     */
    private void changerMenu(){
        MenuCreation menuCreation = new MenuCreation(size,fenetre);
        this.fenetre.setContentPane(menuCreation);
    }
    
}
