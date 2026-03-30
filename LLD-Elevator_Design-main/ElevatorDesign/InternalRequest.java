package ElevatorDesign;
class InternalRequest extends Request {
    int destinationFloor;

    public InternalRequest(int sourceFloor, int destinationFloor) {
        super(sourceFloor);
        this.destinationFloor = destinationFloor;
    }
}