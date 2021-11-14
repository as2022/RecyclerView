package com.smithsona2.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private static final int DEFAULT_LIST_SIZE = 10;
    private static final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFreshlist();

        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab_reset = findViewById(R.id.fab_reset);

        fab.setOnClickListener(view -> {
            int wordListSize = mWordList.size();
            mWordList.addLast("+ Word " + wordListSize);
            mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
            mRecyclerView.smoothScrollToPosition(wordListSize);
        });

        fab_reset.setOnClickListener(view -> {
                createFreshlist();
                mRecyclerView.getAdapter().notifyDataSetChanged();
                Toast.makeText(this, "Deleted Changes", Toast.LENGTH_SHORT).show();
        });

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
// Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList);
// Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
// Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createFreshlist() {
        mWordList.clear();
        for(int i = 0; i < DEFAULT_LIST_SIZE; i++) {
            mWordList.add("Word " + i);
        }
    }
}