package ElevatorDesign;

class FloorButton extends Button {
    private int floor;

    public FloorButton(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }
}
