/**
 * La classe Position représente la position en x et y.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public class Position{
    /**
     * Position x.
     */
    private int x;
    /**
     * Position y.
     */
    private int y;

    /**
     * Constructeur, initialise la position en x, y.
     * @param x Position x (int).
     * @param y Position y (int).
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Obtenir la position en x.
     * @return Le x (int).
     */
    public int getX(){ 
        return x;
    }

    /**
     * Obtenir la position en y.
     * @return Le y (int).
     */
    public int getY(){ 
        return y;
    }
    
    /**
     * Permet de modifier la position en x.
     * @param x Le x à modifier (int).
     */
    public void setX(int x){ 
        this.x = x;
    }
    
    /**
     * Permet de modifier la position en y.
     * @param y Le y à modifier (int).
     */
    public void setY(int y){ 
        this.y = y;
    }

    /**
     * Affichage des informations de la position (x, y).
     */
    public String toString(){
        return "[x : " + this.x +",  y : " + this.y + "]";
    }
}