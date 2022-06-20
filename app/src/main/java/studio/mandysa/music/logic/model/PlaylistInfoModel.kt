package studio.mandysa.music.logic.model

import mandysax.anna2.annotation.Value

/**
 * @author Huang hao
 */
class PlaylistInfoModel {
    @Value("name")
    lateinit var name: String

    @Value("description")
    lateinit var description: String

    @Value("coverImgUrl")
    lateinit var coverImgUrl: String

    @Value("trackIds")
    lateinit var songList: List<SongList>

    class SongList {
        @Value("id")
        lateinit var id: String

        override fun toString(): String {
            return id
        }
    }
}