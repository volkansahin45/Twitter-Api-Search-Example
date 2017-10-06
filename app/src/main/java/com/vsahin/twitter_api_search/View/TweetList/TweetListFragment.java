package com.vsahin.twitter_api_search.View.TweetList;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.vsahin.twitter_api_search.Model.Entity.Status;
import com.vsahin.twitter_api_search.Model.Entity.Tweet;
import com.vsahin.twitter_api_search.Model.Entity.TweetMetaData;
import com.vsahin.twitter_api_search.R;
import com.vsahin.twitter_api_search.View.MainActivity;
import com.vsahin.twitter_api_search.View.TweetDetail.DetailFragment;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Volkan Şahin on 18.09.2017.
 */

public class TweetListFragment extends LifecycleFragment implements RecyclerVewItemClickListener{

    @BindView(R.id.edittext_search)
    EditText searchEditText;

    @BindView(R.id.recycler_view_tweets)
    RecyclerView tweetsRecylerView;

    ArrayList<Status> tweetList = new ArrayList<>();
    TweetListViewModel viewModel;
    String searchingText;
    long max_id = 0;
    TweetsRecyclerViewAdapter adapter;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

    View v;
    String nextResultsUrl;
    private EndlessRecyclerViewScrollListener scrollListener;

    public static TweetListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        TweetListFragment fragment = new TweetListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(v == null) {
            v = inflater.inflate(R.layout.fragment_tweet_list, container, false);
            ButterKnife.bind(this, v);
            adapter = new TweetsRecyclerViewAdapter(getActivity(), tweetList, this);
            tweetsRecylerView.setAdapter(adapter);
            tweetsRecylerView.setLayoutManager(linearLayoutManager);

            scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    loadNextTweets();
                }
            };

            tweetsRecylerView.setOnScrollListener(scrollListener);
            searchEditText.setText("deneme");
        }
        return v;
    }

    private void loadNextTweets() {
        viewModel.getTweets(searchingText, max_id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(TweetListViewModel.class);
        viewModel.authenticate();
        subscribeTweets();
    }

    public void subscribeTweets(){
        viewModel.tweet.observe(this, new Observer<Tweet>() {
            @Override
            public void onChanged(@Nullable Tweet tweet) {
                tweetList = tweet.getStatuses();
                adapter.updateItems(tweet.getStatuses());
                try {
                    TweetMetaData metaData = tweet.getSearch_metadata();
                    Matcher matcher = Pattern.compile("\\d+").matcher(metaData.getNext_results());
                    matcher.find();
                    max_id = Long.valueOf(matcher.group());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if(searchingText != null){
            viewModel.getTweets(searchingText, max_id);
        }
    }

    @OnClick(R.id.button_search)
    public void getTweets(){
        searchingText = searchEditText.getText().toString();
        adapter.clearItems();
        viewModel.getTweets(searchingText, 0);
    }

    @Override
    public void onItemClick(Status clickedStatus) {
        ((MainActivity)getActivity()).showFragment(DetailFragment.newInstance(clickedStatus));
    }
}
