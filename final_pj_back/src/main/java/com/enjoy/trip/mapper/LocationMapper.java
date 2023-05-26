package com.enjoy.trip.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.enjoy.trip.dto.Gugun;
import com.enjoy.trip.dto.Sido;

@Mapper
public interface LocationMapper {
	List<Sido> getSidoList() throws SQLException;
	List<Gugun> getGugunList(int sidoCode) throws SQLException;
}
