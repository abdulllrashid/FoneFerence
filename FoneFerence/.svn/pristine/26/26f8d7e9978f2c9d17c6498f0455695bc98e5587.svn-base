package com.example.training.myapplication;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class CertificateAdapter extends RecyclerView.Adapter<CertificateAdapter.CertificateViewHolder> {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private ArrayList<String> arrayList;

    CertificateAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;

    }


    @Override
    public CertificateAdapter.CertificateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cardview_certificates_row, parent, false);
        return new CertificateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CertificateAdapter.CertificateViewHolder holder, int position) {
        holder.cert_name.setText(arrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class CertificateViewHolder extends RecyclerView.ViewHolder {
        TextView cert_name;
        Button b1;

        public CertificateViewHolder(View itemView) {
            super(itemView);

            cert_name = itemView.findViewById(R.id.cert_name);
        }
    }
}
