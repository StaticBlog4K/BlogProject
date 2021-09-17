package i.blog.manager.task

import java.util.Optional

/**
 * 串行任务调度管理器
 */
interface ITaskManager {
    /**
     * 获取当前执行任务信息
     *
     */
    val currentTaskInfo: Optional<TaskInfo>

    /**
     * 提交一个不带返回参数的任务
     * @param task ITask<Unit> 任务
     */
    fun submit(task: ITask<Unit>)

    /**
     * 提交一个任务并绑定回调
     *
     * @param task ITask<T> 任务
     * @param call Callback<T> 回调
     */
    fun <T : Any> submit(task: ITask<T>, call: Callback<T>)

    /**
     * 撤销任务（仅在任务未执行时可撤销）
     */
    fun revoke(task: ITask<*>)

    /**
     * 提交到任务队列后阻塞，直到任务释放才继续执行
     *
     * @param task ITask<T> 任务
     */
    fun <T : Any> execute(task: ITask<T>): T

    /**
     * 不再接收新任务，等待执行完成后退出
     */
    fun shutdown()
}
