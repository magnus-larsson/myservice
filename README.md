Created with the URL <https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.7.1&packaging=jar&jvmVersion=17&groupId=se.magnus&artifactId=myservice&name=myservice&description=Demo%20project%20for%20Spring%20Boot&packageName=se.magnus.myservice&dependencies=webflux,native,actuator>

Run Tracing Agent and build native image and Docker image:

```
./build-native-image.bash
```

Try native image:

```
./build/native/nativeCompile/myservice
```
