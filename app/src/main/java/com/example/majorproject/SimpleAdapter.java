package com.example.majorproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.CustomViewHolder> {

    private ArrayList<Item> mData;

    public SimpleAdapter(ArrayList<Item> list) {

        mData = list;

    }



    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.item, parent, false) ;
        return new SimpleAdapter.CustomViewHolder(view) ;

    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Item item = mData.get(position) ;
        holder.titleText.setText(item.getContent());
    }


    @Override
    public int getItemCount() {   return (null != mData? mData.size():0);}


    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }
    private OnItemClickListener onItemClickListener = null;


    public void setOnItemClickListener(OnItemClickListener listener)
    {
        onItemClickListener = listener;
    }




   class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView titleText;
        TextView pw;
        TextView uid;

        CustomViewHolder(@NonNull View itemView) {

            super(itemView);
            titleText=itemView.findViewById(R.id.textview);
            pw = itemView.findViewById(R.id.pw);
            uid = itemView.findViewById(R.id.uid);


            itemView.setOnClickListener(v->{

                int pos = getAdapterPosition();
                if(pos!=RecyclerView.NO_POSITION)
                    if(onItemClickListener != null)
                        onItemClickListener.onItemClick(v, pos);
            });

        }
    }

}
