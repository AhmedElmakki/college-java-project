package person;
 abstract class Person {
  private String name,address,birthCountry,national_id,birthDate,phone;
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
}
