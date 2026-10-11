public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();//创建第一个对象，采用无参构造
        Student s2 = new Student("B","2",100);
        Student s3 = new Student("C","3",200);
        s1.setName("A");
        s1.setStudentId("1");
        s1.setScore(-1);
        s1.introduce();
        s2.introduce();
        s3.introduce();
        Student s4 = new GraduateStudent("D","4",999,"Dog");
        s4.introduce();
        //s4.reserch();
    }
}
