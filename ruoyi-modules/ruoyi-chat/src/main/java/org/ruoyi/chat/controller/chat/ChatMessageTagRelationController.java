package org.ruoyi.chat.controller.chat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.chat.service.IChatMessageTagRelationService;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 聊天消息标签关系
 *
 * @author ruoyi
 * @date 2025-11-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/message/tag/relation")
public class ChatMessageTagRelationController extends BaseController {

    private final IChatMessageTagRelationService chatMessageTagRelationService;

    /**
     * 为消息添加标签
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     */
    @PostMapping("/add/{messageId}/{tagId}")
    public R<Boolean> addTagToMessage(@NotNull(message = "消息ID不能为空")
                                         @PathVariable Long messageId,
                                         @NotNull(message = "标签ID不能为空")
                                         @PathVariable Long tagId) {
        return R.ok(chatMessageTagRelationService.addTagToMessage(messageId, tagId));
    }

    /**
     * 为消息移除标签
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     */
    @DeleteMapping("/remove/{messageId}/{tagId}")
    public R<Boolean> removeTagFromMessage(@NotNull(message = "消息ID不能为空")
                                               @PathVariable Long messageId,
                                               @NotNull(message = "标签ID不能为空")
                                               @PathVariable Long tagId) {
        return R.ok(chatMessageTagRelationService.removeTagFromMessage(messageId, tagId));
    }

    /**
     * 为消息设置标签（替换现有标签）
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID列表
     */
    @PutMapping("/set/{messageId}")
    public R<Boolean> setTagsForMessage(@NotNull(message = "消息ID不能为空")
                                           @PathVariable Long messageId,
                                           @NotEmpty(message = "标签ID列表不能为空")
                                           @RequestBody List<Long> tagIds) {
        return R.ok(chatMessageTagRelationService.setTagsForMessage(messageId, tagIds));
    }

    /**
     * 查询消息的所有标签
     *
     * @param messageId 消息ID
     */
    @GetMapping("/getTagsByMessageId/{messageId}")
    public R<List<ChatMessageTag>> getTagsByMessageId(@NotNull(message = "消息ID不能为空")
                                                           @PathVariable Long messageId) {
        return R.ok(chatMessageTagRelationService.getTagsByMessageId(messageId));
    }

    /**
     * 查询标签的所有消息ID
     *
     * @param tagId 标签ID
     */
    @GetMapping("/getMessageIdsByTagId/{tagId}")
    public R<List<Long>> getMessageIdsByTagId(@NotNull(message = "标签ID不能为空")
                                                 @PathVariable Long tagId) {
        return R.ok(chatMessageTagRelationService.getMessageIdsByTagId(tagId));
    }

    /**
     * 查询消息是否有指定标签
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     */
    @GetMapping("/hasTag/{messageId}/{tagId}")
    public R<Boolean> hasTag(@NotNull(message = "消息ID不能为空")
                               @PathVariable Long messageId,
                               @NotNull(message = "标签ID不能为空")
                               @PathVariable Long tagId) {
        return R.ok(chatMessageTagRelationService.hasTag(messageId, tagId));
    }

}
