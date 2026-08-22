plugins {
    java
    id("com.gradleup.shadow")
    id("io.github.intisy.github-gradle") version "1.8.3"
}

group = "dev.sefiraat"
description = "SlimeTinker is a Slimefun addon that recreates Tinker's Construct within Slimefun."

apply(from = "https://raw.githubusercontent.com/Slimefun5/gradle/stable/slimefun-addon.gradle")

repositories {
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    githubImplementation("Slimefun5:SlimefunMetrics:v1.0.0")
    githubImplementation("Slimefun5:InfinityLib:v1.3.14.9")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
}

tasks {
    shadowJar {
        relocate("org.bstats", "slimetinker.libs.bstats")
        relocate("io.github.mooy1.infinitylib", "io.github.sefiraat.slimetinker.infinitylib")
        relocate("co.aikar.commands", "io.github.sefiraat.slimetinker.acf")
        relocate("co.aikar.locales", "io.github.sefiraat.slimetinker.locales")
        exclude("io/github/thebusybiscuit/slimefun5/**")
        exclude("me/mrCookieSlime/**")
    }
}
