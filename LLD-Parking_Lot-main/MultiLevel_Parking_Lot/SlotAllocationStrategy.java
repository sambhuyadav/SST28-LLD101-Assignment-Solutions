package MultiLevel_Parking_Lot;

import java.util.List;

interface SlotAllocationStrategy {
    ParkingSlot findSlot(List<Level> levels, int gateId, SlotType type);
}