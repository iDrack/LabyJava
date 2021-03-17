import java.util.*;

/**
 * La classe Plateau représente le plateau de notre jeu du Labyrinthe.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public class Plateau {
    /**
     * Taille du plateau de 7x7.
     */
    public static final int TAILLE = 7;
    /**
     * Liste des couloirs fixes (ArrayList).
     */
    private ArrayList<CouloirFixe> couloiresFixes = new ArrayList<CouloirFixe>();
    /**
     * Liste des couloirs mobiles (ArrayList).
     */
    private ArrayList<CouloirMobile> couloirsMobiles = new ArrayList<CouloirMobile>();
    /**
     * Matrice qui représente notre plateau de jeu : CouloirImpl[TAILLE][TAILLE].
     */
    private CouloirImpl[][] matriceCouloirs = new CouloirImpl[TAILLE][TAILLE];

    /**
     * Constructeur, initialise notre plateau : place les couloirs fixes et mobiles.
     */
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
                                            CouloirFixe.définirObjectif());
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

    /**
     * Permet de modifier le couloir (en ligne ou colonne) du plateau.
     * @param pos Position d'insertion (PositionInsertion) 'pos'.
     * @param c Couloir mobile 'c' (CouloirMobile).
     * @return Retourne le couloir sortie (CouloirMobile).
     */
    public CouloirMobile modifierCouloirs(PositionInsertion pos, CouloirMobile c) {
        Position posOppose= pos.oppose().getPosition();
        CouloirMobile coul = (CouloirMobile)matriceCouloirs[posOppose.getX()][posOppose.getY()];
        int ord = pos.getPosition().getY();
        int abs = pos.getPosition().getX();

        if(pos == PositionInsertion.N1 || pos == PositionInsertion.N2 || pos == PositionInsertion.N3){
            for(int i = TAILLE -1; i >= 0 ; i--){
                if (i != TAILLE -1){
                    for (Pion pion : matriceCouloirs[i][ord].getPions()){
                        pion.poserA(new Position(i+1, ord));
                    }
                }
                if (i != 0){
                    matriceCouloirs[i][ord] = matriceCouloirs[i-1][ord];
                }
            }
        } else if(pos == PositionInsertion.S1 || pos == PositionInsertion.S2 || pos == PositionInsertion.S3){
            for(int i = 0; i < TAILLE  ; i++){
                if (i!=0){
                    for (Pion pion : matriceCouloirs[i][ord].getPions()){
                        pion.poserA(new Position(i-1, ord));
                    }
                }
                if (i != TAILLE-1){
                    matriceCouloirs[i][ord] = matriceCouloirs[i+1][ord];
                }
            }
        } else if(pos == PositionInsertion.O1 || pos == PositionInsertion.O2 || pos == PositionInsertion.O3){
            for(int i = TAILLE-1; i >= 0 ; i--){
                if (i != TAILLE -1){
                    for (Pion pion : matriceCouloirs[abs][i].getPions()){
                        pion.poserA(new Position(abs, i+1));
                    }
                }
                if (i!= 0){
                    matriceCouloirs[abs][i] = matriceCouloirs[abs][i-1];
                }
            }
        } else if(pos == PositionInsertion.E1 || pos == PositionInsertion.E2 || pos == PositionInsertion.E3){
            for(int i = 0; i < TAILLE ; i++){
                if (i != 0){
                    for (Pion pion : matriceCouloirs[abs][i].getPions()){
                        pion.poserA(new Position(abs, i-1));
                    }
                }
                if (i!= TAILLE -1){
                    matriceCouloirs[abs][i] = matriceCouloirs[abs][i+1];
                }
            }
        }
        
        matriceCouloirs[abs][ord] = c;
        return coul;
    }

    /**
     * Permet d'obtenir la matrice du plateau.
     * @return La matrice : CouloirImpl[0..6][0..6].
     */
    public CouloirImpl[][] getCouloirImpls(){
        return this.matriceCouloirs;
    }

    /**
     * Permet de vérifier si un déplacement est possible/atteignable par le joueur.
     * Déplacement en ligne ou colonne, de 0 à n tant que c'est possible.
     * @param orig Position d'origine (Position).
     * @param dest Position de destination (Position).
     * @return Un boolean : vrai (true) si c'est possible de se déplacer, non (false) sinon.
     */
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

                    if (i == xDestination && parcoursColonneEnHaut(matrice, i, yOrigine) == false){
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

                    if (i == xOrigine && parcoursColonneEnHaut(matrice, i, yOrigine) == false){
                        return false;
                    }
                }
            }
        } else if (xOrigine != xDestination && yOrigine != yDestination) {
            return false;
        }

        return true;
    }
    
    /**
     * Vérifie le parcours en ligne vers la gauche.
     * @param matrice La matrice (CouloirImpl[0..6][0..6]).
     * @param x Position x (int).
     * @param y Position y (int).
     * @return Un boolean : vrai (true) si c'est possible de se déplacer, non (false) sinon.
     */
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

    /**
     * Vérifie le parcours en ligne vers la droite.
     * @param matrice La matrice (CouloirImpl[0..6][0..6]).
     * @param x Position x (int).
     * @param y Position y (int).
     * @return Un boolean : vrai (true) si c'est possible de se déplacer, non (false) sinon.
     */
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

    /**
     * Vérifie le parcours en ligne vers la gauche et la droite.
     * @param matrice La matrice (CouloirImpl[0..6][0..6]).
     * @param x Position x (int).
     * @param y Position y (int).
     * @return Un boolean : vrai (true) si c'est possible de se déplacer, non (false) sinon.
     */
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

    /**
     * Vérifie le parcours en colonne vers le bas.
     * @param matrice La matrice (CouloirImpl[0..6][0..6]).
     * @param x Position x (int).
     * @param y Position y (int).
     * @return Un boolean : vrai (true) si c'est possible de se déplacer, non (false) sinon.
     */
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

    /**
     * Vérifie le parcours en colonne vers le haut.
     * @param matrice La matrice (CouloirImpl[0..6][0..6]).
     * @param x Position x (int).
     * @param y Position y (int).
     * @return Un boolean : vrai (true) si c'est possible de se déplacer, non (false) sinon.
     */
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

    /**
     * Vérifie le parcours en colonne vers le bas et le haut.
     * @param matrice La matrice (CouloirImpl[0..6][0..6]).
     * @param x Position x (int).
     * @param y Position y (int).
     * @return Un boolean : vrai (true) si c'est possible de se déplacer, non (false) sinon.
     */
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

    /**
     * Permet de placer un pion à une position sur le plateau.
     * @param pos Position 'pos' (Position).
     * @param pion Pion 'pion' (Pion).
     * @return L'objectif sur le couloir où l'on a déplacé le pion (Objectif).
     */
    public Objectif déplacer(Position pos, Pion pion) {
        System.out.println(this.matriceCouloirs[pion.getPositionCourante().getX()][pion.getPositionCourante().getY()].toString());
        this.matriceCouloirs[pos.getX()][pos.getY()].addPion(pion);
        this.matriceCouloirs[pion.getPositionCourante().getX()][pion.getPositionCourante().getY()].supPion(pion);
        return this.matriceCouloirs[pos.getX()][pos.getX()].getObjectif();
    }

    /**
     * Permet d'obtenir l'objectif sur le couloir à la position passé en paramètre.
     * @param pos Position 'pos' (Position).
     * @return L'objectif sur le couloir (Objectif).
     */
    public Objectif getObjectifCase(Position pos){
        return matriceCouloirs[pos.getX()][pos.getY()].getObjectif();
    }

    /**
     * Permet d'ajouter un pion à une position sur le plateau.
     * @param pos Position 'pos' (Position).
     * @param p Pion 'p' (Pion).
     */
    public void addPionCouloir(Position pos, Pion p){
        this.matriceCouloirs[pos.getX()][pos.getY()].addPion(p);
    }

    /**
     * Permet de supprimer un pion à une position sur le plateau.
     * @param pos Position 'pos' (Position).
     * @param p Pion 'p' (Pion).
     */
    public void supPionCouloir(Position pos, Pion p){
        this.matriceCouloirs[pos.getX()][pos.getY()].supPion(p);
    }

    /** 
     * Affiche notre plateau en terminal.
     */
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
}