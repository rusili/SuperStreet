@file:JvmName("Tags")

package com.rusili.superstreet.data.util

enum class COMMON(val value: String) {
    A("a"),
    FLAG("flag"),
    INFO("info"),
    HREF("href");
}

enum class LIST(val value: String) {
    MAIN_COLUMN("main-column"),
    TOP_STORY("mod-top-story"),
    STORIES_CONTAINER("mod-list-homepage mod-list-container"),

    PART_ITEM("part-list-item"),
    PART_HERO("part-hero");
}

enum class ARTICLE(val value: String) {
    HEADER_IMAGE("page-schema"),
    ARTICLE_CONTENT("mod-article-content"),
    ARTICLE_BODY("article-page");
}

enum class FLAG(val value: String) {
    TITLE("title"),
    SPAN_LABEL("span.label");
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