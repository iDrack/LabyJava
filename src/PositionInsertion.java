public enum PositionInsertion{ 
    N1,N2,N3,E1,E2,E3,S3,S2,S1,O3,O2,O1;

    public PositionInsertion oppose(){ // 12 - i - 1
        PositionInsertion[] tab = {N1,N2,N3,E1,E2,E3,O3,O2,O1,S3,S2,S1};
        for(int i = 0; i < tab.length; i++){
            if (tab[i] == this){
                return tab[12-i-1];
            }
        }
        return null;
    }

    /** MOYEN DE CORRIGER SA MERDE */
    public Position getPosition(){
        switch(this){
            case N1 :
                return new Position(0,1);

            case N2:
                return new Position(0,3);

            case N3:
                return new Position(0,5);

            case S1:
                return new Position(6,1);

            case S2:
                return new Position(6,3);

            case S3:
                return new Position(6,5);

            case O1:
                return new Position(1,0);

            case O2:
                return new Position(3,0);

            case O3:
                return new Position(5,0);

            case E1:
                return new Position(1,6);

            case E2:
                return new Position(3,6);

            case E3:
                return new Position(5,6);

            default:
                return null;
        }
    }
}