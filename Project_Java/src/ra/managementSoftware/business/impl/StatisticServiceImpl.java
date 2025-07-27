package ra.managementSoftware.business.impl;

import ra.managementSoftware.business.IStatisticService;
import ra.managementSoftware.dao.IStatistic;
import ra.managementSoftware.dao.impl.StatisticImpl;
import ra.managementSoftware.model.Statistic;

import java.util.List;

public class StatisticServiceImpl implements IStatisticService {
    private final IStatistic statistic;


    public StatisticServiceImpl() {
        statistic = new StatisticImpl();
    }

    @Override
    public Statistic getTotalStatisticCourseAndStudent() {
        return statistic.getTotalStatisticCourseAndStudent();
    }

    @Override
    public List<Statistic> getTotalStatisticStudentInOtherCourse() {
        return statistic.getTotalStatisticStudentInOtherCourse();
    }

    @Override
    public List<Statistic> getTop5TotalStatisticStudent() {
        return statistic.getTop5TotalStatisticStudent();
    }

    @Override
    public List<Statistic> getStatisticCourseHavingMore10Student() {
        return statistic.getStatisticCourseHavingMore10Student();
    }
}
