package id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.adapter.RecommendAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.model.Results;
import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.model.ResultsRespons;
import id.sch.smktelkom_mlg.privateassignment.xirpl307.themovie.service.GsonGetRequest;


/**
 * A simple {@link Fragment} subclass.
 */
public class NowFragment extends Fragment {

    ArrayList<Results> mlist = new ArrayList<>();
    RecommendAdapter myPopular;

    public NowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now, container, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        myPopular = new RecommendAdapter(this, mlist, getContext());
        rv.setAdapter(myPopular);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        downloadDataResource();
        return view;
    }

    private void downloadDataResource() {

        String url = "https://api.themoviedb.org/3/movie/popular?api_key=83e9bd45d01bdec860110180bf6d664b&language=en-US&page=1";

        GsonGetRequest<ResultsRespons> myRequest = new GsonGetRequest<ResultsRespons>
                (url, ResultsRespons.class, null, new Response.Listener<ResultsRespons>() {

                    @Override
                    public void onResponse(ResultsRespons response) {
                        Log.d("FLOW", "onResponse: " + (new Gson().toJson(response)));
                        //fillColor(response.results);
                        mlist.addAll(response.results);
                        myPopular.notifyDataSetChanged();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FLOW", "onErrorResponse: ", error);
                    }
                });
        //VolleySingleton.getInstance(this).addToRequestQueue(myRequest);

    }

}
