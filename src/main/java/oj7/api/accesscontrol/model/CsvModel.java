package oj7.api.accesscontrol.model;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByName;

public class CsvModel implements Serializable{
    private static final long serialVersionUID = -1L;
    @CsvBindByName(column = "First Name", required = true)
    private String firstName;

    @CsvBindByName(column = "Last Name", required = true)
    private String lastName;

    @CsvBindByName(column = "1 visit only")
    private boolean onlyOneVisit;
}
