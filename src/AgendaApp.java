import controller.ContactController;
import enums.State;
import repository.AgendaRepository;
import state.AppState;
import ui.Input;
import ui.Screen;

import java.util.Scanner;

public class AgendaApp {
    private final AppState appState;
    private final AgendaRepository agendaRepository;
    private final ContactController contactController;
    private final Scanner scanner = new Scanner(System.in);

    public AgendaApp() {
        this.appState = new AppState();
        this.agendaRepository = new AgendaRepository();
        this.contactController = new ContactController(appState, scanner, agendaRepository);
    }

    public void run() {

        while (appState.getCurrentState() != State.EXIT) {

            Screen.showMenu(agendaRepository.size());

            int userOption = Input.getInt(scanner, "Informe a operação desejada: ", false);

            switch (userOption) {
                case 1:
                    contactController.add();
                    break;
                case 2:
                    contactController.remove();
                    break;
                case 3:
                    contactController.showDetails();
                    break;
                case 4:
                    contactController.edit();
                    break;
                case 5:
                    contactController.showList();
                    break;
                case 6:
                    Screen.showAbout();

                    System.out.println("Enter para continuar...");
                    scanner.nextLine();
                    break;
                case 9:
                    appState.setCurrentState(State.EXIT);
                    break;
                default:
                    System.out.println("Opção inválida! Enter para continuar...");
                    scanner.nextLine();
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        AgendaApp app = new AgendaApp();
        app.run();
    }
}
