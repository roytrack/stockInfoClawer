package com.roytrack.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quotation {
    private Long id;

    private Date transDate;

    private String symbol;

    private String code;

    private String name;

    private BigDecimal trade;

    private BigDecimal pricechange;

    private BigDecimal changepercent;

    private BigDecimal buy;

    private BigDecimal sell;

    private BigDecimal settlement;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal volume;

    private BigDecimal amount;

    private Date ticktime;

    private BigDecimal per;

    private BigDecimal perD;

    private BigDecimal nta;

    private BigDecimal pb;

    private BigDecimal mktcap;

    private BigDecimal nmc;

    private BigDecimal turnoverratio;

    private String favor;

    private String guba;



}