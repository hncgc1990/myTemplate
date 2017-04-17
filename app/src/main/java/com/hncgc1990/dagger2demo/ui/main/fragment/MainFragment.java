package com.hncgc1990.dagger2demo.ui.main.fragment;

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
import com.hncgc1990.dagger2demo.data.model.Result;
import com.hncgc1990.dagger2demo.ui.main.MainContract;
import com.hncgc1990.dagger2demo.ui.main.MainPresenter;
import com.hncgc1990.dagger2demo.ui.main.adapter.MyItemRecyclerViewAdapter;

import java.util.List;

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

        mMainPresenter.loadData();
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
    public void showList(List<Result> list) {
        mRecyclerView.setAdapter(new MyItemRecyclerViewAdapter(list, mListener));
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
        void onListFragmentInteraction(Result item);
    }
}
