package com.ivoronline.springboot_database_jdbctemplate_select_closure.controllers;

import com.ivoronline.springboot_database_jdbctemplate_select_closure.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.sql.PreparedStatement;
import java.util.List;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //=========================================================================================================
  // HELLO
  //=========================================================================================================
  @ResponseBody
  @GetMapping("/hello")
  public List<PersonDTO> hello() {
    List<PersonDTO> personDTOList = select(1, 10);
    return personDTOList;
  }

  //===========================================================================================================
  // SELECT
  //===========================================================================================================
  public List<PersonDTO> select(Integer id, Integer age) {
    return jdbcTemplate.query(
      " SELECT * FROM PERSON WHERE ID > ? AND AGE > ?"    //Parameters order is used => names are ignored
      , new Object[] { id, age }                          //If there are no parameters: new Object[] { }
      , new BeanPropertyRowMapper(PersonDTO.class)
    );
  }

}
