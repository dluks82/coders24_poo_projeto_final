package state;

import enums.State;
import model.User;

public class AppState {
    private State currentState;
    private User loggedInUser;

    public AppState() {
        this.currentState = State.NOT_LOGGED_IN;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public String getLoggedUserId() {
        if (loggedInUser != null) {
            return loggedInUser.getCpf();
        }
        return null;
    }

    public String getLoggedInUserName() {
        if (loggedInUser != null) {
            return loggedInUser.getName();
        }
        return null;
    }
}
