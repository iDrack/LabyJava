import java.util.ArrayList;

public class CouloirMobile extends CouloirImpl {
    private boolean posee;

    public CouloirMobile(Orientation orientation, Forme forme, Objectif objectif){
        super(orientation, forme, objectif);
    }

    public void changeOrientation(Orientation orientation){
        if(! posee)this.setOrientation(orientation);
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }
}