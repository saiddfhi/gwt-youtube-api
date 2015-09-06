# Google API complexity #

Did you ever try to use Google API's or did you ever seen how complex gdata structures are? Following class dialog displays only small subset from the whole data structures.

![http://gwt-youtube-api.googlecode.com/svn/wiki/images/gdata_small.png](http://gwt-youtube-api.googlecode.com/svn/wiki/images/gdata_small.png)

You can find complete reference guide on [API Directory](http://code.google.com/intl/sk/apis/gdata/docs/directory.html). Now the only problem is, how to get all this classes into GWT.

## API JSON response ##

<table>
<tr>
<td><img src='http://gwt-youtube-api.googlecode.com/svn/wiki/images/jsonview.png' /></td>
<td>For better imagination whats behind GData API, we can take one concrete example from our showcase. After pressing search button, request to the Google YouTube API is created with specific <code>ALT</code> paramter equals <code>json-in-script</code> value. This means that YouTube API will respond in <b>JSON format</b> and call specific javascript handler. The most important is that the result is in <b>JSON format</b> like it is shown on image on left side.<br>
<br>
Let's imagine that you want to extract thumbnail for each video entry in the video feed. First you have look at <code>feed</code> JSON value - it is mapped into YouTubeFeed in the Java code and it holds summary information about the feed, like totalResults, itemsPerPage, etc. etc. AND video entries.<br>
<br>
Entry JSON value represents VideoEntry object in the java code and holds all the information about video, like <i>title</i>, <i>author</i>, <i>comments</i>, etc.   Then we have some special JSON fields with <b>$</b> separator, like <b>media$group</b> field. This kind of entries are extensible points and represents very nice plugable architecture of the data structure and represents an easy way of attaching specific piece of data into any extensible point. This meas that, MediaGroup becomes part of the data structure in a JSON responce but in a Java code this classes are completly indenpendent and can be reused and plugged in generic way. So, we have now object MediaGroup which contains advanced information of the video entry. Here, finally, we can see a list of the thubnails.<br>
<br>
Each video contains set of thumbnails with dimensions and they are captured in various video time and for the demonstration purposes we are choosing thumbnail with best resolution.<br>
<br>
Can you see the complexity of the data structures? Of course, this was only my lightweight explanation of the GData API and for complete understanding of the structure, please read the official <a href='http://code.google.com/apis/gdata'>API documentation</a> or at least <a href='http://code.google.com/intl/sk/apis/youtube/2.0/reference.html'>YouTube API</a>.<br>
</td>
</tr>
</table>

The question is: How we can handle so many classes and so complex data structure in easy way with minimum of time and effort? This can be done using great work of engineers in Google and their library [Java client for Google API](http://code.google.com/intl/sk/apis/youtube/2.0/developers_guide_java.html). This library has all the java classes already written and well documented so why not to reuse them? Only problem is that java client uses ATOM parsing mechanism and for JavaScript/GWT code is much better to parse JSON response instead of parsing ATOM. Replacing the parsing mechanism was easy thanks to [AcrIS JSON](http://code.google.com/p/acris/wiki/GWTJsonizer) project which allows you to handle JSON deserialization in easy way - only using annotations.
We can easily demonstrate this situation YtDuration class which represents duration video field.

## JSON data support ##

<table><tr>
<td>
<b>Original class in YouTube client</b>
<pre><code>import com.google.gdata.data.AbstractExtension;<br>
import com.google.gdata.data.AttributeGenerator;<br>
import com.google.gdata.data.AttributeHelper;<br>
import com.google.gdata.data.ExtensionDescription;<br>
import com.google.gdata.util.ParseException;<br>
<br>
/**<br>
 * Duration class.<br>
 *<br>
 * This completes the duration field in<br>
 * {@link com.google.gdata.data.media.mediarss.MediaContent} and is<br>
 * useful when there is no MediaContent.<br>
 *<br>
 * This tag should go inside MediaGroup, next to {@code media:player}<br>
 *<br>
 * <br>
 */<br>
@ExtensionDescription.Default(<br>
    nsAlias = YouTubeNamespace.PREFIX,<br>
    nsUri = YouTubeNamespace.URI,<br>
    localName = "duration"<br>
)<br>
public class YtDuration extends AbstractExtension {<br>
  private long seconds;<br>
<br>
  /** Creates an empty duration tag. */<br>
  public YtDuration() {<br>
<br>
  }<br>
<br>
  /** Creates and initializes a duration tag. */<br>
  public YtDuration(long seconds) {<br>
    this.seconds = seconds;<br>
  }<br>
<br>
  /** Gets duration in seconds. */<br>
  public long getSeconds() {<br>
    return seconds;<br>
  }<br>
<br>
  /** Sets duration in seconds. */<br>
  public void setSeconds(long seconds) {<br>
    this.seconds = seconds;<br>
  }<br>
<br>
  @Override<br>
  protected void putAttributes(AttributeGenerator generator) {<br>
    generator.put("seconds", seconds);<br>
  }<br>
<br>
  @Override<br>
  protected void consumeAttributes(AttributeHelper helper)<br>
      throws ParseException {<br>
    seconds = helper.consumeLong("seconds", true);<br>
  }<br>
}<br>
</code></pre>
</td>
<td>
<b>Modified class in GWT YouTube client</b>
<pre><code>import sk.seges.acris.json.client.annotation.Field;<br>
import sk.seges.acris.json.client.annotation.JsonObject;<br>
import sk.seges.acris.json.client.extension.ExtensionPoint;<br>
<br>
/**<br>
 * Duration class.<br>
 *<br>
 * This completes the duration field in<br>
 * {@link com.google.gdata.data.media.mediarss.MediaContent} and is<br>
 * useful when there is no MediaContent.<br>
 *<br>
 * This tag should go inside MediaGroup, next to {@code media:player}<br>
 *<br>
 * <br>
 */<br>
@JsonObject(group = YouTubeNamespace.PREFIX, value = "duration")<br>
public class YtDuration extends ExtensionPoint {<br>
	<br>
	@Field<br>
	private long seconds;<br>
<br>
	/** Creates an empty duration tag. */<br>
	public YtDuration() {<br>
<br>
	}<br>
<br>
	/** Creates and initializes a duration tag. */<br>
	public YtDuration(long seconds) {<br>
		this.seconds = seconds;<br>
	}<br>
<br>
	/** Gets duration in seconds. */<br>
	public long getSeconds() {<br>
		return seconds;<br>
	}<br>
<br>
	/** Sets duration in seconds. */<br>
	public void setSeconds(long seconds) {<br>
		this.seconds = seconds;<br>
	}<br>
}<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
</code></pre>
</td></tr></table>

Can you see the difference? New class is very simple and contains annotations representing semantic value of the JSON fields. This annotations are used in JSON deserialization process. Field `seconds` will by automatically filed from JSON field with name `seconds`. If you want to change mapping, just change the value attribute of the Field annotation, e.g. `@Field("othername");`.

## Using Google YouTube API ##

Add GWT inherit to your project



&lt;inherits name="com.google.gdata.YouTubeAPI" /&gt;



```
YouTubeManager youTubeManager = new YouTubeManager();
youTubeManager.retrieveMostRecent(new AsyncCallback<List<VideoEntry>>() {
	@Override
	public void onSuccess(List<VideoEntry> result) {
                //Here it is
                String videoId = entry.getMediaGroup().getVideoId();
                showVideo(videoId);
	}

	@Override
	public void onFailure(Throwable caught) {
		GWT.log("Unable to obtain videos from youtube service", caught);
	}
});
```

You will get the result as part of asynchronous callback in method retrieveMostRecent which will provide you the most recent videos with respect of query page you provided into request parameters.
`YouTubeManager` has currently following posibilities:
  * `public void retrieveVideo(String textQuery, AsyncCallback<VideoFeed> callback);`
    * Searches for videos on YouTube reflecting the input query you specified as parameter of the method. This method has another alternative also with QueryPage parameter which provides paging functionality. You can define `startIndex` for the search results and max results which can be returned in the YouTube response.
  * `public void retrieveTopRated(AsyncCallback<VideoFeed> callback);`
    * Provides top rated videos
  * `public void retrieveMostRecent(AsyncCallback<VideoFeed> callback);`
    * Provides most recent videos