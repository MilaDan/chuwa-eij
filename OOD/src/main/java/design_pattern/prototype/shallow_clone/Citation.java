package design_pattern.prototype.shallow_clone;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/3/22 8:25 AM
 */
public class Citation implements Cloneable {
    private Student stu;

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    @Override
    public Citation clone() throws CloneNotSupportedException {
        return (Citation) super.clone();
    }

    public void show() {
        System.out.println(stu.getName() + ": get a citation in 2022");
    }
}
