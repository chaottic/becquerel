pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()

		maven("https://maven.fabricmc.net/")
		maven("https://repo.spongepowered.org/repository/maven-public/")
	}
}
rootProject.name = "becquerel"
include("fabric")
