package com.invoice.contratista.service

import com.invoice.contratista.data.source.web.models.response.event.BudgetEntity
import com.invoice.contratista.domain.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BudgetService(
    private val repository: BudgetRepository,
    userService: UserService
): SuperService(userService) {
    suspend fun getById(
        id: String,
        onSuccess: (BudgetEntity) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (condition) {
            repository.getById(token!!, id, getWebStatus(onSuccess, onError))
        }
    }
}