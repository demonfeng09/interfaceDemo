import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class restAssuredDemo {



    @BeforeClass
    public void setUp() throws Exception {

        //定义所有请求使用ssl配置
        RestAssured.useRelaxedHTTPSValidation();
        //设置请求参数进行url code编码/设置请求、超时时间
        RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().
                defaultCharsetForContentType("utf-8","application/x-www-form-urlencoded")).
                httpClient(HttpClientConfig.httpClientConfig().setParam("http.connection.timeout",2000).
                        setParam("http.socket.timeout",2000));

    }

    @Test
    public void getfunction1() throws Exception {

        //结构验证
        get("https://testerhome.com/api/v3/topics.json?limit=1&offset=0&type=last_actived")
                .then().assertThat().body(matchesJsonSchemaInClasspath("topics.json"));






        Response response = get("https://testerhome.com/api/v3/topics.json?limit=1&offset=0&type=last_actived");

        int statuscode = response.statusCode();

        Assert.assertEquals(200,statuscode,"接口是否正常请求，打开正常");

        String title = response.jsonPath().getString("topics[0].title");

        Assert.assertEquals("愿你被世界温柔对待",title,"title对比");

        int topicsize = response.jsonPath().getList("topics").size();


        //提前构造
        List list = new ArrayList();
            list.add("愿你被");
            list.add("第四届");
            list.add("试之道");




        for(int i=0;i<topicsize;i++){

            Assert.assertEquals(
                    list.get(i), response.jsonPath().getString("topics["+i+"].title"));

        }








    }

    @Test
    public void getfunction2() throws Exception {


//        int statuscode =




                Response response = given().param("limit", 2).and().param("offset", 0).and()
                        .param("type", "last_actived")
                .post("https://testerhome.com/api/v3/topics.json");

               Assert.assertEquals(200,response.statusCode());

    }

    @Test
    public void getfunction3() throws Exception {


        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("limit", 2);
        parameters.put("offset", 0);
        parameters.put("type", "last_actived");

        int statuscode = given().params(parameters).post("https://testerhome.com/api/v3/topics.json").getStatusCode();

    }


    @Test
    public void postfunction1() throws Exception {


        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("limit", 2);
        parameters.put("offset", 0);
        parameters.put("type", "last_actived");

        String abcd = given().params(parameters).get("https://testerhome.com/api/v3/topics.json").jsonPath().getString("");


        int statuscode = given().param("grant_type", abcd).and().param("username", "apitest@apitest.com")
                .and().param("password", "111111")
                .post("https://testerhome.com/oauth/token").getStatusCode();

    }

    @Test
    public void postfunction2() throws Exception {




        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("grant_type", "password");
        parameters.put("username", "apitest@apitest.com");
        parameters.put("password", "111111");

        int statuscode = given().params(parameters).post("https://testerhome.com/oauth/token").getStatusCode();



        given().params(parameters).post("https://testerhome.com/oauth/token").prettyPeek();



    }


    @Test
    public void checkValue() throws Exception {


        //服务端开发的接口
        Response response  = get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived");
        //从第三方得到的原始数据
        Response response1  = get("https://testerhome.com/api/v3/topics.json?limit=2&offset=0&type=last_actived");

        List<String> topics = response.jsonPath().getList("topics");

        //第三方结构
        List<String> topics1 = response1.jsonPath().getList("top");

        System.out.print(topics.size()==topics1.size());


        for(int i=0;i<topics.size();i++){

            System.out.println(response.jsonPath().get("topics["+i+"].user.id"));
//            topics[0].user.id
//            topics[1].user.id
            //期望值 ，是需要从数据库、或第三方来源得到




        }

//        select count(*) from adb
//         System.out.println(response.jsonPath().get("topics[0].id"));

//        //判断收藏列表是否为5条数据（其中5为预期值 ）
//        assertEquals(5,topics.size());
//
//
//
//        //查询用户与id是否与预期值的一致(如果数据来源于数据库，可以直接读取然后进行循环判断)
//        List<Integer> ids = new ArrayList<Integer>();
//        ids.add(8434);
//        ids.add(8397);
//        ids.add(8298);
//        ids.add(8467);
//        ids.add(8432);
//
//        assertEquals(ids,response.jsonPath().getList("topics.id"));
//
//
//        //已知第一条记录的id为8434，判断topic下第一条记录的id是否相等
//        assertEquals(8434,response.jsonPath().getInt("topics[0].id"));
//
//
//        //已经接口业务逻辑 node_id = 13 的时候 node_name 为"社区管理"
//        for(int i=0;i<topics.size();i++){
//
//            if(response.jsonPath().getInt("topics["+i+"].node_id") == 13){
//
//                assertEquals("社区管理",response.jsonPath().getString("topics["+i+"].node_name"));
//            }
//
//        }
//
//
//        //已知node_name为社区管理的帖子是2篇，判断返回的响应数据中是否为2篇
//
//        int nodenames = response.jsonPath().getList("topics.findAll{topics -> topics.node_name == '社区管理'}").size();
//        assertEquals(2,nodenames);



    }


    @Test
    public void jsonscheme(){

        get("https://testerhome.com/api/v3/topics.json?limit=1&offset=0&type=last_actived")
                .then().assertThat().body(matchesJsonSchemaInClasspath("topics.json"));



    }

}