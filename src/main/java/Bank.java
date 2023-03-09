import javax.swing.*;
import java.sql.*;
import java.util.UUID;

public class Bank extends User {

    private boolean occupied;
    private int accountNumber;
    private static float balance;

    public Bank(String userName, String password, String email, String name, String surname, String phoneNumber, boolean occupied, int accountNumber, float balance) {
        super(userName, password, email, name, surname, phoneNumber);
        this.occupied = occupied;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public static String choosingOperation(){
        String [] choice ={"View account information", "Make a deposit", "Withdrawal", "Log out"};
        String chosenOption = (String) JOptionPane.showInputDialog(
                null,
                "Please select next operation",
                "Options:",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choice,
                choice[0]
        );
        return chosenOption;
    }

    public static void showAccountInfo(Connection conn, String userName) throws SQLException {
        String sql = "SELECT name, surname, accountNumber, Balance FROM users WHERE userName = '" + userName + "'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {

            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String accountNumber = resultSet.getString("accountNumber");
            String balance = resultSet.getString("balance");

            String output = "User %s %s: Account Nr. %s Balance - %s";
            JOptionPane.showMessageDialog(null, String.format(output, name, surname, accountNumber, balance));
            //System.out.println(String.format(output, name, surname, accountNumber, balance));
        }
    }

    public static float getBalance(Connection conn, String userName) throws SQLException {
        String sql = "SELECT balance FROM users WHERE userName = '" + userName + "'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            return resultSet.getFloat("balance");
        } else {
            return 0;
        }
    }

    public static void depositAccount(Connection conn, String userName, float depositSum) throws SQLException {
        float newBalance = getBalance(conn, userName) + depositSum;

        String sql = "UPDATE users SET balance = ?  WHERE userName = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setFloat(1, newBalance);
        statement.setString(2, userName);
        int rowInserted = statement.executeUpdate();

        if (rowInserted > 0) {
            JOptionPane.showMessageDialog(null, "Your account was deposited for " + depositSum + " EUR");
            //System.out.println("Your account was deposited for " + depositSum + " EUR");
        } else {
            JOptionPane.showMessageDialog(null,"Something went wrong");
            //System.out.println("Something went wrong");
        }
    }

    public static void withdrawal(Connection conn, String userName, float withdrawalAmount) throws SQLException {
        float newBalance1 = getBalance(conn, userName) - withdrawalAmount;

        if (newBalance1 > 0) {
            String sql = "UPDATE users SET balance = ?  WHERE userName = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setFloat(1, newBalance1);
            statement.setString(2, userName);
            int rowInserted = statement.executeUpdate();

            if (rowInserted > 0) {
                JOptionPane.showMessageDialog(null,"You have withdrawn " + withdrawalAmount + " EUR");
                //System.out.println("You have withdrawn " + withdrawalAmount + " EUR");
            } else {
                JOptionPane.showMessageDialog(null, "Something went wrong");
                //System.out.println("Something went wrong");
            }
        } else if (newBalance1 < 0) {
            JOptionPane.showMessageDialog(null, "You don't have enough money to make withdrawal, please choose another option");
            //System.out.println("You don't have enough money to make withdrawal, please choose another option");

        }
    }

}
