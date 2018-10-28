Provides an easy to run interface for various applications based on
BoofCV's source code. To run the code you need to follow the instructions
below:

```commandline
git submodule update --init --recursive
rm -rf src/main/resources/*
cp -r boofcv/demonstrations/src/main/java/* src/main/resources
cp -r boofcv/examples/src/main/java/* src/main/resources
cp -r boofcv/data/example/* src/main/resources
```

To build the jar simply call ./gradlew demonstrations.jar