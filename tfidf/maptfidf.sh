#!/bin/bash

while read term file tf df; do
  TFIDF=$(echo $N $df $tf | awk '{print $3 * log($1/$2)}')
  printf "%s\t%s\t%s\n" "$term" "$file" "$TFIDF"
done

