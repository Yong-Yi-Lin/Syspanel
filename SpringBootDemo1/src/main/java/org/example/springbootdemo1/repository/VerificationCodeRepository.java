package org.example.springbootdemo1.repository;

import org.example.springbootdemo1.pojo.VerificationCode;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 使用@Repository注解来标识这是一个Spring Data JPA仓库
@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    // 这里可以定义额外的查询方法，如果需要的话
    // 例如，你可以通过email查找验证码
    VerificationCode findByEmail(String email);
    // 检查是否存在具有给定email的验证码
    boolean existsByEmail(String email);
    // 通过email删除验证码，并返回受影响的记录数
}