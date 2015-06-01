#!/bin/bash

readonly APP_NAME="target/com.example.arduino-1.0-SNAPSHOT-shaded.jar"

readonly JAVA_PATH="/usr/lib/jvm/java-7-oracle/bin/java"
readonly RXTX_PATH="/home/raf/opt/libs/rxtx-2.2pre2-bins/x86_64-unknown-linux-gnu"
readonly PORT="/dev/ttyACM0"

readonly JAVA_OPTS="-Xms64m -Xmx128m -Djava.library.path=${RXTX_PATH}"

set -x
exec ${JAVA_PATH} ${JAVA_OPTS} -jar ${APP_NAME} ${PORT}