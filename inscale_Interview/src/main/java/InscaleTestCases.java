import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;


public class InscaleTestCases extends BaseClass {

    @Test
    public void Q1() throws Exception {
        driver.get(baseURL);
        Q1Page question1Page = new Q1Page(driver);
        question1Page.managerLogin();
        question1Page.clickAddCust_Btn();
        question1Page.addCustomer();
        question1Page.verifyCustomer();
        question1Page.deleteCustomers();
    }

    @Test
    public void Q2() throws Exception {
        driver.get(baseURL);
        Q2Page question2Page = new Q2Page(driver);
        question2Page.customerLogin();
        question2Page.selectAccount();
        question2Page.depositMoney("50000");
        question2Page.withdrawMoney("3000");
        question2Page.withdrawMoney("2000");
        question2Page.depositMoney("5000");
        question2Page.withdrawMoney("10000");
        question2Page.withdrawMoney("15000");
        question2Page.depositMoney("1500");
    }
}
