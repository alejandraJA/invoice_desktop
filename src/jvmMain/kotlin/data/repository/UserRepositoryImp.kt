package data.repository

import data.entity.User

class UserRepositoryImp : UserRepository {

    private val _users = mutableListOf<User>(User("name"), User("Ale"))
    override fun findUser(name: String): User? {
        return _users.firstOrNull { it.name == name }
    }

    override fun addUsers(users: List<User>) {
        _users.addAll(users)
    }

    override fun getAll(): List<User> = _users

}