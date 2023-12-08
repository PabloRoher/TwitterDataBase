package io.bootify.twitter_data_base.rest;

import io.bootify.twitter_data_base.model.TweetDTO;
import io.bootify.twitter_data_base.service.TweetService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TweetResource {

    private final TweetService tweetService;

    public TweetResource(final TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public ResponseEntity<List<TweetDTO>> getAllTweets() {
        return ResponseEntity.ok(tweetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetDTO> getTweet(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(tweetService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createTweet(@RequestBody @Valid final TweetDTO tweetDTO) {
        final Long createdId = tweetService.create(tweetDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTweet(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final TweetDTO tweetDTO) {
        tweetService.update(id, tweetDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTweet(@PathVariable(name = "id") final Long id) {
        tweetService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
