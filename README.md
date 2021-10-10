# Movie Search Engine
An implementation of an advanced movie search engine, using TMDB's data &amp; Lucene's indexing, in JavaFX 11.

# Description
This is the core of a simple Movie Database implentation. It downloads data (Movies, Reviews, Actors & Characters, etc.) from TMDB, in JSON Format, then parses them
and finally stores them into a local index (Internet connection is required, in order to download the data, when the app runs for the first time in a device). After that,
the index is loaded in the memory. Then, the user can ask queries and retrieve documents.

# Extremely Fast Parallel Downloading
The core uses parallel streams and parallel executors, in order to download the data from TMDB very fast. By default, the core uses 2 Threads that constantly fetch pages
from the database, as soon as the application opens up. Also, I have added a splash screen with a progressbar that displays the download progress. The application opens up with
fading effects, as soon as the download is over.

# Ultra Fast Parsing with JsonIter
The core uses **JsonIter** library to speed up the parsing process. This is one of the fastest Json Parsers from java, that outspeeds Google's GSon and many other libraries.

# Incredible Fast Indexing & Searching with Lucene
Lucene is one of the best indexers for Java. Not only it does search multiple queries in the speed of light, but the indexing process is very fast as well. The application comes
with a default Search bar, which You can use to ask queries to Lucene.

# Queries
The core implements the following queries. However, one can combine those queries to make an advanced search engine.
1. Titles: A combination of Fuzzy & Phrase queries, that could detect titles with more than 1 term and also apply spelling correction.
1. Keywords: It searches movies that contain multiple phrases as keywords, separated by comma (e.g. life, remote forest, in the).
1. Actors: It searches actors names and character names in the database. It also applyies spelling correction.
1. Suggester: Also, a suggester is implemented that can suggest and autocomplete the terms in the searchbar.

# Graphic User Interface (GUI)
The GUI is implementated using JavaFX and external control libraries, such as controlsFX & animateFX. It comes with a default without that is very nicely customized,
using **FontAwesome Glyphs** and **Material Design** colors.
