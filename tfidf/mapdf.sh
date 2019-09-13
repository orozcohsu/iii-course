#!/bin/bash

while read term file num; do
  printf "%s\t%s\t%s\t%s\n" "$term" "$file" "$num" "1"
done

