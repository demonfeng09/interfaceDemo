import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by xushizhao on 2018/7/1.
 */
public class testngTestDemo {

//    @BeforeSuite - 对于套件测试，在此套件中的所有测试运行之前运行。
//    @AfterSuite - 对于套件测试，在此套件中的所有测试运行之后运行。
//
//
//    @BeforeTest - 对于套件测试，在运行属于<test>标签内的类的任何测试方法之前运行。
//    @AfterTest - 对于套件测试，在运行属于<test>标签内的类的所有测试方法都已运行之后运行。
//
//
//    @BeforeGroups：在调用属于该组的第一个测试方法之前运行。
//    @AfterGroups：在调用属于组的最后一个测试方法之后运行。
//
//    @BeforeClass- 在当前类的第一个测试方法之前运行。
//    @AfterClass - 运行当前类中的所有测试方法之后都运行。
//
//    @BeforeMethod - 在每个测试方法之前运行。
//    @AfterMethod - 在每个测试方法之后运行。



//    @DataProvider
//    标志着一个方法，提供数据的一个测试方法。注解的方法必须返回一个Object[] []，其中每个对象[]的测试方法的参数列表中可以分配。
//    该@Test 方法，希望从这个DataProvider的接收数据，需要使用一个dataProvider名称等于这个注解的名字。

//    @Parameters	介绍如何将参数传递给@Test方法。


////    运行junitTestDemo类的时，会执行一次
//    @BeforeClass
//    public void BeforeClass(){
//        System.out.println("BeforeClass被运行");
//    }
//
//    @AfterClass
//    public void AfterClass(){
//        System.out.println("AfterClass被运行");
//    }
//
////    运行每个测试方法进都会被执行到
    @BeforeMethod
    public void BeforeMethod(){
        System.out.println("BeforeMethod被运行");
    }

    @AfterMethod
    public void AfterMethod(){
        System.out.println("AfterMethod被运行");
    }
//
//
//
//    @BeforeTest
//    public void BeforeTest(){
//        System.out.println("BeforeTest被运行");
//    }
//
//    @AfterTest
//    public void AfterTest(){
//        System.out.println("AfterTest被运行");
//    }

//    @BeforeGroups(groups = {"group1"})
//    public void BeforeGroups(){
//        System.out.println("BeforeGroups被运行");
//    }
//
//    @AfterGroups(groups = {"group1"})
//    public void AfterGroups(){
//        System.out.println("AfterGroups被运行");
//    }




    @Test
    public void function2(){

        System.out.println(111);

    }


    @Test
    public void function1(){

        System.out.println(222);

    }


    //将多个测试用例进行分组
    @Test(groups = {"group1"})
    public void function4(){

        System.out.println(444);

    }

    @Test(groups = {"group1"})
    public void function5(){

        System.out.println(555);

    }

    @Test(groups = {"group2"})
    public void function6(){

        System.out.println(666);

    }



//    未完成的用例，不希望被执行
    @Test(enabled = false)
    public void function3(){

        System.out.println(33);

    }



    //依赖测试
    @Test(dependsOnMethods = {"function1","function2"})
    public void function7(){
        System.out.println(7777);
    }

    //参数化
    @Test
    @Parameters("username")
    public void function8(String test1){
        System.out.println("This is " + test1);
    }




    @DataProvider(name="users")
    public Object[][] Users(){
        return new Object[][]{
                {"xxx","123"},
                {"sss", "456"}
        };
    }

    @Test(dataProvider="users")
    public void funciton9(String name, String password){
        System.out.println("name=== "+ name + " password=== "+ password);
    }




}


