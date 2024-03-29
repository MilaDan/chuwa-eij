package multi_threading.status;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Yanan Lyu
 * @date 3/21/22 11:50 AM
 * @description https://www.liaoxuefeng.com/wiki/1252599548343744/1306580911915042
 */
public class WaitNotify {
    public static void main(String[] args) throws InterruptedException {
        var q = new TaskQueue();
        var ts = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            var t = new Thread() {
                @Override
                public void run() {
                    // 执行task
                    while (true) {
                        try {
                            String s = q.getTask();
                            System.out.println("execute task: " + s);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            };
            t.start();
            ts.add(t);
        }

        var add = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 放入task
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                q.addTask(s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        add.start();
        add.join();
        Thread.sleep(100);
        for (var t : ts) {
            t.interrupt();
        }
    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }

        return queue.remove();
    }
}