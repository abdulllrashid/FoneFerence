package com.example.training.myapplication;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder> {

    private DownloadManager downloadManager;
    Slides slidobj;
    private Context context;
    private long enqueue;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference slideDbRef = database.getReference("/files/slides");


    private ArrayList<Slides> arrayList;

    SlideAdapter(ArrayList<Slides> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public SlideAdapter.SlideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_slide_row, parent, false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SlideViewHolder holder, int position) {
        if (arrayList.get(position) != null) {
            holder.slide_name.setText(arrayList.get(position).getSlideTitle());
        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SlideViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView slide_name;
        Button slide_button;

        SlideViewHolder(View itemView) {
            super(itemView);

            slide_name = itemView.findViewById(R.id.slide_name);
            slide_button = itemView.findViewById(R.id.btn_slide_dwnd);
            slide_button.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Downloading: ", Toast.LENGTH_LONG).show();
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(arrayList.get(getAdapterPosition()).getSlideurl()));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setVisibleInDownloadsUi(true);
            request.setTitle("file1");
            request.setDestinationInExternalPublicDir("/Fonferenca/Downloads", "Slide");
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            enqueue = downloadManager.enqueue(request);
            context.registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }

        BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {

            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, Intent intent) {
                slide_button.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.tick));//code for changing image
                slide_button.setEnabled(false); //code for disabling download button after the download
                Toast.makeText(context.getApplicationContext(), "Downloaded", Toast.LENGTH_SHORT).show();
            }
        };

    }
}

