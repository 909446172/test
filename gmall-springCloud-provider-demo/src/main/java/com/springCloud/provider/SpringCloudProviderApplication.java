package com.springCloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient      //provider 提供者在使用这个注解时会将自己的接口全都注册在eureka中,对于provider而言不用做出改变
//consumer 要加上
@EnableFeignClients  //表示自己是一个feign的客户端;   在common项目中 使用 @Feing

    //作用于Common中
//@FeignClient(value="ATGUIGU-SCMS-DEPT")     指向具体的服务名;
//public interface DeptClientService {
//
//    @RequestMapping(value="/dept/add", method= RequestMethod.POST)  //和具体的请求对应; 由一个代理类来重写这些接口
//    public boolean add(Dept dept);
//
//    @RequestMapping(value="/dept/get/{deptNo}", method=RequestMethod.GET)
//    public Dept get(@PathVariable("deptNo") Integer deptNo);
//
//    @RequestMapping(value="/dept/get/all", method=RequestMethod.GET)
//    public List<Dept> getAll();
//
//}

public class SpringCloudProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudProviderApplication.class, args);


    }

}
