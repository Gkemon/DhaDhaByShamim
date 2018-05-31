package binarygeek.dhadhabyshamim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import binarygeek.dhadhabyshamim.Activity.DhaDhaShowActivity;
import binarygeek.dhadhabyshamim.HelperClasses.RecyclerItemClickListener;
import binarygeek.dhadhabyshamim.Model.Episode;
import binarygeek.dhadhabyshamim.R;


/**
 * Created by uy on 10/28/2017.
 */

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeItemsViewHolder> {
    private ArrayList<Episode> episodeArrayList;
    private Context context;
    private RecyclerItemClickListener recyclerItemClickListener;
//    public ItemsAdapter(ArrayList<Items> Data) {
//        episodeArrayList = Data;
//    }
    public EpisodeAdapter(Context context) {
        this.context=context;
        episodeArrayList = new ArrayList<>();
    }

    private void add(Episode item) {
        episodeArrayList.add(item);
        notifyItemInserted(episodeArrayList.size() - 1);
    }


    public void addAll(ArrayList<Episode> items) {
        if(items!=null) {
            for (Episode i : items) {
                add(i);
            }
        }
    }


    @Override
    public EpisodeItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        Log.d("GK","in onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episod_item_cards, parent, false);
        final EpisodeItemsViewHolder holder = new EpisodeItemsViewHolder(view);

        //CLICK LISTENER
        holder.itemView.findViewById(R.id.card_view_for_items).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = holder.getAdapterPosition();

                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerItemClickListener != null) {
                        recyclerItemClickListener.onItemClick(adapterPos, holder.itemView.findViewById(R.id.card_view_for_items));
                    }
                }
            }
        });

        return holder;
    }
    public void setOnItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

//TODO:set saved icon on onBindViewHolder not onCreateViewHolder
    @Override
    public void onBindViewHolder(final EpisodeItemsViewHolder holder,  int position) {

holder.Episode.setText(episodeArrayList.get(position).episodeName);
String TotalDhaDhaCount="এই পর্বের ধাধার সংখ্যা "+episodeArrayList.get(position).dhaDhas.size();
holder.TotalDhaDhaInEpisode.setText(TotalDhaDhaCount);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DhaDhaShowActivity.class);
                intent.putExtra("Episode",holder.getAdapterPosition());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return episodeArrayList.size();
    }
}

 class EpisodeItemsViewHolder extends RecyclerView.ViewHolder {

    public TextView Episode;
    public TextView TotalDhaDhaInEpisode;
    public View card;

    public EpisodeItemsViewHolder(View v) {
        super(v);
        card=v.findViewById(R.id.card_view_for_items);
        Episode = (TextView) v.findViewById(R.id.episode_name);
        TotalDhaDhaInEpisode = (TextView) v.findViewById(R.id.total_dhadhas);
    }
}