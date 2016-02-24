PhotoTaker Library
=================

This library helps you in retrieving pictures from the camera of your device,
managing the orientation of the picture so that it returns correctly and the
new *Runtime permission request* of Android 6.0.

Importing
---------
Add it in your root `build.gradle` at the end of repositories:

```

allprojects {
  repositories {
	   ...
     maven { url "https://jitpack.io" }
	}
}
```

Add the dependency:

```

dependencies {
  compile 'com.github.Ennova-IT:PhotoTakerLibrary:V1.1'
}

```

__________________

Usage
-------------
Create a new instance of `PhotoLib`
```

photoLib = new PhotoLib(AppCompatActivity, String, OnPhotoRetrievedListener);

```

where the `String` is the name of the directory in which your data will be saved
in the hard drive.

Request a picture:
```
photoLib.takePictureFromCamera();
```

and manage the retrieved picture in the `void onPhotoRetrieved(Bitmap)` method of
your `OnPhotoRetrievedListener`. If the library needs to request a rationale for
the new Android Runtime permission request, the `void onPermissionRationaleRequested()`
method will be invoked.

You can always retrieve the path of the image by using the `PhotoLib.getPhotoPath()`
method.

__________________

License
----------

> The MIT License (MIT)

> Copyright (c) 2016 Ennova S.r.l.

> Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

> The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
