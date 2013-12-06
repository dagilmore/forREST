package com.forrest.core.services.ObjectGeneration;

import com.forrest.config.StandaloneConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author David Gilmore
 * @date 12/6/13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=StandaloneConfig.class, loader=AnnotationConfigContextLoader.class)
public class ClassGenerationTest {

    @Autowired
    private ClassGeneration classGeneration;

    @Test
    public void testCreatePojo() throws Exception {
        classGeneration.createPojo("TestDGC");
    }
}
