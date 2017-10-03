package org.tsofen.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import org.tsofen.mentorim.R;
import org.tsofen.model.APIManager;
import org.tsofen.model.DataManager;
import org.tsofen.model.classes.Meeting;
import org.tsofen.model.classes.User;
import org.tsofen.views.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minitour on 01/10/2017.
 */

public class PendingFragment extends BaseFragment {

    ArrayList<Meeting> meetingArrayList = new ArrayList<>();
    PendingAdapter adapter = new PendingAdapter();
    User user;
    String token;

    private EndlessRecyclerViewScrollListener scrollListener;
    @Override
    public void viewDidLoad(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(linearLayoutManager);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page);
            }
        };

        listView.addOnScrollListener(scrollListener);
        listView.setAdapter(adapter);

        DataManager manager = DataManager.getInstance(getContext());
        user = manager.getUser();
        token = manager.getToken();
        loadNextDataFromApi(1);

    }

    public void loadNextDataFromApi(int offset) {
        if(user != null){
            APIManager.getInstance().getMeetings(user.getId(),token,0,15,offset,(response,meetingList,exception) -> {
                if(exception == null && response.isOK()){
                    updateData(meetingList);
                }else{
                    //TODO: show error
                }
            });
        }
    }

    public void updateData(List<Meeting> newItems){
        meetingArrayList.addAll(newItems);
        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());

    }

    class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.PendingViewHolder>{

        @Override
        public PendingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            return new PendingViewHolder(inflater.inflate(R.layout.list_item_meeting,parent,false));
        }

        @Override
        public void onBindViewHolder(PendingViewHolder holder, int position) {
            Meeting o = meetingArrayList.get(position);
            holder.tvTitle.setText("Meeting ID: "+ o.getId());
            holder.tvDate.setText(o.getAt());
            holder.tvSubtitle.setText(o.getName());
        }

        @Override
        public int getItemCount() {
            return meetingArrayList.size();
        }

        class PendingViewHolder extends RecyclerView.ViewHolder{

            TextView tvTitle;
            TextView tvSubtitle;
            TextView tvDate;
            ImageView imageView;

            public PendingViewHolder(View itemView) {
                super(itemView);
                //link to subviews
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvSubtitle = itemView.findViewById(R.id.tvSubtitle);
                tvDate = itemView.findViewById(R.id.tvDate);
                imageView = itemView.findViewById(R.id.imageView);


            }
        }
    }
}
