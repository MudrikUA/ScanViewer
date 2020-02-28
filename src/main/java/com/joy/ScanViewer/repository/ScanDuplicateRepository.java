/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joy.ScanViewer.repository;

import com.joy.ScanViewer.entity.ScanBug;
import com.joy.ScanViewer.entity.ScanDuplicate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author User
 */
public interface ScanDuplicateRepository extends JpaRepository<ScanDuplicate, Long> {

    List<ScanDuplicate> findAllByScanCode(String scanCode);

    List<ScanDuplicate> findTop1ByOrderByCreationDateDesc();

    List<ScanDuplicate> findTop10ByIsNewDuplTrueOrderByCreationDateDesc();
    
    List<ScanDuplicate> findAllById(Integer id);

//        @Query("select u from Users u where u.email like '%@gmail.com%'")//если этого мало можно написать
//    //собственный запрос на языке похожем на SQL
//    List<Users> findWhereEmailIsGmail();
//    @Query(value = "select * from users where name like '%smith%'", nativeQuery = true)
//    //если и этого мало - можно написать запрос на чистом SQL и все это будет работать
//    List<Users> findWhereNameStartsFromSmith();
}
