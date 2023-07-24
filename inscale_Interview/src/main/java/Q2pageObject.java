import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Q2pageObject extends BaseClass{

    WebDriver ldriver;
    public Q2pageObject(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }
    @FindBy(xpath = "//button[normalize-space()='Customer Login']")
    WebElement customerLogin;

    @FindBy(xpath = "//select[@id='userSelect']")
    WebElement selectUser;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//select[@id='accountSelect']")
    WebElement selectAcc;

    @FindBy(xpath = "//button[normalize-space()='Deposit']")
    WebElement depositButton;

    @FindBy(xpath = "//span[normalize-space()='Deposit Successful']")
    WebElement depositMessage;

    @FindBy(xpath = "//input[@placeholder='amount']")
    WebElement amount;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement depositBtn;

    @FindBy(xpath = "//button[normalize-space()='Withdrawl']")
    WebElement withdrawalButton;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement confirmWithdrawal;
}
