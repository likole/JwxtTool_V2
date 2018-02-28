package cn.likole.jwxt.entity;

import java.util.List;

/**
 * Created by likole on 1/24/18.
 * 课表中的每一项
 */
public class Course {
    /**
     * 编号
     */
    private Id id;
    /**
     * 学分
     */
    private int unit;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 授课教师
     */
    private String attendClassTeacher;
    /**
     * 课程属性码
     */
    private String coursePropertiesCode;
    /**
     * 课程属性名
     */
    private String coursePropertiesName;
    /**
     * 上课时间
     */
    private List<TimeAndPlace> timeAndPlaceList;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAttendClassTeacher() {
        return attendClassTeacher;
    }

    public void setAttendClassTeacher(String attendClassTeacher) {
        this.attendClassTeacher = attendClassTeacher;
    }

    public String getCoursePropertiesCode() {
        return coursePropertiesCode;
    }

    public void setCoursePropertiesCode(String coursePropertiesCode) {
        this.coursePropertiesCode = coursePropertiesCode;
    }

    public String getCoursePropertiesName() {
        return coursePropertiesName;
    }

    public void setCoursePropertiesName(String coursePropertiesName) {
        this.coursePropertiesName = coursePropertiesName;
    }

    public List<TimeAndPlace> getTimeAndPlaceList() {
        return timeAndPlaceList;
    }

    public void setTimeAndPlaceList(List<TimeAndPlace> timeAndPlaceList) {
        this.timeAndPlaceList = timeAndPlaceList;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", unit=" + unit +
                ", courseName='" + courseName + '\'' +
                ", attendClassTeacher='" + attendClassTeacher + '\'' +
                ", coursePropertiesCode='" + coursePropertiesCode + '\'' +
                ", coursePropertiesName='" + coursePropertiesName + '\'' +
                ", timeAndPlaceList=" + timeAndPlaceList +
                '}';
    }

    public class Id {
        /**
         * 课程号
         */
        private String coureNumber;
        /**
         * 课序号
         */
        private String coureSequenceNumber;
        /**
         * 方案计划号
         */
        private String executiveEducationPlanNumber;

        public String getCoureNumber() {
            return coureNumber;
        }

        public void setCoureNumber(String coureNumber) {
            this.coureNumber = coureNumber;
        }

        public String getCoureSequenceNumber() {
            return coureSequenceNumber;
        }

        public void setCoureSequenceNumber(String coureSequenceNumber) {
            this.coureSequenceNumber = coureSequenceNumber;
        }

        public String getExecutiveEducationPlanNumber() {
            return executiveEducationPlanNumber;
        }

        public void setExecutiveEducationPlanNumber(String executiveEducationPlanNumber) {
            this.executiveEducationPlanNumber = executiveEducationPlanNumber;
        }

        @Override
        public String toString() {
            return "Id{" +
                    "coureNumber='" + coureNumber + '\'' +
                    ", coureSequenceNumber='" + coureSequenceNumber + '\'' +
                    ", executiveEducationPlanNumber='" + executiveEducationPlanNumber + '\'' +
                    '}';
        }
    }

    /**
     * 描述上课的时间地点
     */
    public class TimeAndPlace {
        /**
         * 周,例如"111111111111111100000000"
         */
        private String classWeek;
        /**
         * 周,例如"1-16周"
         */
        private String weekDescription;
        /**
         * 星期
         */
        private int classDay;
        /**
         * 节次
         */
        private int classSessions;
        /**
         * 节数
         */
        private int continuingSession;
        /**
         * 校区,例如"北校区"
         */
        private String campusName;
        /**
         * 教学楼,例如"主楼"
         */
        private String teachingBuildingName;
        /**
         * 教室,例如"307"
         */
        private String classroomName;

        public String getClassWeek() {
            return classWeek;
        }

        public void setClassWeek(String classWeek) {
            this.classWeek = classWeek;
        }

        public String getWeekDescription() {
            return weekDescription;
        }

        public void setWeekDescription(String weekDescription) {
            this.weekDescription = weekDescription;
        }

        public int getClassDay() {
            return classDay;
        }

        public void setClassDay(int classDay) {
            this.classDay = classDay;
        }

        public int getClassSessions() {
            return classSessions;
        }

        public void setClassSessions(int classSessions) {
            this.classSessions = classSessions;
        }

        public int getContinuingSession() {
            return continuingSession;
        }

        public void setContinuingSession(int continuingSession) {
            this.continuingSession = continuingSession;
        }

        public String getCampusName() {
            return campusName;
        }

        public void setCampusName(String campusName) {
            this.campusName = campusName;
        }

        public String getTeachingBuildingName() {
            return teachingBuildingName;
        }

        public void setTeachingBuildingName(String teachingBuildingName) {
            this.teachingBuildingName = teachingBuildingName;
        }

        public String getClassroomName() {
            return classroomName;
        }

        public void setClassroomName(String classroomName) {
            this.classroomName = classroomName;
        }

        @Override
        public String toString() {
            return "TimeAndPlace{" +
                    "classWeek='" + classWeek + '\'' +
                    ", weekDescription='" + weekDescription + '\'' +
                    ", classDay=" + classDay +
                    ", classSessions=" + classSessions +
                    ", continuingSession=" + continuingSession +
                    ", campusName='" + campusName + '\'' +
                    ", teachingBuildingName='" + teachingBuildingName + '\'' +
                    ", classroomName='" + classroomName + '\'' +
                    '}';
        }
    }
}
