package com.finance.project.domainLayer.repositoriesInterfaces;

import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.scheduling.Scheduling;
import com.finance.project.domainLayer.domainEntities.vosShared.ScheduleID;

import java.util.List;

/**
 * The interface Schedule repository.
 */
@Repository
public interface IScheduleRepository {
    /**
     * Save scheduling boolean.
     *
     * @param scheduling the scheduling
     * @return the boolean
     */
    boolean saveScheduling(Scheduling scheduling);

    /**
     * Find scheduling by schedule id scheduling.
     *
     * @param scheduleID the schedule id
     * @return the scheduling
     */
    Scheduling findSchedulingByScheduleID(ScheduleID scheduleID);

    /**
     * Count schedulings int.
     *
     * @return the int
     */
    int countSchedulings();

    /**
     * Check if schedule id exists boolean.
     *
     * @param scheduleID the schedule id
     * @return the boolean
     */
    boolean checkIfScheduleIDExists(ScheduleID scheduleID);

    /**
     * Gets schedulings.
     *
     * @return the schedulings
     */
    List<Scheduling> getSchedulings();
}
