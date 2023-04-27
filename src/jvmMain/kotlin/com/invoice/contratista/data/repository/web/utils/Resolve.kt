package com.invoice.contratista.data.repository.web.utils

import com.invoice.contratista.data.source.web.models.response.ResponseApi
import retrofit2.Response

class Resolve<T>(
        private val response: Response<ResponseApi<T>>,
        private val webStatus: WebStatus<T>
) {
    operator fun invoke() {
        if (response.code() == 200) {
            val body = response.body()
            if (body == null) {
                webStatus.error("Error inesperado")
                return
            }
            if (body.status) {
                if (body.data == null) {
                    webStatus.error("Error inesperado")
                    return
                } else {
                    webStatus.success(body.data)
                    return
                }

            } else webStatus.error(body.message)
        } else {
            webStatus.error("Error inesperado")
            return
        }
    }
}