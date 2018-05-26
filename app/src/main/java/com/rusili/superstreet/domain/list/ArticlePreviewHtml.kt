package com.rusili.superstreet.domain.list

data class ArticlePreviewHtml(private val title: String,
                              private val href: String,
                              private val desc: String,
                              private val author: String,
                              private val timestamp: String,
                              private val flag: String,
                              private val label: String)