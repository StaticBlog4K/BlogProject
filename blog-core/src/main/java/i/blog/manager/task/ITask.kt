package i.blog.manager.task

import java.io.Closeable

/**
 * 任务实例 ，一个对象仅会执行一次实例，为保证数据安全性，
 * 任务采用串行化方式执行
 *
 */
interface ITask<T : Any> : Closeable {

    /**
     * 任务名称，方便输出，无其他作用
     */
    val name: String
        get() = "默认任务"

    /**
     *  获取上传
     */

    /**
     * 可复写此方法来控制任务是否执行，此方法在任务启动前调用
     */
    fun check(context: ITaskContext): Boolean {
        return true
    }

    /**
     * 任务执行触发函数，可返回值让下一个任务或者回调获取
     */
    fun run(context: ITaskContext): T

    /**
     * 任务结束时回调函数
     */
    override fun close() {}
}
