package com.roytrack.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * Created by roytrack on 2016-08-26.
 */
@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllData {
    private String code;
    private Date day;
    private int count ;
    private List<String> fields;
    private List<Quotation> items;

}
