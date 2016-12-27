package com.close5.close5adapter.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.close5.close5adapter.Model;
import com.close5.close5adapter.R;

/**
 * Created by ahurwitz on 12/18/16.
 */

public class C5ViewHolder extends RecyclerView.ViewHolder {

    FrameLayout recyclerCell;
    ImageView imageView;

    String imageUrl;

    ViewHolderListener viewHolderListener;

    public interface ViewHolderListener {
        void onCellClicked(String url, int adapterPosition);
    }

    public C5ViewHolder(View view, ViewHolderListener viewHolderListener) {
        super(view);
        recyclerCell = (FrameLayout) view.findViewById(R.id.recycler_cell);
        imageView = (ImageView) view.findViewById(R.id.recycler_cell_image);

        this.viewHolderListener = viewHolderListener;
        recyclerCell.setOnClickListener(this::onClick);
    }

    public void bind(Context context, Model.Item item) {
        this.imageUrl = item.getImageUrl();
        Log.v(C5ViewHolder.class.getSimpleName(), "getLength - " + imageUrl + " " + String
                .valueOf(context));

            Glide.with(context)
                    .load(imageUrl != null && !imageUrl.isEmpty() ? imageUrl : R.drawable.ic_error_black_24dp)
                    .error(R.drawable.ic_error_black_24dp)
                    .into(imageView);
    }

    private void onClick(View view) {
        viewHolderListener.onCellClicked(this.imageUrl, getLayoutPosition());
    }
}
