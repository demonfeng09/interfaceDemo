import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(Parameterized.class)
public class DataDriverbyCsv {

    @Parameterized.Parameters()
    public static List<DataClass> data() throws IOException {
        ArrayList<DataClass> data = new ArrayList<DataClass>();

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(DataClass.class);
        File csvFile = new File(DataDriverbyCsv.class.getResource("data/input.csv").getFile());
//        MappingIterator<DataClass> it = mapper.readerFor(DataClass.class).with(schema).readValues(csvFile);
        MappingIterator<DataClass> it = mapper.reader(DataClass.class).with(schema).readValues(csvFile);
        while (it.hasNext()){
            DataClass row = it.next();
            data.add(row);
        }
        return data;
    }

    @Parameterized.Parameter
    public DataClass data;

    @Test
    public void demo(){
        assertThat(data.getCount(),equalTo(Integer.parseInt(data.getKey())));
    }


}