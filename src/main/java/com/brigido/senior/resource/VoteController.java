package com.brigido.senior.resource;

import com.brigido.senior.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;


}
