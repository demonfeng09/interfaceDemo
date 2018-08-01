import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.*;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Created by xushizhao on 17/5/2.
 */
@RunWith(Parameterized.class)
public class parameterizeDemoTest {


    @Parameters
    public static Collection<Object[]> data(){

        Object[][] data = new Object[][]{
                {0,1},{2,3}
        };

        return Arrays.asList(data);
    }

    private int finput;
    private int finput1;

    public parameterizeDemoTest(int finput,int finput1){
        this.finput = finput;
        this.finput1 = finput1;
    }

    @Before
    public void setUp() throws Exception {

        //定义所有请求使用ssl配置
        RestAssured.useRelaxedHTTPSValidation();
        //设置请求参数进行url code编码/设置请求、超时时间
        RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().defaultCharsetForContentType("utf-8","application/x-www-form-urlencoded")).
                httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout",2000).
                        setParam("http.socket.timeout",200));

    }


    @Test
    public void getfunction2() throws Exception {

        System.out.println(finput+"       "+finput1);
        int statuscode = given().param("limit", finput).and().param("offset", finput1).and().param("type", "last_actived")
                .get("https://testerhome.com/api/v3/topics.json").getStatusCode();
        assertEquals(200,statuscode);

    }


}