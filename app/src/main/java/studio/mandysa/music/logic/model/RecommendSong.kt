package studio.mandysa.music.logic.model

import mandysax.anna2.annotation.Value
import studio.mandysa.music.service.playmanager.model.MetaMusic
import java.io.Serializable

/**
 * @author 黄浩
 */
class RecommendSong : MetaMusic<SingerModel, AlbumModel>, Serializable {
    @Value("name")
    private lateinit var name: String

    @Value("id")
    private lateinit var id: String

    @Value("ar")
    private lateinit var artistsList: List<SingerModel>

    @Value("al")
    private lateinit var album: AlbumModel

    /*@Value("reason")
    val reason = ""*/

    override fun getTitle(): String {
        return name
    }

    override fun getCoverUrl(): String {
        return album.coverUrl
    }

    override fun getArtist(): List<SingerModel> {
        return artistsList
    }

    override fun getId(): String {
        return id
    }

    override fun getAlbum(): AlbumModel {
        return album
    }
}
