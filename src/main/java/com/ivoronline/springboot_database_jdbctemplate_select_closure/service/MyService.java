package com.ivoronline.springboot_database_jdbctemplate_select_closure.service;

import com.ivoronline.springboot_database_jdbctemplate_select_closure.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyService {

  //PROPERTIES
  @Autowired private JdbcTemplate jdbcTemplate;

  //=========================================================================================================
  // SELECT RECORD
  //=========================================================================================================
  public PersonDTO selectRecord(Integer id) {
      return jdbcTemplate.queryForObject(
      "SELECT * FROM PERSON WHERE ID = " + id,
      (resultSet, rowNum) -> {

        //CREATE DTO
        PersonDTO personDTO = new PersonDTO(); //@NoArgsConstructor
                  personDTO.setId  (resultSet.getInt   ("ID"  ));
                  personDTO.setName(resultSet.getString("NAME"));
                  personDTO.setAge (guardAgainstNullNumbers("AGE", resultSet));

        //RETURN DTO
        return    personDTO;

      });
  }

  //=========================================================================================================
  // SELECT RECORDS
  //=========================================================================================================
  //Automatically cycles through resultSet and adds personDTO to a List
  public List<PersonDTO> selectRecords(Integer id) {
    return jdbcTemplate.query(
      "SELECT * FROM PERSON WHERE ID >= " + id,
      (resultSet, rowNum) -> {                    //NEXT RECORD

        //CREATE DTO
        PersonDTO personDTO = new PersonDTO(      //@AllArgsConstructor
          resultSet.getInt   ("ID"  ),
          resultSet.getString("NAME"),
          guardAgainstNullNumbers("AGE", resultSet)
        );

        //ADD DTO TO LIST
        return personDTO;

      });
  }

  //=========================================================================================================
  // SELECT RECORDS MANUALLY
  //=========================================================================================================
  //We manually cycle through resultSet adding personDTO to a List.
  public List<PersonDTO> selectRecordsManually(Integer id) {
    return jdbcTemplate.query(
      "SELECT * FROM PERSON WHERE ID >= " + id,
      resultSet -> {
        List<PersonDTO> personDTOList = new ArrayList<>();
        while(resultSet.next()) {              //NEXT RECORD

          //CREATE DTO
          PersonDTO personDTO = new PersonDTO(); //@NoArgsConstructor
                    personDTO.setId  (resultSet.getInt   ("ID"  ));
                    personDTO.setName(resultSet.getString("NAME"));
                    personDTO.setAge (guardAgainstNullNumbers("AGE", resultSet));

          //ADD DTO TO LIST
          personDTOList.add(personDTO);

        }

        //RETURN DTO LIST
        return personDTOList;

      });
  }

  //=========================================================================================================
  // GUARD AGAINST NULL NUMBERS
  //=========================================================================================================
  public Integer guardAgainstNullNumbers(String columnName, ResultSet resultSet) throws SQLException {
    Integer value = resultSet.getInt(columnName); //It will be 0 for null
    return resultSet.wasNull() ? null : value;
  }

}


