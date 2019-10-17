# Anagram finder

Accepts a block of text (English words, hopefully), and outputs the groups of words that are anagrams of each other.  
For example, "apt", "tap" and "pat" are anagrams of each other.  
From [Wikipedia]()
> An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once. 
> For example, the word _anagram_ can be rearranged into _nag a ram_, or the word _binary_ into _brainy_ or the word _adobe_ into _abode_.


## Getting Started

In a suitable folder:
```
git clone https://github.com/evys4242/anagram.git
cd anagram
mvn package
java -jar target/anagram.jar <args>
```
or skip the maven build
```
git clone https://github.com/evys4242/anagram.git
java -jar anagram/bin/anagram.jar <args>
```

### Arguments:
* two or more "words": `apt but tap tub pat`
* one or more "phrazes" in doublequotes: `"Madam Curie" "Radium came"`
* a URL: `-url https://en.wikipedia.org/wiki/Anagram`
* a file: `-file path/to/local.file`

:confused: _A wiki entry on anagram contains one 'open' (or extra) doublequote character in a middle of an article.
That breaks parsing it into tokens. As a result, this implementation finds roughly a half of the anagrams it suppose to._  
:relaxed: _A copy of an article's text with a fixed quotation is in the_ `src/test/resources/wiki-anagram-fixed.txt`.

### Prerequisites
Java 8 and above.

## Built With

* [Maven](https://maven.apache.org/)

## Authors

* **Eduard Vysotskiy** - [evys4242](https://github.com/evys4242)
