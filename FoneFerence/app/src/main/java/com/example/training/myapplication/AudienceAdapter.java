package com.example.training.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

public class AudienceAdapter extends RecyclerView.Adapter<AudienceAdapter.AudienceViewHolder> {

    private ArrayList<HashMap<String, String>> arrayList;
    private Context context;


    AudienceAdapter(ArrayList<HashMap<String, String>> arrayList, Context context)
    {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public AudienceAdapter.AudienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.activity_audience_list_row,parent,false);
        return new AudienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AudienceAdapter.AudienceViewHolder holder, int position) {


        if (arrayList.get(position) != null) {
            HashMap<String, String> hashMap;
            hashMap = arrayList.get(position);
            holder.audiencename.setText(hashMap.get("Name"));
            holder.audiencejobtitle.setText(hashMap.get("JobTitle"));
            String UID=hashMap.get("userid");
            String ImageURL=" https://firebasestorage.googleapis.com/v0/b/foneferenca.appspot.com/o/images%2F"+UID+"?alt=media&token=8913f66f-745e-4e8a-8904-a657c8db05b3";
            Glide.with(context)
                    .load("https://firebasestorage.googleapis.com/v0/b/foneferenca.appspot.com/o/images%2Fj462kebX9GO01JaiqWP1nBBRf8j2?alt=media&token=8913f66f-745e-4e8a-8904-a657c8db05b3")
                    .into(holder.audienceimage);

        }
//        holder.audiencename.setText(arrayList.get(position));
//        holder.audiencejobtitle.setText(arrayList.get(position+1));
//        holder.audienceimage.setImageResource(R.drawable.baseline_event_black_24dp);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class AudienceViewHolder extends RecyclerView.ViewHolder {

        TextView audiencename,audiencejobtitle;
        ImageView audienceimage;


        AudienceViewHolder(View itemView) {
            super(itemView);

            audiencename=itemView.findViewById(R.id.txtAudianceName);
            audiencejobtitle=itemView.findViewById(R.id.txtJobTitile);
            audienceimage=itemView.findViewById(R.id.audienceimage);

        }
    }
}
