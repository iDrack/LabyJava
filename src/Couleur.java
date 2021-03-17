/**
 * Énumération des couleurs pour les pions des joueurs.
 * Couleurs disponibles : Rouge, Vert, Bleu, Jaune.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public enum Couleur {
    ROUGE,VERT,BLEU,JAUNE;

    /**
     * Permet d'obtenir la couleur correspondant au String, 
     * par exemple la couleur rouge sera retourné pour le string "ROUGE" en paramètre.
     * @param str String de la couleur que l'on souhaite obtenir.
     * @return Une couleur de type Couleur (Enum).
     */
    public static Couleur getCouleur(String str){
        switch(str){
            case "ROUGE" :
                return Couleur.ROUGE;
            case "VERT":
                return Couleur.VERT;
            case "JAUNE":
                return Couleur.JAUNE;
            case "BLEU":
                return Couleur.BLEU;
            default:
                return null;
        }
    }
}