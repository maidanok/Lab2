package com.company.test;

import com.company.data_layer.FileConnector;
import com.company.model.Text;

/**
 * Created by Pasha on 29.03.2017.
 */
public class TestsParagraph {

    public static void main(String[] args){
        FileConnector fileConnector = new FileConnector();
        StringBuilder text = fileConnector.getText();
        Text text1 = new Text(text);
        System.out.println(text1);
    }
}
