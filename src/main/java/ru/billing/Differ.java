package ru.billing;


import java.io.File;

public class Differ {

    public static void main (String[] args)  throws Exception {

        try {
            String options = args[0];
            File firstXmlFile = new File(args[1]);
            File secondXmlFile = new File(args[2]);

            XMLReader xmlReader = new XMLReader(firstXmlFile,secondXmlFile);
            if (options.contains("D")) {
                Comparer comparer = new Comparer(xmlReader);
            }
            if (options.contains("C")) {
                Checker checker = new Checker(xmlReader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}

