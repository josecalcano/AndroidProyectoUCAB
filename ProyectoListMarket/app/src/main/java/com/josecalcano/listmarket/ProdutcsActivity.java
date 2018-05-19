package com.josecalcano.listmarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.LinkedList;

public class ProdutcsActivity extends AppCompatActivity {

    private final LinkedList<String> mWordList = new LinkedList<>();
    private int mCount = 0;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtcs);

        for (int i = 0; i < 20; i++) {
            mWordList.addLast("Word " + mCount++);
            Log.d("WordList", mWordList.getLast());}

        // Get a handle to the RecyclerView.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void launchProductsActivity(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity (intent);
    }
}
