import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized;


import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class myrestAssured {
    @Parameterized.Parameters(name = "{index}:baidu search wd={0} except={1}")
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {0,0},{1,1},{1,1},{3,2},{4,3},{5,5},{6,8}
        });
    }
    @Parameterized.Parameter
    public int fInput;

    @Parameterized.Parameter(1)
    public int fExcept;

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

    @Test
    public void baidu2(){
        given()
                .log().all()
                .queryParam("wd",fInput)
                .when()
                .get("http://www.baidu.com/s")
                .then()
                .log().all()
                .statusCode(200)
                .body("html.head.title",equalTo(fExcept+"_百度搜索"));



    }

    @Test
    public void xueqiu(){
        given()
                .log().all()
                .formParam("on","true")
                .header("User-Agent","Xueqiu Android 10.7")
                .header("Cookie","xq_a_token=a70e0dc28e68341df6233aca7877c5a68a920055;u=8661929679")
                .when()
                .post("https://api.xueqiu.com/etc/mobile/state.json")
                .then().log().all()
                .body("success",equalTo(true));

    }


}

