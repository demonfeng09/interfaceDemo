import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class DataTest {

    @Parameterized.Parameters
    public static Integer[][] data(){
        return new Integer[][]{
                {4,9},{2,3}
        };
    }

    @Parameterized.Parameter
    public int first;
    @Parameterized.Parameter(1)
    public int second;

    @Test
    public void testDemo(){
        assertThat(first,equalTo(second));
        System.out.println(first+"--"+second);
    }
}
