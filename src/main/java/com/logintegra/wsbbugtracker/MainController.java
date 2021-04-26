package com.logintegra.wsbbugtracker;

import com.logintegra.wsbbugtracker.issues.Issue;
import com.logintegra.wsbbugtracker.issues.IssueQueryExample;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    final IssueQueryExample issueQueryExample;

    public MainController(IssueQueryExample issueQueryExample) {
        this.issueQueryExample = issueQueryExample;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/issue";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Issue> all() {
        return issueQueryExample.findAll();
    }

    @GetMapping("/todo")
    @ResponseBody
    public List<Issue> todo() {
        return issueQueryExample.findAllToDo();
    }

    @GetMapping("/todoABC")
    @ResponseBody
    public List<Issue> todoABC() {
        return issueQueryExample.findAllToDoAndProjectIsABC();
    }

}
