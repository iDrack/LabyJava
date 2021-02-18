import java.util.ArrayList;

public class Plateau {

    private ArrayList<CouloirFixe> coulF = new ArrayList<CouloirFixe>();
    private ArrayList<CouloirMobile> coulM = new ArrayList<CouloirMobile>();
    public Plateau(){}

    public CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c){
        return c;
    }
    public static Boolean estAtteignable(Position orig,Position dest){
        return null;
    }
}