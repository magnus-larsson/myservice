#!/usr/bin/env bash

# set -x
set -e

HW=$(uname -m)
HW_ARM_64="arm64"
HW_X86_64="x86_64"
echo "HW architecture $HW"

echo "### Java/GraalVM version:"
java --version

# Folder where the TracingAgent will write its config
CONFIG_FOLDER=src/main/resources/META-INF/native-image

# Run the tracing agent as part of the tests
# DOES NOT SEEM TO BE REQUIRED FOR THIS EXAMPLE?
#rm -r $CONFIG_FOLDER || true # Ok if the folder does not exist
#time ./gradlew clean test # 30 sec
ls -al $CONFIG_FOLDER || true

# Create platform specific native image
time ./gradlew nativeCompile # --no-daemon # 3 min, 1 min

if [[ "$HW" == "$HW_X86_64" ]]; then
  # Create Docker native image
  time ./gradlew bootBuildImage --no-daemon # 5 min
else
  echo "SKip creating Docker image since we are on $HW"
fi

echo "### DONE ###"
echo "Try native image with: ./build/native/nativeCompile/myservice"
if [[ "$HW" == "$HW_X86_64" ]]; then
  echo "Try Docker image with: docker run --rm magnus/myservice:latest"
fi