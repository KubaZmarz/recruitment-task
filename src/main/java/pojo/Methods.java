package pojo;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import io.restassured.RestAssured;


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

    public static void createBooking() {
        JSONObject payload = new JSONObject();
        payload.put("firstName", "Test");
        payload.put("lastName", "Test");

    }
}
