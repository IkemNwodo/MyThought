package com.example.iycomo.mythoughts;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ThoughtsAdapter extends RecyclerView.Adapter<ThoughtsAdapter.ThoughtsViewHolder> {

    // Member variable for handling item clicks.
    final private ItemClickListener mItemClickListener;

    // Variables for caching thoughts
    private List<ThoughtsModel> mThoughtsModel;
    private LayoutInflater mInflater;


    private Context mContext;

    // Constant for the date format
    private static final String DATE_FORMAT = "dd:MM";

    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
    public ThoughtsAdapter(Context context, ItemClickListener listener){
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public ThoughtsAdapter.ThoughtsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.thought_layout, parent, false);
        return new ThoughtsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThoughtsAdapter.ThoughtsViewHolder holder, int position) {
        // Determine the values of the wanted data
        ThoughtsModel thoughtsModel = mThoughtsModel.get(position);
        String title = thoughtsModel.getTitle();
        String description = thoughtsModel.getDescription();
        String date = dateFormat.format(thoughtsModel.getDate());

        // Set values
        holder.titleView.setText(title);
        holder.descriptionView.setText(description);
        holder.dateView.setText(date);
    }

    void setWords(List<ThoughtsModel> thoughtsModels){
        mThoughtsModel = thoughtsModels;
        notifyDataSetChanged();
    }

    public ThoughtsModel getThoughtsModelAtPosition(int position){
        return mThoughtsModel.get(position);
    }

    @Override
    public int getItemCount() {
      return mThoughtsModel != null ? mThoughtsModel.size() : 0;
    }

    /**
     *
     **/

    public interface ItemClickListener{
        void onItemClickListener(int itemId);
    }


     class ThoughtsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleView;
        TextView descriptionView;
        TextView dateView;


        ThoughtsViewHolder(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.title);
            descriptionView = itemView.findViewById(R.id.description);
            dateView = itemView.findViewById(R.id.date);

            /**editButton = itemView.findViewById(R.id.edit_button);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AddThought.class);
                    int elementId = mThoughtsModel.get(getAdapterPosition()).getId();
                    intent.putExtra(AddThought.THOUGHT_ID, elementId);
                    mContext.startActivity(intent);
                }
            });**/
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int elementId = mThoughtsModel.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }

}
