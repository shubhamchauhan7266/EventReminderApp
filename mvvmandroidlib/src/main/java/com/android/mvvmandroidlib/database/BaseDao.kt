package com.android.mvvmandroidlib.database

import androidx.room.*

/**
 * This dao class is used as a base class for all Dao and provide some methods to its subclasses.
 *
 * @param <T> The type of data hold by this instance
 *
 * @author Shubham Chauhan
 */
abstract class BaseDao<T> {

    /**
     * Insert an object in the database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(obj: T): Long

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(vararg obj: T): List<Long>

    /**
     * Insert a list of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    abstract fun insert(obj: List<T>): List<Long>

    /**
     * Update an object from the database.
     *
     * @param obj the object to be updated
     */
    @Update
    abstract fun update(obj: T)

    /**
     * Update list of objects
     *
     * @param obj the objects to be updated
     */
    @Update
    @JvmSuppressWildcards
    abstract fun update(obj: List<T>)


    /**
     * Delete an object from the database
     *
     * @param obj the object to be deleted
     */
    @Delete
    abstract fun delete(obj: T)

    /**
     * Inserts if the obj is not present else
     * updates the obj
     *
     * @param obj the object to be inserted or updated
     */
    @Transaction
    open fun insertOrUpdate(obj: T) {
        val id = insert(obj)
        if (id == -1L) update(obj)
    }

    /**
     *
     * Inserts the list of objects if not present else
     * updates them
     *
     * @param obj the objects to be inserted or updated
     */
    @Transaction
    open fun insertOrUpdate(objList: List<T>) {
        val insertResult = insert(objList)
        val updateList = mutableListOf<T>()

        for (i in insertResult.indices) {
            if (insertResult[i] == -1L) updateList.add(objList[i])
        }

        if (!updateList.isEmpty()) update(updateList)
    }
}