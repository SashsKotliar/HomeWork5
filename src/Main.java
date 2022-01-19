import java.util.Scanner;

public class Main {
    public static final int SIGN_IN = 1;
    public static final int LOG_IN = 2;
    public static final int EXIT = 3;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VirtualMarket shop = new VirtualMarket();
        int choice;
        do {
            System.out.println("If you want to sign in - press 1.");
            System.out.println("If you want to log in - press 2.");
            System.out.println("If you want to exit - press 3.");
            choice = scanner.nextInt();
            if (choice == SIGN_IN){
                shop.signUp(scanner);
            } else if (choice == LOG_IN){
                shop.logIn(scanner);
            } else if (choice != EXIT){
                System.out.println("Invalid choice");
            }
        } while (choice != EXIT);
    }
}
