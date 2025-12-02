package org.ruoyi.chat.controller;

import jakarta.validation.Valid;
import org.ruoyi.chat.domain.entity.ChatSessionShare;
import org.ruoyi.chat.domain.dto.ChatSessionShareDTO;
import org.ruoyi.chat.service.IChatSessionShareService;
import org.ruoyi.common.core.controller.BaseController;
import org.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会话分享Controller
 *
 * @author ruoyi
 * @date 2025-12-01
 */
@RestController
@RequestMapping("/api/chat/share")
public class ChatSessionShareController extends BaseController {

    @Autowired
    private IChatSessionShareService chatSessionShareService;

    /**
     * 创建会话分享
     */
    @PreAuthorize("@ss.hasPermi('chat:session:share')")
    @PostMapping
    public R<ChatSessionShare> createShare(@Valid @RequestBody ChatSessionShareDTO shareDTO) {
        ChatSessionShare share = chatSessionShareService.createShare(shareDTO);
        return R.ok(share);
    }

    /**
     * 验证分享密码
     */
    @PostMapping("/verify/{shareCode}")
    public R<Boolean> verifyPassword(@PathVariable String shareCode, @RequestParam String password) {
        boolean valid = chatSessionShareService.verifyPassword(shareCode, password);
        return R.ok(valid);
    }

    /**
     * 获取分享信息
     */
    @GetMapping("/{shareCode}")
    public R<ChatSessionShare> getShareInfo(@PathVariable String shareCode) {
        if (!chatSessionShareService.isShareValid(shareCode)) {
            return R.fail("分享不存在或已过期失效");
        }
        ChatSessionShare share = chatSessionShareService.getShareInfo(shareCode);
        return R.ok(share);
    }

    /**
     * 增加查看次数
     */
    @PostMapping("/view/{shareCode}")
    public R<Void> incrementViewCount(@PathVariable String shareCode) {
        chatSessionShareService.incrementViewCount(shareCode);
        return R.ok();
    }

    /**
     * 查询我的分享列表
     */
    @PreAuthorize("@ss.hasPermi('chat:session:list')")
    @GetMapping("/my")
    public R<List<ChatSessionShare>> selectMyShareList() {
        List<ChatSessionShare> list = chatSessionShareService.selectMyShareList();
        return R.ok(list);
    }

    /**
     * 取消分享
     */
    @PreAuthorize("@ss.hasPermi('chat:session:share')")
    @PutMapping("/cancel/{id}")
    public R<Void> cancelShare(@PathVariable Long id) {
        chatSessionShareService.cancelShare(id);
        return R.ok();
    }

    /**
     * 更新分享设置
     */
    @PreAuthorize("@ss.hasPermi('chat:session:share')")
    @PutMapping
    public R<Void> updateShare(@RequestBody ChatSessionShare share) {
        chatSessionShareService.updateShare(share);
        return R.ok();
    }
}
