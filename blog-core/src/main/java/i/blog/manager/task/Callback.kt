package i.blog.manager.task

/**
 * 任务回调
 */
@FunctionalInterface
interface Callback<T : Any> {
    /**
     * 任务回调接口
     */
    fun call(data: T)
}
