package inherit;

import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Children  extends Base {

    @BeforeClass
    public static void beforeChildrenClass(){
        System.out.println("Children beforeClass");
    }

    @AfterClass
    public static void afterChildrenClass(){
        System.out.println("Children afterClass");
    }

    @Before
    public void setupChildren(){
        System.out.println("setUp Children");
    }

    @Test
    @Category(Stage.class)
    public void demoChildren00(){
        System.out.println("demoChildren00");
    }

    @Test
    @Category(Prod.class)
    public void demoChildren02(){
        System.out.println("demoChildren02");
    }

    @Test
    @Ignore("just for show")
    public void demoChildren01(){
        System.out.println("demoChildren01");
    }
}
