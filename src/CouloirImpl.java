import java.util.ArrayList;

public class CouloirImpl implements Couloir {
    private Orientation orientation;
    private Forme forme;
    private Objectif objectif;
    private ArrayList<Pion> pions = new ArrayList<Pion>();

    public CouloirImpl(Orientation o, Forme f, Objectif obj){
        this.orientation = o;
        this.forme = f;
        this. objectif = obj;
    }

    @Override
    public Orientation getOrientation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Forme getForme() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Objectif getObjectif() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Pion> getPions() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public void décaler(Orientation orientation){
        // Todo
    }
}
