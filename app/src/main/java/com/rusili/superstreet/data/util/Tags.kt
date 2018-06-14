@file:JvmName("Tags")

package com.rusili.superstreet.data.util

// Base:
enum class COMMON(val value: String) {
    A("a"),
    FLAG("flag"),
    INFO("info"),
    HREF("href"),
    TITLE("title");
}

enum class FLAG(val value: String) {
    TITLE("title"),
    SPAN_LABEL("span.label");
}

enum class FOOTER(val value: String) {
    AUTHOR_CONTRIBUTING_1("span.author"),
    AUTHOR_CONTIBUTING_2("span.title"),
    AUTHOR_STAFF_DIV("div.meta"),
    AUTHOR_STAFF_ATTR("a.author"),
    TIMESTAMP("span.timestamp"),

    TIMESTAMP_FORMAT("MMMM d, yyyy");
}

// Preview List:
enum class LIST(val value: String) {
    MAIN_COLUMN("main-column"),
    TOP_STORY("mod-top-story"),

    STORIES_CONTAINER("mod-list-homepage mod-list-container"),
    PART_ITEM("part-list-item"),
    PART_HERO("part-hero");
}

enum class PREVIEW_HEADER(val value: String) {
    INFO("div.info"),
    TITLE("title"),
    IMG("img"),
    ALT("alt"),
    SRC("src"),
    DATA_SRC("data-src"),
    DATA_ALT("data-alt"),
    DESC("div.desc");
}

// Article:
enum class ARTICLE(val value: String) {
    HEADER_IMAGE("page-schema"),
    ARTICLE_CONTENT("mod-article-content"),
    ARTICLE_BODY("article-page");
}

enum class ARTICLE_HEADER(val value: String) {
    TITLE("h1.title"),
    DESC("h2.desc"),
    IMG_WRAP("img-wrap"),
    IMG("img"),
    SRC("src");
}

enum class ARTICLE_BODY(val value: String) {
    ID("id"),
    DESC("h2.desc"),
    IMG_WRAP("img-wrap"),
    IMG("img"),
    SRC("src");
}
