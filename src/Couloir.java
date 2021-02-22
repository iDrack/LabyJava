import java.util.*;

public interface Couloir {    
    public Orientation getOrientation();

    public Forme getForme();

    public Objectif getObjectif();

    public ArrayList<Pion> getPions();
}