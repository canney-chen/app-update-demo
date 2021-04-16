package cn.canney.aapp.demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.canney.aapp.demo.AppUtils
import cn.canney.aapp.demo.R
import cn.canney.aapp.demo.service.Codes
import cn.canney.aapp.demo.viewmodel.AppUpdateViewModel
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var appUpdateViewModel: AppUpdateViewModel

    private val LINE = "\r\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewModelProvider(this).apply {
            appUpdateViewModel = get(AppUpdateViewModel::class.java)
        }

        tv_version.text = "当前版本:(${AppUtils.getVersionCode(this)})${AppUtils.getVersionName(this)}"


        appUpdateViewModel.lastVersion.observe(this, Observer {

            tv_message.text = ""
            when (it.code) {
                Codes.LAST_VERSION -> tv_message.append("当前已是最新版本$LINE")
                Codes.NET_ERROR -> tv_message.append("${it.message}")
                Codes.SUCCESS -> {
                    tv_message.append("获取到最新版本:${it.data?.versionName}$LINE")
                    tv_message.append("版本信息:$LINE")

                    val props = JSON.toJSON(it.data) as JSONObject

                    for (value in props) {
                        tv_message.append("${value.key}: ${value.value}$LINE")
                    }

                }
            }

        })

        btn_update.setOnClickListener {
            tv_message.text = ""
            tv_message.append("正在获取版本...$LINE")
            appUpdateViewModel.checkVersion(et_host.text.toString())
        }

    }


}