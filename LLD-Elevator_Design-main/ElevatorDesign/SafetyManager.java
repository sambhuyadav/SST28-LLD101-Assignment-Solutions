package ElevatorDesign;
class SafetyManager {

    private Alarm overWeightAlarm = new OverWeightAlarm();
    private Alarm emergencyAlarm = new EmergencyAlarm();

    public boolean canMove(SensorData sensorData, boolean isEmergency) {
        return !sensorData.isOverWeight() && !isEmergency;
    }

    public void handleEmergency(ElevatorCar e) {
        e.setEmergency(true);
        emergencyAlarm.makeSound();
        e.getDoor().open();
    }

    public void handleOverweight(ElevatorCar e) {
        overWeightAlarm.makeSound();
        e.getDoor().open();
    }
}