package com.example.dobrazil.EntityRepositories

import com.example.dobrazil.Dao.FavoriteDao
import com.example.dobrazil.Entity.FavoriteRel
import javax.inject.Inject

/**
 * @brief Repository for the FavoriteRel table
 */
class FavoriteRelRepository @Inject constructor(
    private val dao: FavoriteDao
) {
    // Insert a favorite relation in the table
    suspend fun insert(favoriteRel: FavoriteRel) = dao.insert(favoriteRel)

    // Delete a favorite relation in the table
    suspend fun delete(favoriteRel: FavoriteRel) = dao.delete(favoriteRel)

    // Get all favorite relations in the table
    fun getAll() = dao.getAll()

    // Get a favorite relation by its id
    fun getById(id: Int) = dao.getById(id)

    // Get a favorite relation by its follower
    fun getByFollower(id: Int) = dao.getByFollower(id)

    // Get a favorite relation by its follow
    fun getByFollow(id: Int) = dao.getByFollow(id)
}
