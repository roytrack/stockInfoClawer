package com.roytrack.mapper;

import com.roytrack.model.Quotation;

public interface QuotationMapper {


    int deleteByPrimaryKey(Long id);

    int insert(Quotation record);

    int insertSelective(Quotation record);

    Quotation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Quotation record);

    int updateByPrimaryKey(Quotation record);
}