import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class AssetTiles{
    
    private static final long serialVersionUID = 1L;

    private static BufferedImage NO_TEXTURE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);//Renvoie une tile noir si aucune image ne peut être charger
    static {
        NO_TEXTURE.setRGB(0, 0, new Color(0, 0, 0).getRGB());
    }

    /**
     * Méthode permettant de récupérer une image selon son nom
     * 
     * @param url
     * @return BufferedImage
     */
    public static BufferedImage getImage(String url){
        //récupére le fichier où ce situe les tiles (chemin = url)
        BufferedImage ret = NO_TEXTURE;
        try {
            ret = ImageIO.read(new File("media/img/sprites/".concat(url)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Méthode permettant de récupérer la tuile d'un couloir
     * 
     * @param couloir
     * @return BufferedImage
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

    public static BufferedImage getObjectifImage(Objectif obj){
        return getImage(obj.toString().toLowerCase().concat(".png"));
    }
}