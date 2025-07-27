package ra.managementSoftware.dao;

import ra.managementSoftware.model.Statistic;

import java.util.List;

public interface IStatistic {
    Statistic getTotalStatisticCourseAndStudent();
    List<Statistic> getTotalStatisticStudentInOtherCourse();
    List<Statistic> getTop5TotalStatisticStudent();
    List<Statistic> getStatisticCourseHavingMore10Student();

}
