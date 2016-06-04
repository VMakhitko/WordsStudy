package com.study.wordsstudy.db;

import com.study.wordsstudy.Word;

import java.util.List;

/**
 * Created by vmakhitko on 04.06.16.
 */

public interface IDbHandler {
    public void addWord(Word word);
    public Word getWord(int id);
    public List<Word> getAllWords();
    public List<Word> getWordsForToday();
    public int getWordsCount();
    public int updateWord(Word word);
    public void deleteWord(Word word);
    public void deleteAll();
}
