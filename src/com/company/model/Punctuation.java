package com.company.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.03.2017.
 */

public class Punctuation extends SentenceElement {

    List<Character> puntuation = new ArrayList<>();
    boolean needSpaseAfter = true;


    public Punctuation(String str) {
        for (int i = 0; i < str.length(); i++) {
            puntuation.add(str.charAt(i));
        }
    }


    @Override
    public boolean iAmWord() {
        return false;
    }

    @Override
    public void setNeedSpaseAfrer(boolean needSpaseAfrer) {
        this.needSpaseAfter = needSpaseAfrer;
    }

    @Override
    public boolean isNeedSpaseAfter() {
        return needSpaseAfter;
    }

    @Override
    String getValue() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < puntuation.size(); i++) {
            stringBuilder.append(puntuation.get(i));
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (isNeedSpaseAfter()) {
            str.append(" ");
        }
        for (int i = 0; i < puntuation.size(); i++) {
            str.append(puntuation.get(i));
        }
        return str.toString();
    }
}
