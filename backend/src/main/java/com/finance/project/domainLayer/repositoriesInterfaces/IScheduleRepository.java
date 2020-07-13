package com.finance.project.domainLayer.repositoriesInterfaces;

import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.scheduling.Scheduling;
import com.finance.project.domainLayer.domainEntities.vosShared.ScheduleID;

import java.util.List;


@Repository
public interface IScheduleRepository {

    boolean saveScheduling(Scheduling scheduling);

    Scheduling findSchedulingByScheduleID(ScheduleID scheduleID);

    int countSchedulings();

    boolean checkIfScheduleIDExists(ScheduleID scheduleID);

    List<Scheduling> getSchedulings();
}
