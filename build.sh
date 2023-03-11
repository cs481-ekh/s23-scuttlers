#!/bin/bash

# Scan for implemented classes
./scan.sh

# Run the gradle task to build the fat JAR file
cd antscuttle

./gradlew desktop:dist

# Get the name of the JAR file that was created
JAR_FILE=$(find . -name "*.jar" | grep "antscuttle" | tail -1)

# Copy the JAR file to the directory above
cp "$JAR_FILE" "../${JAR_FILE##*/}"

echo "Fat JAR file copied to $(cd .. && pwd)/${JAR_FILE##*/}"
