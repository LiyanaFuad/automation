import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Q1pageObject extends BaseClass{

    WebDriver ldriver;
    public Q1pageObject(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }
 
    @FindBy(xpath = "//button[normalize-space()='Bank Manager Login']")
    WebElement bankManagerLogin_Btn;

    @FindBy(xpath = "//button[normalize-space()='Add Customer']")
    WebElement addCustomer_Btn;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    WebElement postCode;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement clickAddCustomer;

    @FindBy(xpath = "//button[normalize-space()='Customers']")
    WebElement customersBtn;

    @FindBy(xpath = "//input[@placeholder='Search Customer']")
    WebElement searchCustomer;

    By listOfRows = By.xpath("//table/tbody/tr");

}
