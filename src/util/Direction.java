package util;

public enum Direction{
    NO_DIRECTION(-1),
    UP(1),
    DOWN(0),
    LEFT(2),
    RIGHT(3);

    private final int directionId;

    public int getDirectionId(){
        return directionId;
    }

    Direction(int directionId){
        this.directionId = directionId;
    }
}
