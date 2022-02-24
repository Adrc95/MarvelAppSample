package com.adrc95.marvelappsample.data.database

import androidx.room.*

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(character: CharacterEntity): Long

    @Delete
    fun delete(character: CharacterEntity): Int

    @Query("SELECT * FROM ${Character.TABLE_NAME}  ORDER BY name ASC")
    fun loadAllCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM ${Character.TABLE_NAME} WHERE favorite = 1  ORDER BY name ASC")
    fun loadAllFavoriteCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM ${Character.TABLE_NAME} WHERE name LIKE '%' || :name || '%' ORDER BY name ASC")
    fun searchCharacterByName(name: String): List<CharacterEntity>

    @Query("SELECT * FROM ${Character.TABLE_NAME} WHERE id = :id")
    fun findCharacterById(id: Long): CharacterEntity

    @Query("UPDATE ${Character.TABLE_NAME} SET  ${Character.COLUMN_FAVORITE} = :favorite  WHERE id=:id")
    fun updateFavoriteCharacter(id: Long, favorite: Boolean)
}