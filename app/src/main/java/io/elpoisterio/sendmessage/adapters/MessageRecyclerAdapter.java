package io.elpoisterio.sendmessage.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.elpoisterio.sendmessage.R;
import io.elpoisterio.sendmessage.activity.Conversation;
import io.elpoisterio.sendmessage.models.ModelUser;

/**
 * Created by rishabh on 21/10/16.
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ListViewHolder> {

    private Context context;
    private ArrayList<ModelUser> modelUserArrayList;
    public MessageRecyclerAdapter(Context context, ArrayList<ModelUser> modelUserArrayList){
        this.context = context;
        this.modelUserArrayList = modelUserArrayList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_recycler_view, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        final ModelUser modelUser = modelUserArrayList.get(position);
        holder.message.setText(modelUser.getBody());
        holder.name.setText(modelUser.getAddress());
        holder.time.setText(modelUser.getTime());
        holder.rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Conversation.class);
                intent.putExtra( "id",modelUser.getThread_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelUserArrayList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView time;
        public TextView message;
        RelativeLayout rv ;
        ListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            message = (TextView) itemView.findViewById(R.id.message);
            rv = (RelativeLayout) itemView.findViewById(R.id.rv);
        }
    }
}
