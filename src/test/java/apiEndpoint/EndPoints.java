package apiEndpoint;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import modals.request.CreateUserRequest;

public class EndPoints {
    Response response;
    RequestSpecification requestSpecification;

    public EndPoints(String baseUrl) {
        RestAssured.baseURI = baseUrl;
    }

    public Response createUser(CreateUserRequest createUserRequest,String token) {
        requestSpecification = RestAssured.given()
                .when()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("organisation_id", "0016C00000akx03QAA");
        response = requestSpecification.body(createUserRequest).put("/admin/CreateOrganizationUser");
        return response;
    }
}
