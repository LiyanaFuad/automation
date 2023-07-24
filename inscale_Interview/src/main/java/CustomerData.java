import java.util.ArrayList;
import java.util.List;

public class CustomerData {
    private String firstName;
    private String lastName;
    private String postCode;

    public CustomerData(String firstName, String lastName, String postCode) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
    }

    public static List<CustomerData> inputCust() {
        List<CustomerData> listCustomers = new ArrayList<>();
        listCustomers.add(new CustomerData("Christopher", "Connely", "L789C349"));
        listCustomers.add(new CustomerData("Frank", "Christopher", "A897N450"));
        listCustomers.add(new CustomerData("Christopher", "Minka", "M098Q585"));
        listCustomers.add(new CustomerData("Connely", "Jackson", "L789C349"));
        listCustomers.add(new CustomerData("Jackson", "Frank", "L789C349"));
        listCustomers.add(new CustomerData("Minka", "Jackson", "A897N450"));
        listCustomers.add(new CustomerData("Jackson", "Connely", "L789C349"));
        return listCustomers;

    }

    public static List<CustomerData> deleteCust() {
        List<CustomerData> listDeleteCust = new ArrayList<>();
        listDeleteCust.add(new CustomerData("Jackson", "Frank", "L789C349"));
        listDeleteCust.add(new CustomerData("Christopher", "Connely", "L789C349"));
        return listDeleteCust;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostCode() {
        return postCode;
    }
}
