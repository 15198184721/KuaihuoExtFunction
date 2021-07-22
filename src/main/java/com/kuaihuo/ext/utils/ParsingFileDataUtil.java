package com.kuaihuo.ext.utils;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 文件解析数据的工具
 */
public class ParsingFileDataUtil {

    /**
     * 解析文件的任务接口
     */
    public interface IParsingFileTask{
        /**
         * 任务的运行体
         */
        void run();
    }

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
     * @param task
     */
    public static void addTask(IParsingFileTask task){
        try {
            loadRunTaskQueueList.add(task);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
