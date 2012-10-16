package com.dbobrov.android.translator;

/**
 * Created with IntelliJ IDEA.
 * User: blackhawk
 * Date: 13.10.12
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 */
public class ImageUrlParserFactory {
    public static ImageUrlParser getImageUrlParser() {
        //return new MockImageUrlParser();
        return new GoogleImageUrlParser();
    }
    
}
