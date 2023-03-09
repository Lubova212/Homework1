import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/new_schema";
        String username = "root";
        String password = "12345";
        Scanner scanner = new Scanner(System.in);
        String userName;
        String password1;

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            JOptionPane.showMessageDialog(null,"Welcome");
            
            //System.out.println("In order to register, please press r, for login - l");
            //char log = scanner.nextLine().charAt(0);

            if(User.choosingToLogOrRegister().equals("Registration")){
                /*System.out.println("Please create a username");
                userName = scanner.nextLine();
                System.out.println("Please enter a password");
                password1 = scanner.nextLine();
                System.out.println("Please enter your email");
                String email = scanner.nextLine();
                System.out.println("Please enter your name");
                String name = scanner.nextLine();
                System.out.println("Please enter your surname");
                String surname = scanner.nextLine();
                System.out.println("Please enter your phone number");
                String phoneNumber = scanner.nextLine();*/

                userName = JOptionPane.showInputDialog("Please enter your username");
                password1 = JOptionPane.showInputDialog("Please enter your password");
                String email = JOptionPane.showInputDialog("Please enter your email");
                String name = JOptionPane.showInputDialog("Please enter your name");
                String surname = JOptionPane.showInputDialog("Please enter your surname");
                String phoneNumber = JOptionPane.showInputDialog("Please enter your Phone number");

                JOptionPane.showMessageDialog( null, User.registration(conn, userName, password1, email, name, surname, phoneNumber));

            } else {
               /* System.out.println("Please enter your username");
                userName = scanner.nextLine();
                System.out.println("Please enter your password");
                password1 = scanner.nextLine();*/
                userName = JOptionPane.showInputDialog("Please enter your username");
                password1 = JOptionPane.showInputDialog("Please enter your password");

                if (User.login(conn, userName, password1) > 0) {
                    JOptionPane.showMessageDialog(null, "You have logged in successfully");

                } else {
                    JOptionPane.showMessageDialog(null,"Wrong username or password");
                }
            }
            String chosenOption;
            do {
                chosenOption = Bank.choosingOperation();

                /*System.out.println("Please choose an option:");
                System.out.println("Press 1 to see account information");
                System.out.println("Press 2 to make a deposit");
                System.out.println("Press 3 to withdraw");
                System.out.println("Press 4 to logout");
                chosenOption = scanner.nextInt();*/

                if (chosenOption.equals("View account information")) {
                    Bank.showAccountInfo(conn, userName);
                } else if (chosenOption.equals("Make a deposit")) {
                    //System.out.println("Please enter an amount of deposit in EUR");
                    Float depositSum = Float.parseFloat(JOptionPane.showInputDialog("Please enter an amount of deposit in EUR"));
                    //float depositSum = scanner.nextFloat();
                    Bank.depositAccount(conn, userName, depositSum);

                } else if (chosenOption.equals("Withdrawal")) {
                    //System.out.println("Please enter an amount  you want to withdraw in EUR");
                    //float withdrawalAmount = scanner.nextFloat();
                    Float withdrawalAmount = Float.parseFloat(JOptionPane.showInputDialog("Please enter an amount  you want to withdraw in EUR"));
                    Bank.withdrawal(conn, userName, withdrawalAmount);

                } else if (chosenOption.equals("Log out")) {

                    JOptionPane.showMessageDialog(null, "You have logged out");

                }

            }while (!chosenOption.equals("Log out")) ;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sorry! Something went wrong, try again later");
        }
    }




}
