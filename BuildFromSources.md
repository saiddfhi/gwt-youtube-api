# Prerequisites #
  1. JDK 1.6+
  1. Maven [2.0.9,>

# Steps #
  1. Download sources from http://gwt-youtube-api.googlecode.com/svn/trunk/ or specific tag
  1. Add following URL as your release repository in [settings.xml](https://gwt-youtube-api.googlecode.com/svn/wiki/files/settings.xml) file http://gwt-youtube-api.googlecode.com/svn/releases-repository
```
<?xml version="1.0" encoding="UTF-8"?>
<settings>
	<profiles>
		<profile>
			<id>default</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>
			<!-- Libraries repositories -->
			<repositories>
				<!--
					Our all necessary libraries with SNAPSHOT versions, like
					acris-json, date-time library, etc. These are not located in any
					public repository
				-->
				<repository>
					<id>YouTubeReleases</id>
					<url>http://gwt-youtube-api.googlecode.com/svn/releases-repository
					</url>
					<snapshots>
						<enabled>false</enabled>
					</snapshots>
					<releases>
						<enabled>true</enabled>
					</releases>
				</repository>
				<!--
					Our all necessary libraries with SNAPSHOT versions, like
					acris-json, date-time library, etc. These are not located in any
					public repository
				-->
				<repository>
					<id>YouTubeSnapshots</id>
					<url>http://gwt-youtube-api.googlecode.com/svn/snapshots-repository
					</url>
					<releases>
						<enabled>false</enabled>
					</releases>
					<snapshots>
						<enabled>true</enabled>
					</snapshots>
				</repository>
				<!-- GWT resources -->
				<!--
					You can use standard maven repository
					(http://repo1.maven.org/maven2/) but there is no maven-metadata.xml
					for GWT libraries, so you cannot use version range (Current state
					for date: 11th of June, 2010)
				-->
				<repository>
					<id>JBossRepository</id>
					<url>http://repository.jboss.org/maven2</url>
					<snapshots>
						<enabled>false</enabled>
					</snapshots>
				</repository>
			</repositories>
			<!-- Plugin repositories -->
			<pluginRepositories>
				<pluginRepository>
					<!-- JSR269 processor plugin -->
					<id>maven-annotation-plugin</id>
					<url>http://maven-annotation-plugin.googlecode.com/svn/trunk/mavenrepo
					</url>
					<releases>
						<enabled>true</enabled>
					</releases>
					<snapshots>
						<enabled>true</enabled>
					</snapshots>
				</pluginRepository>
			</pluginRepositories>
		</profile>
	</profiles>
</settings>
```
  1. run `mvn clean install`
  1. run `mvn eclipse:clean eclipse:eclipse` in order to create eclipse projects
  1. import existing project into eclipse