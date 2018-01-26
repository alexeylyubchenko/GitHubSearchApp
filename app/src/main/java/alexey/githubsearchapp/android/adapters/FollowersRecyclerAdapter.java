package alexey.githubsearchapp.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import alexey.githubsearchapp.android.R;
import alexey.githubsearchapp.android.models.Follower;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ally on 25/01/18.
 */

public class FollowersRecyclerAdapter extends RecyclerView.Adapter<FollowersRecyclerAdapter.ViewHolder> {
    private List<Follower> mItems;
    private Context mContext;

    public FollowersRecyclerAdapter(Context context) {
        super();
        mItems = new ArrayList<>();
        this.mContext = context;
    }

    public List<Follower> getFollowers() {
        return mItems;
    }

    public void addFolower(Follower follower){
        mItems.add(follower);
        notifyDataSetChanged();
    }

    public void addFollowerList(List<Follower> list) {
        mItems.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.follower_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if(mItems != null && mItems.size() > 0) {
            final Follower follower = mItems.get(i);

            viewHolder.loginTextView.setText(follower.getLogin());

            String avatarImgUrl = follower.getAvatarUrl();
            if (avatarImgUrl != null && !avatarImgUrl.isEmpty()) {
                Picasso.with(mContext).load(avatarImgUrl).into(viewHolder.followerAvatarImg);
            }

        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.followerItemLlt)
        RelativeLayout followerItemLlt;

        @BindView(R.id.followerAvatarImg)
        ImageView followerAvatarImg;

        @BindView(R.id.loginTextView)
        public TextView loginTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}