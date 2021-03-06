package com.codepath.bestsellerlistapp;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.bestsellerlistapp.models.BestSellerBook;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BestSellerBook} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class BestSellerBooksRecyclerViewAdapter extends RecyclerView.Adapter<BestSellerBooksRecyclerViewAdapter.BookViewHolder> {

    private final List<BestSellerBook> books;
    private final OnListFragmentInteractionListener mListener;

    public BestSellerBooksRecyclerViewAdapter(List<BestSellerBook> items, OnListFragmentInteractionListener listener) {
        books = items;
        mListener = listener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_best_seller_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BookViewHolder holder, final int position) {
        holder.mItem = books.get(position);
        holder.mBookTitle.setText(books.get(position).title);
        holder.mBookAuthor.setText(books.get(position).author);
        holder.mBookRanking.setText(String.valueOf(books.get(position).rank));
        Glide.with(holder.mView).load(books.get(position).bookImageUrl).centerInside().into(holder.mBookImage);
        holder.mBookDiscript.setText(books.get(position).description);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onItemClick(holder.mItem);
                }
            }
        });
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String url = books.get(position).amazonUrl;
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url));
                    holder.mView.getContext().startActivity(browserIntent);
                }

        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mBookTitle;
        public final TextView mBookAuthor;
        public final TextView mBookRanking;
        public final ImageView mBookImage;
        public final TextView mBookDiscript;
        public final Button mButton;
        public BestSellerBook mItem;

        public BookViewHolder(View view) {
            super(view);
            mView = view;
            mBookTitle = (TextView) view.findViewById(R.id.book_title);
            mBookAuthor = (TextView) view.findViewById(R.id.book_author);
            mBookRanking = (TextView)view.findViewById(R.id.ranking);
            mBookImage = (ImageView)view.findViewById(R.id.book_image);
            mBookDiscript = (TextView) view.findViewById(R.id.book_description);
            mButton = (Button) view.findViewById(R.id.buy_button);
            mButton.setText("BUY ON AMAZON");
        }

        @Override
        public String toString() {
            return mBookTitle.toString() + " '" + mBookAuthor.getText() + "'";
        }
    }
}
