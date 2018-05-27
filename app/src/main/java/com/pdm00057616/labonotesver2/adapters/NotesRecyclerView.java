package com.pdm00057616.labonotesver2.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pdm00057616.labonotesver2.R;
import com.pdm00057616.labonotesver2.models.Note;

import java.util.List;

public class NotesRecyclerView extends RecyclerView.Adapter<NotesRecyclerView.ViewHolder> {

    private List<Note> notes;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note aux = notes.get(position);
        System.out.println(aux.getCategoryID());
        holder.textViewTitle.setText(aux.getTitle());
        holder.textViewCategory.setText(aux.getCategoryID()+"");
    }

    @Override
    public int getItemCount() {
        return notes == null ? 0 : notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle, textViewCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewCategory = itemView.findViewById(R.id.text_view_category);
        }
    }
}
