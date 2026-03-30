package MultiLevel_Parking_Lot;

import java.util.List;

public class NearestSlotStrategy implements SlotAllocationStrategy {
    public ParkingSlot findSlot(List<Level> levels, int gateId, SlotType type) {
        ParkingSlot best = null;

        for (Level level : levels) {
            ParkingSlot slot = level.getNearestSlot(gateId, type);
            if (slot == null) continue;

            if (best == null ||
                slot.getDistance(gateId) < best.getDistance(gateId)) {
                best = slot;
            }
        }
        return best;
    }
}
