package com.example.translate.ROOM

import android.arch.persistence.room.*
//import com.google.android.gms.tasks.Task

@Dao
interface WordDAO {
    @Query("SELECT * FROM word")
    fun getAll():List<Word>

    @Query("SELECT * FROM word WHERE id =:id")
    fun findById(id: Int): Word

    @Query("SELECT *FROM word WHERE real_Word LIKE :name")
    fun findByRealWord(name: String): Word

    @Query("SELECT *FROM word WHERE translated_Word LIKE :name")
    fun findByTranslatedWord(name: String): Word

    @Insert
    fun insertAll(vararg todo: Word) : List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj: Word):Long

    @Delete
    fun delete(toto: Word)

    @Update
    fun update(word : Word)

    @Query("DELETE FROM word")
    fun deleteAllTask()
}
