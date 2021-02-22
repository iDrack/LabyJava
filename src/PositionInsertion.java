public enum PositionInsertion{ 
    N1(0,1),N2(0,3),N3(0,5),E1(1,6),E2(3,6),E3(5,6),S3(6,5),S2(6,3),S1(6,1),O3(5,0),O2(3,0),O1(1,0);
    private Position pos;

    private PositionInsertion(int x,int y){
        this.pos = new Position(x,y);
    }

    public PositionInsertion oppose(){ // 12 - i - 1
        PositionInsertion[] tab = {N1,N2,N3,E1,E2,E3,O3,O2,O1,S3,S2,S1};
        for(int i = 0; i < tab.length; i++){
            if (tab[i] == this){
                return tab[12-i-1];
            }
        }
        return null;
    }


    public Position getPosition(){
        return this.pos;
    }

    public static void main(String[] args) {
        PositionInsertion pos = E3;
        System.out.println(pos.getPosition());
    }
}