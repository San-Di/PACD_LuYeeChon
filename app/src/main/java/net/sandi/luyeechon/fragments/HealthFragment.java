package net.sandi.luyeechon.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.adapter.HealthAdapter;
import net.sandi.luyeechon.adapter.HealthTopicListAdapter;
import net.sandi.luyeechon.data.models.HealthModel;
import net.sandi.luyeechon.data.persistence.LuYeeChonContract;
import net.sandi.luyeechon.data.vos.HealthVO;
import net.sandi.luyeechon.events.DataEvent;
import net.sandi.luyeechon.utils.CommonConstants;
import net.sandi.luyeechon.views.holders.HealthViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    private HealthAdapter mHealthAdapter;
    private HealthTopicListAdapter mHealthTopicAdapter;

    private HealthViewHolder.ControllerHealthItem controllerHealthItem;

    private List<HealthVO> healthVOs;
    private List<HealthVO> healthVOType1;
    private List<HealthVO> healthVOType2;
    private List<HealthVO> healthVOType3;
    private int flag;
    public HealthFragment() {
        flag = 0;
        healthVOs= HealthModel.getInstance().getHealthVOList();
        int size = healthVOs.size();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] healthTopicsArray = getResources().getStringArray(R.array.health_array);
        List<String> topicList = new ArrayList<>(Arrays.asList(healthTopicsArray));
        mHealthTopicAdapter = new HealthTopicListAdapter(topicList);

        healthVOType1 = new ArrayList<HealthVO>();
        healthVOType2 = new ArrayList<HealthVO>();
        healthVOType3= new ArrayList<HealthVO>();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        controllerHealthItem = (HealthViewHolder.ControllerHealthItem) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(this)) {
            eventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Toast.makeText(getContext(),"onCreate ",Toast.LENGTH_LONG).show();
        View view = inflater.inflate(R.layout.fragment_health, container, false);

        final RecyclerView rvHealth = (RecyclerView)view.findViewById(R.id.rv_healths);

        Spinner spHealthType = (Spinner)view.findViewById(R.id.health_dropdown);
        spHealthType.setAdapter(mHealthTopicAdapter);

        changeTopics(rvHealth);
        spHealthType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setFlag(position);
                changeTopics(rvHealth);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }

    private BroadcastReceiver mDataLoadedBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //TODO instructions when the new data is ready.
            String extra = intent.getStringExtra("key-for-extra");


            List<HealthVO> newHealthList = HealthModel.getInstance().getHealthVOList();
            mHealthAdapter.setNewData(newHealthList);
        }
    };

    public static HealthFragment newInstance() {
        return new HealthFragment();
    }

    public int getFlag() {

        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;

    }
    public void changeTopics(RecyclerView rvHealth){
        if (getFlag()==0)
            mHealthAdapter = new HealthAdapter(healthVOType1,controllerHealthItem);
        else if (getFlag()==1)
            mHealthAdapter = new HealthAdapter(healthVOType2,controllerHealthItem);
        else if (getFlag()==2)
            mHealthAdapter = new HealthAdapter(healthVOType3,controllerHealthItem);
        else
            mHealthAdapter = new HealthAdapter(healthVOType1,controllerHealthItem);
        rvHealth.setAdapter(mHealthAdapter);

        int columnSpanCount = getResources().getInteger(R.integer.health_list_grid);
        rvHealth.setLayoutManager(new GridLayoutManager(getContext(), columnSpanCount));

    }
    public void onEventMainThread(DataEvent.HealthDataLoadedEvent health) {
        String extra = health.getExtraMessage();


        //List<AttractionVO> newAttractionList = AttractionModel.getInstance().getAttractionList();
        List<HealthVO> newHealthList = health.getHealthList();
        mHealthAdapter.setNewData(newHealthList);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                LuYeeChonContract.HealthEntry.CONTENT_URI,
                null,
                null,
                null,
                LuYeeChonContract.HealthEntry._ID + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Toast.makeText(getContext(),"OnLoadFinished ",Toast.LENGTH_LONG).show();
        List<HealthVO> healthList = new ArrayList<>();
        if (data != null && data.moveToFirst()) {
            do {
                HealthVO health = HealthVO.parseFromCursor(data);
                healthList.add(health);
            } while (data.moveToNext());
        }

        Log.d(LuYeeChonApp.TAG, "Retrieved healths DESC : " + healthList.size());
        mHealthAdapter.setNewData(healthList);

        HealthModel.getInstance().setStoredData(healthList);

        setDataType(healthList);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportLoaderManager().initLoader(CommonConstants.HEALTH_LIST_LOADER, null, this);
    }

    public void setDataType(List<HealthVO> healthList){
        healthVOType1 = new ArrayList<HealthVO>();
        healthVOType2 = new ArrayList<HealthVO>();
        healthVOType3= new ArrayList<HealthVO>();

        for(HealthVO health : healthList){

            if (health.getType().equals("1"))
                healthVOType1.add(health);
            else if (health.getType().equals("2"))
                healthVOType2.add(health);
            else if (health.getType().equals("3"))
                healthVOType3.add(health);
        }
    }
}
