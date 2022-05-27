package stepDefinitions;

import apiEndpoint.EndPoints;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import modals.request.CreateUserRequest;
import modals.request.DeleteRequest;
import modals.response.CreateUserResponse;
import modals.response.GetUsersResponse;
import modals.response.User;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Steps {
    private String firstName;
    private String lastName;
    private String email;
    private String token = "00eab14b-5855-36c4-bf5a-9f263116d979";
    private final String baseUrl = "https://gateway.qa.altusplatform.com/portal-QA/1.0.0";
    private String createdUserEmail;


    Response response;
    RequestSpecification requestSpecification;


    @Given(": I am an admin user")
    public void i_am_an_admin_user() {

    }

    @When(": I add a firstName {string}")
    public void i_add_a_first_name(String firstName) {
        this.firstName = firstName;
        System.out.println("The firstName is " + firstName);
    }

    @When(": I add a lastName {string}")
    public void i_add_a_last_name(String lastName) {
        this.lastName = lastName;
    }

    @When(": I add an email {string}")
    public void i_add_an_email(String email) {
        this.email = email;
    }

    @When(": Send a PUT HTTP request")
    public void i_calls_post_call_to_create_a_user() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setFirstName(firstName);
        createUserRequest.setLastName(lastName);
        createUserRequest.setEmail(email);
        EndPoints endPoints = new EndPoints(baseUrl);
        response = endPoints.createUser(createUserRequest, token);
        System.out.println("The response is " + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode());


    }

    @Then(": New user is created in the application")
    public void new_user_is_created_in_the_application() {
        CreateUserResponse createUserResponse = response.getBody().as(CreateUserResponse.class);
        System.out.println("the response is " + createUserResponse.getIsSuccess());
        Assert.assertEquals("true", createUserResponse.getIsSuccess());
        JsonPath jsonPath=response.jsonPath();
        jsonPath.get("accountId");
        System.out.println("the accountid is" +jsonPath.get("accountId"));
    }

    @Then(": New user is not created in the application")
    public void new_user_is_not_created_in_the_application() {
        CreateUserResponse createUserResponse = response.getBody().as(CreateUserResponse.class);
        System.out.println("The response for negative case is" + createUserResponse.getIsSuccess());
        Assert.assertEquals("false", createUserResponse.getIsSuccess());
    }

    @When(": Send a GET HTTP request")
    public void send_a_get_http_request() {
        requestSpecification = RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("organisation_id", "0016C00000akx03QAA");
        response = requestSpecification.get("/admin/GetAllUsers");
        System.out.println("the status code for get users is " + response.getStatusCode());
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then(": I get all the user records for a organisation id.")
    public void i_get_all_the_user_records_for_a_organisation_id() {
        GetUsersResponse getUsersResponse = response.getBody().as(GetUsersResponse.class);
        Assert.assertEquals(false, getUsersResponse.getOrganizationUser().isEmpty());
        User organizationUser = getUsersResponse.getOrganizationUser().get(1);
        System.out.println("the email is " + organizationUser.getEmail());
        Assert.assertEquals("santosh.devershetty+allproducts08232021@argusexpresstest.com", getUsersResponse.getOrganizationUser().get(0).getEmail());
    }

    @Given(": I am an admin user with hamcrest")
    public void i_am_an_admin_user_with_hamcrest() {

    }

    @When(": Send a GET HTTP request with hamcrest")
    public void send_a_get_http_request_with_hamcrest() {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("organisation_id", "0016C00000akx03QAA")
                .get("/admin/GetAllUsers")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Then(": I get all the user records for a organisation id with hamcrest.")
    public void i_get_all_the_user_records_for_a_organisation_id_with_hamcrest() {
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("organisation_id", "0016C00000akx03QAA")
                .get("/admin/GetAllUsers")
                .then()
                .assertThat()
                .body("organizationUser[0].firstName", equalTo("santosh.devershetty"));
    }

    @Given(":I am an admin user to delete user")
    public void i_am_an_admin_user_to_delete_user() {

    }

    @When(":I send put call for deletion")
    public void i_send_put_call_for_deletion() {
//        DeleteRequest deleteRequest = new DeleteRequest();
//        deleteRequest.setEmail(email);
        JSONObject jsonObject=new JSONObject ();
        jsonObject.put("email","harleen@gmail.com");
        RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("organisation_id", "0016C00000akx03QAA")
                .body(jsonObject)
                .put("/admin/DeleteOrganizationUser")
                .then()
                .assertThat()
                .body("isSuccess", equalTo(true));

    }

    @Then(":the user is successfully deleted from the application.")
    public void the_user_is_successfully_deleted_from_the_application() {

    }


}
