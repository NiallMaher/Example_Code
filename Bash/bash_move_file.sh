#!/bin/bash

cd ~/Niall_Test/perl_files
for filename in *; do
  # this syntax emits the value in lowercase: ${var,,*}
  case "${filename,,*}" in
    file*)    mv "<source>" "<destination>" ;;
    <filename>*) mv "<source>" "<destination>" ;;
    *) echo "don't know where to put $filename";;
  esac
done

