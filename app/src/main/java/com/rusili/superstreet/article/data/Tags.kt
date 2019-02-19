@file:JvmName("Tags")

package com.rusili.superstreet.article.data

// Base:
enum class COMMON(val value: String) {
    A("a"),
    FLAG("flag"),
    DIV("div"),
    INFO("info"),
    HREF("href"),
    TITLE("title"),
    IMG("img"),
    SRC("src");
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
    ALT("alt"),
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
}

enum class ARTICLE_BODY(val value: String) {
    ID("id"),
    DESC("h2.desc"),
    UL("ul"),
    IMG_WRAP("img-wrap"),
    IMG_LINK("img-link"),
    DATA_IMG_SRC("data-img-src"),
    MOD_ARTICLE_CONTENT("mod-article-content"),
    ARTICLE_PAGE("article-page"),
    ARTICLE_PARAGRAPH("article-paragraph"),
    ARTICLE_TEXT("article-text"),
    ARTICLE_IMAGE("article-image"),
    ARTICLE_IMAGE_GROUP("article-image-group"),
}
