# Movie Search Engine
An implementation of an advanced movie search engine, using TMDB's data &amp; Lucene's indexing.

# Description
This is the core of a simple Movie Database implentation. It downloads data (Movies, Reviews, Actors & Characters, etc.) from TMDB, in JSON Format, then parses them
and finally stores them into a local indexes (one index per table). Internet connection is required, in order to download the data, when the app runs for the first time in a device. The user has the option to update & re-create the indexes everytime the app is restarted.

# Ultra Fast Parallel Downloading
Parallel streams and parallel executors, are used in order to download the data from TMDB very fast.

# Ultra Fast Parsing with JsonIter
The **JsonIter** library is one of the fastest Json libraries available in Java. It outperforms traditional Json parsing libraries, such as Google's GSon and many other libraries.

# Ultra Fast Indexing & Searching with Lucene
Lucene is one of the fastest indexers in Java. Lucene is the core of `elasticsearch` library. Lucene's job is:
1. Add downloaded documents to their indexes.
2. Search documents.
3. Highlight documents.

# Readability
The code is implemented using popular design patterns in java, according to the book "Head First Design Patterns"
https://www.amazon.com/Head-First-Design-Patterns-Brain-Friendly/dp/0596007124

# Extensibility
Every part of the project is written in such a way that software engineers can easily add extensions.

# Required libraries in (Java 11)
In order to run the engine, You will need to download the following libraries:
1. JsonIter (0.9.23)
2. Lucene Core (9.3.0)
3. Junit 5
