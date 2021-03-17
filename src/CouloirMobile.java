/**
 * Classe représentant un couloir mobile du plateau. 
 * Cette classe hérite de CouloirImpl.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public class CouloirMobile extends CouloirImpl {
    /**
     * Boolean afin de savoir si le couloir est placé ou non.
     */
    private boolean posee;

    /**
     * Constructeur, initialisation d'un couloir mobile.
     * @param orientation Orientation (Enum) 'orientation' du couloir.
     * @param forme Forme (Enum) 'forme' du couloir.
     * @param objectif Objectif (Enum) 'objectif' du couloir.
     */
    public CouloirMobile(Orientation orientation, Forme forme, Objectif objectif){
        super(orientation, forme, objectif);
    }

    /**
     * Permet de changer l'orientation du couloir mobile s'il n'est pas placé sur le plateau.
     * @param orientation Orientation (Enum) 'orientation' que l'on souhaite.
     */
    public void changeOrientation(Orientation orientation){
        if(! posee) this.setOrientation(orientation);
    }
}