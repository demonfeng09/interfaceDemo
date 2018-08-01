import org.testng.annotations.*;

public class myTest {

    @BeforeClass
    public void BeforClass(){
        System.out.println("BeforeClass被运行");
    }

    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass被运行");
    }

//    @BeforeMethod
//    public void BeforeMethod(){
//        System.out.println("BeforeMethod执行");
//    }
//
//    @AfterMethod
//    public void AfterMethod(){
//        System.out.println("AfterMethod执行");
//    }
    @Test(groups = {"group1"})
    public void abc(){
        System.out.println("这里是abc");
    }

    @Test(groups = {"group1"})
    public void abc1(){
        System.out.println("这里是abc1");
    }

    @Test(groups = {"group2"})
    public void unfinish(){
        System.out.println("没有完成的用例");
    }

    @Test
    @Parameters("pwd")
    public void function1(String pwd){
        System.out.println("pwd is "+pwd);
    }
}
