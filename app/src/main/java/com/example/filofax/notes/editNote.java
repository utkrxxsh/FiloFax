package com.example.filofax.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filofax.MainActivity;
import com.example.filofax.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class editNote extends AppCompatActivity {

    Intent data;
    EditText editnoteTitle, editnoteContent;
    FirebaseFirestore fStore;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fStore = FirebaseFirestore.getInstance();

        data = getIntent();
        editnoteContent = findViewById(R.id.editNoteContent);
        editnoteTitle = findViewById(R.id.editNoteTitle);
        fab = findViewById(R.id.fab);


        String noteTitle = data.getStringExtra("title");
        String noteContent = data.getStringExtra("content");

        editnoteTitle.setText(noteTitle);
        editnoteContent.setText(noteContent);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nTitle = editnoteTitle.getText().toString();
                String nContent = editnoteContent.getText().toString();
                if (nTitle.isEmpty() || nContent.isEmpty()) {
                    Toast.makeText(editNote.this, "Note is empty", Toast.LENGTH_SHORT).show();
                } else {

                    //code to save in firestore in a folder named notes>> (random unique ids will be generated)

                    DocumentReference docref = fStore.collection("notes").document(data.getStringExtra("noteId"));
                    Map<String, Object> note = new HashMap<>();
                    note.put("title", nTitle);
                    note.put("content", nContent);
                    docref.update(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(editNote.this, "Note added successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(editNote.this, "Error , try again!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
