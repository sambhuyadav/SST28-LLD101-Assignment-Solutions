package MultiLevel_Parking_Lot;

import java.util.*;

public class Level {
    private int id;

    // SlotType -> GateId -> TreeSet
    private Map<SlotType, Map<Integer, TreeSet<ParkingSlot>>> slotMap = new HashMap<>();

    public Level(int id, List<ParkingSlot> slots, List<Integer> gateIds) {
        this.id = id;

        for (SlotType type : SlotType.values()) {
            slotMap.put(type, new HashMap<>());
            for (int gateId : gateIds) {
                int finalGateId = gateId;

                TreeSet<ParkingSlot> set = new TreeSet<>(
                        Comparator.comparingInt((ParkingSlot s) -> s.getDistance(finalGateId))
                                .thenComparingInt(ParkingSlot::getId)
                );

                slotMap.get(type).put(gateId, set);
            }
        }

        for (ParkingSlot slot : slots) {
            for (int gateId : gateIds) {
                slotMap.get(slot.getType()).get(gateId).add(slot);
            }
        }
    }

    public ParkingSlot getNearestSlot(int gateId, SlotType type) {
        TreeSet<ParkingSlot> set = slotMap.get(type).get(gateId);
        if (set.isEmpty()) return null;
        return set.first();
    }

    public int getId() {
        return id;
    }

    public void removeSlot(ParkingSlot slot) {
        for (Map<Integer, TreeSet<ParkingSlot>> gateMap : slotMap.values()) {
            for (TreeSet<ParkingSlot> set : gateMap.values()) {
                set.remove(slot);
            }
        }
    }

    public void addSlot(ParkingSlot slot) {
        for (Map<Integer, TreeSet<ParkingSlot>> gateMap : slotMap.values()) {
            for (Map.Entry<Integer, TreeSet<ParkingSlot>> entry : gateMap.entrySet()) {
                entry.getValue().add(slot);
            }
        }
    }
}