package icu.chiou.qrbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("icu.chiou.qrbac.mapper")
public class QrbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrbacApplication.class, args);
    }

}
