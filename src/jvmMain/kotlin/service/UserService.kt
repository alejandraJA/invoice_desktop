package service

import data.repository.UserRepository

class UserService(private val repository: UserRepository) {
    fun getAll() = repository.getAll()
}