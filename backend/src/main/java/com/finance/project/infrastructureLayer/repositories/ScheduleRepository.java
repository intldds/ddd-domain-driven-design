package com.finance.project.infrastructureLayer.repositories;

import com.finance.project.domainLayer.repositoriesInterfaces.IScheduleRepository;
import org.springframework.stereotype.Repository;
import com.finance.project.domainLayer.domainEntities.aggregates.scheduling.Scheduling;
import com.finance.project.domainLayer.domainEntities.vosShared.ScheduleID;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
public class ScheduleRepository implements IScheduleRepository {

    private List<Scheduling> schedulings;

    public static ScheduleRepository createScheduleRepository() {
        return new ScheduleRepository();
    }

    public ScheduleRepository() {
        this.schedulings = new ArrayList<Scheduling>();
    }

    @Override
    public boolean saveScheduling(Scheduling scheduling) {
        if (!schedulings.contains(scheduling)) {
            return schedulings.add(scheduling);
        } else {
            return false;

        }
    }

    @Override
    public Scheduling findSchedulingByScheduleID(ScheduleID scheduleID) {
        for (Scheduling schedulingToAnalyze : schedulings) {
            if (schedulingToAnalyze.checkSchedulingID(scheduleID)) {
                return schedulingToAnalyze;
            }
        }
        return null;
    }

    @Override
    public int countSchedulings() {
        return schedulings.size();
    }

    @Override
    public boolean checkIfScheduleIDExists(ScheduleID scheduleID) {
        for (Scheduling schedulingToAnalyze : schedulings) {
            if (schedulingToAnalyze.checkSchedulingID(scheduleID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Scheduling> getSchedulings() {
        return schedulings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduleRepository)) return false;

        ScheduleRepository that = (ScheduleRepository) o;

        List<Scheduling> schedulings = this.schedulings;
        List<Scheduling> otherScheduling = that.schedulings;

        if (schedulings.size() != otherScheduling.size()) {
            return false;
        }

        for(Scheduling schedulingToAnalyze  : schedulings) {
            if(!otherScheduling.contains(schedulingToAnalyze)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode () {
        return Objects.hash(schedulings);
    }
}
