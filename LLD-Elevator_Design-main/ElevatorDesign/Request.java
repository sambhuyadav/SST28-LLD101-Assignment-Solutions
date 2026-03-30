package ElevatorDesign;
abstract class Request {
    int sourceFloor;

    public Request(int sourceFloor) {
        this.sourceFloor = sourceFloor;
    }
}