package com.example.mahdi.languagelearning.recyclerView;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mahdi.languagelearning.R;
import com.example.mahdi.languagelearning.RoomDB.Word;

import java.util.Collections;
import java.util.List;


public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView wordItemView;
        private RecyclerViewClickListener mListener;

        private WordViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.recyclerItemTextView);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    private final LayoutInflater mInflater;
    private Context context;
    private List<Word> mWords = Collections.emptyList(); // Cached copy of words
    private RecyclerViewClickListener mListener;

    public WordListAdapter(Context context, RecyclerViewClickListener listener) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mListener = listener;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        return new WordViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word current = mWords.get(position);
        holder.wordItemView.setText(current.getWord());

        if (current.isPassed()) {
            holder.wordItemView.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.orange), PorterDuff.Mode.SRC_OVER);
        } else
            holder.wordItemView.getBackground().setColorFilter(ContextCompat.getColor(context, R.color.gray), PorterDuff.Mode.SRC_OVER);
    }

    public void setWords(List<Word> words) {
        mWords = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }
}