/**
 * Énumération des orientations possibles d'un couloir.
 * Orientations disponibles : NORD, SUD, EST, OUEST.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public enum Orientation {
    NORD, SUD, EST, OUEST;
    
    /**
     * Nombre total d'orientation (4).
     */
    public static final int NB = 4; 

    /**
     * Permet d'obtenir l'orientation correspondant au String, 
     * par exemple l'orientation Nord sera retourné pour le string "NORD" en paramètre.
     * @param str String de l'orientation que l'on souhaite obtenir.
     * @return Une orientation de type Orientation (Enum).
     */
    public static Orientation getOrientation(String str){
        switch(str){
            case "NORD" :
                return Orientation.NORD;
            case "SUD":
                return Orientation.SUD;
            case "EST":
                return Orientation.EST;
            case "OUEST":
                return Orientation.OUEST;
            default:
                return null;
        }
    }
}
