package cn.canney.aapp.demo.service

import java.util.*

/**
 * app版本信息
 * @author: LiShuai
 * @date: 2021/3/4 11:37
 */
class AppVersion(
    /**
     * app名称
     */
    val name: String?,
    /**
     * app唯一标识（即packageName）
     */
    val appid: String?,
    /**
     * app版本Code，用于比较当前版本，判断是否升级
     */
    val versionCode: Int = 0,
    /**
     * app版本名称
     */
    val versionName: String?,
    /**
     * app发布时间
     */
    val publishTime: Date?,
    /**
     * apk下载地址
     */
    val apkUrl: String?,
    /**
     * apk大小
     */
    val apkSize: Double = 0.0,
    /**
     * 更新日志
     */
    val versionLog: String?,
    /**
     *
     */
    val versionSlogin: String?
)
