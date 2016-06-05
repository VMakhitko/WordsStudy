package com.study.wordsstudy.translate;

import android.util.Log;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

/**
 * Created by vmakhitko on 04.06.16.
 */

public class Translator implements Runnable {
    private static String TAG = Translator.class.getSimpleName();
    private String toTranslate;
    private String translated;

    public Translator(String toTranslate) {
        this.toTranslate = toTranslate;
        Translate.setClientId("com_study_wordsstudy");
        Translate.setClientSecret("X94xsrAQnkeM44/42is1lr44UTS975wmesDyoDSPbp4=");
    }

    public String exec(String toTranslate) {
        String translatedWord;
        try {
            translatedWord = Translate.execute(toTranslate, Language.ENGLISH, Language.UKRAINIAN);
        } catch (Exception e) {
            Log.d(TAG, "Error translate word");
            return "Error translate word";
        }
        return translatedWord;
    }

    @Override
    public void run() {
        translated = exec(this.toTranslate);
    }

    public String getTranslated() {
        return translated;
    }
}
