/**
 * Classe représentant un couloir fixe du plateau. 
 * Cette classe hérite de CouloirImpl.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public class CouloirFixe extends CouloirImpl {
    /**
     * Constructeur, initialisation d'un couloir fixe.
     * @param orientation Orientation (Enum) 'orientation' du couloir.
     * @param forme Forme (Enum) 'forme' du couloir.
     * @param objectif Objectif (Enum) 'objectif' du couloir.
     */
    public CouloirFixe(Orientation orientation, Forme forme, Objectif objectif){
        super(orientation, forme, objectif);
    }

    /**
     * Permet de définir l'objectif du couloir fixe, ici à "VIDE" obligatoirement.
     * @return Objectif (Enum) définit, ici "VIDE".
     */
    public static Objectif définirObjectif(){
        Objectif objectif = Objectif.VIDE; 
        return objectif;
    }

    /**
     * Permet de définir la forme du couloir fixe, en fonction de la position.
     * @param x Position en x du couloir fixe (int).
     * @param y Position en y du couloir fixe (int).
     * @return Forme (Enum) définit.
     */
    public static Forme définirForme(int x, int y){
        Forme forme;

        if(x == 0 && y == 0 || x == 0 && y == 6 || x == 6 && y == 0 || x == 6 && y == 6){
            forme = Forme.COUDE;
        } else {
            forme = Forme.TE;
        }

        return forme;
    }

    /**
     * Permet de définir l'orientation du couloir fixe, en fonction de la position.
     * @param x Position en x du couloir fixe (int).
     * @param y Position en y du couloir fixe (int).
     * @return Orientation (Enum) définit.
     */
    public static Orientation définirOrientation(int x, int y){
        Orientation orientation;

        if(x == 0){
            if(y == 0){
                orientation = Orientation.OUEST;
            } else {
                orientation = Orientation.NORD;
            }
        } else if(x == 2){
            if(y == 6){
                orientation = Orientation.EST;
            } else if(y == 4){
                orientation = Orientation.NORD;
            } else { // 0 ou 2
                orientation = Orientation.OUEST;
            }
        } else if(x == 4){
            if(y == 0){
                orientation = Orientation.OUEST;
            } else if(y == 2){
                orientation = Orientation.SUD;
            } else { // 4 ou 6
                orientation = Orientation.EST;
            }
        } else { // x == 6
            if(y == 6){
                orientation = Orientation.EST;
            } else {
                orientation = Orientation.SUD;
            }
        }

        return orientation;
    }
}