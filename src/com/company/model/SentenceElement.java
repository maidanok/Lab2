package com.company.model;

import java.util.List;

/**
 * Created by Admin on 27.03.2017.
 */
public abstract class SentenceElement {

    public abstract boolean iAmWord();

    public abstract void setNeedSpaseAfrer(boolean needSpaseAfrer);

    abstract public boolean isNeedSpaseAfter();

    abstract String getValue();


}
