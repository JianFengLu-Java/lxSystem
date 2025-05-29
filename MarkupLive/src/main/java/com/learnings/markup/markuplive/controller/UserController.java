package com.learnings.markup.markuplive.controller;

import com.learnings.markup.markuplive.dto.LoginRequest;
import com.learnings.markup.markuplive.dto.RegisterRequest;
import com.learnings.markup.markuplive.entity.User;
import com.learnings.markup.markuplive.qr.QRCode;
import com.learnings.markup.markuplive.repository.UserRepository;
import com.learnings.markup.markuplive.result.Result;
import com.learnings.markup.markuplive.util.AccountGenerationUtil;
import com.learnings.markup.markuplive.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "用户API", description = "用户管理操作")
public class UserController {
    @Autowired

    private final UserRepository userRepository;//初始化仓库类

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    Logger log = Logger.getLogger(UserController.class.getName());

    @Operation(summary = "用户注册", description = "用户注册")

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody RegisterRequest registerRequest) {
        Map<String, String> res = new HashMap<>();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (registerRequest==null){
            log.warning("注册失败");
            res.put("msg", "注册失败");
            return res;
        }
        try{
            Long account = AccountGenerationUtil.getAccount();
            Boolean isAccountExist = userRepository.existsByAccount(account);
            if (isAccountExist){
                log.warning("注册失败");
                res.put("msg", "用户重复");
                return res;
            }

            userRepository.save(registerRequest.toUser(account,bCryptPasswordEncoder));

            log.info("注册成功");
            res.put("msg", "注册成功");
            res.put("status", "success");
            res.put("account", String.valueOf(account));
            res.put("trueName", registerRequest.getTrueName());
            return res;

        }catch (Exception e){
            log.warning("注册失败");
            res.put("msg", "注册失败");
            return res;
        }

    }

    @Operation(summary = "用户登陆接口",description = "用户登录")

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody LoginRequest loginRequest) {
        Map<String, String> map = new HashMap<>();
        Long account = Long.parseLong(loginRequest.getAccount());
        String password = loginRequest.getPassword();
        if(userRepository.existsByAccount(account)){
            User user = userRepository.findByAccount(account);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                // 密码匹配成功，生成JWT
                String token = JwtUtil.generateToken(user.getAccount(), user.getTrueName());
                map.put("status", "success");
                map.put("msg", "登录成功");
                map.put("token", token);
                map.put("account", String.valueOf(user.getAccount()));
                map.put("trueName", user.getTrueName());
                return map;
            } else {
                // 密码不匹配
                map.put("status", "error");
                map.put("msg", "密码错误");
            }
        } else {
            // 账号不存在
            map.put("status", "error");
            map.put("msg", "账号不存在");
        }


        return map;
    }

    /**
     * 检查token是否有效
     * @param token 前端请求该接口携带的token
     * @return
     */
    @Operation(summary = "检查token", description = "检查token是否有效")
    @Parameters({
            @io.swagger.v3.oas.annotations.Parameter(name = "Authorization", description = "Bearer token")
    })
    @PostMapping("/checkToken")
    public Map<String,String> checkToken(@RequestHeader("Authorization") String token) {
        Map<String , String> map = new HashMap<>();
        return map;
    }
    @Operation(summary = "获取随机账号",description = "测试端口")
    @GetMapping("/getAccountTest")
    public Map<String,Long> getAccountTest() {
        Map<String, Long> map = new HashMap<>();
        map.put("account", AccountGenerationUtil.getAccount());
        return map;
    }

    @GetMapping("/getUserAvatar")
    public Map<String, String> getUserAvatar(@RequestParam("account") Long account) {
        Map<String, String> map = new HashMap<>();
        User user = userRepository.findByAccount(account);
        if (user == null) {
            map.put("msg", "用户不存在");
            return map;
        }
        map.put("status", "success");
        map.put("msg", "获取成功");
        map.put("avatarUrl", user.getAvatarUrl());
        return map;
    }


}
