/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joy.ScanViewer.repository;

import com.joy.ScanViewer.entity.Scan;
import com.joy.ScanViewer.entity.Settings;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface SettingRepository extends JpaRepository<Settings, Long> {

    List<Scan> findAllBySettingName(String settingName);
}
