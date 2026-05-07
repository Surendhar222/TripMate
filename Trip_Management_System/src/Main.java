import com.tripmate.features.signin.SignInView;
import com.tripmate.util.ParseHelper;

public class Main {
    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("   Welcome to TripMate Application   ");
        System.out.println("================================");

        SignInView signInView = new SignInView();

        while (true) {
            System.out.println("\n1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            int choice = ParseHelper.readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    signInView.signInScreen();
                    break;
                case 2:
                    signInView.signUpOption();
                    break;
                case 3:
                    System.out.println("Thank you for using TripMate!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}