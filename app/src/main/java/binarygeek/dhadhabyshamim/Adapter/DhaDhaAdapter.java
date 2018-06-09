package binarygeek.dhadhabyshamim.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import binarygeek.dhadhabyshamim.HelperClasses.RecyclerItemClickListener;
import binarygeek.dhadhabyshamim.Model.DhaDha;
import binarygeek.dhadhabyshamim.R;


/**
 * Created by uy on 10/28/2017.
 */

public class DhaDhaAdapter extends RecyclerView.Adapter<DhadhaItemsViewHolder> {
    private ArrayList<DhaDha> dhadhaArrayList;
    private Context context;
    private RecyclerItemClickListener recyclerItemClickListener;
//    public ItemsAdapter(ArrayList<Items> Data) {
//        dhadhaArrayList = Data;
//    }
    public DhaDhaAdapter(Context context) {
        this.context=context;
        dhadhaArrayList = new ArrayList<>();
    }

    private void add(DhaDha item) {
        dhadhaArrayList.add(item);
        notifyItemInserted(dhadhaArrayList.size() - 1);
    }


    public void addAll(ArrayList<DhaDha> items) {
        if(items!=null) {
            for (DhaDha i : items) {
                add(i);
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public DhadhaItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        Log.d("GK","in onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dhadha_item_cards, parent, false);
        final DhadhaItemsViewHolder holder = new DhadhaItemsViewHolder(view);

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
    public void onBindViewHolder(final DhadhaItemsViewHolder holder, int position) {

        String dhadha=dhadhaArrayList.get(position).dhadha;
        String answer=dhadhaArrayList.get(position).answer;

        holder.dhadha.setText(dhadha);

        String BANGLA_FONT_SOLEMAN_LIPI="BenSenHandwriting.ttf";
        Typeface tf = Typeface.createFromAsset(context.getAssets(),
                BANGLA_FONT_SOLEMAN_LIPI);
        holder.answar.setTypeface(tf);
        holder.answar.setText(answer);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

//                    String BANGLA_FONT_SOLEMAN_LIPI="/BenSenHandwriting.ttf";
//                    Typeface tf = Typeface.createFromAsset(context.getAssets(),
//                    BANGLA_FONT_SOLEMAN_LIPI);
//                    holder.answar.setTypeface(tf);
//                    holder.answar.setText(answer);

           // holder.answar.setText(Html.fromHtml(answer, Html.FROM_HTML_MODE_LEGACY));
        } else {
           // holder.answar.setText(Html.fromHtml(answer));
        }


holder.answarButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

       final AlphaAnimation alphaAnimationHideToShow,alphaAnimationShowToHide;

        alphaAnimationHideToShow = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimationShowToHide = new AlphaAnimation(1.0f,0.0f);

        alphaAnimationHideToShow.setDuration(1000);
        alphaAnimationShowToHide.setDuration(1000);

        if(holder.answarButton.getText().equals("উত্তর দেখুন")){
            holder.answarButton.setText("উত্তর লুকান");
            holder.answar.setVisibility(View.VISIBLE);

            holder.answar.setAnimation(alphaAnimationHideToShow);
            holder.answar.startAnimation(alphaAnimationHideToShow);

        }
        else{
            holder.answarButton.setText("উত্তর দেখুন");

            holder.answar.setAnimation(alphaAnimationShowToHide);

            holder.answar.startAnimation(alphaAnimationShowToHide);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    holder.answar.setVisibility(View.GONE);
                }
            }, 1000);
        }

//
//        if(holder.answarButton.getText().equals("উত্তর দেখুন")){
//            holder.answarButton.setText("উত্তর লুকান");
//            holder.answar.setDuration(3000);
//            holder.answar.setVisibility(View.VISIBLE);
//            holder.answar.show();
//        }
//        else{
//            holder.answarButton.setText("উত্তর দেখুন");
//            holder.answar.setDuration(3000);
//            holder.answar.hide();
//            holder.answar.setVisibility(View.GONE);
//        }




    }
});

    }
    @Override
    public int getItemCount() {
        return dhadhaArrayList.size();
    }
}

class DhadhaItemsViewHolder extends RecyclerView.ViewHolder {

   public TextView dhadha;
   public Button answarButton;
   public TextView answar;

   public DhadhaItemsViewHolder(View v) {
       super(v);
       dhadha = (TextView) v.findViewById(R.id.dhadha_name);
       answarButton = (Button) v.findViewById(R.id.anwer_button) ;
       answar = (TextView) v.findViewById(R.id.answer);
   }
}