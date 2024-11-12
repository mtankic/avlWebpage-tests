package objects;

 public class FormData {
    public String prefix;
    public String firstName;
    public String middleName ;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String roomType;
    public Integer numberOfGuests;
    public Integer numberOfDays;
    public String specialRequest;
    public Integer starRating;
    public String arrivalDate;
    public String arrivalTime;
    public String departureDate;
    public String fileToUpload;

     public FormData(String prefix, String firstName, String lastName, Integer numberOfGuests,
                     Integer numberOfDays, String specialRequest, String arrivalDate, String phoneNumber, String fileToUpload){
         this.prefix = prefix;
         this.firstName = firstName;
         this.lastName =lastName;
         this.numberOfGuests = numberOfGuests;
         this.numberOfDays = numberOfDays;
         this.specialRequest =specialRequest;
         this.arrivalDate = arrivalDate;
         this.phoneNumber = phoneNumber;
         this.fileToUpload = fileToUpload;
     }

     public FormData(String prefix, String firstName, String middleName, String lastName,
                     String email, String phoneNumber, String roomType, Integer numberOfGuests,
                     Integer numberOfDays, String specialRequest, Integer noOfStars){
         this.prefix = prefix;
         this.firstName = firstName;
         this.middleName = middleName;
         this.lastName =lastName;
         this.email = email;
         this.phoneNumber = phoneNumber;
         this.roomType = roomType;
         this.numberOfGuests = numberOfGuests;
         this.numberOfDays = numberOfDays;
         this.specialRequest =specialRequest;
         this.starRating = noOfStars;
     }
}
