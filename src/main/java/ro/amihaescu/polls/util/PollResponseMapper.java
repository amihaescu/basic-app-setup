package ro.amihaescu.polls.util;

import ro.amihaescu.polls.model.Poll;
import ro.amihaescu.polls.model.User;
import ro.amihaescu.polls.payload.ChoiceResponse;
import ro.amihaescu.polls.payload.PollResponse;
import ro.amihaescu.polls.payload.UserSummary;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PollResponseMapper {

    public static PollResponse mapToPollResponse(Poll poll,
                                                 Map<Long, Long> choiceVotesMap,
                                                 User creator,
                                                 Long userVote) {
        PollResponse pollResponse = new PollResponse();
        pollResponse.setId(poll.getId());
        pollResponse.setQuestion(poll.getQuestion());
        pollResponse.setCreationDateTime(poll.getCreatedAt());
        pollResponse.setExpirationDateTime(poll.getExpirationDateTime());
        Instant now = Instant.now();
        pollResponse.setExpired(poll.getExpirationDateTime().isBefore(now));

        List<ChoiceResponse> choiceResponses = poll.getChoices().stream().map(choice -> {
            ChoiceResponse choiceResponse = new ChoiceResponse();
            choiceResponse.setId(choice.getId());
            choiceResponse.setText(choice.getText());
            choiceResponse.setVoteCount(choiceVotesMap.containsKey(choice.getId()) ? choiceVotesMap.get(choice.getId()) : 0);

            return choiceResponse;
        }).collect(Collectors.toList());

        pollResponse.setChoices(choiceResponses);
        UserSummary creatorSummary = new UserSummary(
                creator.getId(),
                creator.getUsername(),
                creator.getName()
        );
        pollResponse.setCreatedBy(creatorSummary);

        if (userVote != null) {
            pollResponse.setSelectedChoice(userVote);
        }

        long totalVotes = pollResponse.getChoices()
                .stream().mapToLong(ChoiceResponse::getVoteCount).sum();

        pollResponse.setTotalVotes(totalVotes);
        return pollResponse;
    }
}
