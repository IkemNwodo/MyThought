package com.example.iycomo.mythoughts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ThoughtDetailActivity extends AppCompatActivity {

    public static final String THOUGHT_ID = "thoughtId";

    TextView title;
    TextView description;

    private int thoughtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_detail);

        // Initialize views
        title = findViewById(R.id.title_view);
        description = findViewById(R.id.description_view);

        Intent intent = getIntent();
        thoughtId = intent.getIntExtra(THOUGHT_ID, -1);

        // ViewModel
        final DetailActivityViewModelFactory factory = new DetailActivityViewModelFactory(getApplication(), thoughtId);
        final DetailActivityViewModel viewModel = ViewModelProviders.of(this,factory).get(DetailActivityViewModel.class);

        viewModel.getLoadThought().observe(this, new Observer<ThoughtsModel>() {
            @Override
            public void onChanged(@Nullable ThoughtsModel thoughtsModel) {
                title.setText(thoughtsModel.getTitle());
                description.setText(thoughtsModel.getDescription());
            }
        });
        setUpTextview(viewModel, title, description);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.edit_button){
            Intent intent = new Intent(ThoughtDetailActivity.this, AddThought.class);
            intent.putExtra(AddThought.THOUGHT_ID, thoughtId);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setUpTextview(DetailActivityViewModel viewModel, TextView title, TextView description) {

    }
}
