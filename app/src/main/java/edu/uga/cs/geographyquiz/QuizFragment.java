package edu.uga.cs.geographyquiz;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizFragment extends Fragment {

    TextView mTextViewQuestion;
    RadioButton rbContinent1, rbContinent2, rbContinent3;

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        mTextViewQuestion = view.findViewById(R.id.questionTextView);
        //String question = getArguments().getString("question");     //get the question from the fragment
        //mTextViewQuestion.setText(question);    //display the question using textview

        //initialize radio buttons
        rbContinent1 = view.findViewById(R.id.radioButton1);
        rbContinent2 = view.findViewById(R.id.radioButton2);
        rbContinent3 = view.findViewById(R.id.radioButton3);

        //get value from radio button that's checked or unchecked
        //https://stackoverflow.com/questions/50403798/how-can-get-selected-radio-button-from-radio-group-from-a-fragment-inside-activi
        /*String answerSelected = "";
        if(rbContinent1.isChecked()){
            answerSelected = rbContinent1.getText().toString();
        }
        else if(rbContinent2.isChecked()){
            answerSelected = rbContinent2.getText().toString();
        }
        else if (rbContinent3.isChecked()){
            answerSelected = rbContinent3.getText().toString();
        }
        */

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setting to this makes sure that each fragment doesnt use the same viewmodel
        final QuizFragmentViewModel quizFragmentViewModel = ViewModelProviders.of(this)
                .get(QuizFragmentViewModel.class);

        Bundle args = getArguments();
        if(args != null){
            quizFragmentViewModel.questionNumberLiveData.setValue(args.getInt("question_number"));
            quizFragmentViewModel.countryLiveData.setValue(args.getString("question"));
            quizFragmentViewModel.option1LiveData.setValue(args.getString("option1"));
            quizFragmentViewModel.option2LiveData.setValue(args.getString("option2"));
            quizFragmentViewModel.option3LiveData.setValue(args.getString("option3"));
        }

        quizFragmentViewModel.countryLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mTextViewQuestion.setText(s);
            }
        });

        quizFragmentViewModel.option1LiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                rbContinent1.setText(s);
            }
        });

        quizFragmentViewModel.option2LiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                rbContinent2.setText(s);
            }
        });

        quizFragmentViewModel.option3LiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                rbContinent3.setText(s);
            }
        });

        final SwipeyBoiViewModel swipeyBoiViewModel = ViewModelProviders.of(getActivity())
                .get(SwipeyBoiViewModel.class);

    }
}
