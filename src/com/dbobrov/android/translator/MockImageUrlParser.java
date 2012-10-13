package com.dbobrov.android.translator;

public class MockImageUrlParser implements ImageUrlParser {
    @Override
    public String[] getImageUrls(String word) {
        if ("nya".equals(word)) {
            return new String[]{
                    "http://i99.beon.ru/dashborodina.narod.ru/elfenlied_004.jpg",
                    "http://img1.liveinternet.ru/images/attach/b/1/9361/9361538_elfenkyu.gif",
                    "http://animelayer.ru/wallpapers/images/Elfen%20Lied/Elfen%20Lied%20-%2006.jpg"
            };
        }
        return null;
    }
}
