package design_pattern.state.after;

/**
 * @author Yanan Lyu
 * @date 2/21/22 7:50 AM
 * @description 电梯run状态类
 */
public class RunningState extends LiftState {
    // 运行的时候开电梯门？你疯了？电梯不会给你开的
    @Override
    public void open() {
        // do nothing
    }

    // 电梯关门？这是肯定了
    @Override
    public void close() {//虽然可以关门，但这个动作不归我执行
        //do nothing
    }

    // 这是在运行状态下要实现的方法
    @Override
    public void run() {
        System.out.println("电梯正在运行...");
    }

    // 这个事绝对是合理的，光运行不停止还有谁敢做这个电梯？
    @Override
    public void stop() {
        super.context.setLiftState(Context.STOPPING_STATE);
        super.context.stop();
    }
}
