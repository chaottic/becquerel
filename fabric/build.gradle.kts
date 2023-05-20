import net.fabricmc.loom.api.mappings.layered.spec.LayeredMappingSpecBuilder
import org.gradle.kotlin.dsl.repositories

plugins {
    id("fabric-loom") version "1.2-SNAPSHOT"
}

group = "com.chaottic"
version = "1.0-SNAPSHOT"

loom {
    runs {
        getByName("client") {
            client()
            ideConfigGenerated(true)
        }
    }

    accessWidenerPath.set(file("src/main/resources/becquerel.aw"))
}

repositories {
    maven("https://api.modrinth.com/maven")
    maven("https://ladysnake.jfrog.io/artifactory/mods")
}

dependencies {
    minecraft("com.mojang:minecraft:1.19.4")
    mappings(loom.layered(LayeredMappingSpecBuilder::officialMojangMappings))

    modImplementation("net.fabricmc:fabric-loader:0.14.19")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.79.0+1.19.4")

    modImplementation("teamreborn:energy:3.0.0") {
        exclude(group = "net.fabricmc.fabric-api")
    }

    modImplementation("dev.onyxstudios.cardinal-components-api:cardinal-components-base:5.1.0")
    modImplementation("dev.onyxstudios.cardinal-components-api:cardinal-components-chunk:5.1.0")
    modImplementation("dev.onyxstudios.cardinal-components-api:cardinal-components-entity:5.1.0")

    // Support for.
    modImplementation("maven.modrinth:gigeresque:0.5.28HF")
    modImplementation("maven.modrinth:azurelib:9lnNzlnu")
    modImplementation("maven.modrinth:smartbrainlib:rAWSa9XC")
}