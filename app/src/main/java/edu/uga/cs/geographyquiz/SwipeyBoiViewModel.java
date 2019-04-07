package edu.uga.cs.geographyquiz;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SwipeyBoiViewModel extends ViewModel {

    public static final int QUESTION_COUNT = 6;

    public SwipeyBoiViewModel(){
        answersLiveData.setValue(new ArrayList<QuizAnswer>());  //initialize the a new quizAnswer list for answersLiveData

        for(int i = 0; i < QUESTION_COUNT; i++){
            answersLiveData.getValue().add(new QuizAnswer());   //populates the list with quizanswer objects
        }

    }

    public MutableLiveData<List<QuizAnswer>> answersLiveData = new MutableLiveData<List<QuizAnswer>>();
}
