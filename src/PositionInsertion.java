/**
 * Énumération des positions d'insertion possibles.
 * Position d'insertion disponibles : N1, N2, ..., O2, O1.
 * 
 * @author Charles Kempa, Thomas Dignoire & Dimitri Wacquez
 * @version Février 2021 - Mars 2021.
 */
public enum PositionInsertion{ 
    N1(0,1),N2(0,3),N3(0,5),E1(1,6),E2(3,6),E3(5,6),S3(6,5),S2(6,3),S1(6,1),O3(5,0),O2(3,0),O1(1,0);
    
    /**
     * Position de la position d'insertion.
     */
    private Position pos;

    /**
     * Constructeur de la position d'insertion, permet de créer la position.
     * @param x Position (int) x.
     * @param y Position (int) y.
     */
    private PositionInsertion(int x,int y){
        this.pos = new Position(x,y);
    }

    /**
     * Permet d'obtenir l'opposé de la position d'insertion.
     * @return La position d'insertion opposée.
     */
    public PositionInsertion oppose(){ // 12 - i - 1
        PositionInsertion[] tab = {N1,N2,N3,E1,E2,E3,O3,O2,O1,S3,S2,S1};
        for(int i = 0; i < tab.length; i++){
            if(tab[i] == this){
                return tab[12-i-1];
            }
        }

        return null;
    }

    /**
     * Permet d'obtenir la position de la position d'insertion.
     */
    public Position getPosition(){
        return this.pos;
    }
}