package test.nelson.teamwork.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;
import test.nelson.teamwork.R;
import test.nelson.teamwork.model.Project;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class ProjectListAdapter extends RealmRecyclerViewAdapter<Project, ProjectListAdapter.ProjectItemViewHolder> {


    public ProjectListAdapter(@Nullable OrderedRealmCollection<Project> data) {
        super(data, true);
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
        holder.bindProjectInformation(getItem(position));

    }

    @Override
    public long getItemId(int position) {
        final Project item = getItem(position);
        return item != null ? item.getId() : 0;
    }

    static class ProjectItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_item_project_view_holder_project_name)
        TextView projectName;

        ProjectItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindProjectInformation(Project project) {
            projectName.setText(project.getName());

        }
    }
}
