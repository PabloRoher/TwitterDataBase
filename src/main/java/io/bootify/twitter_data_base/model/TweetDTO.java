package io.bootify.twitter_data_base.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TweetDTO {

    private Long id;

    @NotNull
    private LocalDateTime dateCreated;

    private Integer numberOfLikes;

    @Size(max = 255)
    private String sourceOfTweet;

    @NotNull
    @Size(max = 280)
    private String tweetContent;

}
