/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joy.ScanViewer.controller;

import com.joy.ScanViewer.entity.Scan;
import com.joy.ScanViewer.repository.ScanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mudrik
 */
@Controller
public class HomeController {

    @Autowired
    private ScanRepository scanRepository;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        List<Scan> findAll = scanRepository.findAll();
        model.addAttribute("allScans", findAll);
        return "index";
    }

    @RequestMapping("/welcome")
    public String getWelcome() {
        return "welcome";
    }
}
