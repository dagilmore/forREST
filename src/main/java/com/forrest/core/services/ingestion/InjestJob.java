package com.forrest.core.services.ingestion;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author David Gilmore
 * @date 11/30/13
 */
public class InjestJob {


    public InjestJob() {

    }

    public boolean injestLocalFile(String file) throws FileNotFoundException, IOException {

        injestCSV(file);
        return true;

    }


    public void injestCSV(String file) {

        LinkedList<String> fileTypes = getColumnTypesCsv(file);

        LinkedList<String> columnNames = new LinkedList<String>();

        for (int i = 0; i < fileTypes.size(); i++) {
            columnNames.add("col" + i);
        }

        String query = getHiveFlatTableCreationQueryExternal(columnNames, fileTypes, file, "default","testTable","\\n",",");

        System.out.println("Query:" + query);

    }

    public String getHiveFlatTableCreationQueryExternal(LinkedList<String> names, LinkedList<String> types, String file, String database, String tableName, String rowDelimiter, String colDelmiter) {

        String query = "CREATE EXTERNAL TABLE " + tableName + "_external (";

        for(int i=0; i<names.size(); i++) {
            query += names.get(i) + " " + types.get(i) + ",";
        }

        query = query.substring(0,query.length() - 1);

        String abbreviatedLocation = file.substring(0,file.lastIndexOf("/"));
        System.out.println("Abbreviated Location:" + abbreviatedLocation);

        query += ") ROW FORMAT DELIMITED FIELDS TERMINATED BY '" + colDelmiter + "' LINES TERMINATED BY '" + rowDelimiter + "' LOCATION '" + abbreviatedLocation + "'; ";

        query += "CREATE TABLE " + tableName + " AS SELECT * FROM " + tableName + "_external WHERE instr(INPUT__FILE__NAME,'"+file+"')!=0; DROP TABLE " + tableName + "_external;";

        return query;

    }

    public LinkedList<String> getColumnTypesCsv(String file) {

        LinkedList<String> fileTypes = new LinkedList<String>();

        try {

            CSVReader reader = new CSVReader(new FileReader(file));
            reader.readNext();
            String[] secondLine = reader.readNext();

            for (String eachCol : secondLine) {
                fileTypes.add(getType(eachCol));
            }

            System.out.println(fileTypes);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.

        }

        return fileTypes;

    }

    public String getType(String value) {

        if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false") || value.toLowerCase().equals("t") || value.toLowerCase().equals("f"))
            return "BOOLEAN";
        else if ((!value.matches(".*\\d.*")) || value.matches(".*[A-Z].*") || value.matches(".*[a-z].*"))
            return "STRING";
        else if (value.contains("."))
            return "DOUBLE";
        else
            return "INT";

    }


}
