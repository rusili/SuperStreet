package com.rusili.superstreet.common.models

interface ModelMapper<T, R> {

    fun to(t: T): R

    fun from(r: R): T
}
