import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        Console console = m.new Console();

        console.displayMenu();
    }
    //team kamatis
    static class UserAuth{

        //fields
        String userName;
        String userNumber;
        String userPin;
        double userBalance;

        //constructor
        public UserAuth(String userName, String userNumber, String userPin, double userBalance){
            this.userName = userName;
            this.userNumber = userNumber;
            this.userPin = userPin;
            this.userBalance = userBalance;
        }

        //getters
        public String getUserName(){
            return userName;
        }
        public String getUserNumber(){
            return userNumber;
        }
        public String getUserPin(){
            return userPin;
        }
        public double getUserBalance(){
            return userBalance;
        }

        //setter
        public void setUserName(String newName){
            this.userName = newName;
        }
        public void setUserNumber(String newNumber){
            this.userNumber = newNumber;
        }
        public void setUserPin(String newPin){
            this.userPin = newPin;
        }
        public void setUserBalance(double newBalance){
            this.userBalance = newBalance;
        }


    }

    //team kamatis eof

    //team sibuyas

    static class ValidUsers {
        private String userName;
        private String userNumber;
        private String userPin;
        private double userBalance;

        public ValidUsers(String userName, String userNumber, String userPin, double userBalance) {
            this.userName = userName;
            this.userNumber = userNumber;
            this.userPin = userPin;
            this.userBalance = userBalance;
        }

        public boolean isValid(String userName, String userNumber, String userPin) {
            if (this.userName.equals(userName) & this.userPin.equals(userPin)) {
                return true;
            }
            if(this.userNumber.equals(userNumber) & this.userPin.equals(userPin)) {
                return true;
            }
            return false;
        }

        public static UserAuth validUser(List<ValidUsers> validUsers, Predicate<ValidUsers> checker) {
            for (ValidUsers validUser : validUsers ) {
                if (checker.test(validUser)) {
                    System.out.println("Valid User");
                    return new UserAuth(validUser.userName, validUser.userNumber, validUser.userPin, validUser.userBalance);
                }
            }
            System.out.println("Invalid User");
            return null;
        }
    }
    
    static class LoginValidation {
        public static UserAuth validate(
            String userNameNumber,
            String userPin
        ) {
            List<ValidUsers> validUsers = new ArrayList<>();
            validUsers.add(new ValidUsers("Test1", "123", "123", 100.00));
            validUsers.add(new ValidUsers("Test2", "234", "123", 100.00));
            validUsers.add(new ValidUsers("Test3", "345", "123", 100.00));
            validUsers.add(new ValidUsers("Test4", "456", "123", 100.00));
            validUsers.add(new ValidUsers("Test5", "567", "123", 100.00));
            return ValidUsers.validUser(validUsers, vu->vu.isValid(userNameNumber, userNameNumber, userPin));
        }
    }

    //team sibuyas eof

    
        //team ba
            class Console {
                Scanner sc = new Scanner(System.in);
        
                public void displayMenu(){
                    System.out.println("<<<<< Welcome To Black Banking System >>>>>>");
                    System.out.println("Options: ");
                    System.out.println("1: Login");
                    System.out.println("2: Exit");
                    System.out.println("Enter your choice: ");
                    String choice = sc.nextLine();
        
                    switch (choice) {
                        case "1":
                            displayLogin();
                            break;
                        case "2":
                            System.out.println("Thank you for using our banking system. Exiting....");
                            System.exit(0);
                    }
                }
        
                private void displayLogin(){
                    Scanner sc = new Scanner(System.in);
                    final int MAX_LOGIN_LIMIT = 3;
                    int numTries = 0;
                    while(true){
                        if(numTries < MAX_LOGIN_LIMIT){
                            System.out.println("Please enter username or number: ");
                            String username = sc.nextLine();
                            System.out.println("Please enter your password: ");
                            String userPin = sc.nextLine();
                            UserAuth user = LoginValidation.validate(username, userPin);
                            if(user != null){
                                displayDashboard(user);
                                break;
                            }else{
                                System.out.println("Invalid username or password");
                                numTries++;
                            }
                        }else{
                            System.out.println("Sorry your ip is blocked!");
                            break;
                        }
                    }
                }
        
                private void displayDashboard(UserAuth user){
                    while(true){
                        System.out.println("Welcome " + user.getUserName());
                        System.out.println("Your current balance is: " + user.getUserBalance());
                        System.out.println("Options: ");
                        System.out.println("1: Check Balance");
                        System.out.println("2: Deposit");
                        System.out.println("4: Exit");
        
                        String input = sc.nextLine();
        
                        switch (input) {
                            case "1":
                                System.out.println("Your current balance is: " + user.getUserBalance());
                                break;
                            case "2":
                                System.out.println("Please enter amount to deposit: ");
                                double amount = sc.nextDouble();
                                deposit(amount, user);
                                break;
                            case "3":
                                System.out.println("Please enter old pin: ");
                                String oldPin = sc.nextLine();
                                System.out.println("Enter new pin: ");
                                String newPin = sc.nextLine();
                                validatePin(oldPin, newPin, user);
                                break;
                            case "4":
                                System.exit(0);
                            default:
                                break;
                        }
                    }
                }
        
                private void deposit(double amount, UserAuth user){
                    user.setUserBalance(user.getUserBalance() + amount);
                    System.out.println("Your new balance is " + user.getUserBalance());
                }
        
                public void validatePin(String oldPin, String newPin, UserAuth user){
                    if(!user.getUserPin().equals(oldPin))
                        System.out.println("Old pin not matched!");
                    else
                        user.setUserPin(newPin);
                }
            }
        //team ba eof
}

