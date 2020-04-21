package com.ipiecoles.java.mdd324.homepage.controller;

import com.ipiecoles.java.mdd324.homepage.model.Film;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static com.ipiecoles.java.mdd324.homepage.utils.Utils.getPageContents;


@RestController
@RequestMapping(value = "/films")
public class CinemaController {

    @RequestMapping(method = RequestMethod.GET)
    public String films() throws IOException {
        var url = "http://rss.allocine.fr/ac/cine/prochainement?format=json";
        String xmlContent = getPageContents(url);
        return xmlContent;
    }
}
