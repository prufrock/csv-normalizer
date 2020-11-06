# CSV Normalizer

Hello folks! You may be wondering how to build and run this wonderful CSV Normalizer you have before you. It's Kotlin on the JVM so depending on your familiarity with Java it might either be super easy or take some doing.

First things first, this project targets Java 11 which means you'll need the Java 11 JDK or newer. If you keep up with Java you may already be there or beyond. If not I highly recommend using [sdkman](https://sdkman.io/install) to make switching between JDK's a breeze.

I built this project with `java 11.0.2-open`. If you're using sdkman run these two commands in your terminal:
1. `sdk install java 11.0.2-open`
2. `sdk use java 11.0.2-open`


With the right version of Java installed you should be able to use the Gradle wrapper* to build from the terminal like so:
```
./gradlew shadowJar
```

If all goes well you should get the message `BUILD SUCCESSFUL` from gradle and if you peak in `/build/libs/` you'll find a `normalizer.jar`. That's it! That's what we've been doing all this for! With that in hand, since it's a shadow jar, you
should be able to take it wherever you want(or leave it there) and take it for a spin like so:
```
java -jar normalizer.jar < sample.csv > output.csv
```
If for some reason the build is not successful you should check your Java version with the command `java -version` which should output info about like this:
```
openjdk version "11.0.2" 2019-01-15
OpenJDK Runtime Environment 18.9 (build 11.0.2+9)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.2+9, mixed mode)
```
If the version is wrong retry the steps for installing Java with sdkman.

Other build problems are likely due to my lack of understanding about building Java on different machines. I'm sorry if you run into those. Feel free to contact me if you do. I may be able to assist.

Happy CSV normalizing!

*If for some reason the gradle wrapper doesn't work you can try installing gradle with sdkman with these commands:
1. `sdk install gradle 6.5`
2. `sdk use gradle 6.5`

Then you would run the build using the gradle command rather than the wrapper:
```
gradle shadowJar
```