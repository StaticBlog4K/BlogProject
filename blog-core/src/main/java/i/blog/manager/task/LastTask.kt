package i.blog.manager.task

import java.util.Optional
import kotlin.reflect.KClass

/**
 * 上次任务信息
 *
 * @param T : Any 任务的返回对象
 * @property name String 任务的名称
 * @property clazz KClass<ITask<T>> 任务的实现 CLASS
 * @property value Optional<T> 任务的返回值,如果没有返回值或返回值为null 则为空`Optional`
 * @constructor
 */
data class LastTask<T : Any>(
    val name: String,
    val clazz: KClass<ITask<T>>,
    val value: Optional<T>
)
