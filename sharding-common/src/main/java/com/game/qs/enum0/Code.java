package com.game.qs.enum0;

/**
 * Created by zun.wei on 2018/8/3 11:33.
 * Description: 错代码
 */
public enum Code {

    // 成功
    ERROR_0(0, "success"),

    // 手机号码为空
    ERROR_1(-1, "phone number is blank"),

    // 邮箱号码为空
    ERROR_2(-2, "email number is blank"),

    // 手机号码无效
    ERROR_3(-3, "phone is invalid"),

    // 邮箱号码无效
    ERROR_4(-4, "email is invalid"),

    // 手机号码已经注册
    ERROR_5(-5, "phone had register"),

    // 邮件已经发送
    ERROR_6(-6, "email had send"),

    // 验证码无效
    ERROR_7(-7, "check code invalid"),

    // 验证码为空
    ERROR_8(-8, "check code is blank"),

    // 验证码不存在
    ERROR_9(-9, "check code not exist"),

    // 插入members 表失败，可能是号码已经注册
    ERROR_10(-10, "insert members table fail,maybe tel had register"),

    // 密码为空
    ERROR_11(-11, "passwd is blank"),

    // 密码长度过短或过长
    ERROR_12(-12, "passwd is too short or too more"),

    // 密码和确认密码不一致
    ERROR_13(-13, "passwd and confirmPw not equals"),

    // 插入members_phone 表失败，可能是号码已经注册
    ERROR_14(-14, "insert members phone table fail,maybe phone had register"),

    // 手机号码未注册或密码错误
    ERROR_15(-15, "phone not register or pw err"),

    // 插入角色成员表失败
    ERROR_16(-16, "insert t_member_roles table fail, maybe had insert"),

    // 不允许授权成为超级管理员
    ERROR_17(-17, "Do not allow authorization to become a super administrator"),

    // 只有超级管理员才能授权管理员角色
    ERROR_18(-18, "Only super administrators can authorize administrator roles"),

    // 如果不是超级管理员并且不是管理员则不允许授权角色
    ERROR_19(-19, "Authorization roles are not allowed if they are not super administrators and not administrators"),

    // 该用户已拥有该角色
    ERROR_20(-20, "The user already has the role"),

    // 只有超级管理员才能修改管理员角色
    ERROR_21(-21, "Only super administrators can modify administrator roles"),

    // 成员只剩一个角色了，不允许删除
    ERROR_22(-22, "Members have only one role left and are not allowed to delete"),

    // accessKey 或者 name 参数为空
    ERROR_23(-23, "accessKey or name parameter is null or empty string"),

    // 请求人不是游戏商户
    ERROR_24(-24, "request mid not game partner !"),

    // 游戏类型不存在
    ERROR_25(-25, "game type not exist!"),

    // 修改的游戏类型不属于请求人的
    ERROR_26(-26, "The modified game type does not belong to the requester"),

    // 登出失败
    ERROR_27(-27, "logout fail"),

    ERROR_28(-28, ""),
    ERROR_29(-29, ""),
    ERROR_30(-30, ""),
    ERROR_31(-31, ""),
    ERROR_32(-32, ""),
    ERROR_33(-33, ""),
    ERROR_34(-34, ""),
    ERROR_35(-35, ""),
    ERROR_36(-36, ""),
    ERROR_37(-37, ""),
    ERROR_38(-38, ""),
    ERROR_39(-39, ""),
    ERROR_40(-40, ""),
    ERROR_41(-41, ""),
    ERROR_42(-42, ""),
    ERROR_43(-43, ""),
    ERROR_44(-44, ""),
    ERROR_45(-45, ""),
    ERROR_46(-46, ""),
    ERROR_47(-47, ""),
    ERROR_48(-48, ""),
    ERROR_49(-49, ""),
    ERROR_50(-50, ""),
    ERROR_51(-51, ""),
    ERROR_52(-52, ""),
    ERROR_53(-53, ""),
    ERROR_54(-54, ""),
    ERROR_55(-55, ""),
    ERROR_56(-56, ""),
    ERROR_57(-57, ""),
    ERROR_58(-58, ""),
    ERROR_59(-59, ""),
    ERROR_60(-60, ""),
    ERROR_61(-61, ""),
    ERROR_62(-62, ""),
    ERROR_63(-63, ""),
    ERROR_64(-64, ""),
    ERROR_65(-65, ""),
    ERROR_66(-66, ""),
    ERROR_67(-67, ""),
    ERROR_68(-68, ""),
    ERROR_69(-69, ""),
    ERROR_70(-70, ""),
    ERROR_71(-71, ""),
    ERROR_72(-72, ""),
    ERROR_73(-73, ""),
    ERROR_74(-74, ""),
    ERROR_75(-75, ""),
    ERROR_76(-76, ""),
    ERROR_77(-77, ""),
    ERROR_78(-78, ""),
    ERROR_79(-79, ""),
    ERROR_80(-80, ""),
    ERROR_81(-81, ""),
    ERROR_82(-82, ""),
    ERROR_83(-83, ""),
    ERROR_84(-84, ""),
    ERROR_85(-85, ""),
    ERROR_86(-86, ""),
    ERROR_87(-87, ""),
    ERROR_88(-88, ""),
    ERROR_89(-89, ""),
    ERROR_90(-90, ""),
    ERROR_91(-91, ""),
    ERROR_92(-92, ""),
    ERROR_93(-93, ""),
    ERROR_94(-94, ""),
    ERROR_95(-95, ""),
    ERROR_96(-96, ""),
    ERROR_97(-97, ""),
    ERROR_98(-98, ""),
    ERROR_99(-99, ""),
    ERROR_100(-100, ""),
    ERROR_101(-101, ""),
    ERROR_102(-102, ""),
    ERROR_103(-103, ""),
    ERROR_104(-104, ""),
    ERROR_105(-105, ""),
    ERROR_106(-106, ""),
    ERROR_107(-107, ""),
    ERROR_108(-108, ""),
    ERROR_109(-109, ""),
    ERROR_110(-110, ""),
    ERROR_111(-111, ""),
    ERROR_112(-112, ""),
    ERROR_113(-113, ""),
    ERROR_114(-114, ""),
    ERROR_115(-115, ""),
    ERROR_116(-116, ""),
    ERROR_117(-117, ""),
    ERROR_118(-118, ""),
    ERROR_119(-119, ""),
    ERROR_120(-120, ""),
    ERROR_121(-121, ""),
    ERROR_122(-122, ""),
    ERROR_123(-123, ""),
    ERROR_124(-124, ""),
    ERROR_125(-125, ""),
    ERROR_126(-126, ""),
    ERROR_127(-127, ""),
    ERROR_128(-128, ""),
    ERROR_129(-129, ""),
    ERROR_130(-130, ""),
    ERROR_131(-131, ""),
    ERROR_132(-132, ""),
    ERROR_133(-133, ""),
    ERROR_134(-134, ""),
    ERROR_135(-135, ""),
    ERROR_136(-136, ""),
    ERROR_137(-137, ""),
    ERROR_138(-138, ""),
    ERROR_139(-139, ""),
    ERROR_140(-140, ""),
    ERROR_141(-141, ""),
    ERROR_142(-142, ""),
    ERROR_143(-143, ""),
    ERROR_144(-144, ""),
    ERROR_145(-145, ""),
    ERROR_146(-146, ""),
    ERROR_147(-147, ""),
    ERROR_148(-148, ""),
    ERROR_149(-149, ""),
    ERROR_150(-150, ""),
    ERROR_151(-151, ""),
    ERROR_152(-152, ""),
    ERROR_153(-153, ""),
    ERROR_154(-154, ""),
    ERROR_155(-155, ""),
    ERROR_156(-156, ""),
    ERROR_157(-157, ""),
    ERROR_158(-158, ""),
    ERROR_159(-159, ""),
    ERROR_160(-160, ""),
    ERROR_161(-161, ""),
    ERROR_162(-162, ""),
    ERROR_163(-163, ""),
    ERROR_164(-164, ""),
    ERROR_165(-165, ""),
    ERROR_166(-166, ""),
    ERROR_167(-167, ""),
    ERROR_168(-168, ""),
    ERROR_169(-169, ""),
    ERROR_170(-170, ""),
    ERROR_171(-171, ""),
    ERROR_172(-172, ""),
    ERROR_173(-173, ""),
    ERROR_174(-174, ""),
    ERROR_175(-175, ""),
    ERROR_176(-176, ""),
    ERROR_177(-177, ""),
    ERROR_178(-178, ""),
    ERROR_179(-179, ""),
    ERROR_180(-180, ""),
    ERROR_181(-181, ""),
    ERROR_182(-182, ""),
    ERROR_183(-183, ""),
    ERROR_184(-184, ""),
    ERROR_185(-185, ""),
    ERROR_186(-186, ""),
    ERROR_187(-187, ""),
    ERROR_188(-188, ""),
    ERROR_189(-189, ""),
    ERROR_190(-190, ""),
    ERROR_191(-191, ""),
    ERROR_192(-192, ""),
    ERROR_193(-193, ""),
    ERROR_194(-194, ""),
    ERROR_195(-195, ""),
    ERROR_196(-196, ""),
    ERROR_197(-197, ""),
    ERROR_198(-198, ""),
    ERROR_199(-199, ""),
    ERROR_200(-200, "server busy"),
    //SUCCESS(200,""),//成功

    FAIL(-400, "bad request"),//失败
    UNAUTHORIZED(-401, "un certified request or se"),//未认证（签名错误）
    AUTHORIZED_EXP(-402, "certified exp"),//token已过期
    ACCESS_DENIED(-403, "access denied"),//拒绝访问
    NOT_FOUND(-404, "not fount"),//接口不存在
    UN_KNOW_REQ(-405, "unKnow req mid"),//未知请求人
    BAD_TOKEN(-406, "bad token"),//无效的token
    NOT_TOKEN(-407, "not token"),//没有token
    NOT_TOKEN_IN_REDIS(-408, "not token in redis"),//redis 中没有token
    REQ_TOKEN_NOT_EQUALS_REDIS_TOKEN(-409, "request token not equals redisToken"),//请求的token 与redis中的缓存不一致
    INTERNAL_SERVER_ERROR(-500, "server err"),//服务器内部错误
    ERROR_1000(-1000, "server err"),
    ;

    public int err;
    public String msg;

    Code(int err, String msg) {
        this.err = err;
        this.msg = msg;
    }

    /*public static void main(String[] args) {
        for (int i = 0; i < 201; i++) {
            System.out.println("ERROR_" + i + "(" + -i + ",\"\"),");
        }
    }*/

}
