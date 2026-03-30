package ElevatorDesign;

abstract class Button {
    protected boolean isPressed;

    public void press() {
        isPressed = true;
    }

    public void reset() {
        isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }
}
