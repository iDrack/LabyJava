import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton jouer;
    private JButton quitter;
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);
    private Image img;

    /**
     * Constructeur de la classe MenuPrincipal
     * MenuPrincipal est un JPanel contenant deux boutons ainsi qu'une image de fond
     */
    public MenuPrincipal(){
        this.img = new ImageIcon("media/img/labyrinthe.png").getImage();
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        this.setLayout(null);

        jouer = new JButton("Jouer");
        quitter = new JButton("Quitter");

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
                System.out.println("Désoler ceci est un placeholder");
            }
        });
        quitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                MainWindow.instance.dispose();  //Ferme proprement la fenêtre ouverte
                System.exit(0);                 //Quitte le programme
            }
        });
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}
