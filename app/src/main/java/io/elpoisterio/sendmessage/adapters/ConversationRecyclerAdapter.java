package io.elpoisterio.sendmessage.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import io.elpoisterio.sendmessage.R;
import io.elpoisterio.sendmessage.models.ModelUser;

/**
 * Created by rishabh on 22/10/16.
 */

public class ConversationRecyclerAdapter extends RecyclerView.Adapter<ConversationRecyclerAdapter.ListViewHolder> {

    private ArrayList<ModelUser> modelUserArrayList;
    private Context context;
    public ConversationRecyclerAdapter (Context context, ArrayList<ModelUser> modelUserArrayList){
        this.context = context;
        this.modelUserArrayList = modelUserArrayList;
        Collections.reverse(this.modelUserArrayList);

    }
    public void addNewMessage(ModelUser modelUser){
        if(modelUserArrayList != null)
            this.modelUserArrayList.add(modelUser);
        else {
            modelUserArrayList = new ArrayList<>();
            modelUserArrayList.add(modelUser);
        }
        notifyDataSetChanged();
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conversations_adapter, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ModelUser modelUser = modelUserArrayList.get(position);
        if(modelUser.getType().equals("1")){
            holder.setLayoutParams("1");
        }else {
            holder.setLayoutParams("0");
        }
        holder.message.setText(modelUser.getBody());
        //holder.time.setText(Utility.getDateTime(modelUser.getDateReceived()));

    }

    @Override
    public int getItemCount() {
        if(modelUserArrayList != null)
            return modelUserArrayList.size();
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView message;

        LinearLayout.LayoutParams params;

        public ListViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
            //ll = (LinearLayout) itemView.findViewById(R.id.ll);
           // time = (TextView) itemView.findViewById(R.id.time);
        }
        public void setLayoutParams(String value){
            params = new LinearLayout.LayoutParams(getDPI(220), RecyclerView.LayoutParams.WRAP_CONTENT);
            int margin = getDPI(10);
            params.setMargins(margin, margin, margin, margin);

            params.gravity = setGravity(value);
            message.setLayoutParams(params);
        }
        public int setGravity(String value){
            if(value.equals("1")){
                return Gravity.START;
            }
            return Gravity.END;
        }
        public int getDPI(int value) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());

        }
    }
}
