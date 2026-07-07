plugins {
    java
    id("com.gradleup.shadow")
    id("io.github.intisy.github-gradle") version "1.8.3"
}

group = "dev.sefiraat"
description = "SlimeTinker is a Slimefun addon that recreates Tinker's Construct within Slimefun."

// Shared Slimefun-addon build conventions (Java 8, spigot-api baseline, core dep, publish, shadow, version).
apply(from = "https://raw.githubusercontent.com/Slimefun5/workflows/stable/slimefun-addon.gradle")

repositories {
    maven("https://jitpack.io")
    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    githubImplementation("Slimefun5:SlimefunMetrics:v1.0.0")
    githubImplementation("Slimefun5:InfinityLib:v1.3.13")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")

    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.15.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.16")
    testImplementation("org.mockbukkit.mockbukkit:mockbukkit-v1.21:4.107.0") {
        exclude(group = "org.jetbrains", module = "annotations")
    }
}

configurations.testImplementation {
    extendsFrom(configurations.compileOnly.get())
}

tasks {
    shadowJar {
        relocate("org.bstats", "slimetinker.libs.bstats")
        relocate("io.github.mooy1.infinitylib", "io.github.sefiraat.slimetinker.infinitylib")
        relocate("co.aikar.commands", "io.github.sefiraat.slimetinker.acf")
        relocate("co.aikar.locales", "io.github.sefiraat.slimetinker.locales")
        // Core is provided at runtime; never bundle it (InfinityLib pulls it transitively).
        exclude("io/github/thebusybiscuit/slimefun5/**")
    }
    compileTestJava { enabled = false }
    test { enabled = false }
}
