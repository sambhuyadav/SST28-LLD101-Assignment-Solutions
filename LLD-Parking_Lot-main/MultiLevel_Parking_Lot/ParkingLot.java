package MultiLevel_Parking_Lot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private List<Level> levels;
    private Map<Integer, EntryGate> gates;

    public ParkingLot(List<Level> levels, List<EntryGate> gateList) {
        this.levels = levels;
        this.gates = new HashMap<>();
        for (EntryGate g : gateList) {
            gates.put(g.id, g);
        }
    }

    public Ticket park(Vehicle vehicle, int gateId) {
        return gates.get(gateId).park(vehicle);
    }

    public double exit(Ticket ticket) {
        ParkingSlot slot = ticket.getSlot();

        PricingStrategy strategy =
                PricingStrategyFactory.get(slot.getType());

        double price = strategy.calculate(ticket);

        slot.freeSlot();

        // add slot back
        for (Level level : levels) {
            level.addSlot(slot);
        }

        return price;
    }
}