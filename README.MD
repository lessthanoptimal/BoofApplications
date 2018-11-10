Provides an easy to run interface for various applications based on
BoofCV's source code. To run the code you need to follow the instructions
below:

```commandline
git submodule update --init --recursive
rm -rf demos/src/main/resources/*
cp -r boofcv/demonstrations/src/main/java/* demos/src/main/resources
cp -r boofcv/examples/src/main/java/* demos/src/main/resources
cp -r boofcv/data/example/* demos/src/main/resources
```

To build the jars call:
```commandline
./gradlew demonstrationsJar
./gradlew applicationsJar
```

Release Zips
```commandline
./gradlew releaseDemoZip
./gradlew releaseAppsZip
```


scp BoofDemonstrations.zip LOGIN@frs.sourceforge.net:/home/frs/project/boofcv/apps