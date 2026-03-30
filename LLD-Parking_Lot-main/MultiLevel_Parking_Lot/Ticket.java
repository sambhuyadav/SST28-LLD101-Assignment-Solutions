package MultiLevel_Parking_Lot;

public class Ticket {
    private long entryTime;
    private Vehicle vehicle;
    private ParkingSlot slot;
    private int gateId;

    public Ticket(Vehicle v, ParkingSlot s, int gateId) {
        this.vehicle = v;
        this.slot = s;
        this.gateId = gateId;
        this.entryTime = System.currentTimeMillis();
    }

    public long getEntryTime() { return entryTime; }
    public ParkingSlot getSlot() { return slot; }
    public int getGateId() { return gateId; }
    public Vehicle getVehicle(){return vehicle;}
}