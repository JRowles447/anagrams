# anagrams
-----
Traverses through a file with one word per line creating a hash table with anagram classes. Takes an input dictionary and writes all anagram class lists to output file

## Running anagrams
---
Compile the project with:   
```
javac ./src/Anagram.java -d ./build
javac ./src/Driver.java -d ./build    
javac ./src/Node.java -d ./build
```
Run from the root of the project directory using:   
`
java ./build/Driver ./inputs/dict1 ./outputs/anagram1
`

Driver takes two arguments:
+ filename: Input file with the dictionary (list) of words (one per line).
+ outfile: Name of the file to write anagram classes to.

## Dependencies
---
Utilizes Java 1.8.0_112
