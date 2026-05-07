package com.tripmate.features.signin;

import com.tripmate.data.dto.*;
import com.tripmate.data.repository.TripMateDB;
import com.tripmate.features.bus.BusView;
import com.tripmate.features.hotel.HotelView;
import com.tripmate.features.signup.SignUpView;
import com.tripmate.features.travel_package.TravelPackageView;
import com.tripmate.util.ParseHelper;

import java.util.List;

public class SignInView {
    private final SignInModel model = new SignInModel();
    private final TripMateDB db = TripMateDB.getInstance();
    private final HotelView hotelView = new HotelView();
    private final BusView busView = new BusView();
    private final TravelPackageView travelPackageView = new TravelPackageView();

    private User loggedInUser;

    public void signInScreen() {
        if (db.getUsers().isEmpty()) {
            System.out.println("\nSystem is empty. Please register the first user (Admin).");
            new SignUpView().signUpScreen();
            return;
        }

        System.out.println("\n--- TripMate Sign In ---");
        String email = ParseHelper.readString("Enter email: ");
        String password = ParseHelper.readString("Enter password: ");

        loggedInUser = model.authenticate(email, password);

        if (loggedInUser != null) {
            System.out.println("Sign In successful! Welcome, " + loggedInUser.getName() + "!");
            checkRoleAndNavigate();
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    public void signUpOption() {
        new SignUpView().signUpScreen();
    }

    private void checkRoleAndNavigate() {
        List<UserRole> allUserRoles = db.getUserRoles();
        UserRole foundRole = null;

        for (UserRole allUserRole : allUserRoles) {
            if (allUserRole.getUserId().equals(loggedInUser.getUserId())) {
                foundRole = allUserRole;
                break;
            }
        }

        if (foundRole != null && foundRole.getRoleId().equals("R_ADMIN")) {
            showAdminMenu();
        } else {
            showCustomerMenu();
        }
    }

    private void showAdminMenu() {
        while (loggedInUser != null) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. User Management");
            System.out.println("2. Add Hotel & Rooms");
            System.out.println("3. Add Bus");
            System.out.println("4. Add Travel Package");
            System.out.println("5. View All Bookings");
            System.out.println("6. Sign Out");
            int choice = ParseHelper.readInt("Choice: ");

            switch (choice) {
                case 1: new SignUpView().adminCreateUser(); break;
                case 2: hotelView.addHotel(); break;
                case 3: busView.addBus(); break;
                case 4: travelPackageView.addPackage(); break;
                case 5: System.out.println("Total Bookings: " + db.getBookings().size());
                        showAllBookings();
                        break;
                case 6: loggedInUser = null; break;
                //case 6: break;
            }
        }
    }

    private void showCustomerMenu() {
        while (loggedInUser != null) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Book Hotel");
            System.out.println("2. Book Bus");
            System.out.println("3. View Travel Packages");
            System.out.println("4. Sign Out");
            int choice = ParseHelper.readInt("Choice: ");

            switch (choice) {
                case 1: new HotelView().hotelBooking(loggedInUser);
                        break;
                case 2: new BusView().busBooking(loggedInUser);
                        break;
                case 3: new TravelPackageView().displayPackages(loggedInUser);
                        break;
                case 4: loggedInUser = null;
                        break;
            }
        }
    }

    private void showAllBookings() {

        while (true) {

            System.out.println("\n--- View Bookings ---");

            System.out.println("1. Bus Bookings");
            System.out.println("2. Hotel Bookings");
            System.out.println("3. Package Bookings");
            System.out.println("4. Back");

            int choice = ParseHelper.readInt("Choice: ");

            switch (choice) {
                case 1:
                    busView.viewBusBookings();
                    break;
                case 2:
                    hotelView.viewHotelBookings();
                    break;
                case 3:
                    travelPackageView.viewPackageBookings();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
