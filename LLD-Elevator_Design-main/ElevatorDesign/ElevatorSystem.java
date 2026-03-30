package ElevatorDesign;
import java.util.ArrayList;
import java.util.List;

class ElevatorSystem {

    private List<ElevatorCar> elevators = new ArrayList<>();
    private List<Floor> floors = new ArrayList<>();
    private ElevatorController controller;

    public void setController(ElevatorController controller) {
        this.controller = controller;
    }

    public void addElevator(ElevatorCar elevator) {
        elevators.add(elevator);
    }

    public void addFloor(Floor floor) {
        floors.add(floor);
    }

    public void requestElevator(int floor, Direction direction) {
        controller.handleExternalRequest(elevators, new ExternalRequest(floor, direction));
    }

    public List<ElevatorCar> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}