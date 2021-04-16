package cn.canney.aapp.demo.service

import java.util.*

/**
 * app版本信息
 * @author: LiShuai
 * @date: 2021/3/4 11:37
 */
class AppVersion() {
    /**
     * app名称
     */
    var name: String? = null

    /**
     * app唯一标识（即packageName）
     */
    var appid: String? = null

    /**
     * app版本Code，用于比较当前版本，判断是否升级
     */
    var versionCode: Int = 0

    /**
     * app版本名称
     */
    var versionName: String? = null

    /**
     * app发布时间
     */
    var publishTime: Date? = null

    /**
     * apk下载地址
     */
    var apkUrl: String? = null

    /**
     * apk大小
     */
    var apkSize: Double = 0.0

    /**
     * 更新日志
     */
    var versionLog: String? = null

    /**
     *
     */
    var versionSlogin: String? = null

}
