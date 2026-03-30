package ElevatorDesign;

class Floor {
    int floorNumber;
    ExternalPanel panel;

    public Floor(int floorNumber, int maxFloor, ElevatorSystem system) {
        this.floorNumber = floorNumber;

        if (floorNumber == 0) {
            panel = new ExternalPanel(true, false, system, floorNumber);
        } else if (floorNumber == maxFloor) {
            panel = new ExternalPanel(false, true, system, floorNumber);
        } else {
            panel = new ExternalPanel(true, true, system, floorNumber);
        }
    }

    public ExternalPanel getPanel() {
        return panel;
    }
}
