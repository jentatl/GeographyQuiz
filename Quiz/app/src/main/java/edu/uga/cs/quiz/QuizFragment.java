package edu.uga.cs.quiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class QuizFragment extends ListFragment {

    ArrayList<Integer> random3 = new ArrayList();
    ArrayList<Integer> random7 = new ArrayList();
    int a1;
    protected static final String [] CONTINENTS = {"Africa", "Asia", "Antartica", "Oceania", "North America", "South America", "Europe" };
    public String[] answerSelection = new String [3];

    public String[] random6 = new String[6];
    int num;
    View v;
    QuizActivity qa = new QuizActivity();

    TextView correct;

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

      random3.add(1); random3.add(2); random3.add(3);
      random7.add(1); random7.add(2); random7.add(3); random7.add(4); random7.add(5); random7.add(6); random7.add(7);

      Collections.shuffle(random3);
      Collections.shuffle(random7);

      num = getArguments() != null ? getArguments().getInt("num") : 1;
  }// onCreate

  /* The fragments ui */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            v = inflater.inflate(R.layout.fragment_quiz, container, false);
            View tv = v.findViewById(R.id.questionTextView);
            ((TextView) tv).setText("\nQuestion Number " + (num + 1) + "\n\nWhich continent is " + getCountry(num) + " from?");


            correct = v.findViewById(R.id.correct);
            correct.setVisibility(View.INVISIBLE);

            Button button = v.findViewById(R.id.button);

            if(num != 5){
                button.setVisibility(View.INVISIBLE);
            }else{
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener( new ResultsClickListener()) ;
            }
        return v;

    }// onCreateView

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        ListView lv = v.findViewById(android.R.id.list);

        a1 = (random3.get(0))-1;
        int a2 = (random3.get(1))-1;
        int a3 = (random3.get(2))-1;

        answerSelection[a1] = qa.getAnswer(num);

        get2RandomContinents();
        // sets continents
        answerSelection[a2] = random6[0];
        answerSelection[a3] = random6[1];

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
        android.R.layout.simple_list_item_single_choice, answerSelection);

        lv.setAdapter(adapter);
    }// onactivitycreated

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
       // l.getChildAt(position).setBackgroundColor(Color.parseColor("#D3D3D3"));
        boolean correct = qa.checkAnswer(num, answerSelection[a1], a1, id);
        Log.i("FragmentList", "Item Clicked: " + id);

    }// on list item clicked

    public String getCountry(int num){
        String country = "";

        System.out.println("Number: " + num);

        if(num == 0){
          country = qa.getCountry(0);
        }else if(num == 1){
            country = qa.getCountry(1);
        }else if(num == 2){
            country = qa.getCountry(2);
        }else if(num == 3){
            country = qa.getCountry(3);
        }else if(num == 4){
            country = qa.getCountry(4);
        }else if(num == 5){
            country = qa.getCountry(5);
        }else{
            country = "LOST DATA :/";
        }
        return country;
    } // get country

    private class ResultsClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            startActivity(intent);
        }
    }

    public void get2RandomContinents(){
        int answer = qa.getNumCorrectAnswer(num);
        int x = 0;
        for(int i = 0; i < 7; i++){
            if((random7.get(i)-1) == answer){
                continue;
            }else{
                random6[x] = CONTINENTS[(random7.get(i)-1)];
            }
            x++;
        }
    }

}