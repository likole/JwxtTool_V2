package cn.likole.jwxt.entity;

/**
 * Created by likole on 1/23/18.
 * 一场考试的信息,包括时间,科目,具体信息
 */
public class Exam {
    /**
     * 考试时间
     */
    private String time;
    /**
     * 考试名称
     */
    private String name;
    /**
     * 详细信息
     */
    private String info;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
