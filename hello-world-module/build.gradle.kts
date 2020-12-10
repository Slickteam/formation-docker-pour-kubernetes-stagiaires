plugins {
    id("java")
    application
}

group = "fr.slickteam.formation.kubernetes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


java {
    modularity.inferModulePath.set(true)

}

application {
    mainModule.set("hello.world.module.main")
    mainClass.set("fr.slickteam.formation.kubernetes.Main")
}

tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "fr.slickteam.formation.kubernetes.Main")
    }
}


dependencies {
    testImplementation("junit:junit:4.12")
}
