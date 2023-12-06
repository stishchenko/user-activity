package com.tish.mappers;

import com.tish.models.DoubleStatisticsPair;
import com.tish.models.IntegerStatisticsPair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VisitMapper {

	Integer getTotalVisitsAmount(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);

	Integer getUniqueVisitsAmount(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);

	List<IntegerStatisticsPair> getVisitsAmountByPeriods(@Param("periodType") String periodType, @Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);

	List<IntegerStatisticsPair> getTargetVisitsAmountByPeriods(@Param("periodType") String periodType, @Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);

	List<DoubleStatisticsPair> getAvgVisitTimeByPeriod(@Param("periodType") String periodType,@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);

	List<DoubleStatisticsPair> getAvgVisitTimeByPage(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);

	Integer getCancellationAmount(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);
}
