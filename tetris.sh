#!/bin/bash

# SCRIPT_DIR="$( cd "$( dirname "$0" )" && pwd )"
CLASSPATH="/home/mart/projects/Tetris/target/classes/"
MAIN_CLASS=tetris.Main

java -cp "$CLASSPATH" "$MAIN_CLASS" "$@"