package com.invoice.contratista.data.repository.web.utils

import com.invoice.contratista.data.source.web.models.response.ResponseApi
import com.invoice.contratista.utils.ERROR_INESPERADO
import retrofit2.Response

class Resolve<T>(
    private val response: Response<ResponseApi<T>>,
    private val webStatus: WebStatus<T>
) {
    operator fun invoke() {
        if (response.code() == 200) {
            val body = response.body()
            if (body == null) {
                webStatus.error(ERROR_INESPERADO)
                return
            }
            if (!body.status) webStatus.error(body.message)
            else {
                return if (body.data == null) webStatus.error(ERROR_INESPERADO)
                else webStatus.success(body.data)


            }
        } else {
            webStatus.error(ERROR_INESPERADO)
            return
        }
    }
}