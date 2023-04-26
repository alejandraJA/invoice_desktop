package data.repository

import data.entity.User

interface UserRepository {
    fun findUser(name: String): User?
    fun addUsers(users: List<User>)
    fun getAll(): List<User>
}