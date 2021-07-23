package com.kuaihuo.ext.utils;

import lombok.Synchronized;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 文件解析数据的工具
 */
public class ParsingFileDataUtil {

    /**
     * 解析文件的任务接口
     */
    public interface IParsingFileTask {
        /**
         * 任务的运行体
         */
        void run();
    }

    /**
     * 运行任务的线程
     */
    private static Thread runTaskThread = null;

    /**
     * 等待执行的任务，解析文件的任务
     * offer(E e) 将指定元素插入此队列的尾部。
     * poll() 获取并移除此队列的头，如果此队列为空，则返回 null。
     * peek() 获取但不移除此队列的头；如果此队列为空，则返回 null。
     * remove(Object o) 从队列中移除指定元素的单个实例（如果存在）。
     */
    private static LinkedBlockingQueue<IParsingFileTask> loadRunTaskQueueList = new LinkedBlockingQueue<>();

    /**
     * 添加一个新任务到队列中
     *
     * @param task
     */
    public static void addTask(IParsingFileTask task) {
        try {
            loadRunTaskQueueList.add(task);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (runTaskThread == null) {
                createRunTaskThread();
            }
        }
    }

    //创建运行任务的线程
    private synchronized static void createRunTaskThread() {
        if (runTaskThread != null) {
            return;
        }
        PrintUtil.println("-------开始创建任务线程------");
        runTaskThread = new Thread(() -> {
            Long fastRunTime = 0L;
            while (true) {
                try {
                    loadRunTaskQueueList.take().run();
                    if(System.currentTimeMillis() - fastRunTime <= 0){
                        break; //快速执行。认为线程在空转。所以直接退出线程。等待下次任务重新创建一个新的
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }catch (Exception e) {
                    PrintUtil.println("-------任务执行出现了错误------");
                    e.printStackTrace();
                }finally {
                    fastRunTime = System.currentTimeMillis();
                }
            }
            PrintUtil.println("----------结束了任务线程。等待下次任务重新创建------");
            //置空，让下次任务重新创建
            runTaskThread = null;
        });
        runTaskThread.start();
    }

}
