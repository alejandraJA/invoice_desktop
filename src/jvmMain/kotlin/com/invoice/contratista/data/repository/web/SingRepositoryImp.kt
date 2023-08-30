package com.invoice.contratista.data.repository.web

import com.invoice.contratista.data.repository.web.utils.Resolve
import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.SingRequest
import com.invoice.contratista.data.source.web.models.Token
import com.invoice.contratista.data.source.web.models.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.User
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.domain.SingRepository

class SingRepositoryImp(private val service: Service) : SingRepository {
    override suspend fun singIn(
        request: SingRequest,
        webStatus: WebStatus<Token?>
    ) = Resolve(service.singIn(request), webStatus).invoke()

    override suspend fun updateToken(
        request: UpdateTokenRequest,
        webStatus: WebStatus<Token>
    ) = Resolve(service.updateToken(request), webStatus).invoke()

    override suspend fun singUp(
        request: SingRequest,
        webStatus: WebStatus<User>
    ) = Resolve(service.singUp(request), webStatus).invoke()

}