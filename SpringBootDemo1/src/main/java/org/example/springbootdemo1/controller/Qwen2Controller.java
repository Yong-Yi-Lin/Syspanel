package org.example.springbootdemo1.controller;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Result;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.example.springbootdemo1.http.Results;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/qwen2")
public class Qwen2Controller {

    private final Generation generation = new Generation();
    private final List<Message> messages = new ArrayList<>();

    public Qwen2Controller() {
        // 初始化系统消息
        Message systemMsg = Message.builder().role(Role.SYSTEM.getValue()).content("You are a helpful assistant.").build();
        messages.add(systemMsg);
    }

    @PostMapping("/message")
    public Results handleMessage(@RequestBody String userInput) throws ApiException, NoApiKeyException, InputRequiredException {
        System.out.println(userInput);
        Results results = null;
        if ("exit".equals(userInput)) {
            // 这里可以实现退出逻辑，例如清除对话历史
            messages.clear();
            results = Results.fail("Exit conversation.");
            return  results;
//            return "Exit conversation.";
        }

        Message userMsg = Message.builder().role(Role.USER.getValue()).content(userInput).build();
        if (messages.size() == 7) {
            messages.remove(0);
        }
        messages.add(userMsg);

        GenerationParam param = GenerationParam.builder()
                .model("qwen-turbo")
                .messages(messages)
                .apiKey("sk-7d3eda7cc42a46ec8935628dce24003e") // 应该从配置文件中读取，而不是硬编码
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .topP(0.8)
                .build();

        GenerationResult result = generation.call(param);

        if (messages.size() == 7) {
            messages.remove(0);
        }
        messages.add(result.getOutput().getChoices().get(0).getMessage());

        String answer = result.getOutput().getChoices().get(0).getMessage().getContent();
        results = Results.success("Ai回答",answer);
        System.out.println(result.getOutput().getChoices().get(0).getMessage().getContent());
        return results;
//        return result.getOutput().getChoices().get(0).getMessage().getContent();
    }
}
