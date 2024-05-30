package com.ivoronline.springboot_database_jdbctemplate_select_closure.controllers;

import com.ivoronline.springboot_database_jdbctemplate_select_closure.dto.PersonDTO;
import com.ivoronline.springboot_database_jdbctemplate_select_closure.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired private MyService myService;

  //=========================================================================================================
  // SELECT RECORD
  //=========================================================================================================
  // Request => http://localhost:8080/selectRecord?id=1
  @ResponseBody
  @GetMapping("/selectRecord")
  public PersonDTO selectRecord(@RequestParam Integer id) {
    PersonDTO personDTO = myService.selectRecord(id);
    return    personDTO;
  }


  //=========================================================================================================
  // SELECT RECORDS
  //=========================================================================================================
  // Request => http://localhost:8080/selectRecords?id=1
  @ResponseBody
  @GetMapping("/selectRecords")
  public List<PersonDTO> selectRecords(@RequestParam Integer id) {
    List<PersonDTO> personDTOList = myService.selectRecords(id);
    return          personDTOList;
  }

  //=========================================================================================================
  // SELECT RECORDS MANUALLY
  //=========================================================================================================
  // Request => http://localhost:8080/selectRecordsManually?id=1
  @ResponseBody
  @GetMapping("/selectRecordsManually")
  public List<PersonDTO> selectRecordsManually(@RequestParam Integer id) {
    List<PersonDTO> personDTOList = myService.selectRecordsManually(id);
    return          personDTOList;
  }

}
