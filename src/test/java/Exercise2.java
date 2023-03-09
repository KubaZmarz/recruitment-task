import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.Methods;

import java.io.FileInputStream;
import java.util.Properties;

public class Exercise2 {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    public void test() {
        Methods.getAuthToken("admin", "password123");
    }

}
