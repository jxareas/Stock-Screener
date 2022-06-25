package com.jxareas.stonks.data.mapper

import com.jxareas.stonks.data.local.entity.CompanyListingEntity
import com.jxareas.stonks.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing =
    CompanyListing(name, symbol, exchange)

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity =
    CompanyListingEntity(name, symbol, exchange)