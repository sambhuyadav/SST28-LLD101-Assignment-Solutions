package MultiLevel_Parking_Lot;

import java.util.List;

public class EntryGate {
    int id;
    private List<Level> levels;
    private SlotAllocationStrategy strategy;
    private TicketFactory factory;

    public EntryGate(int id, List<Level> levels,
                     SlotAllocationStrategy strategy,
                     TicketFactory factory) {
        this.id = id;
        this.levels = levels;
        this.strategy = strategy;
        this.factory = factory;
    }

    public Ticket park(Vehicle vehicle) {
        ParkingSlot slot = strategy.findSlot(levels, id, vehicle.getType());
        if (slot == null) return null;

        if (slot.assignVehicle()) {
            // remove from all levels
            for (Level level : levels) {
                level.removeSlot(slot);
            }
            return factory.create(vehicle, slot, id);
        }
        return null;
    }
    
}