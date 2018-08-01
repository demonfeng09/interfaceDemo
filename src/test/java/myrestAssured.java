import org.junit.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class myrestAssured {

    @Test
    public void baidu(){
        Response response = given()
                .log().all()
                .queryParam("wd","mp3")
        .when()
                .get("http://www.baidu.com/s");
        int statusCode = response.statusCode();
        Assert.assertEquals(200,statusCode);
        String bodyword = response.getBody().htmlPath().getString("html.head.title");
        Assert.assertEquals("mp3_百度搜索",bodyword);
    }
}

