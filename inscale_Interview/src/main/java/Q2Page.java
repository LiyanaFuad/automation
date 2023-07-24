import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Q2Page extends BaseClass{
    WebDriver ldriver;

    public Q2Page(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Q2pageObject q2pageObject = new Q2pageObject(driver);

    public void customerLogin(){
        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.customerLogin)).click();
        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.selectUser)).click();
        Select user = new Select(driver.findElement(By.xpath("//select[@id='userSelect']")));
        user.selectByVisibleText("Hermoine Granger");
        wait.until((ExpectedConditions.visibilityOf(q2pageObject.loginButton)));
        q2pageObject.loginButton.click();
    }

    public void selectAccount(){
        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.selectAcc)).click();
        Select account = new Select(driver.findElement(By.xpath("//select[@id='accountSelect']")));
        account.selectByVisibleText("1003");
    }

    public void depositMoney(String setCreditAmount){
        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.depositButton)).click();

        WebElement balance = (WebElement) driver.findElements(By.cssSelector("strong[class=ng-binding]")).get(1);
        String balance_Str = balance.getText();
        Integer initialBalance = Integer.parseInt(balance_Str);
        System.out.println("Initial balance =  " + initialBalance);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Deposit']")));
        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.amount)).sendKeys(setCreditAmount);
        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.depositBtn)).click();
        if(q2pageObject.depositMessage.getText().equalsIgnoreCase("Deposit Successful")){
            assert true;
        }
        else{
            assert false;
        }

        WebElement newBalance = (WebElement) driver.findElements(By.cssSelector("strong[class=ng-binding]")).get(1);
        String newBalance_Str = newBalance.getText();
        Integer newBalance_Int = Integer.parseInt(newBalance_Str);
        System.out.println("Credit amount =  " + setCreditAmount);
        Integer creditAmount = Integer.parseInt(setCreditAmount);

        try{
            Integer finalBalance = initialBalance + creditAmount;

            System.out.println("Current account balance is: " + finalBalance);

            if(newBalance_Int.intValue() == finalBalance.intValue()){
                System.out.println("Transaction verified");
                assert true;
            }
            else{
                System.out.println("Transaction failed. Calculation does not tally.");
                assert false;
            }
        } catch(NumberFormatException e){

        }
    }

    public void withdrawMoney(String setDebitAmount){
        WebElement balance = driver.findElements(By.cssSelector("strong[class=ng-binding]")).get(1);
        String balance_Str = balance.getText();
        Integer initialBalance = Integer.parseInt(balance_Str);
        System.out.println("Initial balance  =  " + initialBalance);

        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.withdrawalButton)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Withdraw']")));
        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.amount)).sendKeys(setDebitAmount);
        wait.until(ExpectedConditions.elementToBeClickable(q2pageObject.confirmWithdrawal)).click();

        WebElement newBalance = (WebElement) driver.findElements(By.cssSelector("strong[class=ng-binding]")).get(1);
        String newBalance_Str = newBalance.getText();
        Integer newBalance_Int = Integer.parseInt(newBalance_Str);
        System.out.println("Debit amount   = " + setDebitAmount);
        Integer debitAmount = Integer.parseInt(setDebitAmount);

        try{
            Integer finalBalance = initialBalance - debitAmount;

            System.out.println("Current account balance is: " + finalBalance);

            if(newBalance_Int.intValue() == finalBalance.intValue()){
                System.out.println("Transaction verified");
                assert true;
            }
            else{
                System.out.println("Transaction failed. Calculation does not tally.");
                System.out.println(newBalance_Int.intValue() +" vs "+ finalBalance.intValue());
                assert false;
            }
        } catch(NumberFormatException e){

        }
    }
}
