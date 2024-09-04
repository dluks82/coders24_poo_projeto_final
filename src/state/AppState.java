package state;

import enums.State;

public class AppState {
    private State currentState;

    public AppState() {
        this.currentState = State.NOT_LOGGED_IN;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
