package org.tsofen.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tsofen.mentorim.R;
import org.tsofen.views.EndlessRecyclerViewScrollListener;

/**
 * Created by minitour on 01/10/2017.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * The recycler view connected
     */
    protected RecyclerView listView;

    protected SwipeRefreshLayout swipeContainer;


    protected EndlessRecyclerViewScrollListener scrollListener;

    /**
     * A method called when view is ready to be used
     * @param view
     */
    abstract public void viewDidLoad(View view,RecyclerView listView);

    abstract public void onLoadMore(int page,int totalItemsCount, RecyclerView view);

    abstract public void onRefresh();

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        setup(rootView);
        viewDidLoad(rootView,listView);

        swipeContainer.setOnRefreshListener(this::onRefresh);


        return rootView;
    }

    protected void stopRefreshing(){
        swipeContainer.setRefreshing(false);
    }

    private void setup(View view){
        listView = view.findViewById(R.id.listView);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                BaseFragment.this.onLoadMore(page,totalItemsCount,view);
            }
        };

        listView.addOnScrollListener(scrollListener);
    }
}
