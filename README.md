# NoiseView [![Build Status](https://travis-ci.org/hypeapps/NoiseView.svg?branch=master)](https://travis-ci.org/hypeapps/NoiseView) [![Download](https://api.bintray.com/packages/hypeapps/maven/NoiseView/images/download.svg)](https://bintray.com/hypeapps/maven/NoiseView/_latestVersion) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-NoiseView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6063) [![Platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
With NoiseView you can easily add a noise effect to your image.

![Banner](https://github.com/hypeapps/NoiseView/blob/master/img/noise_view_banner_gif.gif?raw=true)
<a href="https://play.google.com/store/apps/details?id=pl.hypeapp.sample" target="_blank">
<img src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" alt="Get it on Google Play" height="90"/></a>

[See demo on YouTube](https://www.youtube.com/watch?v=UMyPszKGa7o)
# Setup
The library is pushed to jCenter() as an AAR, 
so you just need to add the following to your ***build.gradle*** file:

```groovy

dependencies {
    compile 'pl.hypeapp:noiseview:1.0'
}

```
# Usage
#### In your xml layout add:
```xml
<pl.hypeapp.noiseview.NoiseView
        android:id="@+id/noise_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/wheel" <!-- Specify your drawable-->
        app:grainFps="90"
        app:noiseIntensity="0.1"
        app:noiseScale="0.6"
        app:paused="false"/>
```
#### or programatically:
```kotlin
val noiseView: NoiseView = findViewById(R.id.noise_view)
noiseView.grainFps = 120 // Default 90
noiseView.noiseIntensity = 0.3f // Default 0.1f
noiseView.noiseScale = 0.3f // Default 0.6f
noiseView.paused = true // Default false
noiseView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wheel))
```
NoiseView extends from ImageView, so it has all of its possibilities.

[Sample that demonstrates usage of library](https://github.com/hypeapps/NoiseView/tree/master/sample/src/main)

[Project where I used NoiseView in production app](https://github.com/hypeapps/episodie)

#### Acknowledgements
Thanks to [danielzeller/Depth](https://github.com/danielzeller/Depth-LIB-Android-) for his awesome demo :)
# License
<b>NoiseView</b> is licensed under `MIT license`. View [license](https://github.com/hypeapps/NoiseView/blob/master/LICENSE).

```
Authors:
Przemysław Szymkowiak <pszem.szym@gmail.com>
Daniel Zeller <daniel@agens.no>

The MIT License (MIT)
Copyright (c) 2016 Agens AS (http://agens.no/)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
