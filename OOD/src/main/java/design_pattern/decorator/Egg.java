package design_pattern.decorator;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/9/22 12:37 PM
 * @description 具体装饰（ConcreteDecorator）角色 ：实现抽象装饰的相关方法，并给具体构件对象添加附
 * 加的责任。
 */
public class Egg extends Garnish {
    public Egg(FastFood fastFood) {
        super(fastFood, 1, "egg-");
    }

    @Override
    public float cost() {
        return getPrice() + getFastFood().cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + getFastFood().getDesc();
    }
}
