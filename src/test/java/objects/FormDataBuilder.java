package objects;

import java.time.LocalDateTime;
import java.util.Date;

public class FormDataBuilder {
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
    public Date departureDate;
    public String fileToUpload;


    public FormDataBuilder setPrefix (String prefix) {
        this.prefix = prefix;
        return this;
    }

    public FormDataBuilder setFirstName (String firstName) {
        this.firstName = firstName;
        return this;
    }

    public FormDataBuilder setLastName (String lastName) {
        this.lastName = lastName;
        return this;
    }

    public FormDataBuilder setNumberOfGuests (Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
        return this;
    }


    public FormDataBuilder setNumberOfDays (Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
        return this;
    }

    public FormDataBuilder setSpecialRequest (String specialRequest) {
        this.specialRequest = specialRequest;
        return this;
    }

    public FormDataBuilder setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public FormDataBuilder setArrivalDate (String arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public FormDataBuilder setFileToUpload (String filePath) {
        this.fileToUpload = filePath;
        return this;
    }

    public FormData build () {
        return new FormData(prefix, firstName, lastName, numberOfGuests, numberOfDays, specialRequest, arrivalDate, phoneNumber, fileToUpload);
    }

}
