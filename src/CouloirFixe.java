public class CouloirFixe extends CouloirImpl {
    public CouloirFixe(Orientation orientation, Forme forme, Objectif objectif){
        super(orientation, forme, objectif);
    }

    public static Objectif définirObjectif(int x, int y){
        Objectif objectif = new Objectif(); // retourne un des Objectif restant aléatoirement.

        return objectif;
    }

    public static Forme définirForme(int x, int y){
        Forme forme;

        if(x == 0 && y == 0 || x == 0 && y == 6 || x == 6 && y == 0 || x == 6 && y == 6){
            forme = Forme.COUDE;
        } else {
            forme = Forme.TE;
        }

        return forme;
    }

    public static Orientation définirOrientation(int x, int y){
        Orientation orientation;

        if(x == 0){
            if(y == 0){
                orientation = Orientation.OUEST;
            } else {
                orientation = Orientation.NORD;
            }
        } else if(x == 2){
            if(y == 6){
                orientation = Orientation.EST;
            } else if(y == 4){
                orientation = Orientation.NORD;
            } else { // 0 ou 2
                orientation = Orientation.OUEST;
            }
        } else if(x == 4){
            if(y == 0){
                orientation = Orientation.NORD;
            } else if(y == 2){
                orientation = Orientation.SUD;
            } else { // 4 ou 6
                orientation = Orientation.EST;
            }
        } else { // x == 6
            if(y == 6){
                orientation = Orientation.EST;
            } else {
                orientation = Orientation.SUD;
            }
        }

        return orientation;
    }
}