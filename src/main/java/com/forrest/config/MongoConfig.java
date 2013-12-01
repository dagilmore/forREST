package com.forrest.config;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author David Gilmore
 * @date 11/30/13
 */
@EnableMongoRepositories(basePackages = "com.forrest")
@PropertySource(value={"classpath:mongo.properties"})
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    Environment env;

    @Override
    public String getDatabaseName() {
        return env.getProperty("mongo.dbname");
    }

    @Override
    protected UserCredentials getUserCredentials() {
        return new UserCredentials(env.getProperty("mongo.username"), env.getProperty("mongo.password"));
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        Mongo mongo = new MongoClient(env.getProperty("mongo.url"));
        mongo.setWriteConcern(WriteConcern.SAFE);
        return mongo;
    }

    @Bean
    public DB mongoDb() throws Exception {
        DB db =  mongo().getDB(env.getProperty("mongo.dbname"));
        db.authenticate(env.getProperty("mongo.username"),env.getProperty("mongo.password").toCharArray());
        return db;
    }

    @Bean
    public Datastore mongoStore() throws Exception {
        return new Morphia().createDatastore(
                mongo(),
                env.getProperty("mongo.dbname")
        );
    }

    @Override
    public String getMappingBasePackage() {
        return "com.forrest";
    }
}
