package person;
import java.util.Random;
public class Client extends Person {
    //Class variables
    private static int[] acc_nos = new int[0];
    private static String[] emails = new String[0];
    private static String[] passwords = new String[0];
    private static String[] userNames = new String[0];
    private static String[] ids = new String[0];

    //Instance variables
    private final String userName;
    private String  acc_type, password, email;
    private long balance;
    private int acc_no;
    private boolean hasAcc = false;
    Random r = new Random();

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
    }

    //Instance Methods
    public String openAcc(String acc_type, long balance){
        if(!hasAcc){

            this.acc_type = acc_type;
            this.balance = balance;
            do {
                this.acc_no = r.nextInt(10000000, 100000000);
            } while (acc_noExist(this.acc_no));
            acc_nos = addArray(acc_nos, acc_no);
            hasAcc = true;
            return "You've opened new bank account Successfully\nYour account type is "+this.acc_type+",\nYour account number is "+this.acc_no;
        } else {
            return "You already have an account!";
        }
    }
    public String withdraw(long amount){
        if(hasAcc){
            if(amount > balance){
                return "Not enough money in your Account";
            } else{
                balance -= amount;
                return "You withdrew "+amount+"$ Successfully";
            }
        } else {
            return "You don't have Account";
        }
    }
    public String deposit(long amount){
        if(hasAcc){
            balance += amount;
            return "You deposited "+amount+"$ in your Account Successfully";
        } else{
            return "You don't have Account";
        }
    }
    public String checkBalance(){
        return "Your Account's balance is "+balance+"$";
    }

    public boolean login(String userName_input, String password_input){
        if(this.userName.equals(userName_input) && this.password.equals(password_input)) return true;
        else return false;
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
    private int[] addArray(int[] array, int num){
        int[] temp = new int[array.length+1];
        for(int i=0;i<array.length;i++){
            temp[i] = array[i];
        }
        temp[array.length] = num;
        return temp;
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
    public static boolean acc_noExist(int num){
        for(int i=0;i<acc_nos.length;i++){
            if(acc_nos[i] == num) return true;
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
    public int getAccNo(){
        return this.acc_no;
    }
    public String getAccType(){
        return this.acc_type;
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
}
