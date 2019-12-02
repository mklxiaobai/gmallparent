package com.mkl.gmall.pms;

import com.mkl.gmall.pms.entity.Brand;
import com.mkl.gmall.pms.entity.Product;
import com.mkl.gmall.pms.service.BrandService;
import com.mkl.gmall.pms.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallPmsApplicationTests {
    @Autowired
    ProductService productService;

    @Autowired
    BrandService brandService;

    @Autowired
    RedisTemplate redisTemplate;
    @Test
    public void contextLoads() {
        Product byId = productService.getById(1);
        System.out.println(byId.getBrandName());
    }
    @Test
    public void saveBrand() {
        Brand brand=new Brand();
        brand.setName("mkl");
        brandService.save(brand);
        System.out.println("baocun ");
    }
    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("hello","mkl");
        System.out.println(redisTemplate.opsForValue().get("hello"));

    }
}
