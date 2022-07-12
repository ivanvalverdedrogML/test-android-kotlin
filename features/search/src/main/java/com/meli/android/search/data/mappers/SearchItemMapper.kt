package com.meli.android.search.data.mappers

import com.meli.android.search.data.remote.model.RemoteSearchItem
import com.meli.android.search.domain.model.SearchItem

/**
 * Created by Anibal Cortez on 12-07-22.
 */

fun RemoteSearchItem.asDomain() = SearchItem(
    id = id
)
