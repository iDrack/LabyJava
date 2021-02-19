import java.util.ArrayList;

public class CouloirMobile extends CouloirImpl {
    private ArrayList<Pion> pions = new ArrayList<Pion>() ;
    private boolean posée;

    public CouloirMobile(){
        // Todo
    }

    public ArrayList<Pion> getPion(){ 
        return this.pions;
    }

    public void changeOrientation(Orientation orientation){
        // Change l'orientation ssi la carte n'est pas posée .. 
    }
}