package com.tish.mappers;

import com.tish.models.IntegerStatisticsPair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {

	List<IntegerStatisticsPair> getCountryStatisticsByUsers(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);
	List<IntegerStatisticsPair>  getCountryStatisticsByVisits(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);
	List<IntegerStatisticsPair> getCityStatisticsByUsers(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);
	List<IntegerStatisticsPair> getCityStatisticsByVisits(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);
}
