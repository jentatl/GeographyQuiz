package edu.uga.cs.quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuizFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    protected static final String [] CONTINENTS = {"Africa", "Asia", "Antartica", "Austrailia", "North America", "South America", "Europe" };


    public QuizFragmentCollectionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        QuizActivity qa = new QuizActivity();
        QuizFragment quizFragment = new QuizFragment();

        Bundle bundle = new Bundle();   //pass a string thru the bundle
        position = position + 1;    //increment the position (start from 1 instead of 0)


        // auto fills country names based on each question
        if(position == 1) {
            bundle.putString("question", "Question " + position
                    + "\n\nSelect the continent on which "  + qa.getCountry(0) + " is located.");
            qa.getAnswerOptions(1);//attach the question to the bundle
        }else if(position == 2){
            bundle.putString("question", "Question " + position
                    + "\n\nSelect the continent on which " + qa.getCountry(1) + " is located.");
            qa.getAnswerOptions(2);//attach the question to the bundle
        }else if(position == 3){
            bundle.putString("question", "Question " + position
                    + "\n\nSelect the continent on which " + qa.getCountry(2) + " is located.");
            qa.getAnswerOptions(3);//attach the question to the bundle
        }else if(position == 4){
            bundle.putString("question", "Question " + position
                    + "\n\nSelect the continent on which " + qa.getCountry(3) + " is located.");
            qa.getAnswerOptions(4);//attach the question to the bundle
        }else if(position == 5){
            bundle.putString("question", "Question " + position
                    + "\n\nSelect the continent on which " + qa.getCountry(4) + " is located.");
            qa.getAnswerOptions(5);//attach the question to the bundle
        }else if(position == 6) {
            bundle.putString("question", "Question " + position
                    + "\n\nSelect the continent on which " + qa.getCountry(5) + " is located.");
            qa.getAnswerOptions(6);//attach the question to the bundle
        }

        if(position == 7){
            return new ResultsFragment();
        }else {
            quizFragment.setArguments(bundle);      //attach bundle to the fragment
        }
        return quizFragment;
    }

    // gets continent choices to fill radio buttons
    public String getContinentChoice(int answer){
        return "hello";
    }
    /**
     * returns number of fragment pages
     * @return
     */
    @Override
    public int getCount() {
        return 7;
    }
}
