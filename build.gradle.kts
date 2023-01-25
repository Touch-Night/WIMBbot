plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.13.4"
}

group = "com.touchnight"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies {
// https://mvnrepository.com/artifact/net.sourceforge.javacsv/javacsv
    implementation("net.sourceforge.javacsv:javacsv:2.0")
}