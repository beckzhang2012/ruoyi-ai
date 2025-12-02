package org.ruoyi.controller;

import org.ruoyi.domain.bo.ChatSessionShareBo;
import org.ruoyi.domain.vo.ChatSessionShareVo;
import org.ruoyi.service.IChatSessionShareService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会话分享Controller
 * 
 * @author ruoyi
 * @date 2025-10-20
 */
@RestController
@RequestMapping("/chat/share")
public class ChatSessionShareController {
    @Autowired
    private IChatSessionShareService chatSessionShareService;

    /**
     * 查询会话分享列表
     * 
     * @param page 分页参数
     * @param chatSessionShareBo 会话分享业务对象
     * @return 会话分享列表
     */
    @GetMapping("/list")
    public Page<ChatSessionShareVo> list(Page<ChatSessionShareVo> page, ChatSessionShareBo chatSessionShareBo) {
        return chatSessionShareService.selectChatSessionSharePage(page, chatSessionShareBo);
    }

    /**
     * 查询会话分享信息
     * 
     * @param id 会话分享ID
     * @return 会话分享信息
     */
    @GetMapping("/info/{id}")
    public ChatSessionShareVo info(@PathVariable("id") Long id) {
        return chatSessionShareService.selectChatSessionShareById(id);
    }

    /**
     * 根据分享码查询会话分享信息
     * 
     * @param shareCode 分享码
     * @return 会话分享信息
     */
    @GetMapping("/getByCode/{shareCode}")
    public ChatSessionShareVo getByCode(@PathVariable("shareCode") String shareCode) {
        return chatSessionShareService.selectChatSessionShareByCode(shareCode);
    }

    /**
     * 创建会话分享
     * 
     * @param chatSessionShareBo 会话分享业务对象
     * @return 分享链接
     */
    @PostMapping("/create")
    public String create(@Validated @RequestBody ChatSessionShareBo chatSessionShareBo) {
        return chatSessionShareService.createChatSessionShare(chatSessionShareBo);
    }

    /**
     * 更新会话分享
     * 
     * @param chatSessionShareBo 会话分享业务对象
     * @return 结果
     */
    @PutMapping("/update")
    public boolean update(@Validated @RequestBody ChatSessionShareBo chatSessionShareBo) {
        return chatSessionShareService.updateChatSessionShare(chatSessionShareBo);
    }

    /**
     * 取消会话分享
     * 
     * @param id 会话分享ID
     * @return 结果
     */
    @PutMapping("/cancel/{id}")
    public boolean cancel(@PathVariable("id") Long id) {
        return chatSessionShareService.cancelChatSessionShare(id);
    }

    /**
     * 删除会话分享
     * 
     * @param ids 会话分享ID数组
     * @return 结果
     */
    @DeleteMapping("/delete/{ids}")
    public boolean delete(@PathVariable("ids") Long[] ids) {
        return chatSessionShareService.deleteChatSessionShareByIds(ids);
    }

    /**
     * 验证分享密码
     * 
     * @param shareCode 分享码
     * @param password 密码
     * @return 验证结果
     */
    @PostMapping("/verifyPassword")
    public boolean verifyPassword(@RequestParam("shareCode") String shareCode, @RequestParam("password") String password) {
        return chatSessionShareService.verifySharePassword(shareCode, password);
    }

    /**
     * 增加查看次数
     * 
     * @param id 会话分享ID
     * @return 结果
     */
    @PutMapping("/incrementViewCount/{id}")
    public boolean incrementViewCount(@PathVariable("id") Long id) {
        return chatSessionShareService.incrementViewCount(id);
    }
}
