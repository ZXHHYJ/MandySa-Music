package studio.mandysa.music.service.playmanager.ktx

import studio.mandysa.music.service.playmanager.bean.SongBean

fun Array<SongBean.Network.Artist>.allArtist(): String {
    val builder = StringBuilder()
    for (i in indices) {
        if (i != 0) {
            builder.append("/")
        }
        builder.append(get(i).name)
    }
    return builder.toString()
}
