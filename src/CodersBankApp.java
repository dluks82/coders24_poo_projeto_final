import controller.AccountController;
import controller.LoggedInController;
import controller.NotLoggedInController;
import controller.UpdateController;
import enums.State;
import repository.BankRepository;
import state.AppState;
import ui.Screen;

public class CodersBankApp {
    private final AppState appState;

    private final NotLoggedInController notLoggedInController;
    private final LoggedInController loggedInController;
    private final AccountController accountController;
    private final UpdateController updateController;

    public CodersBankApp() {
        this.appState = new AppState();
        BankRepository bankRepository = new BankRepository();
        this.notLoggedInController = new NotLoggedInController(appState, bankRepository);
        this.loggedInController = new LoggedInController(appState, bankRepository);
        this.accountController = new AccountController(appState, bankRepository);
        this.updateController = new UpdateController(appState, bankRepository);
    }

    public void run() {
        while (appState.getCurrentState() != State.EXIT) {
            Screen.clear();
            switch (appState.getCurrentState()) {
                case NOT_LOGGED_IN -> notLoggedInController.run();
                case LOGGED_IN -> loggedInController.run();
                case ACCOUNT_MANAGEMENT -> accountController.run();
                case UPDATE_INFO -> updateController.run();
            }
        }
        Screen.clear();
        System.out.println("Encerrando a aplicação...");
    }

    public static void main(String[] args) {
        CodersBankApp app = new CodersBankApp();
        app.run();
    }
}
