package person;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class BankSystem {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        //variables
        boolean run = true;
        String name, email, userName, password, address, national_id, phone, birthDate, birthCountry, acc_type;
        String username_login, password_login;
        char gender;
        long balance;
        int response, response_2, response_3;

        System.out.println("Welcome to the Banking System");
        while(run){
            System.out.println("Enter 1 for registration\nEnter 2 to Login\nEnter 3 to exit");
            System.out.print("Response: ");
            response = console.nextInt();

            if(response == 1){
                System.out.print("Welcome to registration (Enter e to quit)\nEnter your full name: ");
                console.nextLine();
                name = console.nextLine().trim();
                if(name.equals("e")) continue;
                System.out.print("Enter your email: ");
                do {
                    email = console.next().trim();
                    if((!checkInput(email, "email")) && !email.equals("e")){System.out.println("Invalid Email");}
                } while((Client.emailExist(email) || !checkInput(email, "email")) && !email.equals("e"));
                if(email.equals("e")) continue;
                System.out.print("Enter your username: (6 to 18 letters) ");
                do {
                    console.nextLine();
                    userName = console.nextLine().trim();
                    if(!checkInput(userName, "username") ){System.out.println("username is too short or too long");}
                } while((!checkInput(userName, "username") || (Client.usernameExist(userName)) && !userName.equals("e")));
                if(userName.equals("e")) continue;
                System.out.print("Enter your password: ");
                password = console.nextLine().trim();
                if(password.equals("e")) continue;
                System.out.print("Enter your address: ");
                address = console.nextLine().trim();
                if(address.equals("e")) continue;
                System.out.print("Enter your national ID: (23*************) :");
                do {
                    national_id = console.next().trim();
                    if((!checkInput(national_id, "id")) && !national_id.equals("e")){System.out.println("Invalid ID");}
                } while((Client.idExist(national_id) || !checkInput(national_id, "id")) && !national_id.equals("e"));
                if(national_id.equals("e")) continue;
                System.out.print("Enter your phone: ");
                do {
                    phone = console.next().trim();
                    if((!checkInput(phone, "phone") && !phone.equals("e"))){System.out.println("Invalid phone number");}
                } while(!checkInput(phone, "phone") && !phone.equals("e"));
                if(phone.equals("e")) continue;
                System.out.print("Enter your gender (m/f): ");
                do {
                    gender = console.next().toLowerCase().charAt(0);
                } while((!(gender == 'm' || gender == 'f')) && !(gender == 'e'));
                if(gender =='e') continue;
                System.out.print("Enter your birth date (YYYY-MM-DD): ");
                do {
                    birthDate = console.next().trim();
                    if(!checkInput(birthDate, "date") && !birthDate.equals("e")){System.out.println("Invalid Date");}
                } while(!checkInput(birthDate, "date") && !birthDate.equals("e"));
                if(birthDate.equals("e")) continue;
                System.out.print("Enter your birth country: ");
                console.nextLine();
                birthCountry = console.nextLine().trim();
                if(birthCountry.equals("e")) continue;

                new Client(name, national_id, gender, birthDate, birthCountry, phone, userName, email, password, address);

            } else if (response == 2){
                int i=0;
                Client currentClient = null;
                boolean logged_in = false, logging = true;
                while (logging) {
                    System.out.print("Enter your username: (e to exit) ");
                    username_login = console.next();
                    if(username_login.equals("e")) {
                        logging = false;
                        break;
                    }
                    System.out.print("Enter your password: (e to exit) ");
                    password_login = console.next();
                    if(password_login.equals("e")){
                        logging = false;
                        break;
                    }
                    for (i = 0; i < Client.getClients().length; i++) {
                        if (Client.getClients()[i].login(username_login, password_login)) {
                            logged_in = true;
                            break;
                        }
                    }
                    if(!logged_in){
                        System.out.println("Error in the input data, try again");
                        continue;
                    } else{
                        logging = false;
                        currentClient = Client.getClients()[i];
                        System.out.println("Logged in successfully");
                    }
                }
                while(logged_in){
                    Account currentAccount = null;
                    boolean accountChosen = false;
                    System.out.println("Choose your account: ");
                    for(int j=0;j<currentClient.getClientAccounts().length;j++){
                        System.out.println("Enter "+(j+1)+" for "+currentClient.getClientAccounts()[j]);
                    }
                    System.out.println("To create new bank account, enter 0");
                    System.out.println("To exit, enter -1");
                    System.out.print("Response: ");
                    response_2 = console.nextInt() -1;

                    if(response_2 < currentClient.getClientAccounts().length && response_2 >= 0){
                        accountChosen = true;
                        currentAccount = currentClient.getClientAccounts()[response_2];
                        while(accountChosen){
                            System.out.println("Enter 1 to check balance\nEnter 2 to withdraw\nEnter 3 to deposit\nEnter 4 to exit");
                            System.out.print("Response: ");
                            response_3 = console.nextInt();
                            if(response_3 == 1) System.out.println(currentAccount.checkBalance());
                            else if(response_3 == 2){
                                System.out.print("How much you want to withdraw? ($) ");
                                long amount = console.nextLong();
                                System.out.println(currentAccount.withdraw(amount));
                            } else if (response_3 == 3){
                                System.out.print("How much you want to deposit? ($) ");
                                long amount = console.nextLong();
                                System.out.println(currentAccount.deposit(amount));
                            } else if (response_3 == 4) accountChosen = false;
                             else System.out.println("Error, please try again");

                        }
                    } else if (response_2 == -1) {
                        System.out.print("Enter your account type (saving, current): ");
                        do {
                            acc_type = (console.next()).toLowerCase();
                        } while(!(acc_type.equals("saving") || acc_type.equals("current")));
                        System.out.print("Enter your balance: ($) ");
                        balance = console.nextLong();
                        System.out.println(currentClient.openAcc(acc_type, balance));
                    } else if (response_2 == -2) logged_in = false;
                     else System.out.println("Error, please try again");
                }

            }else if(response == 3){
                run=false;
                System.out.println("See you soon!");
            } else {
                System.out.println("Incorrect input, try again!");
            }
        }
    }
    public static boolean checkInput(String line, String type){
        if(type.equals("date")) {
            Pattern pattern = Pattern.compile("^([1-2][09]\\d{2})-([0-1]\\d)-([0-3]\\d)$");
            Matcher matcher = pattern.matcher(line);
            return matcher.find();
        } else if (type.equals("id")){
            Pattern pattern = Pattern.compile("^[23]\\d{13}$");
            Matcher matcher = pattern.matcher(line);
            return matcher.find();
        } else if(type.equals("phone")){
            Pattern pattern = Pattern.compile("^01(0|1|2|5)\\d{7}$");
            Matcher matcher = pattern.matcher(line);
            return matcher.find();
        } else if (type.equals("username")) {
            Pattern pattern = Pattern.compile("^\\S{6,18}$");
            Matcher matcher = pattern.matcher(line);
            return matcher.find();
        } else {
            Pattern pattern = Pattern.compile("[\\w-]+@[\\w-]+\\.\\w+");
            Matcher matcher = pattern.matcher(line);
            return matcher.find();
        }
    }
}
