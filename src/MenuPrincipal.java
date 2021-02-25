import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MenuPrincipal extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton jouer;
    private JButton quitter;

    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);

    /**
     * Constructeur de la classe MenuPrincipal
     * MenuPrincipal est un JPanel contenant deux boutons ainsi qu'un texte
     */
    public MenuPrincipal(){
        this.setLayout(null);

        JLabel titre = new JLabel("Labyrinthe");
        jouer = new JButton("Jouer");
        quitter = new JButton("Quitter");

        //Positionnement des boutons
        titre.setBounds(245,50,150,50);
        jouer.setBounds(225,200,150,50);
        quitter.setBounds(225,350,150,50);

        //Choix des polices d'écriture
        titre.setFont(fontEntered);
        jouer.setFont(fontEntered);
        quitter.setFont(fontEntered);

        //ajout des boutons / du titre
        this.add(titre);
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

    public JButton getJouer(){
        return jouer;
    }

    public JButton getQuitter(){
        return quitter;
    }
}
