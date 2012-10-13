package com.dbobrov.android.translator;

/**
 * Created with IntelliJ IDEA.
 * User: blackhawk
 * Date: 13.10.12
 * Time: 17:36
 * To change this template use File | Settings | File Templates.
 */
public class WordTranslatorFactory {
    public static WordTranslator getWordTranslator() {
        return new MockWordTranslator();
    }

}
