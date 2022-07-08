#!/usr/bin/env bash

set -x
set -e

echo "### Java/GraalVM version:"
java --version

# Folder where the TracingAgent will write its config
CONFIG_FOLDER=src/main/resources/META-INF/native-image

# Run the tracing agent as part of the tests
rm -r $CONFIG_FOLDER || true # Ok if the folder does not exist
time ./gradlew clean test # 30 sec
ls -al $CONFIG_FOLDER

# Create platform specific native image
time ./gradlew nativeCompile --no-daemon # 3 min

# Create Docker native image
time ./gradlew bootBuildImage --no-daemon # 5 min

echo "### DONE ###"
echo "Try native image with: ./build/native/nativeCompile/myservice"
echo "Try Docker image with: docker run --rm magnus/myservice:latest"