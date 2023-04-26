package com.invoice.contratista.sys.domain.repository.web

import com.invoice.contratista.data.repository.web.utils.Resolve
import com.invoice.contratista.data.repository.web.utils.WebStatus
import com.invoice.contratista.data.source.web.models.request.SingRequest
import com.invoice.contratista.data.source.web.models.request.UpdateTokenRequest
import com.invoice.contratista.data.source.web.models.response.ResponseApi
import com.invoice.contratista.data.source.web.models.response.TokenModel
import com.invoice.contratista.data.source.web.models.response.UserModel
import retrofit2.Response

interface SingRepository {
    suspend fun singIn(request: SingRequest, webStatus: WebStatus<TokenModel?>)
    suspend fun updateToken(request: UpdateTokenRequest, webStatus: WebStatus<TokenModel>)
    suspend fun singUp(request: SingRequest, webStatus: WebStatus<UserModel>)

}