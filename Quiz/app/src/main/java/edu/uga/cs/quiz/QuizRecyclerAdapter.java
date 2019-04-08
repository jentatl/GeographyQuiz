package edu.uga.cs.quiz;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuizRecyclerAdapter  extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizHolder> {
    public static final String DEBUG_TAG = "QuizRecyclerAdapter";

    private List<Quiz> quizList;

    public QuizRecyclerAdapter (List<Quiz> quizList) {
        this.quizList = quizList;
    }

    // The adapter must have a ViewHolder class to "hold" one item to show.
    class QuizHolder extends RecyclerView.ViewHolder {

        TextView id, date, score;

        public QuizHolder(View itemView ) {
            super(itemView);

            id = (TextView) itemView.findViewById( R.id.id );
            date = (TextView) itemView.findViewById( R.id.date );
            score = (TextView) itemView.findViewById( R.id.score );
        }
    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz_card, null );
        return new QuizHolder( view );
    }

    @Override
    public void onBindViewHolder( QuizHolder holder, int position ) {
        Quiz quiz = quizList.get( position );

        Log.d( DEBUG_TAG, "onBindViewHolder: " + quiz );

        holder.id.setText(Long.toString(quiz.getId()));
        holder.date.setText( quiz.getDate() );
        holder.score.setText( quiz.getScore() );
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }
}
