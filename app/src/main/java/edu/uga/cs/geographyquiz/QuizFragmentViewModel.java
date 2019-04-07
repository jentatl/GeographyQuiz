package edu.uga.cs.geographyquiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuizFragmentViewModel extends ViewModel {
    public MutableLiveData<String> option1LiveData = new MutableLiveData<String>();
    public MutableLiveData<String> option2LiveData = new MutableLiveData<String>();
    public MutableLiveData<String> option3LiveData = new MutableLiveData<String>();
    public MutableLiveData<String> countryLiveData = new MutableLiveData<String>();
    public MutableLiveData<Integer> questionNumberLiveData = new MutableLiveData<Integer>();
    public MutableLiveData<String> correctAnsLiveData = new MutableLiveData<String>();


}
