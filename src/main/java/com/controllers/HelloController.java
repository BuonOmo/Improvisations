package com.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/error")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String error() {
        return "Bad son, bad...";
    }

//    @RequestMapping("/error")
//    public String first() {
//        return "FIRST!";
//    }

    @RequestMapping("/{speak}")
    public String speak(@PathVariable("speak")String uSayWut) {
        return "Oh really ? You told me "+uSayWut+" ? Don’t mess with me";
    }

    @RequestMapping("/I/just/{verb}/toSay/{tell:[e-kI]<[0-9][uoy]+}")
    public String tellMeWhatIWant ( @PathVariable("tell")String telling,
                                    @PathVariable("verb")String came) {
        System.out.println("telling: " + telling + "\ncame: " + came);
        return "O BB UR SWIT";
    }
}
