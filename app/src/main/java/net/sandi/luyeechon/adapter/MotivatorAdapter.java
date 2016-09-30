package net.sandi.luyeechon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.sandi.luyeechon.LuYeeChonApp;
import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.MotivatorVO;
import net.sandi.luyeechon.views.holders.MotivatorViewHolder;

import java.util.List;

/**
 * Created by Kaung Htet Lin on 9/20/2016.
 */
public class MotivatorAdapter extends RecyclerView.Adapter<MotivatorViewHolder> {

    private LayoutInflater mInflater;
    private List<MotivatorVO> mMotivatorList;
    //   private AttractionViewHolder.ControllerAttractionItem mControllerAttractionItem;

    public MotivatorAdapter(List<MotivatorVO> motivatorList) {
        mInflater = LayoutInflater.from(LuYeeChonApp.getContext());
        mMotivatorList = motivatorList;
//        mControllerAttractionItem = controllerAttractionItem;
    }

    @Override
    public MotivatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_motivator, parent, false);
        return new MotivatorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MotivatorViewHolder holder, int position) {
        holder.bindData(mMotivatorList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMotivatorList.size();
    }

    public void setNewData(List<MotivatorVO> newMotivatorList) {
        mMotivatorList = newMotivatorList;
        notifyDataSetChanged();
    }
}