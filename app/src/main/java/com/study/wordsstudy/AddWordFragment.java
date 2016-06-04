package com.study.wordsstudy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.study.wordsstudy.db.DbHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vmakhitko on 01.06.16.
 */

public class AddWordFragment extends Fragment {
    private static String TAG = AddWordFragment.class.getSimpleName();

    @BindView(R.id.enter_word_edit_text) EditText enterWord;
    @BindView(R.id.translated_word_edit_text) EditText translatedWord;
    @BindView(R.id.add_word_button) Button addWordButton;
    @BindView(R.id.translate_word_button) Button translateWordButton;

    public AddWordFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_word, container, false);
        ButterKnife.bind(this, view);
        initUi();

        return view;
    }

    private void initUi() {
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHandler dbHandler = new DbHandler(getContext());
                if (!enterWord.getText().toString().isEmpty()) {
                    dbHandler.addWord(new Word(enterWord.getText().toString(),
                            translatedWord.getText().toString()));
                }
            }
        });
    }
}
