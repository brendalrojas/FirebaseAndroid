package com.blrp.firebase.data.model

import android.icu.util.Calendar

class PlayerData(
    val userId: String? = Calendar.getInstance().timeInMillis.hashCode().toString(),
    val playerType: Int? = null,
)
