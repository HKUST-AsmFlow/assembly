import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.3.10"
    id("org.jetbrains.intellij.platform") version "2.18.1"
    id("org.jetbrains.intellij.platform.grammarkit") version "2.18.1"
}

group = "io.github.asmflow"
version = "2026.1"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdea("2025.3.2")
        testFramework(TestFrameworkType.Platform)
    }

    testImplementation("junit:junit:4.13.2")
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild.set("253")
        }

        changeNotes.set("Initial version")
    }
}

tasks {
    generateLexer {
        sourceFile.set(layout.projectDirectory.file("src/main/grammar/armv7/ARMv7.flex"))
        targetRootOutputDir.set(
            layout.projectDirectory.dir("src/main/gen/")
        )
        purgeOldFiles.set(true)
    }

    generateParser {
        sourceFile.set(layout.projectDirectory.file("src/main/grammar/armv7/ARMv7.bnf"))
        targetRootOutputDir.set(
            layout.projectDirectory.dir("src/main/gen/")
        )
        pathToParser.set("io/github/asmflow/assembly/armv7/parser/ARMv7ParserImpl.java")
        pathToPsiRoot.set("io/github/asmflow/assembly/armv7/psi")
        purgeOldFiles.set(true)
    }

    withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<JavaCompile> {
        dependsOn(generateLexer, generateParser)

        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    withType<KotlinCompile> {
        dependsOn(generateLexer, generateParser)
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }

    sourceSets {
        main {
            kotlin.srcDir("src/main/kotlin")
        }

        test {
            kotlin.srcDir("src/test/kotlin")
            resources.srcDir("src/test/resources")
        }
    }
}

sourceSets {
    main {
        java.srcDir("src/main/gen")
        resources.srcDirs("src/main/resources")
    }
}
