package ro.amihaescu.polls.model;

import lombok.Data;

@Data
public class ChoiceVoteCount {

    private Long choiceId;
    private Long voteCount;
}
