package com.example.translate.ROOM

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class  Word(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var real_Word: String? = "",
    var translated_Word: String? = ""
): Parcelable{
    constructor(): this(null, "", "")
}