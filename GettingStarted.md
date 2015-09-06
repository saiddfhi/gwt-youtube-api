# Using `YouTube` API in GWT application #

You can use `YouTube` API in our GWT application in a few steps:
  1. Download newest version of the library in 2 forms - first JAR (gwt-youtube-api-{version}.jar) contains compiled .class files and resources, second JAR (gwt-youtube-api-{version}-sources.jar)
  1. 
    * If you are using maven, add this 2 dependencies to your project
```
<dependency>
	<groupId>com.google</groupId>
	<artifactId>gwt-youtube-api</artifactId>
	<version>${gwt-youtube-api.version}</version>
</dependency>
<dependency>
	<groupId>com.google</groupId>
	<artifactId>gwt-youtube-api</artifactId>
	<version>${gwt-youtube-api.version}</version>
        <classifier>sources</classifier>
</dependency>
```
    * If you are not using maven, add this dependencies to your project
      * http://code.google.com/p/gwt-youtube-api/source/browse/releases-repository/com/google/code/gwtx/gwtx/1.5.3/gwtx-1.5.3.jar
      * http://code.google.com/p/gwt-youtube-api/source/browse/snapshots-repository/org/gwt-time/1.0.0-SNASHOT/gwt-time-1.0.0-SNAPSHOT.jar
      * http://gwt-youtube-api.googlecode.com/svn/releases-repository/com/google/gwt/gwt-dnd/3.0.0/gwt-dnd-3.0.0.jar
      * https://oss.sonatype.org/content/groups/staging/sk/seges/acris/acris-json/1.1.0/acris-json-1.1.0-sources.jar
      * https://oss.sonatype.org/content/groups/staging/sk/seges/acris/acris-json/1.1.0/acris-json-1.1.0.jar
      * https://oss.sonatype.org/content/groups/staging/sk/seges/acris/acris-client-core/1.1.0/acris-client-core-1.1.0-sources.jar
      * https://oss.sonatype.org/content/groups/staging/sk/seges/acris/acris-client-core/1.1.0/acris-client-core-1.1.0.jar
      * https://oss.sonatype.org/content/groups/staging/sk/seges/acris/acris-widgets/1.1.0/acris-widgets-1.1.0.jar
      * https://oss.sonatype.org/content/groups/staging/sk/seges/acris/acris-widgets/1.1.0/acris-widgets-1.1.0-sources.jar
      * (Why you still don't use maven ? :) )
  1. Add release and snapshot repositories to the
    * http://gwt-youtube-api.googlecode.com/svn/releases-repository
    * http://gwt-youtube-api.googlecode.com/svn/snapshots-repository
    * https://oss.sonatype.org/content/groups/staging/
> > into your [settings.xml](http://gwt-youtube-api.googlecode.com/svn/wiki/files/settings.xml)

  1. Add Google dependency using inherit using following line into your XYZ.gwt.xml



&lt;inherits name="com.google.gdata.YouTubeAPI" /&gt;



# Project purpose #

Project is supposed to be as complementary product to [GWT Google API project](http://code.google.com/p/gwt-google-apis/) which provides following APIs into your GWT application:
  * Search
  * Gadgets
  * Gears
  * Maps
  * Chart Tools
  * Language
  * AjaxLoader

You see that? `YouTube` service is missing, so thats the reason why to create this project. But maybe another question, why to make another project instead of extending existing one? Because gwt-google-apis are only wrappers over the javascript client gdata library, but this also does not contains `YouTube` services. Why? I don't know, ask Google directly :). So these were reasons for creating GWT `YouTube` APIs project and it hase one more advantage in comparision with JS wrappers for GWT - you know the what is the correct answer?

**Solution is completly written in Java/GWT and each method and variable can be debugged**

# Project inspiration #

Project is inspired by [Google API Java Client](http://code.google.com/intl/sk/apis/youtube/2.0/developers_guide_java.html) which is written in Java so it is only small step from GWT code. Only difference is that Google API Java Client works with ATOM format but GWT solution works with JSON data format. Data structures are still the same as in original Java client for Google API only deserialization mechanism was changed.

Whole deserialization is realized using [acris-json](http://code.google.com/p/acris/wiki/GWTJsonizer) project which completly process JSON input text and transforms it into Java data structures just using annotations on java classes and fields. Don't believe me? Look at original Java code taken from Java client for Google API
```
protected static class SourceState {

    /** Feed ID. */
    public  String id;

    /** Last updated timestamp. */
    public DateTime updated;

    /** Categories. */
    public HashSet<Category> categories = new HashSet<Category>();

    /** Title. */
    public TextConstruct title;

    /** Subtitle. */
    public TextConstruct subtitle;

    /** Rights. */
    public TextConstruct rights;

    /** Icon URI. */
    public String icon;
    ...
}
```

And compare it with new one, which you can find in GWT `YouTube` API project

```
@JsonObject
public static class SourceState {

    /** Feed ID. */
    @Field
    public String id;
    
    /** Last updated timestamp. */
    @Field
    @DateTimePattern(Source.DATE_TIME_PATTERN)
    public DateTime updated;

    /** Categories. */
    @Field("category")
    public HashSet<Category> categories = new HashSet<Category>();

    /** Title. */
    @Field
    public TextConstruct title;

    /** Subtitle. */
    @Field
    public TextConstruct subtitle;

    /** Rights. */
    @Field
    public TextConstruct rights;

    /** Icon URI. */
    @Field
    public String icon;
    ...
```

You see that? Only difference is using of additional annotation and this makes the migration very easy - just take a data structure from java client project, add required annotations and you are done. With this easy approach, you can migrate also another Google APIs into your project but remember - a lot of basic and shared classes were already migrated in this project, so firstly take a look into the [source code](http://code.google.com/p/gwt-youtube-api/source/browse/#svn/trunk).

# `YouTube` embeded player #

`YouTube` embeded player is also a part of the whole solution and you can use it with added



&lt;inherits name="com.google.youtube.Player" /&gt;



into your XYZ.gwt.xml file.

This 1 line of code will add a posibility to use GWT embeded player in your GWT application for playing youtube videos and you can use it as normal GWT widget.
You can set-up all parameters defined in [`YouTube` Embedded Player Parameters](http://code.google.com/intl/sk/apis/youtube/player_parameters.html) documentation.

Most easiest way how to use `YouTube` player:
```
YouTubeEmbeddedPlayer youTubeEmbeddedPlayer = new YouTubeEmbeddedPlayer(videoId);
youTubeEmbeddedPlayer.setWidth(width + "px");
youTubeEmbeddedPlayer.setHeight(height + "px");
RootPanel.get().add(youTubeEmbeddedPlayer);
```