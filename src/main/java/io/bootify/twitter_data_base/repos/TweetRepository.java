package io.bootify.twitter_data_base.repos;

import io.bootify.twitter_data_base.domain.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TweetRepository extends MongoRepository<Tweet, Long> {
}
