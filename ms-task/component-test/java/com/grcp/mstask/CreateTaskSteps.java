package com.grcp.mstask;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class CreateTaskSteps extends ApplicationTestContext {

    private static final String EMPTY = "empty";

    private String api;
    private Response response;
    private ValidatableResponse validatableResponse;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @Given("the api {string}")
    public void the_url(String api) {
        this.api = api;
    }

    @When("the client sends a post request and body {string}")
    public void the_client_sends_post_request(String body) throws URISyntaxException, IOException {
        var url = getClass().getClassLoader().getResource(String.format("%s.json", body));
        var bytes = Files.readAllBytes(Paths.get(url.toURI()));
        this.response = given().contentType(ContentType.JSON)
                .when().body(new String(bytes)).post(this.api);
    }

    @Then("the client should receive the status code of {int}")
    public void the_client_receives_status_code_of(int statusCode) {
        this.validatableResponse = this.response.then().statusCode(statusCode);
    }

    @And("the client should receive response body equal to {string}")
    public void the_response_body_equals_to(String body) {
        this.validatableResponse.body(org.hamcrest.Matchers.emptyOrNullString());
    }
}
