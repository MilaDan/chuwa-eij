package design_pattern.proxy.static_proxy;

/**
 * @author ylyu
 * @version 1.0
 * @date 1/5/22 10:59 PM
 */
public class TrainStation implements SellTickets {
    @Override
    public void sell() {
        System.out.println("Train Station selling tickets");
    }
}
