package com.tish.mappers;

import com.tish.models.Visit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

	Integer getTotalUsersAmount(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);

	List<Visit> getSingleUsersList(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);

	Integer getFirstPageUsersAmount(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("webApp") String webApp);
}
