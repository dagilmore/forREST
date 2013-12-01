package com.forrest.core.services.ingestion;

import au.com.bytecode.opencsv.CSVReader;
import com.forrest.config.StandaloneConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import static org.easymock.EasyMock.*;

/**
 * @author David Gilmore
 * @date 11/30/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=StandaloneConfig.class, loader=AnnotationConfigContextLoader.class)
public class InjestJobTest {

    @Test
    public void injestLocalFile() throws Exception {
        List<String[]> returnData = new LinkedList<String[]>();

        for(int i = 0;i<10;i++) {
            String[] line = new String[] {"andrew,", "david", "patrick"};
            returnData.add(i,line);
        }

        CSVReader csvReader = createMock(CSVReader.class);
        FileReader fileReader = createMock(FileReader.class);

        expect(csvReader.readAll()).andReturn(returnData);

        replay(csvReader);


        verify(csvReader);
    }
}
