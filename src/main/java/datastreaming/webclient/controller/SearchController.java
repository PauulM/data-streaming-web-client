package datastreaming.webclient.controller;

import datastreaming.webclient.consumer.SearchConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {

    @Autowired
    private SearchConsumer searchConsumer;

    @GetMapping("/search")
    public String searchEverything(@RequestParam(value = "query") String query, Model model, HttpServletRequest request){
        String token = (String) request.getSession().getAttribute("token");
        model.addAttribute("songs", searchConsumer.searchSongs(token, query));
        return "search_results";
    }
}
