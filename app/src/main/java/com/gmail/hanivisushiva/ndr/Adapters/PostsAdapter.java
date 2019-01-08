package com.gmail.hanivisushiva.ndr.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.hanivisushiva.ndr.Detail;
import com.gmail.hanivisushiva.ndr.Model.Post.Datum;
import com.gmail.hanivisushiva.ndr.Model.Post.Posts;
import com.gmail.hanivisushiva.ndr.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {

    private Context context;
    private List<Datum> postsList;
    String title_txt;
    String img_txt;
    String des_txt;

    public PostsAdapter(Context context, List<Datum> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_card,null);
        return new PostsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder postsViewHolder, int i) {




        final Datum postData = postsList.get(i);





        postsViewHolder.title.setText(postsList.get(i).getTxtTitle());
        postsViewHolder.date.setText(postsList.get(i).getTxtDate());
        Picasso.with(context).load(postsList.get(i).getTxtFeatureImage()).into(postsViewHolder.imageView);
        postsViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(),Detail.class);
                intent.putExtra("title",postData.getTxtTitle());
                intent.putExtra("des",postData.getTxtDescription());
                intent.putExtra("img",postData.getTxtFeatureImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }


    public class PostsViewHolder extends RecyclerView.ViewHolder{
        TextView title,date;
        ImageView imageView;
        LinearLayout cardView;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
           title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
    private String  getTime(String s) throws ParseException {

        String date=s;
        SimpleDateFormat spf=new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa");
        Date newDate=spf.parse(date);
        spf= new SimpleDateFormat("dd-MM-yyyy");
        date = spf.format(newDate);
        return date;
    }

}
