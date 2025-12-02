package org.ruoyi.chat.controller;

import jakarta.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ruoyi.chat.domain.entity.ChatMessageFavorite;
import org.ruoyi.chat.domain.dto.ChatMessageFavoriteDTO;
import org.ruoyi.chat.service.IChatMessageFavoriteService;
import org.ruoyi.common.core.controller.BaseController;
import org.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 聊天消息收藏Controller
 *
 * @author ruoyi
 * @date 2025-12-01
 */
@RestController
@RequestMapping("/api/chat/favorite")
public class ChatMessageFavoriteController extends BaseController {

    @Autowired
    private IChatMessageFavoriteService chatMessageFavoriteService;

    /**
     * 添加消息收藏
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite')")
    @PostMapping
    public R<ChatMessageFavorite> addFavorite(@Valid @RequestBody ChatMessageFavoriteDTO favoriteDTO) {
        ChatMessageFavorite favorite = chatMessageFavoriteService.addFavorite(favoriteDTO);
        return R.ok(favorite);
    }

    /**
     * 取消消息收藏
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite')")
    @DeleteMapping("/{messageId}")
    public R<Void> cancelFavorite(@PathVariable Long messageId) {
        chatMessageFavoriteService.cancelFavorite(messageId);
        return R.ok();
    }

    /**
     * 查询我的收藏列表（分页）
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite')")
    @GetMapping("/page")
    public R<IPage<ChatMessageFavorite>> getMyFavoritePage(@RequestParam(defaultValue = "1") Long pageNum,
                                                           @RequestParam(defaultValue = "10") Long pageSize,
                                                           @RequestParam(required = false) Long sessionId,
                                                           @RequestParam(required = false) String modelName,
                                                           @RequestParam(required = false) String searchKey) {
        Page<ChatMessageFavorite> page = new Page<>(pageNum, pageSize);
        IPage<ChatMessageFavorite> result = chatMessageFavoriteService.getMyFavoritePage(page, sessionId, modelName, searchKey);
        return R.ok(result);
    }

    /**
     * 判断消息是否已收藏
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite')")
    @GetMapping("/isFavorite/{messageId}")
    public R<Boolean> isFavorite(@PathVariable Long messageId) {
        boolean favorite = chatMessageFavoriteService.isFavorite(messageId);
        return R.ok(favorite);
    }

    /**
     * 更新收藏备注
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite')")
    @PutMapping("/remark/{id}")
    public R<Void> updateRemark(@PathVariable Long id, @RequestParam String remark) {
        chatMessageFavoriteService.updateRemark(id, remark);
        return R.ok();
    }
}
