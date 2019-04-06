package edu.uga.cs.quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static edu.uga.cs.quiz.QuizActivity.NUM_ITEMS;

public class QuizFragmentCollectionAdapter extends FragmentStatePagerAdapter {

    public QuizFragmentCollectionAdapter(FragmentManager fm) {
        super(fm);
    }// constructor

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {


        return QuizFragment.newInstance(position);
    }

   /* protected static final String [] CONTINENTS = {"Africa", "Asia", "Antartica", "Oceania", "North America", "South America", "Europe" };

    public QuizFragmentCollectionAdapter(FragmentManager fm) {
        super(fm);
    }

//    @Override
//    public Fragment getItem(int position) {
//
//        QuizActivity qa = new QuizActivity();
//        QuizFragment quizFragment = new QuizFragment();
//        Bundle bundle = new Bundle();   //pass a string thru the bundle;
//        // increment the position (start from 1 instead of 0)
//
//        // auto fills country names based on each question
//        if(position == 1) {
//            bundle.putString("question", "Question " + position
//                    + "\n\nSelect the continent on which "  + qa.getCountry(0) + " is located.");
//            qa.getAnswerOptions(1); //attach the question to the bundle
//        }else if(position == 2){
//            bundle.putString("question", "Question " + position
//                    + "\n\nSelect the continent on which " + qa.getCountry(1) + " is located.");
//            qa.getAnswerOptions(2); //attach the question to the bundle
//        }else if(position == 3){
//            bundle.putString("question", "Question " + position
//                    + "\n\nSelect the continent on which " + qa.getCountry(2) + " is located.");
//            qa.getAnswerOptions(3); //attach the question to the bundle;//attach the question to the bundle
//        }else if(position == 4){
//            bundle.putString("question", "Question " + position
//                    + "\n\nSelect the continent on which " + qa.getCountry(3) + " is located.");
//            qa.getAnswerOptions(4); //attach the question to the bundle//attach the question to the bundle
//        }else if(position == 5){
//            bundle.putString("question", "Question " + position
//                    + "\n\nSelect the continent on which " + qa.getCountry(4) + " is located.");
//            qa.getAnswerOptions(5); //attach the question to the bundle//attach the question to the bundle
//        }else if(position == 6) {
//            bundle.putString("question", "Question " + position
//                    + "\n\nSelect the continent on which " + qa.getCountry(5) + " is located.");
//            qa.getAnswerOptions(6); //attach the question to the bundle//attach the question to the bundle
//        }
//
//        System.out.println("posistion: " + position);
//
//        if(position == 7){
//            return new ResultsFragment();
//        }else {
//            quizFragment.setArguments(bundle);      //attach bundle to the fragment
//        }
//
//        return quizFragment;
//    }

    @Override
    public Fragment getItem(int position) {
        return ArrayListFragment.newInstance(position);
    }

    public static class ArrayListFragment extends ListFragment {
        String mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
      /*  static ArrayListFragment newInstance(String num) {
            ArrayListFragment f = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putString("question", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
       /* @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getString("question") : "";
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.fragment_quiz, container, false);

            TextView mTextViewQuestion = v.findViewById(R.id.questionTextView);
            String question = getArguments().getString("question");     //get the question from the fragment
            mTextViewQuestion.setText(question);

            return v;

        }

    }

    // gets continent choices to fill radio buttons
    public String getContinentChoice(int answer){
        return "hello";
    }
    /**
     * returns number of fragment pages
     * @return
     */
   /* @Override
    public int getCount() {
        return 7;
    }*/
}
