package com.company.data_layer;

import java.io.*;

/**
 * Created by Pasha on 28.03.2017.
 */
public class FileConnector {

    private File myTextFile = new File("mytext.txt");

    public StringBuilder getText() {
        FileReader fileReader;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder=new StringBuilder();
        try {
            fileReader = new FileReader(myTextFile);
            bufferedReader = new BufferedReader(fileReader);
            String line=null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
