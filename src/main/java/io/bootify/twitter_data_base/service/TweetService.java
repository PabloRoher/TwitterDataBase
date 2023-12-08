package io.bootify.twitter_data_base.service;

import io.bootify.twitter_data_base.domain.Tweet;
import io.bootify.twitter_data_base.model.TweetDTO;
import io.bootify.twitter_data_base.repos.TweetRepository;
import io.bootify.twitter_data_base.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TweetService {

    private final TweetRepository tweetRepository;

    public TweetService(final TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public List<TweetDTO> findAll() {
        final List<Tweet> tweets = tweetRepository.findAll(Sort.by("id"));
        return tweets.stream()
                .map(tweet -> mapToDTO(tweet, new TweetDTO()))
                .toList();
    }

    public TweetDTO get(final Long id) {
        return tweetRepository.findById(id)
                .map(tweet -> mapToDTO(tweet, new TweetDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final TweetDTO tweetDTO) {
        final Tweet tweet = new Tweet();
        mapToEntity(tweetDTO, tweet);
        return tweetRepository.save(tweet).getId();
    }

    public void update(final Long id, final TweetDTO tweetDTO) {
        final Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(tweetDTO, tweet);
        tweetRepository.save(tweet);
    }

    public void delete(final Long id) {
        tweetRepository.deleteById(id);
    }

    private TweetDTO mapToDTO(final Tweet tweet, final TweetDTO tweetDTO) {
        tweetDTO.setId(tweet.getId());
        tweetDTO.setDateCreated(tweet.getDateCreated());
        tweetDTO.setNumberOfLikes(tweet.getNumberOfLikes());
        tweetDTO.setSourceOfTweet(tweet.getSourceOfTweet());
        tweetDTO.setTweetContent(tweet.getTweetContent());
        return tweetDTO;
    }

    private Tweet mapToEntity(final TweetDTO tweetDTO, final Tweet tweet) {
        tweet.setDateCreated(tweetDTO.getDateCreated());
        tweet.setNumberOfLikes(tweetDTO.getNumberOfLikes());
        tweet.setSourceOfTweet(tweetDTO.getSourceOfTweet());
        tweet.setTweetContent(tweetDTO.getTweetContent());
        return tweet;
    }

}
