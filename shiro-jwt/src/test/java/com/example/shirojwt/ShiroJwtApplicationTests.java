package com.example.shirojwt;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
class ShiroJwtApplicationTests {

//    private SecurityManager securityManager;

//    @Before
//    public void init() {
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        SecurityManager instance = factory.getInstance();
//        SecurityUtils.setSecurityManager(instance);
//    }

    @Test
    void testLogin() {
        System.out.println(1);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("mrbird", "123456");
        subject.login(token);
        System.out.println(subject.isPermitted("user:add"));

    }

  /*  @Autowired
    private UserPermissionDao userPermissionDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

//    @BeforeAll
//    static void setupMockMvc() {
//        System.out.println("dj");
////        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }

    @Test
    @Transactional
    void contextLoads() throws Exception {
        //GET
//        mockMvc.perform(MockMvcRequestBuilders.get("/hello?name={name}", "mrbird"));
        //POST
//        mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}", 1));

        String json = "{\"id\":\"006\",\"name\":\"mrbird\",\"password\":\"123456789\",\"createTime\":\"2019-12-11\",\"status\":\"true\",\"age\":12}";
        User user = mapper.readValue(json, User.class);
        System.out.println(user);
    }


    @Autowired
    private UserService userService;

    @Test
    void test1(){
        System.out.println(userService.queryUserByName("tester"));
    }*/

}
