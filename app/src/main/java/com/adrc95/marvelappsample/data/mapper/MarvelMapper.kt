package com.adrc95.marvelappsample.data.mapper

import com.adrc95.domain.Character
import com.adrc95.marvelappsample.data.database.CharacterEntity
import com.adrc95.marvelappsample.data.server.CharacterDataWrapper
import com.adrc95.marvelappsample.data.server.Character as ServerCharacter

fun CharacterDataWrapper.toDomain(): List<Character> =
    characterDataContainer.results.map { it.toDomain() }

fun ServerCharacter.toDomain(): Character = Character(
    this.id,
    this.name,
    this.description,
    this.uri,
    "${this.thumbnail.path}.${this.thumbnail.extension}",
    false
)

fun CharacterEntity.toDomain(): Character = Character(
    this.id,
    this.name,
    this.description ?: "",
    this.uri,
    thumbnail,
    favorite ?: false,
)

fun Character.toEntity(): CharacterEntity = CharacterEntity(
    this.id,
    this.name,
    this.description,
    this.uri,
    thumbnail,
    favorite
)

