package ElevatorDesign;
interface QueueProcessingStrategy {
    Direction getNextDirection(ElevatorCar elevator);
}