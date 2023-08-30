package com.invoice.contratista.service

import com.invoice.contratista.data.source.web.models.Budget
import com.invoice.contratista.data.source.web.models.Part
import com.invoice.contratista.domain.BudgetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BudgetService(
    private val repository: BudgetRepository,
    userService: UserService
) : SuperService(userService) {
    suspend fun getById(
        id: String,
        onSuccess: (Budget) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
        if (isUserLogged) {
            repository.getById(token!!, id, getWebStatus(onSuccess, onError))
        }
    }

    suspend fun createPart(
        budgetId: String,
        onSuccess: (List<Part>) -> Unit,
        onError: (String) -> Unit
    ) = withContext(Dispatchers.IO) {
            if (isUserLogged) {
                repository.createPart(token!!, budgetId, getWebStatus(onSuccess, onError))
            }
        }
}