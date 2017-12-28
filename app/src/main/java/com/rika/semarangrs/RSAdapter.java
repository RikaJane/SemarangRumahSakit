package com.rika.semarangrs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rika.semarangrs.model.RS;

import java.util.List;

/**
 * Created by User on 11/12/2017.
 */

public class RSAdapter extends RecyclerView.Adapter<RSAdapter.RSHolder> {
    private String mJudul[];
    private String mKet[];
    private List<RS> mData;
    private RSListener mListener;

    public RSAdapter(String judul[], String ket[], RSListener listener) {
        mJudul = judul;
        mKet = ket;
        mListener = listener;
    }

    public RSAdapter(List<RS> data, RSListener listener) {
        mData = data;
        mListener = listener;
    }

    interface RSListener {
        void onClick(int position);
    }


    @Override
    public RSHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_rs, parent, false);
        return new RSHolder(view);
    }

    @Override
    public void onBindViewHolder(RSHolder holder, int position) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.mItemView.getLayoutParams();
        if (position == 0) {
            params.topMargin = params.bottomMargin;
        } else {
            params.topMargin = 0;
        }
        String judul = mData.get(position).getNama();
        String ket = mData.get(position).getKeterangan();
        holder.setRS(judul, ket);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public String getJudul(int position) {
        return mJudul[position];
    }

    public class RSHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView1;
        private TextView textView2;
        private View mItemView;

        public RSHolder(View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.judul);
            textView2 = (TextView) itemView.findViewById(R.id.keterangan);
            mItemView = itemView;
            mItemView.setOnClickListener(this);

        }

        public void setRS(String judul, String ket) {
            textView1.setText(judul);
            textView2.setText(ket);
        }

        @Override
        public void onClick(View v) {

            mListener.onClick(getAdapterPosition());
        }

    }
}
