package cn.canney.aapp.demo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cn.canney.aapp.demo.AppUtils
import cn.canney.aapp.demo.service.AppUpdateService
import cn.canney.aapp.demo.service.AppVersion
import cn.canney.aapp.demo.service.WebResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppUpdateViewModel(app: Application) : AndroidViewModel(app) {

    private val service = AppUpdateService()

    val lastVersion = MutableLiveData<WebResult<AppVersion>>()

    fun checkVersion(host: String){

        GlobalScope.launch(Dispatchers.IO){
            lastVersion.postValue(service.check("http", host, AppUtils.getPackageName(getApplication()), AppUtils.getVersionCode(getApplication())))
        }

    }

}