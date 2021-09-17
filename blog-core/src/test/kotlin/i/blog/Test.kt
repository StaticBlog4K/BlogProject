package i.blog

import i.blog.modules.localStorage.LocalFileStorage
import java.io.File

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        BlogProject(File(""), File(""), true) {
            install(LocalFileStorage) {
            }
        }
    }
}
