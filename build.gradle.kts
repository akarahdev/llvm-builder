plugins {
    id("java")
    id("maven-publish")
}

group = "dev.akarah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name = "llvm-builder"
                description = "API for creating LLVM IR"
                url = "https://akarah.dev"

                scm {
                    url = "https://github/akarahdev/llvm-builder"
                }

                issueManagement {
                    system = "github"
                    url = "https://github.com/akarahdev/llvm-builder/issues"
                }
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
            }
        }
    }
}