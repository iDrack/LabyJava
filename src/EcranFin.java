import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcranFin extends JPanel {

    /**
     * Police d'écriture utilisé par EcranFin
     */
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);

    /**
     * Image de fond de EcranFin
     */
    private Image img;

    /**
     * Fenêtre actuel de l'application, de type MainWindow
     */
    private MainWindow fenetre;
    
    /**
     * EcranFin est un JPanel affichant un message de félicitation
     */
    public EcranFin(MainWindow f){
        fenetre=f;
        
        //Accés à l'image de fond
        this.img = new ImageIcon("media/img/victoire.png").getImage();

        //Détection de la taille de l'image de fond
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        this.setLayout(null);

        //Paramétrage de la taille de la fenêtre
        fenetre.setSize(size);
        fenetre.setLocationRelativeTo(null);
        MainWindow.instance.requestFocusInWindow(); 

        JButton quitter = new JButton("Quitter");

        quitter.setBounds(454-75,500,150,50);

        quitter.setFont(fontEntered);

        this.add(quitter);

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
}
