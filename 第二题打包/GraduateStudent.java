public class GraduateStudent extends Student {
    private String advisor;
    public GraduateStudent() {
        super();              // 调用父类无参构造
        this.advisor = "";
    } //无参构造
    public GraduateStudent(String name, String studentId, int score, String advisor) {
        super(name, studentId, score);   // 调用父类构造
        this.advisor = advisor;
    }    // 有参构造
    public String getAdvisor() {
        return advisor;
    } //getter
    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    } //setter
    /*@Override
    public void introduce() {
        System.out.println("我是" + getName() + "，学号" + getStudentId()
                + "，成绩" + getScore() + "，我的导师是" + advisor);
    }  //重写introduce*/
    public void research() {
        System.out.println("我在做科研");
    } //特有方法
}