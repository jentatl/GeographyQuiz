package edu.uga.cs.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class QuizFragment extends ListFragment {

    public String[] continents = {"Asia", "Africa", "Europe"};
    int num;
    long idClicked;
    View v;

  /* Create a new instance of fragment */
  static QuizFragment newInstance(int x){
      QuizFragment qf = new QuizFragment();

      // supply x as an input argument
      Bundle args = new Bundle();
      args.putInt("num", x);
      qf.setArguments(args);

      return qf;
  } // newInstance

  /* When creating, retrieve this instanc's number from its arguments */
  @Override
  public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      num = getArguments() != null ? getArguments().getInt("num") : 1;
  }// onCreate

  /* The fragments ui */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        v = inflater.inflate(R.layout.fragment_quiz, container, false);
        View tv = v.findViewById(R.id.questionTextView);

        ((TextView)tv).setText("Fragment # " + num);
        return v;
    }// onCreateView

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        ListView lv = v.findViewById(android.R.id.list);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_single_choice, continents);

        lv.setAdapter(adapter);
    }// onactivitycreated

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
       // l.getChildAt(position).setBackgroundColor(Color.parseColor("#D3D3D3"));
        Log.i("FragmentList", "Item Clicked: " + id);
    }// on list item clicked
/*
    protected ArrayList <Integer> random7 = new ArrayList();

    TextView mTextViewQuestion;
    QuizActivity qa = new QuizActivity();
    String random2Continents [] = new String[2];
    int answer = 0;
    int question = 0;

    RadioGroup radioGroup;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;

    ArrayList<Integer> random3 = new ArrayList();
    protected static final String [] CONTINENTS = {"Africa", "Asia", "Antartica", "Oceania", "North America", "South America", "Europe" };

    public QuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        random3.add(1); random3.add(2); random3.add(3);
        random7.add(1); random7.add(2); random7.add(3); random7.add(4); random7.add(5); random7.add(6); random7.add(7);
        Collections.shuffle(random3);
        Collections.shuffle(random7);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        mTextViewQuestion = view.findViewById(R.id.questionTextView);
        String question = getArguments().getString("question");     //get the question from the fragment
        mTextViewQuestion.setText(question);    //display the question using textview

        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);

        rb1 = view.findViewById(R.id.radioButton1);
        rb2 = view.findViewById(R.id.radioButton2);
        rb3 = view.findViewById(R.id.radioButton3);

        String continents [] = new String[3];
        int numAnswer = qa.getNumCorrectAnswer();

        // continents array holds where all of the continent options will show. One slot will have the correct answer
        continents[0] = " ";
        continents[1] = " ";
        continents[2] = " " ;

        // gets correct answer
        continents[(random3.get(0))-1] = qa.getCorrectAnswer();

        //System.out.println("Random: " + (random3.get(0)-1));
       // System.out.println("Correct answer: " + qa.getCorrectAnswer());

        answer = qa.getNumCorrectAnswer();

        get2RandomContinents();

        // sets continents
        continents[(random3.get(1))-1] = random2Continents[0];
        continents[(random3.get(2))-1] = random2Continents[1];

        // sets text
        rb1.setText(continents[0]);
        rb2.setText(continents[1]);
        rb3.setText(continents[2]);

        return view;
    }

    public void get2RandomContinents(){
        random2Continents[0] = "hello";
        random2Continents[1] = "there";
    }

*/
}