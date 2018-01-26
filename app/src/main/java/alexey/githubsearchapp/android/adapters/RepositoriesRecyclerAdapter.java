package alexey.githubsearchapp.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import alexey.githubsearchapp.android.R;
import alexey.githubsearchapp.android.models.GitHubRepository;
import alexey.githubsearchapp.android.views.base.iOnRepoItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ally on 25/01/18.
 */

public class RepositoriesRecyclerAdapter extends RecyclerView.Adapter<RepositoriesRecyclerAdapter.ViewHolder> {
    private List<GitHubRepository> mItems;
    private Context mContext;
    private iOnRepoItemClickListener mListener;

    public RepositoriesRecyclerAdapter(Context context, iOnRepoItemClickListener listener) {
        super();
        mItems = new ArrayList<>();
        this.mContext = context;
        this.mListener = listener;
    }

    public List<GitHubRepository> getGitHubRepositories() {
        return mItems;
    }

    public void addGitHubRepository(GitHubRepository repo) {
        mItems.add(repo);
        notifyDataSetChanged();
    }

    public void addGitHubRepositoriesList(List<GitHubRepository> reposList) {
        mItems.addAll(reposList);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.repo_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        if(mItems != null && mItems.size() > 0) {
            final GitHubRepository repo = mItems.get(i);

            viewHolder.titleTextView.setText(repo.getName());
            viewHolder.descTextView.setText(repo.getDescription());
            viewHolder.forksCountTextView.setText("" + repo.getForksCount());

            String avatarImgUrl = repo.getOwner().getAvatarUrl();
            if (avatarImgUrl != null && !avatarImgUrl.isEmpty()) {
                Picasso.with(mContext).load(avatarImgUrl).into(viewHolder.avatarImg);
            }

            viewHolder.itemLlt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(repo.getOwner().getLogin());
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemLlt)
        LinearLayout itemLlt;

        @BindView(R.id.avatarImg)
        ImageView avatarImg;

        @BindView(R.id.titleTextView)
        public TextView titleTextView;

        @BindView(R.id.descTextView)
        public TextView descTextView;

        @BindView(R.id.forksCountTextView)
        public TextView forksCountTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}