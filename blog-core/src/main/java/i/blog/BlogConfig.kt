package i.blog

import java.io.File

data class BlogConfig(val src: File, val cache: File, val readOnly: Boolean)
