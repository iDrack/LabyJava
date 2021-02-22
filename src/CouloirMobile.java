import java.util.ArrayList;

public class CouloirMobile extends CouloirImpl {
    private boolean posee;

    public CouloirMobile(){
        // Todo
    }

    public void changeOrientation(Orientation orientation){
        if(! posee)this.setOrientation(orientation);
    }

    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
    }
}