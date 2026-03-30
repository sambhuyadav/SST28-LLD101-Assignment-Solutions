package ElevatorDesign;

enum ControlType {
    OPEN, CLOSE, EMERGENCY
}

class ControlButton extends Button {
    private ControlType type;

    public ControlButton(ControlType type) {
        this.type = type;
    }

    public ControlType getType() {
        return type;
    }
}
