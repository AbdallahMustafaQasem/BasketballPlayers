package abdallah.qasem.basketballplayers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;


import java.util.ArrayList;
import abdallah.qasem.basketballplayers.adapters.PlayersAdapter;
import abdallah.qasem.basketballplayers.models.PlayersData;
import abdallah.qasem.basketballplayers.viewmodels.MainActivityViewModel;


public class MainActivity extends AppCompatActivity {


    RecyclerView mRecyclerView;
    LinearLayoutManager linerLayoutManager;
    private PlayersAdapter playersAdapter;
    private MainActivityViewModel mMainActivityViewModel;
    private ArrayList<Object> itemList = new ArrayList<>();
    int pasteVisibleItems, visibleItemCount, totalItemCount ;
    int currentPage = 0 ;
    private boolean loading = true;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progressBar);

        initRecyclerView();
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.getPage((0), "20").observe(this, new Observer<PlayersData>() {
            @Override
            public void onChanged(PlayersData playersData) {


                itemList.addAll(playersData.getData());
                itemList.addAll(playersData.getData());
                itemList.add (null);
                playersAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        });



        mRecyclerView.addOnScrollListener (new RecyclerView.OnScrollListener () {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = linerLayoutManager.getChildCount ();
                    totalItemCount = linerLayoutManager.getItemCount ();
                    pasteVisibleItems = linerLayoutManager.findFirstVisibleItemPosition ();

                    if (loading) {
                        if ((visibleItemCount + pasteVisibleItems) >= totalItemCount) {
                            loading = false;

                            mMainActivityViewModel.getPage((currentPage++), "20").observe(MainActivity.this, new Observer<PlayersData>() {
                                @Override
                                public void onChanged(PlayersData playersData) {

                                   // Remove loading indicator
                                    itemList.remove (itemList.size () - 1);
                                    playersAdapter.notifyItemRemoved (itemList.size () - 1);
                                    itemList.addAll(playersData.getData());
                                    playersAdapter.notifyDataSetChanged();
                                    // Add loading indicator
                                    itemList.add (null);
                                    loading = true;
                                }
                            });

                        }
                    }
                }
            }
        });

    }

    private void initRecyclerView() {
        playersAdapter = new PlayersAdapter(MainActivity.this, itemList);
         linerLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linerLayoutManager);
        mRecyclerView.setAdapter(playersAdapter);
    }
}
