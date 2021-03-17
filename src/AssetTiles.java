import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe gérant les images utilisé dans le GUI.
 * Elle contient des méthode permettant de retrouver une image selon son nom, selon le Couloir, le Pion ou l'Objectif.
 * Elle contient aussi des méthode permettant la combinaison d'image ainsi que la redimension.
 */
public class AssetTiles{
    
    /**
     * Renvoie une tile noir si aucune image ne peut être charger.
     */
    private static BufferedImage NO_TEXTURE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    static {
        NO_TEXTURE.setRGB(0, 0, new Color(0, 0, 0).getRGB());
    }

    /**
     * Méthode permettant de récupérer une image selon son nom (url).
     * @param url String du nom de l'image à retourner.
     * @return BufferedImage correspond à l'image dans le fichier 'media/img/sprites/'.
     */
    public static BufferedImage getImage(String url){
        BufferedImage ret = NO_TEXTURE;
        try {
            ret = ImageIO.read(new File("media/img/sprites/".concat(url)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Méthode permettant de récupérer l'image d'un couloir.
     * @param couloir Couloir que l'on veut afficher.
     * @return Image du couloir à afficher.
     */
    public static BufferedImage getCouloirImage(Couloir couloir){
        if(couloir.getForme() == Forme.DROIT) {
            //Si le couloir voulue est un couloir droit, 2 cas (nord,sud) ou (ouest,est)
            if(couloir.getOrientation() == Orientation.EST || couloir.getOrientation() == Orientation.OUEST){
                return getImage("ligne2.png");
            }else{
                return getImage("ligne.png");
            }
        }else if(couloir.getForme() == Forme.COUDE) {
            //Si le couloir voulue est un coude droit, 4 cas nord, sud, ouest, est
            if(couloir.getOrientation() == Orientation.EST){
                return getImage("angle.png");
            }else if(couloir.getOrientation() == Orientation.OUEST){
                return getImage("angle3.png");
            }else if(couloir.getOrientation() == Orientation.NORD){
                return getImage("angle4.png");
            }else{
                return getImage("angle2.png");
            }
        }else {
            //Si le couloir voulue est un formet droit, 4 cas nord, sud, ouest, est
            if(couloir.getOrientation() == Orientation.EST){
                return getImage("formet4.png");
            }else if(couloir.getOrientation() == Orientation.OUEST){
                return getImage("formet2.png");
            }else if(couloir.getOrientation() == Orientation.NORD){
                return getImage("formet3.png");
            }else{
                return getImage("formet.png");
            }
        }
    }

    /**
     * Méthode permettant de récupérer l'image d'un objectif.
     * @param obj Objectif que l'on veut afficher.
     * @return BufferedImage correspondant à l'Objectif voulu dans le fichier 'media/img/sprites/'.
     */
    public static BufferedImage getObjectifImage(Objectif obj){
        return getImage(obj.toString().toLowerCase().concat(".png"));
    }

    /**
     * Méthode permettant de récupérer l'image d'un pion selon sa couleur.
     * @param pion Pion que l'on veut afficher.
     * @return BufferedImage correspondant au Pion voulu dans le fichier 'media/img/sprites/'.
     */
    public static BufferedImage getPionImage(String pion){
        return getImage(pion.toLowerCase().concat(".png"));
    }

    /**
     * Combine image2 à image1 au premier plan.
     * @param image1 Image utilisé au dernier plan.
     * @param image2 Image utilisé au premier plan.
     * @param centrer Boolean déterminant si il faut centrer image2.
     * @return Image combinant les deux images passées en paramètre.
     */
    public static BufferedImage combinerImage(BufferedImage image1, BufferedImage image2, Boolean centrer){ 
        Graphics2D g2d = image1.createGraphics(); 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                        RenderingHints.VALUE_ANTIALIAS_ON); 
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, 
                        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY); 
        if(centrer){
            g2d.drawImage(image2, 23, 23, null); 
        }else{
            g2d.drawImage(image2, 0, 0, null); 
        }
      
        g2d.dispose(); 
      
        return image1 ; 
    }

    /**
     * Redimensionne img avec les valeurs de w et h.
     * @param img Image original.
     * @param w Nouvelle largeur de img.
     * @param h Nouvelle Hauteur de img.
     * @return Image redimensionnée.
     */
    public static BufferedImage redimensionner(BufferedImage img, int w, int h) { 
        Image tmp = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
    
        return dimg;
    }  
}