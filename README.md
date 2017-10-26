# anagrams
-----
Traverses through a file with one word per line creating a hash table with anagram classes. Takes an input dictionary and writes all anagram class lists to output file

## Running anagrams
---
Compile the project with:   
```
javac ./src/* -d ./build
```
Run from the build folder of the project directory using:   
`
java Driver ../inputs/dict1 ../outputs/anagram1
`

Driver takes two arguments:
+ filename: Input file with the dictionary (list) of words (one per line).
+ outfile: Name of the file to write anagram classes to.

## Dependencies
---
Utilizes Java 1.8.0_112
