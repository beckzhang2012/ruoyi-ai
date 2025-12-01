package org.ruoyi.chat.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ruoyi.chat.domain.dto.ChatMessageTagDTO;
import org.ruoyi.chat.domain.dto.MessageTagRelationDTO;
import org.ruoyi.chat.domain.entity.ChatMessageTag;
import org.ruoyi.chat.service.IChatMessageTagService;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.common.core.domain.R;

import java.util.List;

/**
 * 聊天消息标签Controller
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@RestController
@RequestMapping("/chat/tag")
public class ChatMessageTagController extends BaseController {

    private final IChatMessageTagService chatMessageTagService;

    public ChatMessageTagController(IChatMessageTagService chatMessageTagService) {
        this.chatMessageTagService = chatMessageTagService;
    }

    /**
     * 获取当前用户的所有标签
     */
    @GetMapping("/list")
    public R<List<ChatMessageTag>> list() {
        List<ChatMessageTag> tags = chatMessageTagService.selectUserTags();
        return R.ok(tags);
    }

    /**
     * 获取标签详细信息
     */
    @GetMapping(value = "/{id}")
    public R<ChatMessageTag> getInfo(@PathVariable Long id) {
        ChatMessageTag tag = chatMessageTagService.selectChatMessageTagById(id);
        return R.ok(tag);
    }

    /**
     * 新增标签
     */
    @PostMapping
    public R<Void> add(@Validated @RequestBody ChatMessageTagDTO chatMessageTagDTO) {
        int result = chatMessageTagService.insertChatMessageTag(chatMessageTagDTO);
        return toAjax(result);
    }

    /**
     * 修改标签
     */
    @PutMapping
    public R<Void> edit(@Validated @RequestBody ChatMessageTagDTO chatMessageTagDTO) {
        int result = chatMessageTagService.updateChatMessageTag(chatMessageTagDTO);
        return toAjax(result);
    }

    /**
     * 删除标签
     */
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        int result = chatMessageTagService.deleteChatMessageTagByIds(ids);
        return toAjax(result);
    }

    /**
     * 给消息添加标签
     */
    @PostMapping("/addMessageTags")
    public R<Void> addMessageTags(@Validated @RequestBody MessageTagRelationDTO messageTagRelationDTO) {
        int result = chatMessageTagService.addMessageTags(messageTagRelationDTO);
        return toAjax(result);
    }

    /**
     * 移除消息的标签
     */
    @PostMapping("/removeMessageTags")
    public R<Void> removeMessageTags(@Validated @RequestBody MessageTagRelationDTO messageTagRelationDTO) {
        int result = chatMessageTagService.removeMessageTags(messageTagRelationDTO);
        return toAjax(result);
    }

    /**
     * 获取消息的标签列表
     */
    @GetMapping("/messageTags/{messageId}")
    public R<List<ChatMessageTag>> getMessageTags(@PathVariable Long messageId) {
        List<ChatMessageTag> tags = chatMessageTagService.selectMessageTags(messageId);
        return R.ok(tags);
    }

    /**
     * 获取标签下的所有消息ID
     */
    @GetMapping("/messageIds/{tagId}")
    public R<List<Long>> getMessageIdsByTagId(@PathVariable Long tagId) {
        List<Long> messageIds = chatMessageTagService.selectMessageIdsByTagId(tagId);
        return R.ok(messageIds);
    }
}