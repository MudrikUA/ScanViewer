/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joy.ScanViewer.controller;

import com.joy.ScanViewer.entity.Scan;
import com.joy.ScanViewer.entity.ScanBug;
import com.joy.ScanViewer.repository.ScanBugRepository;
import com.joy.ScanViewer.repository.ScanRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Autowired
    private ScanBugRepository bugRepository;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    private ScanBug lastBugPanel;

    @RequestMapping(value = {"/", "/page1"}, method = RequestMethod.GET)
    public String manager(Model model) {
        List<ScanBug> findAll = bugRepository.findAll();
        if (findAll != null && !findAll.isEmpty()) {
            // if (!findAll.get(0).equals(lastBugPanel)) {
            //    lastBugPanel = findAll.get(0);
            model.addAttribute("leftPanel", findAll.get(0));
            model.addAttribute("currentPanel", findAll.get(0));
            model.addAttribute("rightPanel", findAll.get(0));
            model.addAttribute("curDate", dateFormat.format(findAll.get(0).getCreationDate()));
            // }
        }
        return "page1";
    }

    @RequestMapping(value = {"/page2"}, method = RequestMethod.GET)
    public String bugControl(Model model) {
        List<Scan> findAll = scanRepository.findAll();
        if (findAll != null && !findAll.isEmpty()) {
            model.addAttribute("scan", findAll.get(0));
        }
        return "page2";
    }

    @RequestMapping(value = {"/searchPanel"}, method = RequestMethod.POST)
    public String savePerson(Model model, //
            @ModelAttribute("bugPanelScanCode") String panelScanCode) {
        List<Scan> findAllByScanCode = scanRepository.findAllByScanCode(panelScanCode);
        if (findAllByScanCode != null && !findAllByScanCode.isEmpty()) {
            model.addAttribute("selectedPanel", findAllByScanCode.get(0));
            model.addAttribute("leftPanel", findAllByScanCode.get(0));
            model.addAttribute("rightPanel", findAllByScanCode.get(0));
        }
        return "page2";
    }
}
