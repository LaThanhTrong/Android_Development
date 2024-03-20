package com.example.lab10;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import  android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ViewNotes extends AppCompatActivity {
    private ArrayList<NoteModal> noteArrayList;
    private DBHandler dbHandler;
    private NoteAdapter noteAdapter;
    private RecyclerView notesRV;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);

        noteArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewNotes.this);

        noteArrayList = dbHandler.getAllNotes();

        noteAdapter = new NoteAdapter(noteArrayList, ViewNotes.this);
        notesRV = findViewById(R.id.idRVNotes);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewNotes.this, RecyclerView.VERTICAL, false);
        notesRV.setLayoutManager(linearLayoutManager);
        notesRV.setAdapter(noteAdapter);

        registerForContextMenu(notesRV);

        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewNotes.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = NoteAdapter.selectedNotePosition;
        NoteModal selectedNote = noteArrayList.get(position);
        String selectTitle = selectedNote.getTitle();
        String selectDateTime = selectedNote.getDate();
        String selectContent = selectedNote.getContent();
        if(item.getItemId() == R.id.edit){
            Intent intent = new Intent(ViewNotes.this, UpdateActivity.class);
            intent.putExtra("title", selectTitle);
            intent.putExtra("content", selectContent);
            intent.putExtra("date", selectDateTime);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete Note");
            builder.setMessage("Are you sure you want to delete this note?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    noteArrayList.remove(position);
                    dbHandler.deleteNote(selectTitle, selectDateTime);
                    noteAdapter.notifyItemRemoved(position);
                    Toast.makeText(ViewNotes.this, "Note has been deleted.", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(ViewNotes.this, "Note is not deleted.", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        }

        return super.onContextItemSelected(item);
    }


}
