import java.util.*;

public class Plateau {
    public static final int TAILLE = 7;
    private ArrayList<CouloirFixe> couloiresFixes = new ArrayList<CouloirFixe>();
    private ArrayList<CouloirMobile> couloirsMobiles = new ArrayList<CouloirMobile>();
    private CouloirImpl[][] matriceCouloirs = new CouloirImpl[TAILLE][TAILLE];

    public Plateau() {
        // Plateau = 49 cases, soit : 16 couloirs fixes et 34 couloirs mobiles.
        // 12 flèches en bordures de plateau = PositionInsertion.
        Objectif[] objectifs = Objectif.values();

        ArrayList<Objectif> objectifss = new ArrayList<Objectif>();
        for (int i = 0; i < objectifs.length; objectifss.add(objectifs[i++]));
        for (int i = 0; i < 9; i++, objectifss.add(Objectif.VIDE));
        Collections.shuffle(objectifss);

        Orientation[] orientations = Orientation.values();
        Forme[] formes = Forme.values();
        
        for (int x = 0; x < TAILLE; x++) {
            for (int y = 0; y < TAILLE; y++) {
                if (x % 2 == 0 && y % 2 == 0) {
                    matriceCouloirs[x][y] = new CouloirFixe(
                                            CouloirFixe.définirOrientation(x, y),
                                            CouloirFixe.définirForme(x, y), 
                                            CouloirFixe.définirObjectif(x, y));
                    couloiresFixes.add((CouloirFixe) matriceCouloirs[x][y]);
                } else {
                    Random r = new Random();
                    int or, f, obj;
                    or = r.nextInt(Orientation.NB);
                    f = r.nextInt(Forme.NB);
                    obj = r.nextInt(objectifss.size());

                    matriceCouloirs[x][y] = new CouloirMobile(orientations[or], formes[f], objectifss.get(obj));
                    objectifss.remove(obj);
                    couloirsMobiles.add((CouloirMobile) matriceCouloirs[x][y]);
                }
            }
        }

        // Couloirs fixe : orientation qui ne change pas.
        // Couloirs mobiles : orientation choisie lors de l'insertion sur le plateau.
        // Une fois posée, l'orientation ne peut plus être modifiée.
    }

    public CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c) {
        Position posOppose= pos.oppose().getPosition();
        CouloirMobile coul = (CouloirMobile)matriceCouloirs[posOppose.getX()][posOppose.getY()];
        int ord = pos.getPosition().getY();
        int abs = pos.getPosition().getX();

        if(pos == PositionInsertion.N1 || pos == PositionInsertion.N2 || pos == PositionInsertion.N3){
            for(int i = TAILLE -1; i > 0 ; i--){
                matriceCouloirs[i][ord] = matriceCouloirs[i-1][ord];
            }
        } else if(pos == PositionInsertion.S1 || pos == PositionInsertion.S2 || pos == PositionInsertion.S3){
            for(int i = 0; i < TAILLE -1 ; i++){
                matriceCouloirs[i][ord] = matriceCouloirs[i+1][ord];
            }
        } else if(pos == PositionInsertion.O1 || pos == PositionInsertion.O2 || pos == PositionInsertion.O3){
            for(int i = TAILLE-1; i > 0 ; i--){
                matriceCouloirs[abs][i] = matriceCouloirs[abs][i-1];
            }
        } else if(pos == PositionInsertion.E1 || pos == PositionInsertion.E2 || pos == PositionInsertion.E3){
            for(int i = 0; i < TAILLE -1 ; i++){
                matriceCouloirs[abs][i] = matriceCouloirs[abs][i+1];
            }
        }

        matriceCouloirs[abs][ord] = c;
        return coul;
    }

    public CouloirImpl[][] getCouloirImpls(){
        return this.matriceCouloirs;
    }

    public boolean estAtteignable(Position orig, Position dest) {
        // Objectif : vérifier la forme et l'orientation ..
        int xOrigine = orig.getX();
        int yOrigine = orig.getY();
        int xDestination = dest.getX();
        int yDestination = dest.getY();
        CouloirImpl[][] matrice = getCouloirImpls();
        // Point de vue de la matrice : 'x' = lignes et 'y' = colonnes.

        // Si sur la même case :
        if(xOrigine == xDestination && yOrigine == yDestination){
            return true;
        }

        // Si aligné sur x (même ligne) alors :
        if (xOrigine == xDestination) {
            // Gauche - droite, droite - gauche.
            if (yOrigine < yDestination) {
                for (int i = yOrigine; i <= yDestination; i++) {
                    // Vérification :
                    if (i == yOrigine && parcoursLigneADroite(matrice, xOrigine, i) == false) {
                        return false;
                    }

                    if (i != yOrigine && i != yDestination && parcoursLigneDeuxCôtés(matrice, xOrigine, i) == false) {
                        return false;
                    }

                    if (i == yDestination && parcoursLigneAGauche(matrice, xOrigine, i) == false){
                        return false;
                    }
                }
            } else {
                for (int i = yDestination; i <= yOrigine; i++) {
                    // Vérification :
                    if (i == yDestination && parcoursLigneADroite(matrice, xOrigine, i) == false) {
                        return false;
                    }

                    if (i != yDestination && i != yOrigine && parcoursLigneDeuxCôtés(matrice, xOrigine, i) == false) {
                        return false;
                    }

                    if (i == yOrigine && parcoursLigneAGauche(matrice, xOrigine, i) == false){
                        return false;
                    }
                }
            }
        } else if (yOrigine == yDestination) { // Sinon, si aligné sur y (même colonne) alors :
            // Haut - bas, bas - haut.
            if (xOrigine < xDestination) {
                for (int i = xOrigine; i <= xDestination; i++) {
                    // Vérification :
                    if (i == xOrigine && parcoursColonneEnBas(matrice, i, yOrigine) == false) {
                        return false;
                    }

                    if (i != xOrigine && i != xDestination && parcoursColonneDeuxCôtés(matrice, i, yOrigine) == false) {
                        return false;
                    }

                    if (i == xDestination && parcoursColonneEnHaut(matrice, i, xOrigine) == false){
                        return false;
                    }
                }
            } else {
                for (int i = xDestination; i <= xOrigine; i++) {
                    // Vérification :
                    if (i == xDestination && parcoursColonneEnBas(matrice, i, yOrigine) == false) {
                        return false;
                    }

                    if (i != xDestination && i != xOrigine && parcoursColonneDeuxCôtés(matrice, i, yOrigine) == false) {
                        return false;
                    }

                    if (i == xOrigine && parcoursColonneEnHaut(matrice, i, xOrigine) == false){
                        return false;
                    }
                }
            }
        } else if (xOrigine != xDestination && yOrigine != yDestination) {
            return false;
        }

        return true;
    }
    
    private boolean parcoursLigneAGauche(CouloirImpl[][] matrice, int x, int y) {
        CouloirImpl couloir = matrice[x][y];
        Forme saForme = couloir.getForme();
        Orientation sonOrientation = couloir.getOrientation();

        System.out.println("Ligne G : ["+x+", "+y+"] ("+couloir+")");

        if (saForme == Forme.COUDE) {
            if (sonOrientation == Orientation.SUD || sonOrientation == Orientation.OUEST) {
                return false;
            }
        }

        if (saForme == Forme.TE) {
            if (sonOrientation == Orientation.OUEST) {
                return false;
            }
        }

        if (saForme == Forme.DROIT) {
            if (sonOrientation == Orientation.NORD || sonOrientation == Orientation.SUD) {
                return false;
            }
        }

        return true;
    }

    private boolean parcoursLigneADroite(CouloirImpl[][] matrice, int x, int y) {
        CouloirImpl couloir = matrice[x][y];
        Forme saForme = couloir.getForme();
        Orientation sonOrientation = couloir.getOrientation();

        System.out.println("Ligne D : ["+x+", "+y+"] ("+couloir+")");

        if (saForme == Forme.COUDE) {
            if (sonOrientation == Orientation.NORD || sonOrientation == Orientation.EST) {
                return false;
            }
        }

        if (saForme == Forme.TE) {
            if (sonOrientation == Orientation.EST) {
                return false;
            }
        }

        if (saForme == Forme.DROIT) {
            if (sonOrientation == Orientation.NORD || sonOrientation == Orientation.SUD) {
                return false;
            }
        }

        return true;
    }

    private boolean parcoursLigneDeuxCôtés(CouloirImpl[][] matrice, int x, int y) {
        CouloirImpl couloir = matrice[x][y];
        Forme saForme = couloir.getForme();
        Orientation sonOrientation = couloir.getOrientation();

        System.out.println("Ligne G+D : ["+x+", "+y+"] ("+couloir+")");

        if (saForme == Forme.COUDE) {
            return false;
        }

        if (saForme == Forme.TE) {
            if (sonOrientation == Orientation.EST || sonOrientation == Orientation.OUEST) {
                return false;
            }
        }

        if (saForme == Forme.DROIT) {
            if (sonOrientation == Orientation.NORD || sonOrientation == Orientation.SUD) {
                return false;
            }
        }

        return true;
    }

    private boolean parcoursColonneEnBas(CouloirImpl[][] matrice, int x, int y) {
        CouloirImpl couloir = matrice[x][y];
        Forme saForme = couloir.getForme();
        Orientation sonOrientation = couloir.getOrientation();

        System.out.println("Colonne B : ["+x+", "+y+"] ("+couloir+")");

        if (saForme == Forme.COUDE) {
            if (sonOrientation == Orientation.EST || sonOrientation == Orientation.SUD) {
                return false;
            }
        }

        if (saForme == Forme.TE) {
            if (sonOrientation == Orientation.SUD) {
                return false;
            }
        }

        if (saForme == Forme.DROIT) {
            if (sonOrientation == Orientation.EST || sonOrientation == Orientation.OUEST) {
                return false;
            }
        }

        return true;
    }

    private boolean parcoursColonneEnHaut(CouloirImpl[][] matrice, int x, int y) {
        CouloirImpl couloir = matrice[x][y];
        Forme saForme = couloir.getForme();
        Orientation sonOrientation = couloir.getOrientation();

        System.out.println("Colonne H : ["+x+", "+y+"] ("+couloir+")");

        if (saForme == Forme.COUDE) {
            if (sonOrientation == Orientation.NORD || sonOrientation == Orientation.OUEST) {
                return false;
            }
        }

        if (saForme == Forme.TE) {
            if (sonOrientation == Orientation.NORD) {
                return false;
            }
        }

        if (saForme == Forme.DROIT) {
            if (sonOrientation == Orientation.EST || sonOrientation == Orientation.OUEST) {
                return false;
            }
        }

        return true;
    }

    private boolean parcoursColonneDeuxCôtés(CouloirImpl[][] matrice, int x, int y) {
        CouloirImpl couloir = matrice[x][y];
        Forme saForme = couloir.getForme();
        Orientation sonOrientation = couloir.getOrientation();

        System.out.println("Colonne H+B : ["+x+", "+y+"] ("+couloir+")");

        if (saForme == Forme.COUDE) {
            return false;
        }

        if (saForme == Forme.TE) {
            if (sonOrientation == Orientation.NORD || sonOrientation == Orientation.SUD) {
                return false;
            }
        }

        if (saForme == Forme.DROIT) {
            if (sonOrientation == Orientation.EST || sonOrientation == Orientation.OUEST) {
                return false;
            }
        }

        return true;
    }

    public Objectif déplacer(Position pos, Pion pion) {
        return null;
    }

    public Objectif getObjectifCase(Position pos){
        return matriceCouloirs[pos.getX()][pos.getY()].getObjectif();
    }

    public String toString(){
        CouloirImpl[][] matrice = getCouloirImpls();
        String chaine = "";
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                //chaine += "(" + i + ", " + j + ") : \t" + matrice[i][j] + "\n";
                chaine += "| " + matrice[i][j] + "\t";
            }
            
           chaine += "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        }
        
        return chaine;
    }

   

    public static void main(String[] args) {
        Plateau p = new Plateau();
        System.out.println(p);

        /*System.out.println("Tests estAtteignable() : ");
        Position p0 = new Position(0, 0);
        Position p1 = new Position(0, 2);
        Position p2 = new Position(2, 2);
        Position p3 = new Position(2, 4);
        Position p4 = new Position(1, 3);
        Position p5 = new Position(1, 6);
        Position p6 = new Position(3, 3);
        Position p7 = new Position(1, 3);
        System.out.println(p0 + " à " + p1 + " == " + p.estAtteignable(p0, p1) + "\n");
        System.out.println(p2 + " à " + p3 + " == " + p.estAtteignable(p2, p3) + "\n");
        System.out.println(p4 + " à " + p5 + " == " + p.estAtteignable(p4, p5) + "\n");
        System.out.println(p6 + " à " + p7 + " == " + p.estAtteignable(p6, p7) + "\n");
        System.out.println(p1 + " à " + p2 + " == " + p.estAtteignable(p1, p2) + "\n");
        System.out.println(p1 + " à " + p6 + " == " + p.estAtteignable(p1, p6) + "\n");
        System.out.println(p4 + " à " + p7 + " == " + p.estAtteignable(p4, p7) + "\n");*/

        System.out.println("Tests ModifierCouloir : ");
        CouloirMobile test = new CouloirMobile(Orientation.NORD, Forme.TE, Objectif.VIDE);
        p.modifierCouloirs(PositionInsertion.N1, test);
        System.out.println(p);
        p.modifierCouloirs(PositionInsertion.O1, test);
        System.out.println(p);
        p.modifierCouloirs(PositionInsertion.O2, test);
        System.out.println(p);
        p.modifierCouloirs(PositionInsertion.S1, test);
        System.out.println(p);
        p.modifierCouloirs(PositionInsertion.E1, test);
        System.out.println(p);
        
        System.out.println();
    }
}