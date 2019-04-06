package com.rusili.superstreet.jsoup.parsing

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

fun Elements.second(): Element = get(1)
