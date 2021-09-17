package i.blog.manager.task

interface ITaskStatus {

    /**
     * 更新任务进度
     */
    fun updateProcess(process: Float)
}
