import net.fabricmc.loom.api.mappings.layered.spec.LayeredMappingSpecBuilder

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
}


dependencies {
    minecraft("com.mojang:minecraft:1.19.4")
    mappings(loom.layered(LayeredMappingSpecBuilder::officialMojangMappings))

    modImplementation("net.fabricmc:fabric-loader:0.14.19")
    modImplementation("net.fabricmc.fabric-api:fabric-api:0.79.0+1.19.4")

    modImplementation("teamreborn:energy:3.0.0") {
        exclude(group = "net.fabricmc.fabric-api")
    }
}