package cn.canney.aapp.demo.service

import android.util.Log
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.ConnectException
import java.nio.charset.Charset

class AppUpdateService {

    val TAG = AppUpdateService::class.java.simpleName

    suspend fun check(
        scheme: String,
        host: String,
        appId: String,
        versionCode: Int
    ): WebResult<AppVersion>? {

        var rawHost: String?
        var port: Int? = null
        host.split(":").apply {
            rawHost = get(0)
            if (size > 1) {
                port = get(1).toInt()
            }
        }

        val url = HttpUrl.Builder().apply {
            scheme(scheme)
            rawHost?.let { host(it) }
            port?.let { port(it) }
            addPathSegments("api/app/update/check")
            addQueryParameter("versionCode", versionCode.toString())
            addQueryParameter("appid", appId).build()
        }.build()

        val client = OkHttpClient()

        val request = Request.Builder().url(url).build()

        try {
            val resp = client.newCall(request).execute()

            if(resp.code != 200){
                return WebResult<AppVersion>().apply{
                    code = Codes.HTTP_FAILED
                    message = "调用接口失败:HTTP Status: ${resp.code}"
                }
            }

            val data = String(resp.body?.bytes()!!, Charset.forName("utf-8"))
            Log.d(TAG, data)

            return JSON.parseObject(
                data,
                object : TypeReference<WebResult<AppVersion>>() {})

        } catch (e: ConnectException) {
            return WebResult<AppVersion>().apply{
                code = Codes.NET_ERROR
                message = "网络错误"
            }
        }
    }
}