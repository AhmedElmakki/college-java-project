package person;
 abstract class Person {
   String name,address,birthCountry,national_id,birthDate,phone;
   char gender;
   
  public Person(String name,String national_id,char gender,
          String birthDate,String birthCountry,String phone,String address){
   this.name=name;
   this.national_id=national_id;
   this.gender=gender;
   this.birthDate=birthDate;
   this.birthCountry=birthCountry;
   this.phone=phone;
   this.address=address;
   }
   //setters
   public abstract void setPhone(String num);
   public abstract void setAddress(String line);

   //getters
  public abstract String getName();
  public abstract String getID();
  public abstract char getGender();
  public abstract String getBirthDate();
  public abstract String getBirthCountry();
  public abstract String getPhone();
  public abstract String getAddress();

}
