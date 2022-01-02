package factory.simplefactory.staticfactory;

/**
 * @author ylyu
 * @date 1/2/22 9:10 AM
 * @version 1.0
 */
public class Client {
    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();
        Coffee coffee = store.orderCoffee("latte");
        System.out.println(coffee.getName());

        System.out.println("**************************");
        Coffee american = store.orderCoffee("American");
        System.out.println(american.getName());
    }
}
