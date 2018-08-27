package com.example.iycomo.mythoughts;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import java.util.List;


public class AddThought extends AppCompatActivity {

    public static final String THOUGHT_ID = "thoughtId";


    EditText title;
    EditText description;
    Button saveButton;

    // Boolean variable to track the state of activity
    private boolean isActivityEmpty = true;

    private AddThoughtViewModel viewModel;

    int defaultId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_thought);

        // Initialize views
        title = findViewById(R.id.title_editText);
        description = findViewById(R.id.description_editText);

        saveButton = findViewById(R.id.save_button);

        // Capture the coming Intent
        Intent intent = getIntent();
        final int thoughtId = intent.getIntExtra(THOUGHT_ID, -1);
        defaultId = thoughtId;

        AddThoughtViewModelFactory factory = new AddThoughtViewModelFactory(getApplication(), thoughtId);

        viewModel =
                ViewModelProviders.of(this, factory).get(AddThoughtViewModel.class);

        if (intent.hasExtra(THOUGHT_ID)){
            saveButton.setText(R.string.update);

            isActivityEmpty = false;

            viewModel.getThought().observe(this, new Observer<ThoughtsModel>() {
                @Override
                public void onChanged(@Nullable ThoughtsModel thoughtsModel) {
                    viewModel.getThought().removeObserver(this);
                    fillUI(thoughtsModel);
                }
            });
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButton();
            }
        });
    }

    private void fillUI(ThoughtsModel thoughtsModel) {

        if (thoughtsModel != null){
            title.setText(thoughtsModel.getTitle());
            description.setText(thoughtsModel.getDescription());
        }
    }

    public void onSaveButton() {
        String getTitle = title.getText().toString();
        String getDescription = description.getText().toString();
        long date = new Date().getTime();


        ThoughtsModel thought = new ThoughtsModel(getTitle, getDescription, date);

        if (isActivityEmpty){
            viewModel.insertThought(thought);
        }
        else {
            thought.setId(defaultId);
            viewModel.updateThought(thought);
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
