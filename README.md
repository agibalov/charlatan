# charlatan [![Build Status](https://travis-ci.org/loki2302/charlatan.png?branch=master)](https://travis-ci.org/loki2302/charlatan)

# Maven

    <repositories>
      <repository>
        <id>charlatan-mvn-repo</id>
        <url>https://raw.github.com/loki2302/charlatan/mvn-repo/</url>
        <snapshots>
          <enabled>true</enabled>
          <updatePolicy>always</updatePolicy>
        </snapshots>
      </repository>
    </repositories>
    ...
    <dependencies>
      <dependency>
        <groupId>me.loki2302</groupId>
        <artifactId>charlatan</artifactId>
        <version>0.0.1-SNAPSHOT</version>
      </dependency>
    </dependencies>
