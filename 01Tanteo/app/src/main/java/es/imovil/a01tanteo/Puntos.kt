package es.imovil.a01tanteo

import android.os.Parcel
import android.os.Parcelable

class Puntos() : Parcelable{
    var total = 0
    var tirosTotales = 0
    var threePointer = 0
    var twoPointer = 0
    var onePointer = 0

    constructor(parcel: Parcel) : this() {
        total = parcel.readInt()
        tirosTotales = parcel.readInt()
        threePointer = parcel.readInt()
        twoPointer = parcel.readInt()
        onePointer = parcel.readInt()
    }

    fun calcTotal():Int {
        return threePointer*3 + twoPointer*2 + onePointer
    }

    fun nTirosTotal():Int {
        tirosTotales = threePointer + twoPointer + onePointer
        return tirosTotales
    }

    fun incOnePointer() {
        onePointer ++
        total = calcTotal()
    }
    fun incTwoPointer() {
        twoPointer ++
        total = calcTotal()
    }
    fun incThreePointer() {
        threePointer ++
        total = calcTotal()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(total)
        parcel.writeInt(tirosTotales)
        parcel.writeInt(threePointer)
        parcel.writeInt(twoPointer)
        parcel.writeInt(onePointer)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Puntos> {
        override fun createFromParcel(parcel: Parcel): Puntos {
            return Puntos(parcel)
        }

        override fun newArray(size: Int): Array<Puntos?> {
            return arrayOfNulls(size)
        }
    }
}