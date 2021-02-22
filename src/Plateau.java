import java.util.ArrayList;

public class Plateau {
    private static final int TAILLE = 7;
    private ArrayList<CouloirFixe> couloiresFixes = new ArrayList<CouloirFixe>();
    private ArrayList<CouloirMobile> couloirsMobiles = new ArrayList<CouloirMobile>();
    private CouloirImpl[][] matriceCouloirs = new CouloirImpl[TAILLE][TAILLE];
    
    public Plateau(){
        // Plateau = 49 cases, soit : 16 couloirs fixes et 34 couloirs mobiles.
        // 12 flèches en bordures de plateau = PositionInsertion.
        Orientation orientation;
        Forme forme;
        Objectif objectif;

        for(int x = 0; x < TAILLE; x++){
            for(int y = 0; y < TAILLE; y++){
                if(x % 2 == 0 && y % 2 == 0){
                    //orientation = CouloirFixe.this.définirOrientation(x, y);
                    // PB !!
                    matriceCouloirs[x][y] = new CouloirFixe(orientation, forme, objectif);
                }
            }
        }

        // Couloirs fixe : orientation qui ne change pas.
        // Couloirs mobiles : orientation choisie lors de l'insertion sur le plateau.
        // Une fois posée, l'orientation ne peut plus être modifiée.
    }

    public CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c){
        return c;
    }
    
    public static Boolean estAtteignable(Position orig, Position dest){
        return null;
    }

    public Objectif déplacer(Position pos, Pion pion){
        return null;
    }
}