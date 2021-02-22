import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Plateau {
    private static final int TAILLE = 7;
    private ArrayList<CouloirFixe> couloiresFixes = new ArrayList<CouloirFixe>();
    private ArrayList<CouloirMobile> couloirsMobiles = new ArrayList<CouloirMobile>();
    private CouloirImpl[][] matriceCouloirs = new CouloirImpl[TAILLE][TAILLE];
    
    public Plateau(){
        // Plateau = 49 cases, soit : 16 couloirs fixes et 34 couloirs mobiles.
        // 12 flèches en bordures de plateau = PositionInsertion.
      
        Objectif[] objectifs = Objectif.values();
        
        ArrayList<Objectif> objectifss = new ArrayList<Objectif>();
        for(int i=0;i<objectifs.length;objectifss.add(objectifs[i++]));
        for(int i=0;i<9;i++,objectifss.add(Objectif.VIDE));
        Collections.shuffle(objectifss);

        Orientation[] orientations = Orientation.values();
        
        Forme[] formes = Forme.values();
        for(int x = 0; x < TAILLE; x++){
            for(int y = 0; y < TAILLE; y++){
                if(x % 2 == 0 && y % 2 == 0){
                    matriceCouloirs[x][y] = new CouloirFixe(CouloirFixe.définirOrientation(x, y), CouloirFixe.définirForme(x, y), CouloirFixe.définirObjectif(x, y));
                } else {
                    Random r = new Random();
                    int or,f,obj;
                    or = r.nextInt(Orientation.NB);
                    f = r.nextInt(Forme.NB);
                    obj = r.nextInt(objectifss.size());

                    matriceCouloirs[x][y] = new CouloirMobile(orientations[or], formes[f], objectifss.get(obj));
                    objectifss.remove(obj);
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
    
    public static void main(String[] args) {
        Plateau p = new Plateau();
        for(int i = 0; i < TAILLE; i++){
            for(int j = 0; j < TAILLE; j++){
                System.out.println(p.matriceCouloirs[i][j] + " ") ;
            }
           System.out.println("\n");
        }
    }
}