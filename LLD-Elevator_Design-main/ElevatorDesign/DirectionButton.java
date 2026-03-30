package ElevatorDesign;

class DirectionButton extends Button {
    private Direction direction;

    public DirectionButton(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
