package com.adrc95.marvelappsample.ui.common

enum class ModeType(val value: Int) {
    DAY(1),
    NIGHT(2),
    AUTOMATIC(3);

    companion object {
        fun toModeType(value: Int): ModeType =
            when (value) {
                1 -> DAY
                2 -> NIGHT
                else -> AUTOMATIC
            }
    }
}