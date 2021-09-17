package i.blog.manager.task.manager

import com.github.openEdgn.logger4k.getLogger
import i.blog.manager.task.Callback
import i.blog.manager.task.ITask
import i.blog.manager.task.ITaskContext
import i.blog.manager.task.ITaskManager
import i.blog.manager.task.ITaskStatus
import i.blog.manager.task.LastTask
import i.blog.manager.task.TaskInfo
import java.util.Deque
import java.util.Optional
import java.util.concurrent.ConcurrentLinkedDeque
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

/**
 *  通用串行任务管理器
 *
 *  一个单线程FIFO任务管理器
 */
class UniversalTaskManager : ITaskManager, ITaskContext, Thread.UncaughtExceptionHandler {
    override var currentTaskInfo: Optional<TaskInfo> = Optional.empty()
    private val lock = ReentrantLock()
    private val condition: Condition = lock.newCondition()

    /**
     * 状态
     */
    private val status = AtomicInteger(TASK_MANAGER_STATUS_CREATE)

    /**
     * 任务队列
     */
    private val tasks: Deque<TaskItem<*>> = ConcurrentLinkedDeque()

    /**
     * 串行线程
     */
    private val thread: Thread = thread(name = "task-mgr@${count.inc()}") {
        while (status.get() != TASK_MANAGER_STATUS_SHUTDOWN || tasks.isNotEmpty()) {
            /**
             * 处理线程实例
             */
            executeTask()
        }
    }

    /**
     * 串行线程方法
     *
     */
    private fun executeTask() {
        if (tasks.isEmpty()) {
            // 没有任务，线程挂起
            condition.await()
        }
        val task = tasks.pollFirst() ?: return
        // 得到任务实例
    }

    init {
        thread.uncaughtExceptionHandler = this
        thread.start()
        logger.debug("任务线程已启动！")
    }

    override fun submit(task: ITask<Unit>) {
        synchronized(tasks) {
        }
    }

    override fun <T : Any> submit(task: ITask<T>, call: Callback<T>) {
        synchronized(tasks) {
        }
    }

    override fun revoke(task: ITask<*>) {
        synchronized(tasks) {
        }
    }

    override fun <T : Any> execute(task: ITask<T>): T {
        synchronized(tasks) {
        }

        TODO()
    }

    override fun shutdown() {
        synchronized(tasks) {
        }
    }

    override fun currentTask(): ITaskStatus {
        TODO("Not yet implemented")
    }

    override fun lastTask(): Optional<LastTask<Any>> {
        TODO("Not yet implemented")
    }

    companion object {
        /**
         * 初始化
         */
        private const val TASK_MANAGER_STATUS_CREATE = 0

        /**
         * 就绪
         */
        private const val TASK_MANAGER_STATUS_READY = 1

        /**
         * 销毁
         */
        private const val TASK_MANAGER_STATUS_SHUTDOWN = 2

        @Volatile
        private var count = 0

        private val logger = getLogger()
    }

    internal data class TaskItem<T : Any>(
        val name: String,
        val task: ITask<T>,
        val taskStatus: TaskStatus,
        val callback: Callback<T>,
        val status: AtomicInteger = AtomicInteger(TASK_STATUS_READY)
    ) {
        companion object {
            private const val TASK_STATUS_READY = 0
            private const val TASK_STATUS_RUN = 1
            private const val TASK_STATUS_DESTROY = 2
        }
    }

    internal class TaskStatus : ITaskStatus {
        var process: Float = 0f
        override fun updateProcess(process: Float) {
            this.process = process
        }
    }

    override fun uncaughtException(t: Thread, e: Throwable) {
        logger.errorThrowable("线程发生未捕获的异常！来源线程 $thread.", e)
    }
}
