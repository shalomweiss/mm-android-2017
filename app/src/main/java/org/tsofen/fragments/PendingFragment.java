package org.tsofen.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.tsofen.mentorim.R;
import org.tsofen.model.classes.Meeting;

import java.util.ArrayList;

/**
 * Created by minitour on 01/10/2017.
 */

public class PendingFragment extends BaseFragment {

    ArrayList<Meeting> meetingArrayList = new ArrayList<>();

    @Override
    public void viewDidLoad(View view) {

    }

    class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.PendingViewHolder>{

        @Override
        public PendingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            return new PendingViewHolder(inflater.inflate(R.layout.list_item_meeting,parent,false));
        }

        @Override
        public void onBindViewHolder(PendingViewHolder holder, int position) {
            Meeting o = meetingArrayList.get(position);
            
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
