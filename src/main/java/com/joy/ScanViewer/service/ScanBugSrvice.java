/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joy.ScanViewer.service;

import com.joy.ScanViewer.repository.ScanBugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service
public class ScanBugSrvice {

    @Autowired
    private final ScanBugRepository scanBugRep;

    public ScanBugSrvice(ScanBugRepository scanBugRepository) {
        this.scanBugRep = scanBugRepository;
    }


}
