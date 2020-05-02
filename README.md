# JsonAPIusingRetrofit

[Youtube Tutorial](https://www.youtube.com/watch?v=4JGvDUlfk7Y)

[Retrofit](https://square.github.io/retrofit/)

[JsonPlaceholder(Fake API)](http://jsonplaceholder.typicode.com/)

If you face the following error(s):

Error: 1 - Invoke-customs are only supported starting with android 0 --min-api 26

Goto app:build.gradle and add:

```java
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

Error: 2 - Response Failure like ClearText

Goto Manifest and add:

```java 
<application
	...
	android:usesCleartextTraffic="true">
	<activity>...</activity>
</application>
```

