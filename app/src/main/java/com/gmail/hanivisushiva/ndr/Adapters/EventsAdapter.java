package com.gmail.hanivisushiva.ndr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.hanivisushiva.ndr.Detail;
import com.gmail.hanivisushiva.ndr.EventsDetail;
import com.gmail.hanivisushiva.ndr.Model.Event.Datum;
import com.gmail.hanivisushiva.ndr.Model.Event.Events;
import com.gmail.hanivisushiva.ndr.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewholder> {

    private List<Datum> eventsList;
    private Context context;
    String title_txt;
    String img_txt;
    String des_txt;


    public EventsAdapter(List<Datum> eventsList, Context context) {
        this.eventsList = eventsList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventsViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.event_card,null);
        return new EventsViewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewholder eventsViewholder, int i) {



        final Datum datum = eventsList.get(i);

        eventsViewholder.title.setText(datum.getTxtTitle());
        eventsViewholder.location.setText(datum.getTxtLocation());
        eventsViewholder.date.setText(eventsList.get(i).getTxtDate());
        Picasso.with(context).load(datum.getTxtFeatureImage()).into(eventsViewholder.imageView);
        eventsViewholder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),EventsDetail.class);
                intent.putExtra("title",datum.getTxtTitle());
                intent.putExtra("des",datum.getTxtDescription());
                intent.putExtra("img",datum.getTxtFeatureImage());
                intent.putExtra("date",datum.getTxtDate());
                intent.putExtra("loc",datum.getTxtLocation());
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class EventsViewholder extends RecyclerView.ViewHolder {
        TextView title,date,location;
        ImageView imageView;
        LinearLayout cardView;
        public EventsViewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
