package com.rusili.superstreet.domain.common.util

class ATags {

    enum class COMMON(val value: String) {
        A("a"),
        FLAG("flag"),
        INFO("info");
    }

    enum class FLAG(val value: String) {
        TITLE("title"),
        DATA_HREF("data-href"),
        HREF("href");
    }

    enum class HEADER(val value: String) {
        TITLE("title"),
        IMG("img"),
        IMG_TITLE("data-src"),
        IMG_SRC("data-alt"),
        HREF("href"),
        DESC("div.desc");
    }

    enum class FOOTER(val value: String) {
        AUTHOR("span.author"),
        TIMESTAMP("span.timestamp");
    }
}