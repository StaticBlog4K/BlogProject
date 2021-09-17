package i.blog.manager.task

/**
 * 任务队列信息
 * @property name String 任务名称
 * @property process Float 任务进度
 * @constructor
 */
data class TaskInfo(
    val name: String,
    val process: Float
)
