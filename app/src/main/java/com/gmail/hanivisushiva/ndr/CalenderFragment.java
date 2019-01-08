package com.gmail.hanivisushiva.ndr;

import android.app.ProgressDialog;
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
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.slider.library.SliderLayout;
import com.gmail.hanivisushiva.ndr.Adapters.EventsAdapter;
import com.gmail.hanivisushiva.ndr.Model.Event.Datum;
import com.gmail.hanivisushiva.ndr.Model.Event.Events;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderFragment extends Fragment {

    RecyclerView recyclerView;
    List<Datum> postList;
    private SliderLayout mDemoSlider;
    private CalendarView calendarView;
    private TextView selected_text;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_calender,container,false);

        calendarView = view.findViewById(R.id.calender);
        selected_text = view.findViewById(R.id.selected_date);



        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String selectedDate = sdf.format(new Date(calendarView.getDate()));

        Log.e("date",selectedDate);
        selected_text.setText(selectedDate);

        get_events(selectedDate);







        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                Long eventOccursOn=  c.getTimeInMillis();

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String selectedDate = sdf.format(new Date(eventOccursOn));

                Log.e("date",selectedDate);
                selected_text.setText(selectedDate);

                get_events(selectedDate);
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_events);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }


    private void get_events(String s){

        Log.e("s",s);
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<Events> eventsCall = RetrofitClient.getmInstance().getApi().get_event_date(s);
        eventsCall.enqueue(new Callback<Events>() {
            @Override
            public void onResponse(Call<Events> call, Response<Events> response) {
                Events events = response.body();

                assert events != null;
                if (events.getStatus()){

                    postList = events.getData();

                    EventsAdapter eventsAdapter = new EventsAdapter(postList,getContext());
                    recyclerView.setAdapter(eventsAdapter);
                    progressDialog.dismiss();

                }else {
                    t_toast("No Events Found");
                    postList = events.getData();
                    EventsAdapter eventsAdapter = new EventsAdapter(postList,getContext());
                    recyclerView.setAdapter(eventsAdapter);
                    postList.clear();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<Events> call, Throwable t) {
                Log.e("error events",t.getMessage());
                progressDialog.dismiss();
            }
        });
    }


    private void t_toast(String s){
        if (s != null){
            Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
        }

    }



}
