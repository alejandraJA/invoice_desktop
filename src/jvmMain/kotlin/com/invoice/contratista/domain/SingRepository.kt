package com.invoice.contratista.domain

import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.SingRequest
import com.invoice.contratista.data.source.web.models.Token
import com.invoice.contratista.data.source.web.models.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.User

interface SingRepository {
    suspend fun singIn(request: SingRequest, webStatus: WebStatus<Token?>)
    suspend fun updateToken(request: UpdateTokenRequest, webStatus: WebStatus<Token>)
    suspend fun singUp(request: SingRequest, webStatus: WebStatus<User>)

}