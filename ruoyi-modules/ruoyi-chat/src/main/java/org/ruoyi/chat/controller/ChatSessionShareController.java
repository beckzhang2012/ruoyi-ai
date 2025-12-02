package org.ruoyi.chat.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.chat.domain.ChatSessionShare;
import org.ruoyi.chat.service.IChatSessionShareService;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.common.satoken.utils.LoginHelper;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.domain.vo.ChatSessionVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会话分享Controller
 * 
 * @author ageerle
 * @date 2025-11-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/share")
public class ChatSessionShareController extends BaseController {

    private final IChatSessionShareService chatSessionShareService;

    /**
     * 生成会话分享链接
     * 
     * @param sessionId 会话ID
     * @param password 查看密码（可选）
     * @param expireTime 有效期（可选，格式：yyyy-MM-dd HH:mm:ss）
     * @return 分享链接
     */
    @Log(title = "会话分享", businessType = BusinessType.INSERT)
    @PostMapping("/generate")
    public R<String> generateShareLink(@NotNull(message = "会话ID不能为空") @RequestParam Long sessionId,
                                         @RequestParam(required = false) String password,
                                         @RequestParam(required = false) String expireTime) {
        String shareLink = chatSessionShareService.generateShareLink(sessionId, password, expireTime);
        return R.ok(shareLink);
    }

    /**
     * 验证分享链接并返回会话信息
     * 
     * @param shareToken 分享token
     * @param password 查看密码（可选）
     * @return 会话信息
     */
    @GetMapping("/{shareToken}")
    public R<ChatSessionVo> validateShareLink(@NotBlank(message = "分享token不能为空") @PathVariable String shareToken,
                                                  @RequestParam(required = false) String password) {
        ChatSessionVo chatSessionVo = chatSessionShareService.validateShareLink(shareToken, password);
        return R.ok(chatSessionVo);
    }

    /**
     * 取消会话分享
     * 
     * @param shareToken 分享token
     * @return 结果
     */
    @Log(title = "会话分享", businessType = BusinessType.DELETE)
    @DeleteMapping("/{shareToken}")
    public R<Void> cancelShareLink(@NotBlank(message = "分享token不能为空") @PathVariable String shareToken) {
        return toAjax(chatSessionShareService.cancelShareLink(shareToken));
    }

    /**
     * 修改会话分享设置
     * 
     * @param shareToken 分享token
     * @param password 查看密码（可选）
     * @param expireTime 有效期（可选，格式：yyyy-MM-dd HH:mm:ss）
     * @return 结果
     */
    @Log(title = "会话分享", businessType = BusinessType.UPDATE)
    @PutMapping("/{shareToken}")
    public R<Void> updateShareLink(@NotBlank(message = "分享token不能为空") @PathVariable String shareToken,
                                      @RequestParam(required = false) String password,
                                      @RequestParam(required = false) String expireTime) {
        return toAjax(chatSessionShareService.updateShareLink(shareToken, password, expireTime));
    }

    /**
     * 查询当前用户的所有分享会话
     * 
     * @return 分享会话列表
     */
    @GetMapping("/list")
    public R<List<ChatSessionShare>> listShareSessions() {
        ChatSessionShare chatSessionShare = new ChatSessionShare();
        chatSessionShare.setCreateBy(LoginHelper.getUserId());
        List<ChatSessionShare> shareSessions = chatSessionShareService.selectChatSessionShareList(chatSessionShare);
        return R.ok(shareSessions);
    }
}
