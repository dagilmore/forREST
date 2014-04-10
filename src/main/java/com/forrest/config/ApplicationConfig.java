package com.forrest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Contains middle-tier services, data sources, etc.
 *
 * @author David Gilmore
 * @date 11/30/13
 */
@Configuration
@ComponentScan(basePackages = { "com.forrest.core" })
@Import({ HiveConfig.class, ImpalaConfig.class, CubeDataConfig.class })
public class ApplicationConfig {
}
