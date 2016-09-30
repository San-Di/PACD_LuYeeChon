package net.sandi.luyeechon.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.sandi.luyeechon.R;
import net.sandi.luyeechon.data.vos.MotivatorVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Kaung Htet Lin on 9/20/2016.
 */
public class MotivatorViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_motivator)
    ImageView ivMotivator;

//    public MotivatorViewHolder(View itemView) {
//        super(itemView);
//        ButterKnife.bind(this, itemView);
//    }

    private MotivatorVO mMotivator;

    public MotivatorViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
       /* itemView.setOnClickListener(this);
        mController = controller;
  */
    }

    public void bindData(MotivatorVO mMotivator) {
        mMotivator = mMotivator;
        String imageUrl = mMotivator.getImage();
//        String imageUrl = MyanmarAttractionsConstants.IMAGE_ROOT_DIR + attraction.getImages()[0];


        Glide.with(ivMotivator.getContext())
                .load(imageUrl)
                .centerCrop()
                .placeholder(R.drawable.drawer_background)
                .error(R.drawable.drawer_background)
                .into(ivMotivator);
    }

}