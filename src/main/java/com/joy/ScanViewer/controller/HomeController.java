/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joy.ScanViewer.controller;

import com.joy.ScanViewer.entity.Scan;
import com.joy.ScanViewer.entity.ScanBug;
import com.joy.ScanViewer.entity.ScanDuplicate;
import com.joy.ScanViewer.repository.ScanBugRepository;
import com.joy.ScanViewer.repository.ScanDuplicateRepository;
import com.joy.ScanViewer.repository.ScanRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private ScanDuplicateRepository scanDuplicateRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @RequestMapping(value = {"/page1"}, method = RequestMethod.GET)
    public String manager(Model model) {
        model = setupBugPanel(model);
        model = setupDuplPanel(model);
        return "page1";
    }

    private Model setupBugPanel(Model model) {
        List<ScanBug> last10Bugs = bugRepository.findTop10ByOrderByCreationDateDesc();
        if (last10Bugs != null && !last10Bugs.isEmpty()) {
            List<ScanBug> leftPanel = bugRepository.findAllById(last10Bugs.get(0).getId() - 1);
            List<ScanBug> rightPanel = bugRepository.findAllById(last10Bugs.get(0).getId() - 1);
            model.addAttribute("leftPanel", !leftPanel.isEmpty() ? leftPanel.get(0) : null);
            model.addAttribute("currentPanel", last10Bugs.get(0));
            model.addAttribute("rightPanel", !rightPanel.isEmpty() ? rightPanel.get(0) : null);
            model.addAttribute("curDate", dateFormat.format(last10Bugs.get(0).getCreationDate()));
            model.addAttribute("lastBug", last10Bugs);
        }
        return model;
    }

    private Model setupDuplPanel(Model model) {
        List<ScanDuplicate> lastDupl = scanDuplicateRepository.findTop10ByIsNewDuplTrueOrderByCreationDateDesc();
        if (lastDupl != null && !lastDupl.isEmpty()) {
            model.addAttribute("duplicateScan", lastDupl);
            model.addAttribute("isExistDupl", true);
            model.addAttribute("duplicateText", "Виявлено дублювання штрих-кодів!");
            model.addAttribute("isNewDupl", true);
        }
        return model;
    }

    @RequestMapping(value = {"/performCuurentDupl/{id}"}, method = RequestMethod.GET)
    public String performCuurentDupl(@PathVariable Integer id) {
        List<ScanDuplicate> scanDuplicate = scanDuplicateRepository.findAllById(id);
        if (!scanDuplicate.isEmpty() && scanDuplicate.get(0).getId() != null) {
            scanDuplicate.get(0).setIsNewDupl(Boolean.FALSE);
            scanDuplicateRepository.save(scanDuplicate.get(0));
        }
        return "redirect:/page1";
    }

    //?????????
    @RequestMapping(value = {"/page2"}, method = RequestMethod.GET)
    public String searchPanelPage(Model model) {
//        List<Scan> findAll = scanRepository.findAll();
//        if (findAll != null && !findAll.isEmpty()) {
//            model.addAttribute("scan", findAll.get(0));
//            model.addAttribute("scanCode", "");
//        }
        return "page2";
    }

    @RequestMapping(value = "/searchPanel", method = RequestMethod.GET)
    public String searchPanel(@RequestParam(value = "search", required = false) String code, Model model) {

        List<Scan> findAllByScanCode = scanRepository.findAllByScanCode(code);
        if (findAllByScanCode != null && !findAllByScanCode.isEmpty()) {
            model.addAttribute("currentPanel", findAllByScanCode.get(0));
            model.addAttribute("leftPanel", findAllByScanCode.get(0));
            model.addAttribute("rightPanel", findAllByScanCode.get(0));
        }
        return "redirect:/page2";
    }

    @RequestMapping(value = "/clock", method = RequestMethod.GET)
    public String liveClock(Model map) {
        map.addAttribute("currentTime", new Date());
        return "page1 :: #clock";
    }

    @RequestMapping(value = "/bugLiveUpdate", method = RequestMethod.GET)
    public String liveUpdateBugTable(Model model) {
        model = setupBugPanel(model);
        return "page1 :: #bugScansTbl";
    }

    @RequestMapping(value = "/duplLiveUpdate", method = RequestMethod.GET)
    public String liveUpdateDuplTable(Model model) {
        model = setupDuplPanel(model);
        return "page1 :: #duplScansTbl";
    }
}
