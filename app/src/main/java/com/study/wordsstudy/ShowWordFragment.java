package com.study.wordsstudy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.study.wordsstudy.db.DbHandler;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShowWordFragment extends Fragment {
    private static String TAG = ShowWordFragment.class.getSimpleName();

    @BindView(R.id.word) TextView word;
    @BindView(R.id.translate) TextView translate;
    @BindView(R.id.one_min_button) Button oneMinButton;
    @BindView(R.id.one_day_button) Button oneDayButton;
    @BindView(R.id.four_day_button) Button fourDaysButton;

    public ShowWordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        ButterKnife.bind(this, view);
        initUi();
        return view;
    }

    private void initUi() {
        DbHandler dbHandler = new DbHandler(getContext());
        Word word = dbHandler.getWord(1);
        if (word != null) {
            this.word.setText(word.getWord());
            this.translate.setText(word.getTranslate());
        } else {
            this.word.setText("Empty DB");
        }
        dbHandler.getAllWords();
    }
}
