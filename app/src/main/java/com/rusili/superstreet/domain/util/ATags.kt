package com.rusili.superstreet.domain.util

class ATags {

    enum class COMMON(val value: String) {
        A("a"),
        FLAG("flag"),
        INFO("info");
    }

    enum class FLAG(val value: String) {
        TITLE("title"),
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
        AUTHOR_CONTRIBUTING("span.author"),
        AUTHOR_STAFF("span.author"),
        TIMESTAMP("span.timestamp");
    }
}