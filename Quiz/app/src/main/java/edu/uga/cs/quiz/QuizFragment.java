package edu.uga.cs.quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuizFragment extends Fragment {


    TextView mTextViewQuestion;

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        mTextViewQuestion = view.findViewById(R.id.questionTextView);
        String question = getArguments().getString("question");     //get the question from the fragment
        mTextViewQuestion.setText(question);    //display the question using textview

        return view;
    }

}