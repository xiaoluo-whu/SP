package com.bnq.supplier.sp;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
//import io.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.bnq.supplier"})
public class SupplierPortalWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplierPortalWebApplication.class, args);
    }
}
