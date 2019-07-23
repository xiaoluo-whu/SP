package com.bnq.supplier.sp;

//import io.shardingsphere.shardingjdbc.spring.boot.SpringBootConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.bnq.supplier"})
public class SupplierPortalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SupplierPortalServiceApplication.class, args);
    }
}
