package com.tish.mappers;

import com.tish.models.IntegerStatisticsPair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeviceMapper {

	Integer getDevicesAmount(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<IntegerStatisticsPair> getDeviceStatisticsByType(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<IntegerStatisticsPair> getDeviceStatisticsByOS(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<IntegerStatisticsPair> getDeviceStatisticsByBrowser(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
