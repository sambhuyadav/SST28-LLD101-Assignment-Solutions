package MultiLevel_Parking_Lot;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingSlot {
    private int id;
    private SlotType type;
    private boolean isAvailable = true;
    private Map<Integer, Integer> distanceMap; // gateId -> distance
    private ReentrantLock lock = new ReentrantLock();

    public ParkingSlot(int id, SlotType type, Map<Integer, Integer> distanceMap) {
        this.id = id;
        this.type = type;
        this.distanceMap = distanceMap;
    }

    public int getId() {
        return id;
    }

    public SlotType getType() {
        return type;
    }

    public int getDistance(int gateId) {
        return distanceMap.get(gateId);
    }

    public boolean assignVehicle() {
        lock.lock();
        try {
            if (!isAvailable) return false;
            isAvailable = false;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void freeSlot() {
        lock.lock();
        try {
            isAvailable = true;
        } finally {
            lock.unlock();
        }
    }
}