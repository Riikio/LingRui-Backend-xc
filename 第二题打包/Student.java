public  class Student {
    private String name;
    private String studentId;
    private int score;

    public String getName(){
        return this.name;
    }
    public String getStudentId(){
        return this.studentId;
    }
    public int getScore(){
        return this.score;
    }

    //上边为getter 用于读取

    public void setName(String name) {
        this.name = name;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public void setScore(int score) {
        if(score >= 0)this.score = score;
        else {System.out.println("输入正数"); // 防止数据不符合实际
            this.score = 0;
        }
    }

    //这里为setter 用于输入

    public Student(){
        this.name = "";
        this.score = 0;
        this.studentId = "";
    }//构造空参
    public Student(String name,String studentId,int score){
        this.name = name;
        this.score = score;
        this.studentId = studentId;
    }//有参构造

    public void introduce(){
        System.out.println("我是"+name+"学号"+studentId+"成绩"+score);
    }//此方法由于跟private修饰的变量在同一个类中，因此不需要通过getter调用
}//创建学生这一个类，并设置变量及方法

