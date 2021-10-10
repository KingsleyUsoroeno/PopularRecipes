package com.example.popularrecipes.domain.mapper.base

interface DomainMapper<T, Domain> {

    fun mapToDomainModel(model: T): Domain

    fun mapFromDomainModel(domain: Domain): T

    fun mapToDomainList(value: List<T>): List<Domain> {
        return value.mapTo(mutableListOf(), ::mapToDomainModel)
    }
}