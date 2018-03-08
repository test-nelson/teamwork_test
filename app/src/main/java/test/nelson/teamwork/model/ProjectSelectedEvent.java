package test.nelson.teamwork.model;

import lombok.Getter;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */
@Getter
public class ProjectSelectedEvent {
    private long id;

    public ProjectSelectedEvent(long projectId) {
        this.id = projectId;
    }
}
