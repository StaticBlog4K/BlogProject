package i.blog.manager.task

import java.util.Optional

interface ITaskContext {
    /**
     * 获取上次任务的信息
     */
    fun lastTask(): Optional<LastTask<Any>>

    /**
     * 管理当前任务信息
     */
    fun currentTask(): ITaskStatus
}
