package webpages;

import objects.FormData;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class InputPage {
    private final WebDriver driver;
    private static String PAGE_URL = "https://tinyurl.com/avl-qa-zadatak";

    @FindBy(id = "prefix_16")
    private WebElement prefix;
    @FindBy(id = "first_16")
    private WebElement firstName;
    @FindBy(id = "middle_16")
    private WebElement middleName;
    @FindBy(id = "last_16")
    private WebElement lastName;
    @FindBy(xpath = "//div[@id='cid_16']/div[@class='form-error-message']")
    private WebElement nameError;

    @FindBy(id = "input_105")
    private WebElement numberOfDays;
    @FindBy(xpath = "//div[@id='cid_105']/div[@class='form-error-message']")
    private WebElement numberOfDaysError;

    @FindBy(id = "input_18")
    private WebElement numberOfGuests;
    @FindBy(xpath = "//div[@id='cid_18']/div[@class='form-error-message']")
    private WebElement numberOfGuestsError;

    @FindBy(id = "lite_mode_22")
    private WebElement arrivalDate;
    @FindBy(xpath = "//div[@id='cid_22']/div[@class='form-error-message']")
    private WebElement arrivalDateError;

    @FindBy(id = "input_104_full")
    private WebElement phoneNumber;
    @FindBy(xpath = "//div[@id='cid_104']/div[@class='form-error-message']")
    private WebElement phoneNumberError;

    @FindBy(id = "input_109")
    private WebElement email;
    @FindBy(id = "input_6")
    private WebElement roomType;
    @FindBy(id = "input_2")
    private WebElement submitButton;
    @FindBy(className = "nicEdit-main")
    private WebElement specialRequest;

    @FindBy(id = "input_112")
    private WebElement fileUpload;
    @FindBy(className = "qq-upload-success")
    private WebElement successfulUpload;

    @FindBy(css = ".form-textbox.Stars")
    private WebElement starRating;

    @FindBy(className = "thankyou")
    private WebElement successMessage;
    @FindBy(className = "thankyou-sub-text")
    private WebElement submissionReceivedMessage;

    public InputPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public Boolean isSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    public void selectRoomType(String type) {
        Select objSelect = new Select(roomType);
        objSelect.selectByValue(type);
    }

    public void rateWithStars(Integer noOfStars) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value=arguments[1];", starRating, noOfStars.toString());
    }

    public Boolean verifyFileUpload(String fileName) {
        return successfulUpload.isDisplayed() && Objects.equals(successfulUpload.getAttribute("actual-filename"), fileName);
    }

    public void clickSubmitButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public String getSubmissionReceivedMessageText() {
        return submissionReceivedMessage.getText();
    }

    public void fillForm(FormData input) {
        if (input.prefix != null) prefix.sendKeys(input.prefix);
        if (input.firstName != null) firstName.sendKeys(input.firstName);
        if (input.middleName != null) middleName.sendKeys(input.middleName);
        if (input.lastName != null) lastName.sendKeys(input.lastName);
        if (input.email != null) email.sendKeys(input.email);
        if (input.phoneNumber != null) phoneNumber.sendKeys(input.phoneNumber);
        if (input.roomType != null) selectRoomType(input.roomType);
        if (input.starRating != null) rateWithStars(input.starRating);
        if (input.numberOfDays != null) numberOfDays.sendKeys(input.numberOfDays.toString());

        if (input.arrivalDate != null) {
            arrivalDate.clear();
            arrivalDate.sendKeys(input.arrivalDate);
        }

        numberOfGuests.clear();
        if (input.numberOfGuests != null) {
            numberOfGuests.sendKeys(input.numberOfGuests.toString());
        }

        specialRequest.clear();
        if (input.specialRequest != null) specialRequest.sendKeys(input.specialRequest);

        if (input.fileToUpload != null) fileUpload.sendKeys(input.fileToUpload);
    }

    public Boolean isSpecificErrorMessageDisplayed(String requiredField) {
        return switch (requiredField) {
            case "firstName", "lastName" -> nameError.isDisplayed();
            case "numberOfDays" -> numberOfDaysError.isDisplayed();
            case "numberOfGuests" -> numberOfGuestsError.isDisplayed();
            case "arrivalDate" -> arrivalDateError.isDisplayed();
            case "phoneNumber" -> phoneNumberError.isDisplayed();
            default -> false;
        };
    }

    public String getErrorMessageText(String field) {
        return switch (field) {
            case "firstName", "lastName" -> nameError.getText();
            case "numberOfDays" -> numberOfDaysError.getText();
            case "numberOfGuests" -> numberOfGuestsError.getText();
            case "arrivalDate" -> arrivalDateError.getText();
            case "phoneNumber" -> phoneNumberError.getText();
            default -> "";
        };
    }
}