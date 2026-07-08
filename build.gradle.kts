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
<<<<<<< HEAD
<<<<<<< HEAD
    implementation("com.github.Slimefun5:SlimefunMetrics:master-SNAPSHOT")
<<<<<<< HEAD
    "githubCompileOnly"("Slimefun5:Slimefun5:v5.1.1")
    compileOnly("io.papermc.paper:paper-api:${property("paperApiVersion")}")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")

    implementation("com.github.Slimefun5:InfinityLib:v1.3.10") {
        isTransitive = false
    }
=======
=======
    implementation("com.github.Slimefun5:SlimefunMetrics:c3a795e")
>>>>>>> origin/experimental
=======
    githubImplementation("Slimefun5:SlimefunMetrics:v1.0.0")
<<<<<<< HEAD
>>>>>>> origin/experimental
    githubCompileOnly("Slimefun5:Slimefun5:gh-v5.2.3.2")
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")

    githubImplementation("Slimefun5:InfinityLib:v1.3.13")
>>>>>>> origin/experimental
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
=======
    githubImplementation("Slimefun5:InfinityLib:v1.3.13")
    implementation("co.aikar:acf-paper:0.5.1-SNAPSHOT")
>>>>>>> origin/experimental
}

tasks {
    shadowJar {
        relocate("org.bstats", "slimetinker.libs.bstats")
        relocate("io.github.mooy1.infinitylib", "io.github.sefiraat.slimetinker.infinitylib")
        relocate("co.aikar.commands", "io.github.sefiraat.slimetinker.acf")
        relocate("co.aikar.locales", "io.github.sefiraat.slimetinker.locales")
        exclude("io/github/thebusybiscuit/slimefun5/**")
    }
}
