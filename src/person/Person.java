package person;


 abstract class Person {

   String Name,Gender,Adress,BirthCountry,Email,Religion,National_ID,Birthdate,phone;
   
  public void person(String Name,String National_ID,String Gender,
          String Birthdate,String Birthcountry,String phone,String Email,String Address,String Religion){
   this.Name=Name;
   this.National_ID=National_ID;
   this.Gender=Gender;
   this.Birthdate=Birthdate;
   this.BirthCountry=Birthcountry;
   this.phone=phone;
   this.Email=Email;
   this.Adress=Address;
   this.Religion=Religion;
   }

}
