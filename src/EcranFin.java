import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcranFin extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton quitter;
    private final Font fontEntered = new Font(Font.DIALOG, Font.ROMAN_BASELINE, 20);
    private Image img;
    private MainWindow fenetre;
    private Dimension size;

    /**
     * Constructeur de la classeEcranFin
     * EcranFin est un JPanel contenant un bouton ainsi qu'une image de fond
     */
    public EcranFin(MainWindow f){
        fenetre=f;
        
        //Accés à l'image de fond
        this.img = new ImageIcon("media/img/victoire.png").getImage();

        //Détection de la taille de l'image de fond
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        this.size = size;
        this.setLayout(null);

        //Paramétrage de la taille de la fenêtre
        fenetre.setSize(size);
        fenetre.setLocationRelativeTo(null);
        MainWindow.instance.requestFocusInWindow(); 

        quitter = new JButton("Quitter");

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

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }    
}
