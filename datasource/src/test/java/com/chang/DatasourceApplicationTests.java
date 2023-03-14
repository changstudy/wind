package com.chang;

import com.chang.activity.dao.ActivityMapper;
import com.chang.configuration.ExecutorsConfig;
import com.chang.configuration.SpringContext;
import com.chang.executors.ThreadPoolDetailInfo;
import com.chang.executors.ThreadPoolMonitorExecutor;
import com.chang.user.dao.UserMapper;
import com.chang.user.service.UserService;
import com.chang.user.service.impl.UserServiceImpl;
import com.chang.utils.SignBean;
import com.chang.utils.SignService;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;


@SpringBootTest
class DatasourceApplicationTests {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        //PageHelper.startPage(1, 5);
        //List<UserBean> beans = userMapper.selectList();
        //PageInfo<UserBean> pageInfo = new PageInfo<>(beans);
        //System.out.println(pageInfo);
        System.out.println(userService);
        UserServiceImpl userServiceImpl = SpringContext.getBean("userServiceImpl", UserServiceImpl.class);
        System.out.println(userServiceImpl);
        System.out.println(SpringContext.getBean("userServiceImpl"));

        //System.out.println(SpringContext.getBean("userService", UserService.class));
    }

    @Autowired
    private SignService signService;
    @Autowired
    public RedisTemplate redisService;
    @Test
    public void bitMapTest(){
        //设置001用户11.16-25号签到
        for(int i=10;i<20;i++){
           redisService.opsForValue().setBit("UserId:Sign:001:2022-11",i,true);
        }
        System.out.println("001用户今日是否已签到:"+this.signService.checkSign("001"));
        LinkedHashMap<String, Boolean> linkedHashMap = this.signService.querySignedInMonth("001");
        System.out.println("本月签到情况:");
        for (Map.Entry<String, Boolean> booleanEntry : linkedHashMap.entrySet()) {
            String p = booleanEntry.getValue() ? "√" : "X";
            System.out.println(booleanEntry.getKey() + ": " + p);
        }
        long countSignedInDayOfMonth = this.signService.countSignedInDayOfMonth("001");
        System.out.println("本月一共签到:"+countSignedInDayOfMonth+"天");
        System.out.println("目前连续签到:"+ this.signService.queryContinuousSignCount("001",7)+"天");
    }



    @Test
    public void dateTest() throws InterruptedException {
        ExecutorService monitorExecutor = ExecutorsConfig.monitorThreadPool();
        for (int i = 0; i < 240; i++) {
            try {
                monitorExecutor.execute(new Runnable() {
                    @SneakyThrows
                    @Override
                    public void run() {
                        int anInt = new Random().nextInt(20);
                        TimeUnit.SECONDS.sleep(anInt);
                    }
                });
            }catch (Exception e){
                if (e instanceof RejectedExecutionException){
                    System.out.println("任务被拒绝了");
                }
            }
        }
        TimeUnit.SECONDS.sleep(5);
        ThreadPoolDetailInfo threadPoolDetailInfo = ExecutorsConfig.threadPoolInfo(ExecutorsConfig.MONITOR_THREAD_POOL_NAME);
        System.out.println(threadPoolDetailInfo);
    }


    @Test
    public void ioTest(){


    }
}
