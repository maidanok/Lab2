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
    public void setNeedSpaseAfrer(boolean needSpaseAfrer) {
        this.needSpaseAfter = needSpaseAfrer;
    }

    @Override
    public boolean isNeedSpaseAfter() {
        return needSpaseAfter;
    }

    @Override
    public String toString() {
        String str = "";
        if (isNeedSpaseAfter()) {
            str += " ";
        }
        for (int i = 0; i < puntuation.size(); i++) {
            str += puntuation.get(i);
        }
        return str;
    }
}
