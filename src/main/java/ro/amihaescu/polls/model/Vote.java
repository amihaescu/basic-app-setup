package ro.amihaescu.polls.model;

import lombok.Data;
import ro.amihaescu.polls.model.audit.DateAudit;

import javax.persistence.*;

@Entity
@Table( name = "votes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {

        })
})
@Data
public class Vote extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "choice_id", nullable = false)
    private Choice choice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
