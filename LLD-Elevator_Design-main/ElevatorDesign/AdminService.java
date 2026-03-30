package ElevatorDesign;

class AdminService {

    private ElevatorSystem system;

    public AdminService(ElevatorSystem system) {
        this.system = system;
    }

    public void addFloors(int min, int max) {
        for (int i = min; i <= max; i++) {
            system.addFloor(new Floor(i, max, system));
        }
        System.out.println("Floors added: " + min + " to " + max);
    }

    public void addElevators(int count, int maxFloor) {
        for (int i = 0; i < count; i++) {
            system.addElevator(new ElevatorCar(i, maxFloor));
        }
        System.out.println(count + " elevators added");
    }

    public void updateStrategy(ElevatorChoosingStrategy strategy) {
        system.setController(new ElevatorController(strategy));
        System.out.println("Strategy updated");
    }
}
