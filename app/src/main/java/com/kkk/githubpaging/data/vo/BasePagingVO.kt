package com.kkk.githubpaging.data.vo

import android.os.Parcel
import android.os.Parcelable

open class BasePagingVO() :Parcelable,BaseVO {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BasePagingVO> {
        override fun createFromParcel(parcel: Parcel): BasePagingVO {
            return BasePagingVO(parcel)
        }

        override fun newArray(size: Int): Array<BasePagingVO?> {
            return arrayOfNulls(size)
        }
    }
}