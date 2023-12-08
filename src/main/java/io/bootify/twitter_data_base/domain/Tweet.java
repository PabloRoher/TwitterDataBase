package io.bootify.twitter_data_base.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
public class Tweet {

    @Id
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
