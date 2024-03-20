package com.example.lab10;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private  ArrayList<NoteModal> noteArrayList;
    private Context context;
    public static NoteModal selectedNote ;
    public static int selectedNotePosition ;

    public NoteAdapter(ArrayList<NoteModal> noteArrayList, Context context) {
        this.noteArrayList = noteArrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        private TextView title, content, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.idTile);
            content = itemView.findViewById(R.id.idContent);
            date = itemView.findViewById(R.id.idDateTime);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public boolean onLongClick(View v) {
            selectedNote = noteArrayList.get(getAdapterPosition());
            selectedNotePosition = getAdapterPosition();
            return false;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    public int getItemCount() {
        return noteArrayList.size();
    }
    public Object getItem(int position) {
        return noteArrayList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoteModal modal = noteArrayList.get(position);
        holder.title.setText(modal.getTitle());
        holder.content.setText(modal.getContent());
        holder.date.setText(modal.getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
