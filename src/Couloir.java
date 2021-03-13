import java.util.*;

public interface Couloir {    

    public void setOrientation(Orientation orientation);
    
    public Orientation getOrientation();

    public Forme getForme();

    public Objectif getObjectif();

    public ArrayList<Pion> getPions();

    public void addPion(Pion p);

    public void supPion(Pion p);

    public void suppToutPion();
}