Created with the URL <https://start.spring.io/#!type=gradle-project&language=java&platformVersion=2.7.1&packaging=jar&jvmVersion=17&groupId=se.magnus&artifactId=myservice&name=myservice&description=Demo%20project%20for%20Spring%20Boot&packageName=se.magnus.myservice&dependencies=webflux,native,actuator>

Run Tracing Agent and build native image and Docker image:

```
./build-native-image.bash
```

Try native image:

```
./build/native/nativeCompile/myservice
```

Try Docker native image:

```
docker run --rm magnus/myservice:latest
```

Run Graal VM native-compiler in Docker:

See: 
1. <https://docs.oracle.com/en/learn/graalvm-and-containerisation/index.html#step-6-building-a-mostly-static-executable--packaging-it-in-a-distroless-image>
2. <https://www.graalvm.org/22.1/reference-manual/native-image/StaticImages/>

```
docker pull ghcr.io/graalvm/native-image:ol8-java17-22.1.0
docker run -it --rm ghcr.io/graalvm/graalvm-ce:ol8-java17-22.1.0 bash
docker run -it --rm ghcr.io/graalvm/jdk:ol8-java17-22.1.0 bash
./gradlew dependencies # SHOULD NOT BE RQUIRED: --refresh-dependencies
```

# Build Docker image on Apple silicon



```
# NOT WORKING IN Dockerfile!? USE: --progress=plain WHEN REQUIRED
ENV BUILDKIT_PROGRESS=plain

time docker build . -t myservice -f Dockerfile-from-source
time docker build . -t myservice -f Dockerfile-from-source --progress=plain 

# Results in:
# #10 12.42 Warning: Aborting stand-alone image build due to reflection use without configuration.
# #10 29.41 Warning: Image 'output' is a fallback image that requires a JDK for execution
time docker build --progress=plain . -t myservice -f Dockerfile-from-jar
```

# Run Docker image 

```
docker run myservice
```
