package cn.likole.jwxt.entity;

/**
 * Created by likole on 1/23/18.
 * 成绩查询结果中的每一项
 */
public class Score {
    /**
     * 编号
     */
    private Id id;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 课程属性名
     */
    private String courseAttributeName;
    /**
     * 课程属性编号
     */
    private String courseAttributeCode;
    /**
     * 学分
     */
    private String credit;
    /**
     * 成绩模式 001分数 002等级
     */
    private String scoreEntryModeCode;
    /**
     * 成绩(等级)
     */
    private String gradeName;
    /**
     * 成绩(分数)
     */
    private double courseScore;
    /**
     * 未通过原因
     */
    private String notByReasonName;
    /**
     * 英文课程名
     */
    private String englishCourseName;
    /**
     * 学年
     */
    private String academicYearCode;
    /**
     * 学期模式代码
     */
    private String termTypeCode;
    /**
     * 学期模式名,比如说"三学期"模式
     */
    private String termTypeName;
    /**
     * 学期代码
     */
    private String termCode;
    /**
     * 学期,比如说"春"季学期
     */
    private String termName;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseAttributeName() {
        return courseAttributeName;
    }

    public void setCourseAttributeName(String courseAttributeName) {
        this.courseAttributeName = courseAttributeName;
    }

    public String getCourseAttributeCode() {
        return courseAttributeCode;
    }

    public void setCourseAttributeCode(String courseAttributeCode) {
        this.courseAttributeCode = courseAttributeCode;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getScoreEntryModeCode() {
        return scoreEntryModeCode;
    }

    public void setScoreEntryModeCode(String scoreEntryModeCode) {
        this.scoreEntryModeCode = scoreEntryModeCode;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public double getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(double courseScore) {
        this.courseScore = courseScore;
    }

    public String getNotByReasonName() {
        return notByReasonName;
    }

    public void setNotByReasonName(String notByReasonName) {
        this.notByReasonName = notByReasonName;
    }

    public String getEnglishCourseName() {
        return englishCourseName;
    }

    public void setEnglishCourseName(String englishCourseName) {
        this.englishCourseName = englishCourseName;
    }

    public String getAcademicYearCode() {
        return academicYearCode;
    }

    public void setAcademicYearCode(String academicYearCode) {
        this.academicYearCode = academicYearCode;
    }

    public String getTermTypeCode() {
        return termTypeCode;
    }

    public void setTermTypeCode(String termTypeCode) {
        this.termTypeCode = termTypeCode;
    }

    public String getTermTypeName() {
        return termTypeName;
    }

    public void setTermTypeName(String termTypeName) {
        this.termTypeName = termTypeName;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseAttributeName='" + courseAttributeName + '\'' +
                ", courseAttributeCode='" + courseAttributeCode + '\'' +
                ", credit='" + credit + '\'' +
                ", scoreEntryModeCode='" + scoreEntryModeCode + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", courseScore=" + courseScore +
                ", notByReasonName='" + notByReasonName + '\'' +
                ", englishCourseName='" + englishCourseName + '\'' +
                ", academicYearCode='" + academicYearCode + '\'' +
                ", termTypeCode='" + termTypeCode + '\'' +
                ", termTypeName='" + termTypeName + '\'' +
                ", termCode='" + termCode + '\'' +
                ", termName='" + termName + '\'' +
                '}';
    }

    public class Id {
        /**
         * 课程号
         */
        private String courseNumber;
        /**
         * 课序号
         */
        private String coureSequenceNumber;
        /**
         * 方案计划号
         */
        private String executiveEducationPlanNumber;

        public String getCourseNumber() {
            return courseNumber;
        }

        public void setCourseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
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
            return "id{" +
                    "courseNumber='" + courseNumber + '\'' +
                    ", coureSequenceNumber='" + coureSequenceNumber + '\'' +
                    ", executiveEducationPlanNumber='" + executiveEducationPlanNumber + '\'' +
                    '}';
        }
    }
}
