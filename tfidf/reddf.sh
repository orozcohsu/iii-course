#!/bin/bash

read currterm currfile currtf currdf
while read term file tf df; do
  if [[ $term = "$currterm" ]]; then
    currdf=$(( currdf + df ))
    buffer+="${term}\t${file}\t${tf}\n"
  else
    echo -e -n $buffer | while read line; do echo -e "${line}\t${currdf}"; done
    printf "%s\t%s\t%s\t%s\n" "$currterm" "$currfile" "$currtf" "$currdf"
    buffer=""
    currterm="$term"
    currfile="$file"
    currtf="$tf"
    currdf="$df"
  fi
done
echo -e -n $buffer | while read line; do echo -e "${line}\t${currdf}"; done
printf "%s\t%s\t%s\t%s\n" "$currterm" "$currfile" "$currtf" "$currdf"
