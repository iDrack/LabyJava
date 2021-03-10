import java.util.*;

public class CouloirImpl implements Couloir {
    protected Orientation orientation;
    private Forme forme;
    private Objectif objectif;
    private ArrayList<Pion> pions = new ArrayList<Pion>();

    public CouloirImpl(Orientation o, Forme f, Objectif obj){
        this.orientation = o;
        this.forme = f;
        this. objectif = obj;
    }

    @Override
    public Orientation getOrientation(){
        return this.orientation;
    }

    @Override
    public Forme getForme(){
        return this.forme;
    }

    public void addPion(Pion p){
        this.pions.add(p);
    }

    public void supPion(Pion p){
        this.pions.remove(p);
    }
    public void suppToutPion(){
        this.pions.clear();
    }

    @Override
    public Objectif getObjectif(){
        return this.objectif;
    }

    @Override
    public ArrayList<Pion> getPions(){
        return this.pions;
    }
    
    public void décaler(Orientation orientation){
        this.orientation = orientation;
        // Et il faut rendre le couloir à l'autre bout disponible : 
        // Ce fait avec le retour de la méthode ?! 
    }
    
    public String toString(){
        String tmp;
        tmp = this.objectif.toString().substring(0,3) + "..";
        return "" + this.forme + "\t" + tmp + "\t" + this.orientation; 
    }
}
