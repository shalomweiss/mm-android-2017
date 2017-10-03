package org.tsofen.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.tsofen.mentorim.R;

/**
 * Created by minitour on 01/10/2017.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * The recycler view connected
     */
    protected RecyclerView listView;



    /**
     * A method called when view is ready to be used
     * @param view
     */
    abstract public void viewDidLoad(View view);

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view, container, false);
        setup(rootView);
        viewDidLoad(rootView);

        return rootView;
    }

    private void setup(View view){
        listView = view.findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
