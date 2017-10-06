package com.vsahin.twitter_api_search.View.TweetDetail;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vsahin.twitter_api_search.Model.Entity.Status;
import com.vsahin.twitter_api_search.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Volkan Şahin on 19.09.2017.
 */

public class DetailFragment extends LifecycleFragment {

    @BindView(R.id.textview_username)
    TextView user;

    @BindView(R.id.textview_text)
    TextView text;

    View v;
    Status status;

    public static DetailFragment newInstance(Status status) {
        Bundle args = new Bundle();
        args.putSerializable("status", status);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        status = (Status) bundle.getSerializable("status");
        v = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, v);

        user.setText(status.getText());
        text.setText(status.getUser().getName());
        return v;
    }
}
