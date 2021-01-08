package edu.wuyi.fans;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.wuyi.fans.mapper.CategoryMapper;
import edu.wuyi.fans.mapper.UserMapper;
import edu.wuyi.fans.model.entity.Follow;
import edu.wuyi.fans.model.entity.Picture;
import edu.wuyi.fans.model.entity.User;
import edu.wuyi.fans.model.param.PictureESParam;
import edu.wuyi.fans.model.param.PictureParam;
import edu.wuyi.fans.service.*;
import edu.wuyi.fans.util.RedisUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class FansApplicationTests {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @Autowired
    private RoleService roleService;

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;


    @Test
    void saveToES(){
        List<Picture> list = pictureService.list();
        list.forEach(picture -> {

            IndexRequest request = new IndexRequest("fans");
            request.id(picture.getPid());
            request.timeout("10s");
            PictureESParam esParam = new PictureESParam();
            BeanUtils.copyProperties(picture,esParam);
            esParam.setCategory(categoryService.getById(picture.getCategory()).getName());
            esParam.setUsername(userService.getById(picture.getUserId()).getUsername());
            request.source(JSONUtil.parseObj(esParam).toStringPretty(), XContentType.JSON);
            try {
                client.index(request, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    void addUser(){
        Random random = new Random();
        int i1 = 0;
        Follow follow = null;
        for (int i=1; i<=300; i++){
            Format f1=new DecimalFormat("000");
            String  count =f1.format(i);
            User user = new User();
            String uid = IdUtil.simpleUUID();
            user.setUid(uid);
            user.setUsername("_test_"+ count);
            user.setPassword("10ad6291761f95c337d55633d3c9cf81");
            user.setDescription("虚假账号，仅供测试");
            user.setEmail("test_"+count+"@fans.com");
            user.setProfilePictureUrl("http://localhost:8888/profile-picture/fans.png");
            i1 = random.nextInt(3);
            if (i1 == 2){
                i1 = 4;
            }
            user.setUserStatus(i1);
            userMapper.insert(user);
            follow = new Follow();
            follow.setFanId(uid);
            follow.setFollowerId("97a85b60bc92426299acd8078485ac2b");
            followService.save(follow);

            roleService.saveUserAsSupervisor(uid);
        }
        //userMapper.insert()
    }

    @Test
    void contextLoads() {
        //categoryMapper.selectById(77);
        //User user = userMapper.selectById(135);
        //System.out.println(user);
        //Category category = new Category();
        //category.setName("hhhh1313dad1316hhh");
        //categoryMapper.insert(category);
        //userMapper.selectById("c76b07644e804408876215b786e501f6");
        userMapper.getUserByIdIgnoreStatus("c76b07644e804408876215b786e501f6");
        //User user = new User();
        //user.setUid("eaa88cd803b748189b749f298a3f7452");
        //user.setUserStatus(0);
        //userMapper.updateUserStatusById(user.getUid(),user.getUserStatus(),new Date());
    }

    @Test
    void convertTest(){
        PictureParam pictureParam = new PictureParam();
        pictureParam.setCategory(1);
        pictureParam.setSummary("jsaodfjaosfjnaj");
        pictureParam.setTitle("djsoafjo");

        Picture picture = new Picture();
        picture.setTitle("abc");
        BeanUtils.copyProperties(pictureParam,picture);
        System.out.println(picture);
    }

    @Test
    void redisTest(){
        //redisUtils.set("helo","world");
        //Category category = new Category();
        //category.setName("joo");
        //category.setCreateTime(new Date());
        //category.setId(3);
        //category.setUpdateTime(new Date());
        //redisUtils.set("category",category);
        Object category = redisUtils.get("category");

        System.out.println(category);
    }

    @Test
    void userWrapperTest(){
        QueryWrapper<User> userQuery = new QueryWrapper<>();

        userQuery.clear();
        userQuery.eq("email","2276323859@qq.com");
        userQuery.eq("user_status",1);
        User user = userMapper.selectOne(userQuery);
        System.out.println(user);
    }

}
