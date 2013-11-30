package com.forrest.core.services.ingestion;

import au.com.bytecode.opencsv.CSVReader;
import com.forrest.web.config.StandaloneConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

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
        CSVReader csvReader = createMock(CSVReader.class);
        replay(csvReader);
        verify(csvReader);
    }
}
