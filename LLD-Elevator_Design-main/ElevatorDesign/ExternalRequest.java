package ElevatorDesign;
class ExternalRequest extends Request {
    Direction direction;

    public ExternalRequest(int sourceFloor, Direction direction) {
        super(sourceFloor);
        this.direction = direction;
    }
}