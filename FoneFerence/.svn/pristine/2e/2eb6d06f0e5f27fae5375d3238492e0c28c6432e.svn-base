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

import java.util.ArrayList;
import java.util.HashMap;

public class SlidenoteAdapter extends RecyclerView.Adapter<SlidenoteAdapter.SlideNoteViewHolder> {
    Context context;
    DownloadManager downloadManager;
    private long enqueue;


    private ArrayList<SlideNoteBean> arrayList;

    SlidenoteAdapter(ArrayList<SlideNoteBean> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public SlidenoteAdapter.SlideNoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_slidenote_row, parent, false);
        return new SlideNoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SlidenoteAdapter.SlideNoteViewHolder holder, int position) {
        if (arrayList.get(position) != null) {
            holder.slideNote_name.setText(arrayList.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class SlideNoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView slideNote_name;
        Button slideNote_button;

        public SlideNoteViewHolder(View itemView) {
            super(itemView);

            slideNote_name = (TextView) itemView.findViewById(R.id.slidenote_name);
            slideNote_button = (Button) itemView.findViewById(R.id.btn_slidenote_dwnd);
            slideNote_button.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {


            Toast.makeText(context.getApplicationContext(), "Downloading..", Toast.LENGTH_SHORT).show();
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(arrayList.get(getAdapterPosition()).getUrl()));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);request.setVisibleInDownloadsUi(true);
            request.setTitle("file1");
            request.setDestinationInExternalPublicDir("/Fonferenca/Downloads", "SlideNote");
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            enqueue = downloadManager.enqueue(request);
            context.registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }

        BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {
            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, Intent intent) {
                slideNote_button.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.tick));//code for changing image
                slideNote_button.setEnabled(false);
                //code for disabling download button after the download
                Toast.makeText(context.getApplicationContext(), "Downloaded", Toast.LENGTH_SHORT).show();

            }
        };

    }
}