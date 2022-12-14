package studio.mandysa.music.logic.bean

import mandysax.anna2.annotation.Path
import mandysax.anna2.annotation.Value

class AlbumContentBean {
    @Value("songs")
    lateinit var songList: List<MusicBean>

    @Value("picUrl")
    @Path("album")
    lateinit var picUrl: String

    @Path("album/artist")
    @Value("name")
    lateinit var artistName: String

    @Value("company")
    @Path("album")
    lateinit var company: String

    @Value("description")
    @Path("album")
    lateinit var description: String

    @Value("name")
    @Path("album")
    lateinit var name: String

    @Value("id")
    @Path("album")
    lateinit var id: String

    @Value("publishTime")
    @Path("album")
    lateinit var publishTime: String
}