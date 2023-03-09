import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.Methods;

public class Exercise2 {

    private String token;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        token = "token=" + (Methods.getAuthToken("admin", "password123"));
    }

    @Test
    public void test() {
        Response createBookingRS = Methods.createBooking(token);
        createBookingRS.then().statusCode(HttpStatus.SC_OK).log(); // verify and log

        String jsonBody = createBookingRS.then().extract().body().toString();
        JSONObject json = new JSONObject(jsonBody);
        String id = json.getString("bookingid");

        Methods.getBooking(token, id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log();

        Methods.updateBooking(token, id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log();

        Methods.deleteBooking(token, id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .log();
    }

}
