package ElevatorDesign;

class ExternalPanel {

    private DirectionButton upButton;
    private DirectionButton downButton;

    private ElevatorSystem system;
    private int floor;

    public ExternalPanel(boolean hasUp, boolean hasDown, ElevatorSystem system, int floor) {
        this.system = system;
        this.floor = floor;

        if (hasUp) upButton = new DirectionButton(Direction.UP);
        if (hasDown) downButton = new DirectionButton(Direction.DOWN);
    }

    public void pressUp() {
        if (upButton != null) {
            upButton.press();
            system.requestElevator(floor, Direction.UP);
        }
    }

    public void pressDown() {
        if (downButton != null) {
            downButton.press();
            system.requestElevator(floor, Direction.DOWN);
        }
    }
}
