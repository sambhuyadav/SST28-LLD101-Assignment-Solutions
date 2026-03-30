package ElevatorDesign;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== SYSTEM SETUP BY ADMIN ===");

        ElevatorSystem system = new ElevatorSystem();

        AdminService admin = new AdminService(system);

        admin.addFloors(0, 10);
        admin.addElevators(3, 10);
        admin.updateStrategy(new NearestElevatorStrategy());

        System.out.println("=== SYSTEM READY ===\n");

        System.out.println("=== USER REQUESTS ===");

        System.out.println("-> User at Floor 3 clicks UP panel");
        Floor floor3 = system.getFloors().get(3);
        floor3.getPanel().pressUp();

        System.out.println("-> User at Floor 7 clicks DOWN panel");
        Floor floor7 = system.getFloors().get(7);
        floor7.getPanel().pressDown();

        System.out.println("-> User inside Elevator 0 clicks Floor 8 button");
        ElevatorCar e = system.getElevators().get(0);
        e.getPanel().pressFloorButton(e, 8);

        System.out.println("-> User presses Emergency button (commented out for smooth run)");
        // e.getPanel().pressEmergency(e);

        simulate(system, 10);
    }


    private static void simulate(ElevatorSystem system, int steps) {
        System.out.println("\n=== SIMULATION START ===");

        for (int t = 1; t <= steps; t++) {
            System.out.println("\nTime Step: " + t);

            for (ElevatorCar e : system.getElevators()) {
                e.move();

                System.out.println(
                        "Elevator " + e.getId() +
                        " | Floor: " + e.getCurrentFloor() +
                        " | State: " + e.getState() +
                        " | Door: " + (e.getDoor().isOpen() ? "OPEN" : "CLOSED")
                );
            }
        }

        System.out.println("\n=== SIMULATION END ===");
    }
}