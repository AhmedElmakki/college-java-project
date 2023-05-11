package person;
import java.util.Random;
public class Client extends Person {
    //Class variables
    private static Client[] clients = new Client[0];
    private static String[] emails = new String[0];
    private static String[] passwords = new String[0];
    private static String[] userNames = new String[0];
    private static String[] ids = new String[0];

    //Instance variables
    private Account[] clientAccounts = new Account[0];
    private final String userName;
    private String password, email;

    //Constructor
    public Client(String name, String national_id, char gender, String birthDate, String birthCountry, String phone, String userName, String email, String password, String address) {
        super(name.trim(), national_id.trim(), gender, birthDate, birthCountry.trim(), phone, address.trim());
        this.password = password.trim();
        this.userName = userName.trim();
        this.email=email.trim();
        emails = addArray(emails, email.trim());
        userNames = addArray(userNames, userName.trim());
        passwords = addArray(passwords, password.trim());
        ids = addArray(ids, national_id.trim());
        addClient(clients, this);
    }

    public String toString(){
        return "Bank client with id "+this.national_id;
    }
    //Instance Methods
    public boolean login(String userName_input, String password_input){
        if(this.userName.equals(userName_input) && this.password.equals(password_input)) return true;
        else return false;
    }

    public String openAcc(String acc_type, long balance){
        Account account = new Account(acc_type, balance);
        addAccount(clientAccounts, account);
        return "You've opened new "+acc_type+" bank account Successfully";

    }

    // Private Methods
    private String[] addArray(String[] array, String line){
        String[] temp = new String[array.length+1];
        for(int i=0;i<array.length;i++){
            temp[i] = array[i];
        }
        temp[array.length] = line;
        return temp;
    }

    private void addAccount(Account[] array, Account line){
        Account[] temp = new Account[array.length+1];
        for(int i=0;i<array.length;i++){
            temp[i] = array[i];
        }
        temp[array.length] = line;
        this.clientAccounts = temp;
    }
    private void addClient(Client[] array, Client line){
        Client[] temp = new Client[array.length+1];
        for(int i=0;i<array.length;i++){
            temp[i] = array[i];
        }
        temp[array.length] = line;
        clients = temp;
    }

    //Class Methods
    public static boolean emailExist(String line){
        for(int i=0;i<emails.length;i++){
            if(emails[i].equals(line.trim())) return true;
        }
        return false;
    }
    public static boolean usernameExist(String line){
        for(int i=0;i<userNames.length;i++){
            if(userNames[i].equals(line.trim())) return true;
        }
        return false;
    }
    public static boolean idExist(String line){
        for(int i=0;i<ids.length;i++){
            if(ids[i].equals(line.trim())) return true;
        }
        return false;
    }

    //setters
    public void setPhone(String num){
        super.phone = num;
    }
    public void setAddress(String line){
        super.address = line;
    }
    public void setEmail(String line){
        this.email = line;
    }
    public void setPassword(String line){
        this.password = line;
    }

    //getters
    public String getPhone(){
        return super.phone;
    }
    public String getName(){
        return super.name;
    }
    public String getID(){
        return super.national_id;
    }
    public char getGender(){
        return super.gender;
    }
    public String getAddress(){
        return super.address;
    }
    public String getBirthDate(){
        return super.birthDate;
    }
    public String getBirthCountry(){
        return super.birthCountry;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public static Client[] getClients(){
        return clients;
    }
    public Account[] getClientAccounts(){
        return this.clientAccounts;
    }
}
