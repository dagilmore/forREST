package com.forrest.core.services.ingestion;

import com.forrest.config.StandaloneConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * @author David Gilmore
 * @date 11/30/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StandaloneConfig.class, loader = AnnotationConfigContextLoader.class)
public class InjestJobTest {

    @Autowired
    public InjestJob job;


    private static final String testPath = "unittest.tmp";

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        new File(testPath).delete();
    }


    @Test //TODO: More robust test coverage
    public void injestLocalFile() throws Exception {

        PrintWriter out = new PrintWriter(testPath);

        List<String[]> returnData = new LinkedList<String[]>();

        for(int i = 0;i<10;i++) {
            String line = "0.3,test string,true\n";
            out.write(line);
        }
        out.close();

        JdbcTemplate jdbcTemplateMock = Mockito.mock(JdbcTemplate.class);
        ReflectionTestUtils.setField(job, "hiveTemplate", jdbcTemplateMock);
        Mockito.verify(jdbcTemplateMock,Mockito.times(1));
    }

    @Test
    public void testGetHiveFlatTableCreationQueryExternal() {
        //TODO
        assert true;
    }

    @Test
    public void testIntDetection() {
        boolean passed = true;

        if(!job.getType("1234").equals("INT"))
            passed = false;

        assert passed;
    }

    @Test
    public void testStringDetection() {

     boolean passed = true;

        if(!job.getType("hello").equals("STRING"))
            passed = false;
        if(!job.getType("hello1234").equals("STRING"))
            passed = false;

        assert passed;
    }

    @Test
    public void testDoubleDetection() {

        boolean passed = true;

        if(!job.getType("12.34").equals("DOUBLE"))
            passed = false;

        assert passed;

    }

    @Test
    public void testBooleanDetection() {

        boolean passed = true;

        if(!job.getType("TRUE").equals("BOOLEAN"))
            passed = false;


        assert passed;
    }

}
