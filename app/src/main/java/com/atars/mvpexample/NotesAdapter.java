package com.atars.mvpexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private final ArrayList<String> noteList;
    private final DeleteButtonListener listener;

    public NotesAdapter(ArrayList<String> noteList, DeleteButtonListener listener) {
        this.noteList = noteList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.noteTextView.setText(noteList.get(position));
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteButtonClick(holder.getLayoutPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void updateNotes(ArrayList<String> newNoteList) {
        noteList.clear();
        noteList.addAll(newNoteList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView noteTextView;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            noteTextView = (TextView) itemView.findViewById(R.id.item_note_text_view);
            deleteButton = (Button) itemView.findViewById(R.id.item_note_delete_button);
        }
    }

    public interface DeleteButtonListener {

        void onDeleteButtonClick(int position);
    }
}