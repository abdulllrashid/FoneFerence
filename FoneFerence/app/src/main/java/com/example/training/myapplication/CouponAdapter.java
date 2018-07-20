package com.example.training.myapplication;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CoupenViewHolder> {

    private final Context context;
    private DownloadManager downloadManager;
    private long enqueue;
    private Coupon coupon;



    final FirebaseDatabase database = FirebaseDatabase.getInstance();


    private ArrayList<Coupon> arrayList;

    CouponAdapter(ArrayList<Coupon> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }



    @Override
    public CouponAdapter.CoupenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_coupon_row, parent, false);
        return new CoupenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CoupenViewHolder holder, int position) {
        holder.coupen_name.setText((arrayList.get(position)).getCouponTitle());

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CoupenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView coupen_name;
        Button coupon_del;

        public CoupenViewHolder(View itemView) {
            super(itemView);

            coupen_name = (TextView) itemView.findViewById(R.id.coupon_name);
            coupon_del = (Button) itemView.findViewById(R.id.btn_coupon_del);

            coupon_del.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Toast.makeText(v.getContext(), "Downloading: ", Toast.LENGTH_LONG).show();
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(arrayList.get(getAdapterPosition()).getCouponurl()));
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
            request.setVisibleInDownloadsUi(true);
            request.setTitle("file1");
            request.setDestinationInExternalPublicDir("/Fonferenca/Downloads", "Coupon");
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            enqueue = downloadManager.enqueue(request);
            context.registerReceiver(downloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
        BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {

            @SuppressLint("NewApi")
            @Override
            public void onReceive(Context context, Intent intent) {
                coupon_del.setBackground(context.getApplicationContext().getResources().getDrawable(R.drawable.tick));//code for changing image
                coupon_del.setEnabled(false); //code for disabling download button after the download
                Toast.makeText(context.getApplicationContext(), "Downloaded", Toast.LENGTH_SHORT).show();
            }
        };

    }
}



