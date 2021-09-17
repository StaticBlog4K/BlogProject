group = "com.gitHub.StaticBlog4K.BlogProject"

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
    for (childProject in childProjects.values) {
        delete(childProject.buildDir)
    }
}
