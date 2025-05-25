plugins {
    `java-library`
    id("com.gradleup.shadow") version "9.0.0-beta11"
}
group = "fr.maxlego08.superiorskyblock"
version = "1.0.0"

extra.set("targetFolder", file("target/"))
extra.set("apiFolder", file("target-api/"))
extra.set("classifier", System.getProperty("archive.classifier"))
extra.set("sha", System.getProperty("github.sha"))

repositories {
    mavenLocal()
    mavenCentral()

    maven(url = "https://jitpack.io/")
    maven(url = "https://repo.papermc.io/repository/maven-public/")
    maven(url = "https://oss.sonatype.org/content/groups/public/")
    maven(url = "https://repo.extendedclip.com/releases/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly(files("libs/SuperiorSkyblock2-2024.4-b445.jar"))
    compileOnly(files("libs/zMenu-API-1.1.0.0.jar"))
    compileOnly("me.clip:placeholderapi:2.11.6")
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks {
    shadowJar {
        rootProject.extra.properties["sha"]?.let { sha ->
            archiveClassifier.set("${rootProject.extra.properties["classifier"]}-${sha}")
        } ?: run {
            archiveClassifier.set(rootProject.extra.properties["classifier"] as String?)
        }
        destinationDirectory.set(rootProject.extra["targetFolder"] as File)
    }

    build {
        dependsOn(shadowJar)
    }

    compileJava {
        options.release = 21
    }
}