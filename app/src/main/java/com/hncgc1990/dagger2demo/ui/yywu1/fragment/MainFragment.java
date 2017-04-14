package com.hncgc1990.dagger2demo.ui.yywu1.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hncgc1990.dagger2demo.R;
import com.hncgc1990.dagger2demo.ui.MainContract;
import com.hncgc1990.dagger2demo.ui.MainPresenter;
import com.hncgc1990.dagger2demo.ui.yywu1.adapter.MyItemRecyclerViewAdapter;
import com.hncgc1990.dagger2demo.ui.dummy.DummyContent;
import com.hncgc1990.dagger2demo.ui.dummy.DummyContent.DummyItem;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static dagger.internal.Preconditions.checkNotNull;


public class MainFragment extends Fragment implements MainContract.View {

    private OnListFragmentInteractionListener mListener;

    RecyclerView mRecyclerView;

    MainPresenter mMainPresenter;

    ProgressDialog mProgressDialog;


    public MainFragment() {
    }

    @SuppressWarnings("unused")
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        setUpList(view);

        mMainPresenter.start();


        Observable.just(1).delay(3, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mMainPresenter.showList();
                    }
                });

        return view;
    }

    private void setUpList(View view) {
        // Set the adapter
            Context context = view.getContext();
            mRecyclerView= (RecyclerView) view;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;

    }

    @Override
    public void showProgress() {
        mProgressDialog=new ProgressDialog(getActivity());
        mProgressDialog.show();
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showList() {
        mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if(mProgressDialog!=null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void setPresenter(@NonNull  MainPresenter presenter) {
        mMainPresenter=checkNotNull(presenter);
    }


    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }
}
