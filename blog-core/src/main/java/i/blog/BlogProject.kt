package i.blog

import java.io.Closeable
import java.io.File

/**
 *
 * blog 实例
 *
 */
class BlogProject(src: File, cache: File, readOnly: Boolean) : Closeable {
    private val blogConfig: BlogConfig

    init {
        blogConfig = BlogConfig(src, cache, readOnly)
    }

    /**
     * 启动实例
     * 注意，启动实例后会生成守护线程，会影响 JVM 正常退出，调用销毁方法即可
     */
    fun start() {
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}
