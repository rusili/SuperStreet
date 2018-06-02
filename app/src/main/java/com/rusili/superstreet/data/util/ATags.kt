package com.rusili.superstreet.data.util

class ATags {

    enum class COMMON(val value: String) {
        A("a"),
        FLAG("flag"),
        INFO("info"),
        HREF("href");
    }

    enum class FLAG(val value: String) {
        TITLE("title"),
    }

    enum class HEADER(val value: String) {
        TITLE("title"),
        IMG("img"),
        DATA_SRC("data-src"),
        DATA_ALT("data-alt"),
        DESC("div.desc");
    }

    enum class FOOTER(val value: String) {
        AUTHOR_CONTRIBUTING_1("span.author"),
        AUTHOR_CONTIBUTING_2("span.title"),
        AUTHOR_STAFF_DIV("div.meta"),
        AUTHOR_STAFF_ATTR("a.author"),
        TIMESTAMP("span.timestamp");
    }
}