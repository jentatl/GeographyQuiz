package edu.uga.cs.geographyquiz;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProviders;

public class QuizFragmentCollectionAdapter extends FragmentStatePagerAdapter {
    public QuizFragmentCollectionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        //https://medium.com/@kyroschow/how-to-use-viewpager-for-navigating-between-fragments-with-tablayout-a28b4cf92c42
        if (position == 6){     //after answering the last question, call the results fragment
            return new ResultsFragment();
        }

        CountryRepository cr = new CountryRepository();
        List<Country> dummyCountryList = new ArrayList<>();
        dummyCountryList = cr.getDummyCountries();

        String[] dummyAnswerList = cr.getDummyAnswers();

        QuizFragment quizFragment = new QuizFragment();
        Bundle bundle = new Bundle();   //pass a string thru the bundle
        bundle.putInt("question_number" , position);
        bundle.putString("question", "\n\nSelect the continent on which " + dummyCountryList.get(position).countryName + " is located." );   //attach the question to the bundle
        bundle.putString("option1", dummyAnswerList[0]);
        bundle.putString("option2", dummyAnswerList[1]);
        bundle.putString("option3", dummyAnswerList[2]);
        quizFragment.setArguments(bundle);      //attach bundle to the fragment

        /////

        return quizFragment;
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
