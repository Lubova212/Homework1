import javax.swing.*;
import java.sql.*;

public class User {

private String userName;
private String password;
private String email;
private String name;
private String surname;
private String phoneNumber;


    public User(String userName, String password, String email, String name, String surname, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


public static String choosingToLogOrRegister(){
        String [] choice ={"Registration", "Login"};
        String choiceOptions = (String) JOptionPane.showInputDialog(
                null,
                "Please select an action",
                "Options:",
                JOptionPane.QUESTION_MESSAGE,
                null,
                choice,
                choice[0]
        );
    return choiceOptions;
    }

    public static String registration(Connection conn, String userName, String password, String email, String name, String surname, String phoneNumber) throws SQLException {


        String sql = "INSERT INTO users (username, password, email, name, surname, phoneNumber, accountNumber, balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, userName);
        statement.setString(2, password);
        statement.setString(3, email);
        statement.setString(4, name);
        statement.setString(5, surname);
        statement.setString(6, phoneNumber);
        int accountNumber = (int) (Math.random() * 90000000 + 10000000);
        statement.setInt(7, accountNumber);
        float balance = 0.00f;
        statement.setFloat(8, balance);

        int rowInserted = statement.executeUpdate();

        if (rowInserted > 0) {
            return "Registration of a user was successful";
        } else {
            return "Something went wrong";
        }

    }

    public static int login(Connection conn, String userName, String password) throws SQLException{
    String sql = "SELECT * FROM users WHERE userName = '" + userName + "' and password = '" +password+ "'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            return 1;
        } else {
            return 0;
        }
    }


    }

