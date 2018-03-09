package test.nelson.teamwork.model;

import lombok.Getter;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */
@Getter
public class ProjectSelectedEvent {
    private long id;
    private int adapterPosition;

    public ProjectSelectedEvent(long projectId, int adapterPosition) {
        this.id = projectId;
        this.adapterPosition = adapterPosition;
    }
}
