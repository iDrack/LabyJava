import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    public static JFrame instance;
    public final static int WIDTH=908;
    public final static int HEIGHT=633;

    public static MenuPrincipal menu;

    /**
     * Constructeur de la classe MainWindow.
     */
    public MainWindow(){

        // Generation de la fenêtre.
        setTitle("Labyrinthe");
        setSize(WIDTH, HEIGHT);
        setResizable(false);

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
    
    public static void main(String[] args){
        new MainWindow();
    }
}
