package ElevatorDesign;
import java.util.PriorityQueue;

class ElevatorCar {

    private int id;
    private int currentFloor;
    private State state;
    private boolean isEmergency;

    private Door door;
    private SensorData sensorData;
    private SafetyManager safetyManager;
    private MovementManager movementManager;
    private QueueProcessingStrategy queueStrategy;
    private InternalPanel panel;

    private PriorityQueue<Integer> upRequests = new PriorityQueue<>();
    private PriorityQueue<Integer> downRequests = new PriorityQueue<>((a, b) -> b - a);

    public ElevatorCar(int id, int maxFloor) {
        this.id = id;
        this.currentFloor = 0;
        this.state = State.IDLE;

        this.door = new Door();
        this.sensorData = new SensorData();
        this.safetyManager = new SafetyManager();
        this.movementManager = new MovementManager();
        this.queueStrategy = new DirectionalQueueStrategy();
        this.panel = new InternalPanel(maxFloor);
    }

    public void addRequest(int floor, Direction direction) {
        if (direction == Direction.UP)
            upRequests.add(floor);
        else
            downRequests.add(floor);
    }

    public void removeCurrentFloorRequest() {
        upRequests.remove(currentFloor);
        downRequests.remove(currentFloor);
    }

    public boolean hasRequestAtCurrentFloor() {
        return upRequests.contains(currentFloor) || downRequests.contains(currentFloor);
    }

    public void move() {
        movementManager.move(this);
    }

    // Getters & Setters

    public int getId() { return id; }

    public int getCurrentFloor() { return currentFloor; }
    public void setCurrentFloor(int f) { currentFloor = f; }

    public State getState() { return state; }
    public void setState(State s) { state = s; }

    public boolean isEmergency() { return isEmergency; }
    public void setEmergency(boolean val) { isEmergency = val; }

    public Door getDoor() { return door; }

    public InternalPanel getPanel() { return panel; }

    public SensorData getSensorData() { return sensorData; }

    public SafetyManager getSafetyManager() { return safetyManager; }

    public QueueProcessingStrategy getQueueStrategy() { return queueStrategy; }

    public PriorityQueue<Integer> getUpRequests() { return upRequests; }
    public PriorityQueue<Integer> getDownRequests() { return downRequests; }
}