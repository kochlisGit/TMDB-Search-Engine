package server;

import parsers.EntityParser;

import java.net.URI;
import java.util.List;

public class EntityRetriever<T>
{
    List<T> retrieveEntities(EntityParser<T> parser,
                                  List<String> requestList,
                                  String params,
                                  int nPages)
    {
        List<URI> uriList = URIBuilder.newBuilder()
                .setParams(params)
                .setnPages(nPages)
                .build(requestList);

        List<String> pageList = new PageDownloader().downloadPages(uriList);
        return parser.parseEntities(pageList);
    }
}
