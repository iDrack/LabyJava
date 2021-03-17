import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe gérant les menus à afficher.
 * Elle hérite de la classe JFrame.
 */
public class MainWindow extends JFrame {
    /**
     *serialVersionUI par défaut.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Instance de la classe MainWindow actuel.
     */
    public static MainWindow instance;

    /**
     * Largeur du JFrame.
     */
    public final static int WIDTH=908;

    /**
     * Hauteur du JFrame.
     */
    public final static int HEIGHT=633;

    /**
     * JPanel actuellement affiché dans MainWindow.
     */
    public static JPanel menu;

    /**
     * Instance actuel de VueJeu dans MainWindow.
     */
    public static VueJeu menuJeu;

    /**
     * Instancie un nouveau JFrame avec le titre "Labyrinthe", de taille 908 par 633.
     *
     */
    public MainWindow(){
        // Generation de la fenêtre.
        setTitle("Labyrinthe");
        setSize(WIDTH, HEIGHT);
        setResizable(true);

        // Generation du contenu.
        MainWindow.menu = new MenuPrincipal(this);
        setContentPane(menu);

        instance = this;
        setLocationRelativeTo(null);
        registerClock();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Permet de repeindre le contenu de notre fenêtre.
     * Cette fonction appel le repaint() du JPanel actuel toute les miliseconde.
     */
    private void registerClock() {
        Timer clock = new Timer();
        clock.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                instance.getContentPane().repaint();
            }
        }, 1000, 17);
    }

    /**
     * Permet de récupérer l'attribut menuJeu de MainWindow.
     * 
     * @return VueJeu, menu composant actuel de la fenêtre active.
     */
    public VueJeu getMenuJeu(){
        return menuJeu;
    }

    /**
     * Permet de modifier l'attribut menuJeu de MainWindow.
     * 
     * @param menu nouveau VueJeu de MainWindow.
     */
    public void setMenuJeu(VueJeu menu){
        menuJeu = menu;
    }

    /**
     * Fonction principal de l'application.
     * Créer un nouveau MainWindow.
     * 
     * @param args Option de l'application.
     */
    public static void main(String[] args){
        new MainWindow();
    }
}
