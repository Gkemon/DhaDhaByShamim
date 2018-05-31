package binarygeek.dhadhabyshamim.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import binarygeek.dhadhabyshamim.Adapter.DhaDhaAdapter;
import binarygeek.dhadhabyshamim.R;

import static binarygeek.dhadhabyshamim.Activity.MainActivity.GlodbalDataStorageForEpisode;

public class DhaDhaShowActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dha_dha_show);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setTitle(MainActivity.);
        setSupportActionBar(toolbar);

        DhaDhaAdapter dhaDhaAdapter =new DhaDhaAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.dhadha_recycler_view);
        dhaDhaAdapter.addAll(GlodbalDataStorageForEpisode.get(getIntent().getExtras().getInt("Episode")).dhaDhas);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(dhaDhaAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
