package com.msc.microservices.common.util;

import java.text.NumberFormat;
import java.util.LinkedList;

/**
 * 一个有边界的监视器,模仿{@link org.springframework.util.StopWatch}
 *
 * @author zjl
 */
public class BoundaryStopWatch {
    /**
     * Identifier of this stop watch. Handy when we have output from multiple stop watches and need
     * to distinguish between them in log or console output.
     */
    private final String id;

    private boolean keepTaskList = true;

    private static final int DEFAULT_BOUNDARY = 10;

    private final int boundary;

    private final LinkedList<TaskInfo> taskList = new LinkedList<>();

    /**
     * Start time of the current task
     */
    private ThreadLocal<Long> startTimeMillis = new ThreadLocal<>();

    /**
     * Name of the current task
     */
    private ThreadLocal<String> currentTaskName = new ThreadLocal<>();

    private TaskInfo lastTaskInfo;

    /**
     * Total running time
     */
    private long totalTimeMillis;
    private final Object lock = new Object();

    /**
     * Construct a new stop watch. Does not start any task.
     */
    public BoundaryStopWatch() {
        this("", DEFAULT_BOUNDARY);
    }

    public BoundaryStopWatch(String id) {
        this(id, DEFAULT_BOUNDARY);
    }

    public BoundaryStopWatch(int boundary) {
        this("", boundary);
    }

    /**
     * Construct a new stop watch with the given id. Does not start any task.
     *
     * @param id identifier for this stop watch. Handy when we have output from multiple stop
     *           watches and need to distinguish between them.
     */
    public BoundaryStopWatch(String id, int boundary) {
        this.id = id;
        this.boundary = boundary > 0 ? Math.min(boundary, DEFAULT_BOUNDARY) : DEFAULT_BOUNDARY;
    }


    /**
     * Return the id of this stop watch, as specified on construction.
     *
     * @return the id (empty String by default)
     * @see #BoundaryStopWatch(String)
     * @since 4.2.2
     */
    public String getId() {
        return this.id;
    }

    /**
     * Determine whether the TaskInfo array is built over time. Set this to "false" when using a
     * BoundaryStopWatch for millions of intervals, or the task info structure will consume
     * excessive memory. Default is "true".
     */
    public void setKeepTaskList(boolean keepTaskList) {
        this.keepTaskList = keepTaskList;
    }


    /**
     * Start an unnamed task. The results are undefined if {@link #stop()} or timing methods are
     * called without invoking this method.
     *
     * @see #stop()
     */
    public void start() throws IllegalStateException {
        start("");
    }

    /**
     * Start a named task. The results are undefined if {@link #stop()} or timing methods are called
     * without invoking this method.
     *
     * @param taskName the name of the task to start
     * @see #stop()
     */
    public void start(String taskName) throws IllegalStateException {
        this.currentTaskName.set(taskName);
        this.startTimeMillis.set(System.currentTimeMillis());
    }

    /**
     * Stop the current task. The results are undefined if timing methods are called without
     * invoking at least one pair {@code start()} / {@code stop()} methods.
     *
     * @see #start()
     */
    public void stop() throws IllegalStateException {
        long lastTime = System.currentTimeMillis() - this.startTimeMillis.get();
        startTimeMillis.remove();
        // 已有的任务数量,去除最旧的
        // TODO 有多线程安全问题,暂时不解决
        synchronized (lock) {
            int totalTask = taskList.size();
            if (totalTask >= boundary) {
                TaskInfo oldest = taskList.pollFirst();
                this.totalTimeMillis -= oldest.timeMillis;
            }
            this.totalTimeMillis += lastTime;
            this.lastTaskInfo = new TaskInfo(this.currentTaskName.get(), lastTime);
            currentTaskName.remove();
            taskList.addLast(lastTaskInfo);
        }
    }

    /**
     * Return the name of the currently running task, if any.
     *
     * @see #
     * @since 4.2.2
     */
    public String currentTaskName() {
        return this.currentTaskName.get();
    }


    /**
     * Return the time taken by the last task.
     */
    public long getLastTaskTimeMillis() throws IllegalStateException {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task interval");
        }
        return this.lastTaskInfo.getTimeMillis();
    }

    /**
     * Return the name of the last task.
     */
    public String getLastTaskName() throws IllegalStateException {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task name");
        }
        return this.lastTaskInfo.getTaskName();
    }

    /**
     * Return the last task as a TaskInfo object.
     */
    public TaskInfo getLastTaskInfo() throws IllegalStateException {
        if (this.lastTaskInfo == null) {
            throw new IllegalStateException("No tasks run: can't get last task info");
        }
        return this.lastTaskInfo;
    }


    /**
     * Return the total time in milliseconds for all tasks.
     */
    public long getTotalTimeMillis() {
        return this.totalTimeMillis;
    }

    /**
     * Return the total time in seconds for all tasks.
     */
    public double getTotalTimeSeconds() {
        return this.totalTimeMillis / 1000.0;
    }

    /**
     * Return an array of the data for tasks performed.
     */
    public TaskInfo[] getTaskInfo() {
        synchronized (lock) {
            if (!this.keepTaskList) {
                throw new UnsupportedOperationException("Task info is not being kept!");
            }
            return this.taskList.toArray(new TaskInfo[taskList.size()]);
        }
    }


    /**
     * Return a short description of the total running time.
     */
    public String shortSummary() {
        return "BoundaryStopWatch '" + getId() + "': running time (millis) = " + getTotalTimeMillis();
    }

    /**
     * Return a string with a table describing all tasks performed. For custom reporting, call
     * getTaskInfo() and use the task info directly.
     */
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder(shortSummary());
        sb.append('\n');
        if (!this.keepTaskList) {
            sb.append("No task info kept");
        } else {
            sb.append("-----------------------------------------\n");
            sb.append("ms     %     Method name\n");
            sb.append("-----------------------------------------\n");
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(5);
            nf.setGroupingUsed(false);
            NumberFormat pf = NumberFormat.getPercentInstance();
            pf.setMinimumIntegerDigits(3);
            pf.setGroupingUsed(false);
            for (TaskInfo task : getTaskInfo()) {
                if (task != null) {
                    sb.append(nf.format(task.getTimeMillis())).append("  ");
                    sb.append(pf.format(task.getTimeSeconds() / getTotalTimeSeconds())).append("  ");
                    sb.append(task.getTaskName()).append("\n");
                }
            }
        }
        return sb.toString();
    }

    /**
     * Return an informative string describing all tasks performed For custom reporting, call {@code
     * getTaskInfo()} and use the task info directly.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(shortSummary());
        if (this.keepTaskList) {
            for (TaskInfo task : getTaskInfo()) {
                if (task != null) {
                    sb.append("; [").append(task.getTaskName()).append("] took ").append(task.getTimeMillis());
                    long percent = Math.round((100.0 * task.getTimeSeconds()) / getTotalTimeSeconds());
                    sb.append(" = ").append(percent).append("%");
                }
            }
        } else {
            sb.append("; no task info kept");
        }
        return sb.toString();
    }


    /**
     * Inner class to hold data about one task executed within the stop watch.
     */
    public static final class TaskInfo {

        private final String taskName;

        private final long timeMillis;

        TaskInfo(String taskName, long timeMillis) {
            this.taskName = taskName;
            this.timeMillis = timeMillis;
        }

        /**
         * Return the name of this task.
         */
        public String getTaskName() {
            return this.taskName;
        }

        /**
         * Return the time in milliseconds this task took.
         */
        public long getTimeMillis() {
            return this.timeMillis;
        }

        /**
         * Return the time in seconds this task took.
         */
        public double getTimeSeconds() {
            return (this.timeMillis / 1000.0);
        }
    }
}
