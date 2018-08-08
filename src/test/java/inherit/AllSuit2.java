package inherit;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)

@Categories.ExcludeCategory(Stage.class)
@Suite.SuiteClasses({
        Children2.class,
        Children.class
})
public class AllSuit2 {
}
