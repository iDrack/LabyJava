import java.util.*;

/**
 * La classe CouloirImpl représente un couloir de notre jeu de labyrinthe. 
 * Cette classe implémente l'interface Couloir.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public class CouloirImpl implements Couloir {
    /**
     * Orientation (Enum) du couloir.
     */
    protected Orientation orientation;
    /**
     * Forme (Enum) du couloir.
     */
    private Forme forme;
    /**
     * Objectif (Enum) présent sur le couloir.
     */
    private Objectif objectif;
    /**
     * ArrayList de pions, représente la liste des pions sur ce couloir.
     */
    private ArrayList<Pion> pions = new ArrayList<Pion>();

    /**
     * Constructeur, initialisation d'un couloir.
     * @param o Orientation (Enum) 'o' du couloir.
     * @param f Forme (Enum) 'f' du couloir.
     * @param obj Objectif (Enum) 'obj' sur le couloir.
     */
    public CouloirImpl(Orientation o, Forme f, Objectif obj){
        this.orientation = o;
        this.forme = f;
        this. objectif = obj;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public Orientation getOrientation(){
        return this.orientation;
    }

    @Override
    public Forme getForme(){
        return this.forme;
    }

    @Override
    public Objectif getObjectif(){
        return this.objectif;
    }

    @Override
    public ArrayList<Pion> getPions(){
        return this.pions;
    }

    @Override
    public void addPion(Pion p){
        this.pions.add(p);
    }

    @Override
    public void supPion(Pion p){
        this.pions.remove(p);
    }
    
    @Override
    public void suppToutPion(){
        this.pions.clear();
    }
    
    /**
     * Affiche les données d'un couloir (forme, objectif, orientation).
     */
    public String toString(){
        String tmp;
        tmp = this.objectif.toString().substring(0,3) + "..";
        return "" + this.forme + "\t" + tmp + "\t" + this.orientation; 
    }
}
