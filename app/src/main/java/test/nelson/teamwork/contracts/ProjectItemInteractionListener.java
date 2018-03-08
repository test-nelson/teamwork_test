package test.nelson.teamwork.contracts;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */

public interface ProjectItemInteractionListener {
    void onItemDescriptionExpanded(int position);
    boolean isExpanded(int adapterPosition);

    void onCompleteExpansion(int adapterPosition);
}
