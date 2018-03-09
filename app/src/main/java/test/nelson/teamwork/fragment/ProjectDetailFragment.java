package test.nelson.teamwork.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.nelson.teamwork.R;
import test.nelson.teamwork.contracts.ProjectDetailView;
import test.nelson.teamwork.presenter.ProjectDetailPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectDetailFragment extends BaseFragment implements ProjectDetailView {

    public static final String KEY_PROJECT_ID = "project_id";
    private ProjectDetailPresenter presenter;
    private long projectId;

    @BindView(R.id.fab_fragment_project_detail_star_button)
    FloatingActionButton starButton;
    @BindView(R.id.image_view_fragment_project_detail_project_logo)
    ImageView projectLogo;
    @BindView(R.id.text_view_fragment_project_detail_project_description)
    TextView projectDescription;
    @BindView(R.id.view_group_fragment_project_detail_project_misc_settings)
    View miscSettings;
    @BindView(R.id.text_view_fragment_project_detail_project_company)
    TextView companyName;
    @BindView(R.id.text_view_fragment_project_detail_created)
    TextView createdDate;
    @BindView(R.id.text_view_fragment_project_detail_last_modified)
    TextView lastModified;
    @BindView(R.id.text_view_fragment_project_detail_period)
    TextView projectExecutionPeriod;
    @BindView(R.id.switch_fragment_project_detail_status)
    Switch status;
    @BindView(R.id.switch_fragment_project_detail_reply_email)
    Switch replyEmail;
    @BindView(R.id.switch_fragment_project_detail_timers_enabled)
    Switch timersEnabled;

    @BindView(R.id.button_fragment_project_detail_show_project_misc_settings)
    View showMiscSettingsButton;


    public ProjectDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_detail, container, false);
        ButterKnife.bind(this, view);
        presenter = new ProjectDetailPresenter(this);
        if (getArguments() != null)
            projectId = getArguments().getLong(KEY_PROJECT_ID);


        return view;
    }

    @OnClick(R.id.button_fragment_project_detail_show_project_misc_settings)
    public void onShowMiscSettings() {
        miscSettings.setVisibility(View.VISIBLE);
        showMiscSettingsButton.setVisibility(View.GONE);
    }


    @OnClick(R.id.fab_fragment_project_detail_star_button)
    public void onStarButtonClicked() {
        presenter.onProjectStarred();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onViewDestroyed();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public long getProjectId() {
        return projectId;
    }

    @Override
    public void setLogo(String logo) {
        Glide.with(this)
                .load(logo)
                .into(projectLogo);
    }

    @Override
    public void setDescription(String description) {
        projectDescription.setText(description);
    }

    @Override
    public void setCreatedDate(String date) {
        createdDate.setText(date);
    }

    @Override
    public void setLastModifiedDate(String date) {
        lastModified.setText(date);
    }

    @Override
    public void setExecutionPeriod(String period) {
        projectExecutionPeriod.setText(period);
    }

    @Override
    public void setActivityState(boolean active) {
        status.setChecked(active);

    }

    @Override
    public void setTimersEnabled(boolean enabled) {
        timersEnabled.setChecked(enabled);
    }

    @Override
    public void setReplyByEmailEnabled(boolean enabled) {
        replyEmail.setChecked(enabled);
    }

    @Override
    public void starProject() {
        starButton.setImageResource(R.drawable.ic_star_white_24dp);
    }

    @Override
    public void unstarProject() {
        starButton.setImageResource(R.drawable.ic_star_border_black_24dp);
    }

    @Override
    public void setCompanyName(String name) {
        companyName.setText(name);
    }


    public static ProjectDetailFragment getInstance(long projectId) {
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(KEY_PROJECT_ID, projectId);
        fragment.setArguments(bundle);
        return fragment;
    }


}
