import java.util.ArrayList;

public class CouloirMobile extends CouloirImpl {
    private ArrayList<Pion> pions = new ArrayList<Pion>() ;
    private boolean posee;
    private Orientation orientation; // Via le parent .. 

    public CouloirMobile(){
        // Todo
    }

    public ArrayList<Pion> getPion(){ 
        return this.pions;
    }

    public void changeOrientation(Orientation orientation){
        if(! posee)this.setOrientation(orientation);
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;;
    }
}