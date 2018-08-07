import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith (Parameterized.class)
public class DataTest {

    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static Integer[][] data(){
        return new Integer[][]{
                {4,9},{2,3},{1,1}
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
