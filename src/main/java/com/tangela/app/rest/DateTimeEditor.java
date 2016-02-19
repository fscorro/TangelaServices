package com.tangela.app.rest;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.beans.PropertyEditorSupport;

public class DateTimeEditor extends PropertyEditorSupport {

    private static final String DATIME_PATTERN = "yyyy-MM-dd";

    @Override
    public void setAsText(String text) {
        this.setValue(DateTimeFormat.forPattern(DATIME_PATTERN).parseDateTime(text));
    }

    @Override
    public String getAsText() {
        return ((DateTime) this.getValue()).toString(DateTimeFormat.forPattern(DATIME_PATTERN));
    }

}