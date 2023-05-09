package person;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class BankSystem {
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        //variables
        Client[] clients = new Client[0];
        Client client;
        boolean run = true;
        String name, email, userName, password, address, national_id, phone, birthDate, birthCountry, acc_type;
        String username_login, password_login;
        char gender;
        long balance;

        System.out.println("Welcome to the Banking System");
        while(run){
            System.out.println("Enter 1 for registration\nEnter 2 to Login\nEnter 3 to exit");
            System.out.print("Response: ");
            int response = console.nextInt();
            if(response == 1){
                System.out.print("Enter your full name: ");
                console.nextLine();
                name = console.nextLine();
                System.out.print("Enter your email: ");
                do {
                    email = console.next();
                } while(Client.emailExist(email) || !checkInput(email, "email"));
                System.out.print("Enter your username: ");
                do {
                    userName = console.next();
                } while(Client.usernameExist(userName));
                System.out.print("Enter your password: ");
                password = console.next();
                System.out.print("Enter your address: ");
                console.nextLine();
                address = console.nextLine();
                System.out.print("Enter your national ID: ");
                do {
                    national_id = console.next();
                } while(Client.idExist(national_id) || !checkInput(national_id, "id"));
                System.out.print("Enter your phone: ");
                do {
                    phone = console.next();
                } while(!checkInput(phone, "phone"));
                System.out.print("Enter your gender (m/f): ");
                do {
                    gender = console.next().toLowerCase().charAt(0);
                } while(!(gender == 'm' || gender == 'f'));
                System.out.print("Enter your birth date (YYYY-MM-DD): ");
                do {
                    birthDate = console.next();
                } while(!checkInput(birthDate, "date"));
                System.out.print("Enter your birth country: ");
                console.nextLine();
                birthCountry = console.nextLine();
                System.out.print("Enter your account type (saving, current): ");
                do {
                    acc_type = (console.next()).toLowerCase();
                } while(!(acc_type.equals("saving") || acc_type.equals("current")));
                System.out.print("Enter your balance: ($) ");
                balance = console.nextLong();

                client = new Client(name, national_id, gender, birthDate, birthCountry, phone, userName, email, password, address);
                clients = addClient(clients ,client);
                System.out.println("\n"+client.openAcc(acc_type, balance)+"\n");

            } else if (response == 2){
                int i=0;
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
                    for (i = 0; i < clients.length; i++) {
                        if (clients[i].login(username_login, password_login)) {
                            logged_in = true;
                            break;
                        }
                    }
                    if(!logged_in){
                        System.out.println("Error in the input data, try again");
                        continue;
                    } else{
                        logging = false;
                        System.out.println("Logged in successfully");
                    }
                }
                while(logged_in){
                    int response_2;
                    System.out.println("Enter 1 to check balance\nEnter 2 to withdraw money\nEnter 3 to deposit money\nEnter 4 to exit");
                    System.out.print("Response: ");
                    response_2 = console.nextInt();
                    if(response_2==1){
                        System.out.println(clients[i].checkBalance());
                    } else if(response_2==2){
                        System.out.print("How much money you want to withdraw? ($) ");
                        long amount = console.nextLong();
                        System.out.println(clients[i].withdraw(amount));
                    } else if(response_2==3){
                        System.out.print("How much money you want to deposit? ($) ");
                        long amount = console.nextLong();
                        System.out.println(clients[i].deposit(amount));
                    } else if (response_2==4) {
                        logged_in = false;
                    } else{
                        System.out.println("Error, try again");
                    }
                }

            }else if(response == 3){
                run=false;
                System.out.println("See you soon!");
            } else {
                System.out.println("Incorrect input, try again!");
            }
        }
    }
    public static Client[] addClient(Client[] array, Client object){
        Client[] temp = new Client[array.length+1];
        for(int i=0;i<array.length;i++){
            temp[i] = array[i];
        }
        temp[array.length] = object;
        return temp;
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
            Pattern pattern = Pattern.compile("^01(0|1|2|5)\\d{8}$");
            Matcher matcher = pattern.matcher(line);
            return matcher.find();
        } else {
            Pattern pattern = Pattern.compile("[\\w-]+@[\\w-]+\\.\\w+");
            Matcher matcher = pattern.matcher(line);
            return matcher.find();
        }
    }
}
