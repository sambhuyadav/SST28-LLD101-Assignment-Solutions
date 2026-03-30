package MultiLevel_Parking_Lot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Integer> gateIds = List.of(1);

        // slots creation
        List<ParkingSlot> slots = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Map<Integer, Integer> distance = Map.of(1, i * 10);
            slots.add(new ParkingSlot(i, SlotType.SMALL, distance));
        }
        for (int i = 6; i <= 10; i++) {
            Map<Integer, Integer> distance = Map.of(1, i * 10);
            slots.add(new ParkingSlot(i, SlotType.MEDIUM, distance));
        }

        Level level = new Level(1, slots, gateIds);
        List<Level> levels = List.of(level);

        EntryGate gate = new EntryGate(
                1,
                levels,
                new NearestSlotStrategy(),
                new TicketFactory()
        );

        ParkingLot lot = new ParkingLot(levels, List.of(gate));

        Vehicle v1 = new Vehicle("UP01", SlotType.MEDIUM);

        Ticket t1 = lot.park(v1, 1);

        if (t1 != null) {
            System.out.println("Vehicle parked. Ticket generated.");

            double price = lot.exit(t1);

            System.out.println("Exit price: " + price);
        } else {
            System.out.println("Parking full for this vehicle type.");
        }
    }
}
