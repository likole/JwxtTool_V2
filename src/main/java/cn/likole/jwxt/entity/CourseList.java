package cn.likole.jwxt.entity;

import java.util.List;

/**
 * Created by likole on 1/24/18.
 * 课表
 */
public class CourseList {
    /**
     * 方案计划号
     */
    private String programPlanCode;
    /**
     * 方案计划名
     */
    private String programPlanName;
    /**
     * 总学分
     */
    private int totalUnits;
    /**
     * 课表
     */
    private List<Course> selectCourseList;

    public String getProgramPlanCode() {
        return programPlanCode;
    }

    public void setProgramPlanCode(String programPlanCode) {
        this.programPlanCode = programPlanCode;
    }

    public String getProgramPlanName() {
        return programPlanName;
    }

    public void setProgramPlanName(String programPlanName) {
        this.programPlanName = programPlanName;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    public List<Course> getSelectCourseList() {
        return selectCourseList;
    }

    public void setSelectCourseList(List<Course> selectCourseList) {
        this.selectCourseList = selectCourseList;
    }

    @Override
    public String toString() {
        return "CourseList{" +
                "programPlanCode='" + programPlanCode + '\'' +
                ", programPlanName='" + programPlanName + '\'' +
                ", totalUnits=" + totalUnits +
                ", selectCourseList=" + selectCourseList +
                '}';
    }
}
