package edu.uga.cs.geographyquiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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

        QuizFragment quizFragment = new QuizFragment();
        Bundle bundle = new Bundle();   //pass a string thru the bundle
        position = position + 1;    //increment the position (start from 1 instead of 0)
        bundle.putString("question", "Question " + position
                + "\n\nSelect the continent on which {country} is located." );   //attach the question to the bundle
        quizFragment.setArguments(bundle);      //attach bundle to the fragment

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
