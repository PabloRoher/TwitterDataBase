package io.bootify.twitter_data_base.repos;

import io.bootify.twitter_data_base.domain.Tweet;
import io.bootify.twitter_data_base.service.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class TweetListener extends AbstractMongoEventListener<Tweet> {

    private final PrimarySequenceService primarySequenceService;

    public TweetListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Tweet> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());
        }
    }

}
