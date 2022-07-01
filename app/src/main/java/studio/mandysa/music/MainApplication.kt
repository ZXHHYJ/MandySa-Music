package studio.mandysa.music

import android.app.Application
import android.content.Intent
import simon.tuke.Tuke
import studio.mandysa.music.logic.ktx.playManager
import studio.mandysa.music.service.MediaPlayService
import studio.mandysa.music.service.playmanager.PlayManager

/**
 * @author Huang hao
 */
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Tuke.init(this)
        PlayManager.init(this)
        //初始化播放管理器
        playManager {
            //确保播放音乐时播放启动服务
            pauseLiveData()
                .observeForever {
                    if (it == false)
                        startPlayerService()
                }
            changeMusicLiveData()
                .observeForever {
                    if (it != null)
                        startPlayerService()
                }
        }
    }

    private fun startPlayerService() {
        if (MediaPlayService.instance == null) {
            try {
                startForegroundService(
                    Intent(
                        this,
                        MediaPlayService::class.java
                    )
                )
            } catch (e: Exception) {

            }
        }
    }

}