package inherit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Base {
    @BeforeClass
    public static void beforClass(){
        System.out.println("Base beforClass");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("Base afterClass");
    }

    @Before
    public void setUp(){
        System.out.println("setUp");
    }

    @Test
    public void demo1(){
        System.out.println("demo1");
    }

    @Test
    public void demo2(){
        System.out.println("demo2");
    }
}
