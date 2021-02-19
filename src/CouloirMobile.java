import java.util.ArrayList;

public class CouloirMobile extends CouloirImpl {
    private ArrayList<Pion> pions = new ArrayList<Pion>() ;
    private boolean posee;

    public CouloirMobile(){
        // Todo
    }

    public ArrayList<Pion> getPion(){ 
        return this.pions;
    }

    public changeOrientation(Orientation orientation){
        if(! posee)this.setOrientation(orientation);
    }
}