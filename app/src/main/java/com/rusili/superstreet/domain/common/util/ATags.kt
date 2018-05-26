package com.rusili.superstreet.domain.common.util

class ATags {

    enum class COMMON(val value: String) {
        A("a"),
        FLAG("flag"),
        INFO("info");
    }

    enum class FLAG(val value: String) {
        TITLE("title"),
        HREF("data-href");
    }

    enum class HEADER(val value: String) {
        TITLE("title"),
        IMG("img"),
        IMG_TITLE("data-src"),
        IMG_SRC("data-alt"),
        HREF("href"),
        DESC("desc");
    }

    enum class FOOTER(val value: String) {
        AUTHOR("span.author"),
        TIMESTAMP("span.timestamp");
    }
}