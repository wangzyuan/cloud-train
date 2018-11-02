/**  
 * All rights Reserved, Designed By Suixingpay.
 * @author: renjinhao 
 * @date: 2018年10月22日 下午6:11:32   
 * @Copyright ©2018 Suixingpay. All rights reserved. 
 * 注意：本内容仅限于随行付支付有限公司内部传阅，禁止外泄以及用于其他的商业用途。
 */
package com.suixingpay.cloud.demo.consumer.client;

import com.springboot.app.common.domain.Student;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import com.suixingpay.cloud.demo.consumer.client.ProviderClient.FallbackConfiguration;
import com.suixingpay.cloud.demo.consumer.client.ProviderClient.TestFallback;

import javax.ws.rs.GET;

/**
 * 
 * @author: renjinhao
 * @date: 2018年10月22日 下午6:11:32
 * @version: V1.0
 * @review: renjinhao/2018年10月22日 下午6:11:32
 */
//, fallback = TestFallback.class, configuration = FallbackConfiguration.class
@FeignClient(name = "cloud-demo-provider")
@RequestMapping("/student")
public interface ProviderClient {

    @GetMapping()
    public String list();
    @PostMapping()
    public int insert(@RequestBody Student student);
    @DeleteMapping
    public int  deleteById(@RequestParam("id") int id);
    @PutMapping
    public int  updateById(@RequestBody Student student);

    public static class TestFallback implements ProviderClient {

        @Override
        public String list() {
            return "errrrrror";
        }

        @Override
        public int insert(Student student) {
            return 0;
        }

        @Override
        public int deleteById(int id) {
            return 0;
        }

        @Override
        public int updateById(Student student) {
            return 0;
        }


    }

    public static class FallbackConfiguration {

        @Bean
        public TestFallback fallbackFactory() {
            return new TestFallback();
        }
    }
}
