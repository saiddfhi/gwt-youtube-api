## GWT integration ##

Using embeded player in your GWT application is perfectly easy. Firstly, add GWT inherit into your XYZ.gwt.xml file:
```
<inherits name="com.google.youtube.Player"/>
```

This 1 line of code will add a posibility to use GWT embeded player in your GWT application for playing youtube videos and you can use it as normal GWT widget. You can set-up all parameters defined in [YouTube Embedded Player Parameters documentation](http://code.google.com/intl/sk/apis/youtube/player_parameters.html).

Most easiest way how to use YouTube player:
```
YouTubeEmbeddedPlayer youTubeEmbeddedPlayer = new YouTubeEmbeddedPlayer("hqXUKxJiDls");
youTubeEmbeddedPlayer.setWidth("427px");
youTubeEmbeddedPlayer.setHeight("320px");
RootPanel.get().add(youTubeEmbeddedPlayer);
```

This, in a result, will add a flash player into your page, like this:

<a href='http://www.youtube.com/watch?feature=player_embedded&v=hqXUKxJiDls' target='_blank'><img src='http://img.youtube.com/vi/hqXUKxJiDls/0.jpg' width='425' height=344 /></a>

By default embed object is added to the DOM model on onLoad methos. You have also another posibility how to embed player into page manually (also before onLoad method, so you can call inner HTML method event before player is shown on the page).

```
YouTubeEmbeddedPlayer youTubeEmbeddedPlayer = new YouTubeEmbeddedPlayer("hqXUKxJiDls");
youTubeEmbeddedPlayer.setWidth("427px");
youTubeEmbeddedPlayer.setHeight("320px");
youTubeEmbeddedPlayer.embed(); 
RootPanel.get().add(youTubeEmbeddedPlayer);
```

## Player parameters ##

Using embed player, you can define all the parameters:

  * **rel**
    * Values: 0 or 1. Default is 1. Sets whether the player should load related videos once playback of the initial video starts. Related videos are displayed in the "genie menu" when the menu button is pressed. The player search functionality will be disabled if rel is set to 0.
    * `void setLoadRelatedVideos(Boolean rel)`
    * `boolean isLoadRelatedVideos()`
  * **autoplay**
    * Values: 0 or 1. Default is 0. Sets whether or not the initial video will autoplay when the player loads.
    * `void setAutoplay(Boolean autoplay)`
    * `boolean isAutoplay()`
  * **loop**
    * Values: 0 or 1. Default is 0. In the case of a single video player, a setting of 1 will cause the player to play the initial video again and again. In the case of a playlist player (or custom player), the player will play the entire playlist and then start again at the first video.
    * `void setLoop(Boolean loop)`
    * `boolean isLoop()`
  * **enablejsapi**
    * Values: 0 or 1. Default is 0. Setting this to 1 will enable the Javascript API
    * `void setEnableJSAPI(Boolean enablejsapi)`
    * `boolean isEnableJSAPI()`
  * **playerapiid**
    * Value can be any alphanumeric string. This setting is used in conjunction with the JavaScript API
    * `void setPlayerAPIId(String playerapiid`
    * `String getPlayString()`
  * **disablekb**
    * Values: 0 or 1. Default is 0. Setting to 1 will disable the player keyboard controls. Keyboard controls are as follows:
> > > Spacebar: Play / Pause
> > > Arrow Left: Jump back 10% in the current video
> > > Arrow Right: Jump ahead 10% in the current video
> > > Arrow Up: Volume up
> > > Arrow Down: Volume Down
    * `void setDisableKeyboardControls(Boolean disablekb)`
    * `boolean isDisableKeyboardControls()`
  * **egm**
    * Values: 0 or 1. Default is 0. Setting to 1 enables the "Enhanced Genie Menu". This behavior causes the genie menu (if present) to appear when the user's mouse enters the video display area, as opposed to only appearing when the menu button is pressed.
    * `void setEnhancedGenieMenu(Boolean egm)`
    * `boolean isEnhancedGenieMenu()`
  * **border**
    * Values: 0 or 1. Default is 0. Setting to 1 enables a border around the entire video player. The border's primary color can be set via the color1  parameter, and a secondary color can be set by the color2 parameter.
    * `void setBorder(Boolean border)`
    * `boolean isBorder()`
  * **color1**, **color2**
    * Color1 is the primary border color, and color2 is the video control bar background color and secondary border color.
    * `void setBorderColor1(Color color1), void setBorderColor2(Color color2)`
    * `void setBorderHSVColor1(int h, int s, int v), void setBorderHSVColor2(int h, int s, int v)` in [HSV color format](http://en.wikipedia.org/wiki/Web_colors)
    * ` void setBorderColor1(int r, int g, int b),  void setBorderColor2(int r, int g, int b)` in [RGB color format](http://en.wikipedia.org/wiki/Web_colors)
    * `void setBorderColor1(String hexColor), void setBorderColor1(String hexColor)` [in hexadecimal format](http://en.wikipedia.org/wiki/Web_colors)
    * `Color getBorderColor1(), Color getBorderColor2()`
  * **start**
    * Values: A positive integer. This parameter causes the player to begin playing the video at the given number of seconds from the start of the video. Note that similar to the seekTo  function, the player will look for the closest keyframe to the time you specify. This means sometimes the play head may seek to just before the requested time, usually no more than ~2 seconds.
    * `void setStartSeekTime(Integer start)`
    * `Integer getStartSeekTime()`
  * **fs**
    * Values: 0 or 1. Default is 0. Setting to 1 enables the fullscreen button.
    * `void setFullScreen(Boolean fs)`
    * `boolean isFullScreen()`
  * **hd**
    * Values: 0 or 1. Default is 0. Setting to 1 enables HD playback by default. This also has no effect if an HD version of the video is not available. If you enable this option, keep in mind that users with a slower connection may have an sub-optimal experience unless they turn off HD. You should ensure your player is large enough to display the video in its native resolution.
    * `void setHd(Boolean hd)`
    * `boolean isHd()`
  * **showsearch**
    * Values: 0 or 1. Default is 1. Setting to 0 disables the search box from displaying when the video is minimized. Note that if the rel parameter is set to 0 then the search box will also be disabled, regardless of the value of showsearch.
    * `void setShowsearch(Boolean showsearch)`
    * `boolean getShowsearch()`
  * **showinfo**
    * Values: 0 or 1. Default is 1. Setting to 0 causes the player to not display information like the video title and rating before the video starts playing.
    * `void setShowinfo(Boolean showinfo)`
    * `boolean isShowinfo()`
  * **iv\_load\_policy**
    * Values: 1 or 3. Default is 1. Setting to 1 will cause video annotations to be shown by default, whereas setting to 3 will cause video annotation to not be shown by default.
    * `void setVideoAnnotation(VideoAnnotation videoAnnotation)`
    * `VideoAnnotation getVideoAnnotation()`
    * 
```
public enum VideoAnnotation {
    ANNOATIONS_ON(1), ANNOTATIONS_OFF(3);
}
```
  * **cc\_load\_policy**
    * Values: 1. Default is based on user preference. Setting to 1  will cause closed captions to be shown by default, even if the user has turned captions off.
    * `void setShowCaptions(boolean showCaptions)`
    * `boolean getShowCaptions()`