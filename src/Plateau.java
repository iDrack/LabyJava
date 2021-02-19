import java.util.ArrayList;

public class Plateau {
    private ArrayList<CouloirFixe> couloiresFixes = new ArrayList<CouloirFixe>();
    private ArrayList<CouloirMobile> couloirsMobiles = new ArrayList<CouloirMobile>();
    
    public Plateau(){
        // Todo
    }

    public CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c){
        return c;
    }
    
    public static Boolean estAtteignable(Position orig, Position dest){
        return null;
    }
}