package com.forrest.core.services.ingestion;

import au.com.bytecode.opencsv.CSVReader;
import com.forrest.web.config.StandaloneConfig;
import org.junit.Before;
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
@ContextConfiguration(classes = StandaloneConfig.class, loader = AnnotationConfigContextLoader.class)
public class InjestJobTest {

    private InjestJob job;


    @Before
    public void setup() {
        job = new InjestJob();

    }

    @Test
    public void injestLocalFile() throws Exception {

        if (job.injestLocalFile("/Users/atrask/Dropbox/ForRest/src/test/resources/data/test.csv"))
            assert true;
        else
            assert false;
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
