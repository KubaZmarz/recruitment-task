package pojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.HashMap;


public class Methods {
    public static String getAuthToken(String username, String password) {
        JSONObject payload = new JSONObject();
        payload.put("username", username);
        payload.put("password", password);
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("token");
    }

    public static Response getBooking(String token, String bookingId) {
        return RestAssured
                .given()
                .accept(ContentType.JSON)
                .cookie(token)
                .queryParam(bookingId)
                .when()
                .get("/booking");
    }

    public static Response createBooking(String token) { // normally I'd pass a model object with payload, sth like CreateBookingRQ but for those tests I'll hardcode data
        JSONObject payload = new JSONObject();
        payload.put("firstName", "Kuba");
        payload.put("lastName", "Test");
        payload.put("totalprice", 100);
        payload.put("depositpaid", true);
        payload.put("bookingdates", new HashMap<String, String>() {{
            put("checkin", "2013-02-23");
            put("checkout", "2014-10-23");
        }});
        payload.put("additionalneeds", "breakfast");

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .cookie(token)
                .body(payload)
                .post("/booking");
    }

    public static Response updateBooking(String token, String bookingId) {
        JSONObject payload = new JSONObject();
        payload.put("bookingid", bookingId);
        payload.put("firstName", "Test");
        payload.put("totalprice", 200);
        payload.put("additionalneeds", "dinner");

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .cookie(token)
                .body(payload)
                .patch("/booking");
    }

    public static Response deleteBooking(String token, String bookingId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .cookie(token)
                .queryParam(bookingId)
                .delete("/booking");
    }
}
