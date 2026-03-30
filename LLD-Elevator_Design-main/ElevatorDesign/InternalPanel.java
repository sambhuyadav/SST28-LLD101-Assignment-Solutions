package ElevatorDesign;

import java.util.ArrayList;
import java.util.List;

class InternalPanel {

    private List<FloorButton> floorButtons = new ArrayList<>();
    private ControlButton openButton = new ControlButton(ControlType.OPEN);
    private ControlButton closeButton = new ControlButton(ControlType.CLOSE);
    private ControlButton emergencyButton = new ControlButton(ControlType.EMERGENCY);

    public InternalPanel(int maxFloor) {
        for (int i = 0; i <= maxFloor; i++) {
            floorButtons.add(new FloorButton(i));
        }
    }

    public void pressFloorButton(ElevatorCar elevator, int floor) {
        FloorButton button = floorButtons.get(floor);
        button.press();

        Direction dir = (floor > elevator.getCurrentFloor())
                ? Direction.UP : Direction.DOWN;

        elevator.addRequest(floor, dir);
    }

    public void pressOpen(ElevatorCar elevator) {
        openButton.press();
        elevator.getDoor().open();
    }

    public void pressClose(ElevatorCar elevator) {
        closeButton.press();
        elevator.getDoor().close();
    }

    public void pressEmergency(ElevatorCar elevator) {
        emergencyButton.press();
        elevator.getSafetyManager().handleEmergency(elevator);
    }
}
