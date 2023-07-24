import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Q1Page extends BaseClass {

    WebDriver ldriver;

    public Q1Page(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    Q1pageObject q1pageObject = new Q1pageObject(driver);

    public void managerLogin() {
        wait.until(ExpectedConditions.visibilityOf(q1pageObject.bankManagerLogin_Btn));
        q1pageObject.bankManagerLogin_Btn.click();
        System.out.println("-----Manager Login------");
    }

    public void clickAddCust_Btn() {
        wait.until(ExpectedConditions.visibilityOf(q1pageObject.addCustomer_Btn));
        q1pageObject.addCustomer_Btn.click();
        System.out.println("----Adding Customer----");
    }

    public void addCustomer() {
        wait.until(ExpectedConditions.visibilityOf(q1pageObject.firstName));
        for (CustomerData input : CustomerData.inputCust()) {
            wait.until(ExpectedConditions.elementToBeClickable(q1pageObject.firstName));
            q1pageObject.firstName.sendKeys(input.getFirstName());
            q1pageObject.lastName.sendKeys(input.getLastName());
            q1pageObject.postCode.sendKeys(input.getPostCode());

            q1pageObject.clickAddCustomer.click();

            wait.until(ExpectedConditions.alertIsPresent());
            org.openqa.selenium.Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        System.out.println("-----All customer added------");

    }

    public String getColumnData(int row, int column) {
        return driver.findElement(By.xpath("//table/tbody/tr[" + row + "]/td[" + column + "]")).getText();
    }

    public void verifyCustomer() {
        wait.until(ExpectedConditions.elementToBeClickable(q1pageObject.customersBtn));
        q1pageObject.customersBtn.click();
        verifyCustomerInserted();
    }

    private List<CustomerData> listRegisteredCust() {
        List<CustomerData> listCustomer = new ArrayList<>();
        List<WebElement> listOfRows = driver.findElements(q1pageObject.listOfRows);
        for (int row = 1; row <= listOfRows.size(); row++) {
            List<WebElement> listOfColumns = driver.findElements(By.xpath("//table/tbody/tr[" + row + "]"));
            if (listOfColumns.size() > 0) {
                for (int column = 1; column < 4; column++) {
                    listCustomer.add(new CustomerData(getColumnData(row, 1), getColumnData(row, 2), getColumnData(row, 3)));
                }
            }
        }
        return listCustomer;
    }

    public boolean verifyCustomerInserted() {
        boolean present = false;
        for (CustomerData cust : CustomerData.inputCust()) {
            List<CustomerData> listCustomer = listRegisteredCust();
            present = listCustomer.stream().filter(i -> i.getFirstName().equals(cust.getFirstName()) && i.getLastName().equals(cust.getLastName())
                    && i.getPostCode().equals(cust.getPostCode())).findFirst().isPresent();

            if (present) {
                System.out.println("--------------------------------------------------");
                System.out.println("Customers added succesfully");
            }
        }
        return present;
    }

    public void deleteCustomers() throws Exception {
        String firstName;
        String lastName;
        String postCode;
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(q1pageObject.listOfRows));
        List<WebElement> listOfRows = driver.findElements(q1pageObject.listOfRows);

        for (CustomerData cust : CustomerData.deleteCust()) {
            for (int row = 1; row <= listOfRows.size(); row++) {
                int colmn = 1;
                firstName = driver.findElement(By.xpath("//table/tbody/tr[" + row + "]/td[" + colmn + "]")).getText();
                lastName = driver.findElement(By.xpath("//table/tbody/tr[" + row + "]/td[" + (colmn + 1) + "]")).getText();
                postCode = driver.findElement(By.xpath("//table/tbody/tr[" + row + "]/td[" + (colmn + 2) + "]")).getText();

                //if data in row matches
                if (cust.getFirstName().equalsIgnoreCase(firstName) && cust.getLastName().equalsIgnoreCase(lastName) && cust.getPostCode().equalsIgnoreCase(postCode)) {
                    WebElement deleteButton = driver.findElement(By.xpath("//*[contains(@class,'ng-scope')]//table/tbody/tr[" + row + "]/td[5]/button[text()='Delete']"));
                    wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
                    row = listOfRows.size() + 1;
                }
            }
        }
        System.out.println("----------Customers deleted succesfully---------");

    }

}
