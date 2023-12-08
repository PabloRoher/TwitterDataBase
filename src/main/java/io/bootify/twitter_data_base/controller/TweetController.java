package io.bootify.twitter_data_base.controller;

import io.bootify.twitter_data_base.model.TweetDTO;
import io.bootify.twitter_data_base.service.TweetService;
import io.bootify.twitter_data_base.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(final TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("tweets", tweetService.findAll());
        return "tweet/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("tweet") final TweetDTO tweetDTO) {
        return "tweet/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("tweet") @Valid final TweetDTO tweetDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tweet/add";
        }
        tweetService.create(tweetDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("tweet.create.success"));
        return "redirect:/tweets";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id, final Model model) {
        model.addAttribute("tweet", tweetService.get(id));
        return "tweet/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") final Long id,
            @ModelAttribute("tweet") @Valid final TweetDTO tweetDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tweet/edit";
        }
        tweetService.update(id, tweetDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("tweet.update.success"));
        return "redirect:/tweets";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") final Long id,
            final RedirectAttributes redirectAttributes) {
        tweetService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("tweet.delete.success"));
        return "redirect:/tweets";
    }

}
