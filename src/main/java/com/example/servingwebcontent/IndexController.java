package com.example.servingwebcontent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.example.servingwebcontent.Utils.listFilesUsingJavaIO;


@Controller
public class IndexController {
    @Autowired
    private YAMLConfig myConfig;

    @RequestMapping("/")
    public String index(Model model) {
        String path = myConfig.getFilePath();
        List<String> list = listFilesUsingJavaIO(path);
        model.addAttribute("files", list);
        return "index";
    }


}