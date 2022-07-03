package studio.mandysa.music.logic.model

import mandysax.anna2.annotation.Path
import mandysax.anna2.annotation.Value

/**
 * @author 黄浩
 */
class UserPlaylist {
    @Value("id")
    lateinit var id: String

    @Value("name")
    lateinit var name: String

    @Value("nickname")
    @Path("creator")
    lateinit var nickname: String

    @Value("coverImgUrl")
    lateinit var coverImgUrl: String

    @Value("signature")
    lateinit var signature: String
}