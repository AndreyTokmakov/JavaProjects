# false-sharing

The repository to demonstrate the impact of false sharing on performance. 

[Blog post about false-sharing](https://wkoszolko.github.io/2021/03/10/false-sharing/)

## Maven and Java-11 setup

```shell script
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export M2_HOME=/opt/maven
export MAVEN_HOME=/opt/maven
export PATH=${JAVA_HOME}/bin:${M2_HOME}/bin:${PATH}
```

## Benchmark results

Two classes were compared. One class was affected by false sharing, 
the other one uses `@Contended ` annotation in order to solve the issue.

Class affected by false sharing:
```shell script
Benchmark                                             Mode  Cnt   Score   Error  Units
false_sharing.FalseSharingBenchmark.falseSharing                    avgt   20  48,897 ± 0,763  ns/op
false_sharing.FalseSharingBenchmark.falseSharing:incrementCounterA  avgt   20  48,905 ± 0,898  ns/op
false_sharing.FalseSharingBenchmark.falseSharing:incrementCounterB  avgt   20  48,889 ± 0,859  ns/op
```
After `@Contended` was added to the class:
```shell script
Benchmark                                             Mode  Cnt   Score   Error  Units
false_sharing.FalseSharingBenchmark.contended                       avgt   20   6,765 ± 0,063  ns/op
false_sharing.FalseSharingBenchmark.contended:contendedIncrementA   avgt   20   6,765 ± 0,063  ns/op
false_sharing.FalseSharingBenchmark.contended:contendedIncrementB   avgt   20   6,765 ± 0,063  ns/op
```

## Build
```shell script
mvn clean package
docker build -t false-sharing .
```

## Run
You can run benchmarks by yourself. Docker container shows benchmark results and `perf` output.
`Perf` is a Linux profiler and it's able to show the number of CPU cache load events. 
 
Benchmark class affected by false-sharing:
```shell script
 docker run --cap-add SYS_ADMIN \
  false-sharing \
  java -jar -XX:-RestrictContended /opt/app/app.jar \
  false_sharing.FalseSharingBenchmark
```

Benchmark class with `@Contended` annotation:
```shell script
 docker run --cap-add SYS_ADMIN \
  false-sharing \
  java -jar -XX:-RestrictContended /opt/app/app.jar \
  false_sharing.ContendedBenchmark
```

Run all benchmarks:
```shell script
 docker run --cap-add SYS_ADMIN \
  false-sharing \
  java -jar -XX:-RestrictContended /opt/app/app.jar
```
