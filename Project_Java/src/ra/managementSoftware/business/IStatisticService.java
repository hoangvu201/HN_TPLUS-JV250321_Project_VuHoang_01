package ra.managementSoftware.business;

import ra.managementSoftware.model.Statistic;

import java.util.List;

public interface IStatisticService {
    Statistic getTotalStatisticCourseAndStudent();
    List<Statistic> getTotalStatisticStudentInOtherCourse();
    List<Statistic> getTop5TotalStatisticStudent();
    List<Statistic> getStatisticCourseHavingMore10Student();
}
