package com.invoice.contratista.data.repository.web

import com.invoice.contratista.data.repository.web.utils.Resolve
import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.response.ResponseApi
import com.invoice.contratista.data.source.web.models.response.TokenModel
import com.invoice.contratista.data.source.web.models.response.UserModel
import com.invoice.contratista.data.source.web.retrofit.Service
import com.invoice.contratista.sys.domain.repository.web.SingRepository
import retrofit2.Response

class SingRepositoryImp(private val service: Service) : SingRepository {
    override suspend fun singIn(
            request: SingRequest,
            webStatus: WebStatus<TokenModel?>
    ) = Resolve(service.singIn(request), webStatus).invoke()

    override suspend fun updateToken(
            request: UpdateTokenRequest,
            webStatus: WebStatus<TokenModel>
    ) = Resolve(service.updateToken(request), webStatus).invoke()

    override suspend fun singUp(
            request: SingRequest,
            webStatus: WebStatus<UserModel>
    ) = Resolve(service.singUp(request), webStatus).invoke()

}