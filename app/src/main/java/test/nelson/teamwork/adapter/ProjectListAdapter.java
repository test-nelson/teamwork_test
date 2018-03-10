package test.nelson.teamwork.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import org.greenrobot.eventbus.EventBus;

import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import test.nelson.teamwork.R;
import test.nelson.teamwork.contracts.ProjectItemInteractionListener;
import test.nelson.teamwork.model.Project;
import test.nelson.teamwork.model.ProjectSelectedEvent;
import test.nelson.teamwork.utils.CustomLinearLayoutManager;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class ProjectListAdapter extends RealmRecyclerViewAdapter<Project, ProjectListAdapter.ProjectItemViewHolder> implements ProjectItemInteractionListener {

    private HashSet<Integer> expandedIndices = new HashSet<>();
    private RecyclerView recyclerView;
    private RequestManager requestManager;

    public ProjectListAdapter(@Nullable OrderedRealmCollection<Project> data, RequestManager requestManager) {
        super(data, true);
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public ProjectItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project_view_holder, parent, false);
        return new ProjectItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectItemViewHolder holder, int position) {
        holder.setInteractionListener(this);
        holder.setRequestManager(requestManager);
        holder.bindProjectInformation(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        final Project item = getItem(position);
        return item != null ? item.getId() : 0;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }


    @Override
    public void onItemDescriptionExpanded(int position) {
        if (expandedIndices.contains(position))
            expandedIndices.remove(position);
        else expandedIndices.add(position);
        notifyItemChanged(position);

    }

    @Override
    public boolean isExpanded(int adapterPosition) {
        return expandedIndices.contains(adapterPosition);
    }

    @Override
    public void onCompleteExpansion(final int adapterPosition) {
        if (recyclerView != null) {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    CustomLinearLayoutManager manager = (CustomLinearLayoutManager) recyclerView.getLayoutManager();
                    manager.smoothScrollToPosition(recyclerView, null, adapterPosition);
                }
            });

        }
    }

    @Override
    public void onProjectSelected(int adapterPosition) {
        final Project project = getItem(adapterPosition);
        if (project != null)
            EventBus.getDefault().post(new ProjectSelectedEvent(project.getId()));
    }

    static class ProjectItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_item_project_view_holder_project_name)
        TextView projectName;
        @BindView(R.id.image_view_item_project_view_holder_project_logo)
        ImageView projectLogo;
        @BindView(R.id.text_view_item_project_view_holder_project_description)
        TextView projectDescription;
        @BindView(R.id.text_view_item_project_view_holder_project_sub_title)
        TextView projectSubtitle;
        @BindView(R.id.image_view_item_project_view_holder_expand_button)
        ImageView expandButton;
        @BindView(R.id.image_view_item_project_view_holder_star_button)
        ImageView starButton;


        private ProjectItemInteractionListener interactionListener;
        private RequestManager requestManager;


        ProjectItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindProjectInformation(Project project) {
            projectName.setText(project.getName());
            projectSubtitle.setText(project.getCompany().getName());
            projectDescription.setText(project.getDescription());

            requestManager.load(project.getLogo())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.project_place_holder)
                            .error(R.drawable.project_place_holder))
                    .into(projectLogo);

            if (project.isStarred())
                starButton.setImageResource(R.drawable.ic_star_accent_24dp);
            else starButton.setImageResource(R.drawable.ic_star_border_accent_24dp);


            setExpandState();


        }


        private void setExpandState() {

            if (interactionListener.isExpanded(getAdapterPosition())) {
                projectDescription.setVisibility(View.VISIBLE);
                expandButton.setImageResource(R.drawable.ic_expand_less_black_24dp);
                interactionListener.onCompleteExpansion(getAdapterPosition());
            } else {
                projectDescription.setVisibility(View.GONE);
                expandButton.setImageResource(R.drawable.ic_expand_more_black_24dp);
            }

        }

        @OnClick(R.id.project_view_holder_container)
        public void onProjectItemSelected() {
            interactionListener.onProjectSelected(getAdapterPosition());
        }

        @OnClick(R.id.view_group_item_project_view_holder_expand_button)
        void onExpandButtonClicked() {
            interactionListener.onItemDescriptionExpanded(getAdapterPosition());
        }


        void setInteractionListener(ProjectItemInteractionListener interactionListener) {
            this.interactionListener = interactionListener;
        }


        public void setRequestManager(RequestManager requestManager) {
            this.requestManager = requestManager;
        }
    }


}
