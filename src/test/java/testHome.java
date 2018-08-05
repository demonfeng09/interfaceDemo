import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

public class testHome {

    public static String token;
    @BeforeClass
    public static void beforClass(){

        useRelaxedHTTPSValidation();
        RestAssured.baseURI = "https://testerhome.com";
        RestAssured.proxy("127.0.0.1",8080);
        RestAssured.filters((req,res,ctx)->{
            req.cookie("testerhome","seveniruby");
            return ctx.next(req,res);
        });
    }
    @Test
    public void testGetHtml(){
        given().log().all()
                .when()
                .get("https://testerhome.com/")
                .then().log().all()
                .statusCode(200)
                .body("html.head.title",equalTo("TesterHome"));
    }

    @Test
    public void topics(){

        Response response = given()
                .when()
                .get("/api/v3/topics.json")
                .prettyPeek()
                .then()
                .body("topics[0].id",equalTo(15323))
                .body("topics[-1].user.login",equalTo("arrow"))
                .body("topics.find{it.id == 15388}.title",equalTo("艺龙酒店列表搜索页面，点击地区超链接报 404"))
               .extract().response();

       Integer id1 = response.path("topics.id[0]");
       Integer id2 = response.path("topics[0].id");
       assertEquals(id1,id2);

       token =  response.path("topics[0].title");

    }

    @Test
    public void testXML(){
        given().log().all()
                .proxy("127.0.0.1",8080)
        .when().get("http://127.0.0.1:8000/demo.xml").prettyPeek()
                .then()
                .body("shopping.category.size()",equalTo(3))
                .body("shopping.category.find { it.@type == 'supplies'}.size()",equalTo(1))
                .body("shopping.category.find { it.@type == 'supplies'}.item.name[0]",equalTo("Paper"))
                .time(lessThan(500L))
        ;
    }

    @Test
    public void testExcellent(){
        given()
        .when()
             .get("https://testerhome.com/api/v3/topics.json")
        .then().log().all()
            .body("topics.find{it.excellent == 1}.size()",greaterThanOrEqualTo(4))
        ;
    }

    @Test
    public void testBaidu(){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("x",1);
        data.put("y","xxxx");

        given()
                .contentType(ContentType.JSON).body(data)
                .when()
                .post("http://www.baidu.com/s")
                .then()
                .statusCode(200);
    }

    @Test
    public void testBase64(){
        given()
                .auth().basic("hogwarts","123456")//输入用户名密码进行安全认证
                .log().all()
                .filter((req,res,ctx)->{
                    Response responseOri = ctx.next(req,res);//获取response得返回值
                    ResponseBuilder responseBuilder = new ResponseBuilder().clone(responseOri);//克隆原始得加密过得response到新的responseBuilder
                    responseBuilder.setBody(Base64.getDecoder().decode(responseOri.getBody().asString().trim()));//用base64进行解密，并把解密结果放到responseBuilder的body里
                    responseBuilder.setContentType(ContentType.JSON);
                    return responseBuilder.build();
                })
        .when().get("http://XX/sec.json")
        .then().log().all()
                .statusCode(200);
    }
}
