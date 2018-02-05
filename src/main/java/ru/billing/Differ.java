package ru.billing;


import java.io.File;

public class Differ {

    public static void main (String[] args)  throws Exception {

        try {

            File firstXmlFile = new File("C:\\out\\sprint001_24_406.xml");
            File secondXmlFile = new File("C:\\out\\sprint001_24_415.xml");

            XMLReader xmlReader = new XMLReader(firstXmlFile,secondXmlFile);
            Comparer  comparer  = new Comparer(xmlReader);
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

