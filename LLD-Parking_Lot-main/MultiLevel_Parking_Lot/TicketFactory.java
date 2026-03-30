package MultiLevel_Parking_Lot;

public class TicketFactory {
    public Ticket create(Vehicle v, ParkingSlot slot, int gateId) {
        return new Ticket(v, slot, gateId);
    }    
}