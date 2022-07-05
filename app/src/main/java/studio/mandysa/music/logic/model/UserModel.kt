package studio.mandysa.music.logic.model

import mandysax.anna2.annotation.Value
import java.io.Serializable

/**
 * @author 黄浩
 */
class UserModel : Serializable {
    @Value("userId")
    lateinit var userId: String

    @Value("nickname")
    lateinit var nickname: String

    @Value("avatarUrl")
    lateinit var avatarUrl: String

    @Value("signature")
    lateinit var signature: String

    @Value("createTime")
    lateinit var createTime: String
}