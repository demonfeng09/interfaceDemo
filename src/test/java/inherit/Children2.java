package inherit;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Children2 extends Base {

    @BeforeClass
    public static void beforeChildren2Class(){
        System.out.println("Children2 beforeClass");
    }

    @AfterClass
    public static void afterChildren2Class(){
        System.out.println("Children2 afterClass");
    }

    @Before
    public void setupChildren2(){
        System.out.println("setUp Children2");
    }

    @Test
    @Category(Stage.class)
    public void demo2Children02(){
        System.out.println("demo2Children02");
    }

    @Test
    public void demo2Children01(){
        assertThat(1,equalTo(2));
        System.out.println("demo2Children01");
    }
}
