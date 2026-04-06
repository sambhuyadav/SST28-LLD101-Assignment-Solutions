package MultiLevel_Parking_Lot;

public class Vehicle {
    private String number;
    private SlotType type;

    public Vehicle(String number, SlotType type) {
        this.number = number;
        this.type = type;
    }

    public SlotType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }    
}