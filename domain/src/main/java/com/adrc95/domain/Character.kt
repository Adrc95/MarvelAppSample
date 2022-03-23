package com.adrc95.domain

data class Character(
    val id: Long,
    val name: String,
    val description: String,
    val uri : String,
    val thumbnail : String,
    val favorite : Boolean
    //val urls : List<String>
)
