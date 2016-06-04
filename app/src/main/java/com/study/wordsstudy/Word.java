package com.study.wordsstudy;

import java.util.Date;

/**
 * Created by vmakhitko on 04.06.16.
 */

public class Word {
    int mId;
    private String mWord;
    private String mTranslate;
    private Date mRepeateDate;

    public Word() {
    }

    public Word(int id, String word, String translate, Date date) {
        this.mId = id;
        this.mWord = word;
        this.mTranslate = translate;
        this.mRepeateDate = date;
    }

    public Word(String word, String translate) {
        this.mWord = word;
        this.mTranslate = translate;
        this.mRepeateDate = new Date();
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getWord() {
        return this.mWord;
    }

    public void setWord(String word) {
        this.mWord = word;
    }

    public String getTranslate() {
        return this.mTranslate;
    }

    public void setTranslate(String translate) {
        this.mTranslate = translate;
    }

    public Date getRepeateDate() {
        return  this.mRepeateDate;
    }

    public void setRepeateDate(Date date) {
        this.mRepeateDate = date;
    }
}
