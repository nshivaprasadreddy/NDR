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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.gmail.hanivisushiva.ndr.Adapters.PostsAdapter;
import com.gmail.hanivisushiva.ndr.Model.Post.Datum;
import com.gmail.hanivisushiva.ndr.Model.Post.Posts;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{


    RecyclerView recyclerView;
    List<Datum> postList;
    List<Datum> post_trendingList;
    private SliderLayout mDemoSlider;
    HashMap<String,Datum> trending_data= new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_posts,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_posts);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDemoSlider = (SliderLayout) view.findViewById(R.id.post_slider);
        post_trendingList = get_trending_posts();

        get_posts();



        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(9000);
        mDemoSlider.addOnPageChangeListener(this);











        return view;
    }


    private List get_trending_posts(){
        Call<Posts> trending_posts = RetrofitClient.getmInstance().getApi().get_trending_posts();

        trending_posts.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Posts posts = response.body();

                if (posts.getStatus()){

                    post_trendingList = posts.getData();




                    HashMap<String,String> file_maps = new HashMap<String, String>();

                    for (int i = 0;i<post_trendingList.size();i++){
                        trending_data.put(post_trendingList.get(i).getTxtTitle(),post_trendingList.get(i));
                        file_maps.put(post_trendingList.get(i).getTxtTitle(),post_trendingList.get(i).getTxtFeatureImage());
                    }

                    for(String name : file_maps.keySet()){
                        TextSliderView textSliderView = new TextSliderView(getContext());
                        // initialize a SliderLayout
                        textSliderView
                                .description(name)
                                .image(file_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(PostsFragment.this);

                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                .putString("extra",name);

                        mDemoSlider.addSlider(textSliderView);
                    }

                }




            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

            }
        });

        return post_trendingList;
    }


    private void get_posts(){
        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data,Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Call<Posts> postsCall = RetrofitClient.getmInstance().getApi().get_posts();

        postsCall.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                Posts posts = response.body();


                if (posts.getStatus()){
                    //Log.e("posts", posts.toString());
                    postList = posts.getData();

                    //creating recyclerview adapter
                    PostsAdapter adapter = new PostsAdapter(getContext(), postList);

                    //setting adapter to recyclerview
                    recyclerView.setAdapter(adapter);

                }



progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

                Log.e("posts", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {


        Datum title = trending_data.get(slider.getDescription());


        Intent intent = new Intent(getContext(),Detail.class);
        intent.putExtra("title",title.getTxtTitle());
        intent.putExtra("des",title.getTxtDescription());
        intent.putExtra("img",title.getTxtFeatureImage());
        startActivity(intent);
        //Toast_t(title.getTxtTitle()+"=="+title.getTxtId());

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

    private void Toast_t(String s){
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }
}
