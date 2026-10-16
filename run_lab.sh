#!/bin/bash
# usage: run_lab.sh labdir Main1 Main2 ...   (compiles *.java then runs each, printing headers)
d=$1; shift
cd "$d" || exit 1
rm -f *.class
javac -encoding UTF-8 -Xlint:none *.java 2>&1 | grep -v "Picked up"
for m in "$@"; do echo "=== java $m ==="; java $m 2>&1 | grep -v "Picked up"; echo; done
