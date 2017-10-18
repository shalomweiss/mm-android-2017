package org.tsofen.fragments;


import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.tsofen.mentorim.MeetingController;
import org.tsofen.mentorim.R;
import org.tsofen.model.APIManager;
import org.tsofen.model.DataManager;
import org.tsofen.model.classes.Meeting;
import org.tsofen.model.classes.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win10 on 18/10/2017.
 */

public class HistoryFragment extends BaseFragment {
    ArrayList<Meeting> meetingArrayList=new ArrayList<>();
    HistoryAdapter adapter=new HistoryAdapter();
    User user;
    User[] associatedUsers;
    String token;
    DataManager manager;
    int currentPage = 1;
    boolean didLoadAll = false;
    @Override
    public void viewDidLoad(View view, RecyclerView listView) {
        meetingArrayList.clear();
        manager = DataManager.getInstance(getContext());
        listView.setAdapter(adapter);
        loadNextDataFromApi(currentPage);
    }

    @Override
    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        if(page != currentPage && !didLoadAll) {
            loadNextDataFromApi(page);
            currentPage = page;
        }
    }

    @Override
    public void onRefresh() {
        didLoadAll = false;
        currentPage = 1;
        meetingArrayList.clear();
        adapter.notifyDataSetChanged();
        loadNextDataFromApi(currentPage);
    }
    public void loadNextDataFromApi(int offset) {
        user = manager.getUser();
        token = manager.getToken();
        if(user != null){
            APIManager.getInstance().getMeetings(user.getId(),token,2,15,offset,(response, meetingList, exception) -> {
                try{
                    getActivity().runOnUiThread(this::stopRefreshing);
                }catch (NullPointerException e){}

                if(exception == null && response.isOK()){
                    if(meetingList.size() > 0 ) {
                        updateData(meetingList);
                    }else{
                        didLoadAll = true;
                    }
                }else{
                    Snackbar.make(listView, "Failed to load!", Snackbar.LENGTH_LONG).setAction("Try Again", view -> {
                        loadNextDataFromApi(offset);
                    }).show();
                }
            });
        }
    }

    public void updateData(List<Meeting> newItems){
        meetingArrayList.addAll(newItems);
        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());

    }
    class HistoryAdapter extends RecyclerView.Adapter<HistoryFragment.HistoryAdapter.HistoryViewHolder>{

        @Override
        public HistoryFragment.HistoryAdapter.HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View v = inflater.inflate(R.layout.list_item_meeting,parent,false);
            return new HistoryFragment.HistoryAdapter.HistoryViewHolder(v);
        }

        @Override
        public void onBindViewHolder(HistoryFragment.HistoryAdapter.HistoryViewHolder holder, int position) {
            Meeting o = meetingArrayList.get(position);
            holder.tvTitle.setText(o.getMeetingTitle(user.isMentor(),manager.getAssociatedUsers()));
            holder.tvDate.setText(o.getStartTime());
            holder.tvSubtitle.setText(o.getLocation());

            holder.itemView.setOnClickListener(view -> {
                Intent i = new Intent(getContext(),MeetingController.class);
                //i.putExtra(MeetingController.Constants.MEETING_ID,o.getMeetingId());
                i.putExtra(MeetingController.Constants.MEETING_ID,o);
                getContext().startActivity(i);
            });
        }

        @Override
        public int getItemCount() {
            return meetingArrayList.size();
        }

        class HistoryViewHolder extends RecyclerView.ViewHolder{

            TextView tvTitle;
            TextView tvSubtitle;
            TextView tvDate;
            ImageView imageView;

            public HistoryViewHolder(View itemView) {
                super(itemView);
                //link to subviews
                itemView.findViewById(R.id.badge_completed).setVisibility(View.VISIBLE);
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvSubtitle = itemView.findViewById(R.id.tvSubtitle);
                tvDate = itemView.findViewById(R.id.tvDate);
                imageView = itemView.findViewById(R.id.imageView);
            }
        }
    }

}
