package testcases;

import objects.FormData;
import objects.FormDataBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import webpages.InputPage;
import org.testng.annotations.Test;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class HotelDayQATest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(dataProvider = "negativeFormData")
    public void fillDataNegativeTests(FormData inputData, String requiredField, String errorMessage) {
        InputPage hotelInputPage = new InputPage(driver);
        hotelInputPage.fillForm(inputData);
        hotelInputPage.clickSubmitButton();
        Assert.assertTrue(hotelInputPage.isSpecificErrorMessageDisplayed(requiredField));
        Assert.assertEquals(hotelInputPage.getErrorMessageText(requiredField), errorMessage);
    }

    @Test(dataProvider = "positiveFormData")
    public void fillDataPositiveTests(FormData inputData) {
        InputPage hotelInputPage = new InputPage(driver);
        hotelInputPage.fillForm(inputData);
        hotelInputPage.clickSubmitButton();
        Assert.assertTrue(hotelInputPage.isSuccessMessageDisplayed());
        Assert.assertEquals(hotelInputPage.getSubmissionReceivedMessageText(), SUCCESS_MESSAGE);
    }

    @Test
    public void uploadFileTest() {
        InputPage hotelInputPage = new InputPage(driver);
        hotelInputPage.fillForm(new FormDataBuilder().setFirstName(NAME).setLastName(SURNAME).setNumberOfGuests(2).setNumberOfDays(1).setFileToUpload(FILE_PATH).build());
        Assert.assertTrue(hotelInputPage.verifyFileUpload(FILE_NAME));
        hotelInputPage.clickSubmitButton();
        Assert.assertTrue(hotelInputPage.isSuccessMessageDisplayed());
        Assert.assertEquals(hotelInputPage.getSubmissionReceivedMessageText(), SUCCESS_MESSAGE);
    }

    @DataProvider
    public Object[][] negativeFormData() {
        return new Object[][]{
                {new FormDataBuilder().setLastName(SURNAME).setNumberOfGuests(1).setNumberOfDays(1).build(), "firstName", REQUIRED_ERROR},
                {new FormDataBuilder().setFirstName(NAME).setNumberOfGuests(1).setNumberOfDays(1).build(), "lastName", REQUIRED_ERROR},
                {new FormDataBuilder().setFirstName(NAME).setLastName(SURNAME).setNumberOfGuests(5).build(), "numberOfDays", REQUIRED_ERROR},
                {new FormDataBuilder().setFirstName(NAME).setLastName(SURNAME).setNumberOfDays(3).build(), "numberOfGuests", REQUIRED_ERROR},
                {new FormDataBuilder().setFirstName(NAME).setLastName(SURNAME).setNumberOfDays(1).setNumberOfGuests(1).setPhoneNumber("123456").build(), "phoneNumber", FORMAT_ERROR},
                {new FormDataBuilder().setFirstName(NAME).setLastName(SURNAME).setNumberOfGuests(2).setNumberOfDays(1).
                        setArrivalDate(LocalDate.now().plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.WEDNESDAY)).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))).build(), "arrivalDate", DATE_ERROR}
        };
    }

    @DataProvider
    public Object[][] positiveFormData() {
        return new Object[][]{
                {new FormDataBuilder().setFirstName(NAME).setLastName(SURNAME).setNumberOfGuests(1).setNumberOfDays(1).build()},
                {new FormDataBuilder().setFirstName(NAME).setLastName(SURNAME).setNumberOfDays(3).setNumberOfGuests(2).setPhoneNumber("3851234567").build()},
                {new FormDataBuilder().setFirstName(NAME).setLastName(SURNAME).setNumberOfGuests(4).setNumberOfDays(1).setArrivalDate(LocalDate.now().plusMonths(1).with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.MONDAY)).format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))).build()},
                {new FormData("Miss", NAME, MIDDLENAME, SURNAME, "email@test.com", "3851234567", "Solo Room (1 Person)", 1, 2, "My Test Request", 3)}
        };
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }

    static String NAME = "TestFirstName";
    static String SURNAME = "TestLastName";
    static String MIDDLENAME = "TestMiddleName";
    static String REQUIRED_ERROR = "This field is required.";
    static String FORMAT_ERROR = "Field value must fill mask.";
    static String DATE_ERROR = "This date is unavailable.";
    static String FILE_NAME = "Minnesota_fake_id_card_driver_license.jpg";
    static String FILE_PATH = System.getProperty("user.dir") + "/src/test/java/testfiles/" + FILE_NAME;
    static String SUCCESS_MESSAGE = "Your submission has been received.";
}