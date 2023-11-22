package com.tish.mappers;

import com.tish.models.DoubleStatisticsPair;
import com.tish.models.IntegerStatisticsPair;
import com.tish.models.Visit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VisitMapper {

	Integer getTotalVisitsAmountWithTimePeriod(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<Visit> getUniqueVisitsAmountWithTimePeriod(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<IntegerStatisticsPair> getVisitsAmountByPeriods(@Param("periodType") String periodType, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<IntegerStatisticsPair> getTargetVisitsAmountByPeriods(@Param("periodType") String periodType, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<DoubleStatisticsPair> getAvgVisitTimeByPeriod(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	List<DoubleStatisticsPair> getAvgVisitTimeByPage(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	Integer getCancellationAmountWithTimePeriod(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
}
