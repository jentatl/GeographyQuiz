package edu.uga.cs.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ResultsFragment extends Fragment implements View.OnClickListener {


    MainActivity ma = new MainActivity();

    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);

        //set onclicklistener for corresponding buttons
        view.findViewById(R.id.butt_newQuiz).setOnClickListener(mListener);
        view.findViewById(R.id.butt_pastResults).setOnClickListener(mListener);

        return view;
    }

    /**
     * create single click listener for both buttons
     * https://stackoverflow.com/questions/15478105/start-an-activity-from-a-fragment
     * https://stackoverflow.com/questions/35788127/multiple-buttons-in-fragment-how-to-redirect-to-a-different-layout
     */
    private final View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.butt_newQuiz:
                    ma.score = 0;
                    Intent intent = new Intent(getActivity(), QuizActivity.class);
                    startActivity(intent);
                    break;
                case R.id.butt_pastResults:
                    ma.score = 0;
                    Intent intent2 = new Intent(getActivity(), PastActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {

    }
}