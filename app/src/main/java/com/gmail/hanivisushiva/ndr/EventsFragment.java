package com.gmail.hanivisushiva.ndr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gmail.hanivisushiva.ndr.Adapters.EventsAdapter;
import com.gmail.hanivisushiva.ndr.Model.Event.Datum;
import com.gmail.hanivisushiva.ndr.Model.Event.Events;
import com.gmail.hanivisushiva.ndr.Model.Post.Posts;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    RecyclerView recyclerView;
    List<Datum> eventList;
    List<Datum> events_trendingList;
    private SliderLayout mDemoSlider;
    HashMap<String,Datum> trending_data= new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_events);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mDemoSlider = (SliderLayout) view.findViewById(R.id.post_slider);
        events_trendingList = get_trending_events();

        get_events();



        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(9000);
        mDemoSlider.addOnPageChangeListener(this);



        return view;
    }



    private List get_trending_events(){
        Call<Events> trending_events = RetrofitClient.getmInstance().getApi().get_trending_events();


        trending_events.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Events posts = response.body();
                if (posts.getStatus()){

                    events_trendingList = posts.getData();


                    HashMap<String,String> file_maps = new HashMap<String, String>();

                    for (int i = 0;i<events_trendingList.size();i++){
                        trending_data.put(events_trendingList.get(i).getTxtTitle(),events_trendingList.get(i));
                        file_maps.put(events_trendingList.get(i).getTxtTitle(),events_trendingList.get(i).getTxtFeatureImage());
                    }

                    for(String name : file_maps.keySet()){
                        TextSliderView textSliderView = new TextSliderView(getContext());
                        // initialize a SliderLayout
                        textSliderView
                                .description(name)
                                .image(file_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(EventsFragment.this);

                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                .putString("extra",name);

                        mDemoSlider.addSlider(textSliderView);
                    }

                }
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {

            }
        });

        return events_trendingList;
    }




    private void get_events(){
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<Events> eventsCall = RetrofitClient.getmInstance().getApi().get_events();

        eventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Events events = response.body();

                if (events.getStatus()){
                    eventList = events.getData();

                    EventsAdapter eventsAdapter = new EventsAdapter(eventList,getContext());
                    recyclerView.setAdapter(eventsAdapter);
                }

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.e("error events",t.getMessage());
                progressDialog.dismiss();
            }
        });
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {

        Datum title = trending_data.get(slider.getDescription());


        Intent intent = new Intent(getContext(),EventsDetail.class);
        intent.putExtra("title",title.getTxtTitle());
        intent.putExtra("des",title.getTxtDescription());
        intent.putExtra("img",title.getTxtFeatureImage());
        intent.putExtra("date",title.getTxtDate());
        intent.putExtra("loc",title.getTxtLocation());
        startActivity(intent);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
