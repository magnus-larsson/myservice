#!/usr/bin/env bash

set -e

# Folder where the TracingAgent will write its config
CONFIG_FOLDER=src/main/resources/META-INF/native-image

# Run the tracing agent as part of the tests
rm -r $CONFIG_FOLDER || true # Ok if the folder does not exist
time ./gradlew clean test
ls -al $CONFIG_FOLDER

# Create platform specific native image
time ./gradlew nativeCompile --no-daemon

echo "### DONE ###"
echo "Try native image with: build/native/nativeCompile/myservice"
