package com.example.dobrazil.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Transaction
import com.example.dobrazil.Entity.FavoriteRel

/**
 * @brief Dao class for the FavoriteRel table
 */
@Dao
interface FavoriteDao {

    // Insert a favorite relation in the table
    @Insert
    fun insert(favoriteRel: FavoriteRel)

    // Delete a favorite relation in the table
    @Delete
    fun delete(favoriteRel: FavoriteRel)

    // Get all favorite relations in the table
    @Query("SELECT * FROM FavoriteRel")
    fun getAll(): List<FavoriteRel>

    // Get a favorite relation by its id
    @Query("SELECT * FROM FavoriteRel WHERE idFavoriteRel = :id")
    fun getById(id: Int): FavoriteRel

    // Get a favorite relation by its follower
    @Query("SELECT * FROM FavoriteRel WHERE idFollower = :id")
    fun getByFollower(id: Int): List<FavoriteRel>

    // Get a favorite relation by its follow
    @Query("SELECT * FROM FavoriteRel WHERE idFollow = :id")
    fun getByFollow(id: Int): List<FavoriteRel>

    // Get a profil id by its username
    @Query("SELECT idProfil FROM ProfilEntity WHERE username = :username")
    fun getIdByUsername(username: String): Int

    // Insert a favorite relation using usernames of follower and follow
    @Transaction
    fun insertWithUsernames(followerUsername: String, followUsername: String) {
        val idFollower = getIdByUsername(followerUsername)
        val idFollow = getIdByUsername(followUsername)
        val existingRelation = getByFollowerAndFollow(idFollower, idFollow)
        if (existingRelation == null) {
            insert(FavoriteRel(null, idFollower, idFollow))
        }
    }

    // Get a favorite relation by its follower and follow
    @Query("SELECT * FROM FavoriteRel WHERE idFollower = :idFollower AND idFollow = :idFollow")
    fun getByFollowerAndFollow(idFollower: Int, idFollow: Int): FavoriteRel?

    // Delete a favorite relation using usernames of follower and follow
    @Transaction
    fun deleteWithUsernames(followerUsername: String, followUsername: String) {
        val idFollower = getIdByUsername(followerUsername)
        val idFollow = getIdByUsername(followUsername)
        val existingRelation = getByFollowerAndFollow(idFollower, idFollow)
        if (existingRelation != null) {
            delete(existingRelation)
        }
    }
    
}