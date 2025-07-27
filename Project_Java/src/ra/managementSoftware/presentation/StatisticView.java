package ra.managementSoftware.presentation;

import ra.managementSoftware.business.IStatisticService;
import ra.managementSoftware.business.impl.StatisticServiceImpl;
import ra.managementSoftware.model.Statistic;
import ra.managementSoftware.validation.Validator;

import java.util.List;
import java.util.Scanner;

public class StatisticView {
    private final IStatisticService statisticService;

    public StatisticView() {
        this.statisticService = new StatisticServiceImpl();
    }

    public void menuStatisticView(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("================MENU THỐNG KÊ===============");
            System.out.println("1.Thống kê số lượng khoá học và học viên");
            System.out.println("2.Thống kê học viên theo từng khoá học");
            System.out.println("3.Top 5 khoá học đông học viên nhất");
            System.out.println("4.Liệt kê khoá học có trên 10 học viên");
            System.out.println("5.Quay về menu chính: ");
            System.out.print("Lựa chọn chức năng: ");
            try {
                int choice = Validator.validateMenuChoice(scanner, 1, 5);
                switch (choice) {
                    case 1:
                        displayTotalStatisticCourseAndStudent();
                        break;
                    case 2:
                        displayTotalStatisticStudentInCourse();
                        break;
                    case 3:
                        displayTop5CoursesWithMostStudents();
                        break;
                    case 4:
                        displayCoursesHavingMoreThan10Students();
                        break;
                    case 5:
                        exit = true;
                        break;

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!exit);
    }

    public void displayTotalStatisticStudentInCourse() {
        List<Statistic> statistics = statisticService.getTotalStatisticStudentInOtherCourse();
        if (statistics.isEmpty()) {
            System.out.println("Không có dữ liệu thống kê.");
            return;
        }
        System.out.println("===========THỐNG KÊ SỐ LƯỢNG HỌC VIÊN THEO TỪNG KHOÁ HỌC=============");
        System.out.printf("| %-30s | %-15s |\n", "Tên khoá học", "Số sinh viên");
        System.out.println("--------------------------------------------------------------");

        for (Statistic s : statistics) {
            System.out.printf("| %-30s | %-15d |\n", s.getCourseName(), s.getStudentCount());
        }
    }

    public void displayTop5CoursesWithMostStudents() {
        List<Statistic> topCourses = statisticService.getTop5TotalStatisticStudent();

        if (topCourses.isEmpty()) {
            System.out.println("Không có dữ liệu thống kê.");
            return;
        }
        System.out.println("==============TOP 5 KHOÁ HỌC ĐÔNG SINH VIÊN NHẤT====================");
        System.out.printf("| %-5s | %-30s | %-15s |\n", "Top", "Tên khoá học", "Số sinh viên");
        System.out.println("---------------------------------------------------------------");

        int index = 1;
        for (Statistic s : topCourses) {
            System.out.printf("| %-5d | %-30s | %-15d |\n",
                    index, s.getCourseName(), s.getStudentCount());
            index++;
        }
    }

    public void displayCoursesHavingMoreThan10Students() {
        List<Statistic> courseList = statisticService.getStatisticCourseHavingMore10Student();
        if (courseList.isEmpty()) {
            System.out.println("Không có khoá học nào có hơn 10 sinh viên.");
            return;
        }
        System.out.println("==============CÁC KHOÁ HỌC CÓ TRÊN 10 SINH VIÊN====================");
        System.out.printf("| %-5s | %-30s | %-15s |\n", "STT", "Tên khoá học", "Số sinh viên");
        System.out.println("---------------------------------------------------------------");
        int index = 1;
        for (Statistic s : courseList) {
            System.out.printf("| %-5d | %-30s | %-15d |\n",
                    index++, s.getCourseName(), s.getStudentCount());
        }
    }

    public void displayTotalStatisticCourseAndStudent() {
        Statistic statistic = statisticService.getTotalStatisticCourseAndStudent();
        if (statistic != null) {
            System.out.println("===========THỐNG KÊ SỐ LƯỢNG KHOÁ HỌC VÀ HỌC VIÊN=============");
            System.out.printf("| %-15s | %-15s |\n", "Total Courses", "Total Students");
            System.out.printf("| %-15d | %-15d |\n",
                    statistic.getTotalCourse(), statistic.getTotalStudents());
        } else {
            System.out.println("Không có dữ liệu thống kê.");
        }
    }




}
