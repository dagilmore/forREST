package com.forrest.core.services.ingestion;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static org.easymock.EasyMock.*;

/**
 * @author David Gilmore
 * @date 11/30/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class InjestJobTest {

    @Test
    public void injestLocalFile() throws Exception {
        CSVReader csvReader = createMock(CSVReader.class);
        replay(csvReader);
        verify(csvReader);
    }
}
