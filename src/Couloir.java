import java.util.ArrayList;
import java.util.List;

public class Couloir {

    private Orientation orientation;
    private Forme forme;
    private Objectif objectif;
    private List<Pion> pions;

    public Orientation getOrientation() {
        return this.orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Forme getForme() {
        return this.forme;
    }

    public void setForme(Forme forme) {
        this.forme = forme;
    }

    public Objectif getObjectif() {
        return this.objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    public List<Pion> getPions() {
        return this.pions;
    }

    public void setPions(List<Pion> pions) {
        this.pions = pions;
    }

    public Couloir(){
        // Todo
    }


    public void decaler(){
        // Todo
    }

}