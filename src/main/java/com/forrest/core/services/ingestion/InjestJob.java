package com.forrest.core.services.ingestion;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author David Gilmore
 * @date 11/30/13
 */
public class InjestJob {


    public void injestLocalFile(String file) {
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            reader.readAll();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
