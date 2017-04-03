package com.company.console;

import com.company.data_layer.FileConnector;
import com.company.model.Text;

/**
 * Created by Pasha on 03.04.2017.
 */
public class Runner {
    public static void main(String[] args){
        FileConnector fileConnector = new FileConnector();
        StringBuilder text = fileConnector.getText();
        Text text1 = new Text(text);
        System.out.println(text1);
    }
}
