package com.company.test;

import com.company.data_layer.FileConnector;

public class TestReadFile {

    public static void main(String[] args) {
        FileConnector fileConnector = new FileConnector();
        String text = fileConnector.getText().toString();
        System.out.println(text);
    }
}
