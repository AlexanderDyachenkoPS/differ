package ru.billing;


import java.io.File;

public class Differ {

    public static void main (String[] args)  throws Exception {

        try {

            File firstXmlFile = new File(args[0]);
            File secondXmlFile = new File(args[1]);

            XMLReader xmlReader = new XMLReader(firstXmlFile,secondXmlFile);
            Comparer  comparer  = new Comparer(xmlReader);
            Checker   checker   = new Checker(xmlReader);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

