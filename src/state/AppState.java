package state;

import enums.State;
import model.Account;
import model.User;

public class AppState {
    private State currentState;
    private User loggedInUser;
    private Account loggedInAccount;

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

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
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
