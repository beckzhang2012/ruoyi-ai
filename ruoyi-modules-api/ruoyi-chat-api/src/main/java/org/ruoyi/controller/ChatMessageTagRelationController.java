package org.ruoyi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.page.TableDataInfo;
import org.ruoyi.domain.bo.ChatMessageTagRelationBo;
import org.ruoyi.domain.vo.ChatMessageTagRelationVo;
import org.ruoyi.service.IChatMessageTagRelationService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 消息标签关联表Controller
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/message/tag/relation")
@SaCheckLogin
public class ChatMessageTagRelationController {

    private final IChatMessageTagRelationService chatMessageTagRelationService;

    /**
     * 查询消息标签关联表列表
     */
    @GetMapping("/list")
    public TableDataInfo<ChatMessageTagRelationVo> list(ChatMessageTagRelationBo bo) {
        return chatMessageTagRelationService.queryPageList(bo);
    }

    /**
     * 获取消息标签关联表详细信息
     *
     * @param id 消息标签关联表主键
     */
    @GetMapping("/{id}")
    public R<ChatMessageTagRelationVo> getInfo(@NotNull(message = "主键不能为空")
                                                   @PathVariable Long id) {
        return R.ok(chatMessageTagRelationService.queryById(id));
    }

    /**
     * 新增消息标签关联表
     */
    @PostMapping
    public R<Boolean> add(@Validated @RequestBody ChatMessageTagRelationBo bo) {
        return chatMessageTagRelationService.insertByBo(bo);
    }

    /**
     * 修改消息标签关联表
     */
    @PutMapping
    public R<Boolean> edit(@Validated @RequestBody ChatMessageTagRelationBo bo) {
        return chatMessageTagRelationService.updateByBo(bo);
    }

    /**
     * 删除消息标签关联表
     *
     * @param ids 消息标签关联表主键集合
     */
    @DeleteMapping("/{ids}")
    public R<Boolean> remove(@NotEmpty(message = "主键不能为空")
                               @PathVariable List<Long> ids) {
        return chatMessageTagRelationService.deleteWithValidByIds(ids, true);
    }

    /**
     * 为消息添加标签
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     */
    @PostMapping("/addTags")
    public R<Boolean> addTagsToMessage(@RequestParam Long messageId, @RequestBody List<Long> tagIds) {
        return chatMessageTagRelationService.addTagsToMessage(messageId, tagIds);
    }

    /**
     * 从消息中移除标签
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     */
    @PostMapping("/removeTags")
    public R<Boolean> removeTagsFromMessage(@RequestParam Long messageId, @RequestBody List<Long> tagIds) {
        return chatMessageTagRelationService.removeTagsFromMessage(messageId, tagIds);
    }

    /**
     * 获取消息的所有标签
     *
     * @param messageId 消息ID
     */
    @GetMapping("/getTagsByMessageId")
    public R<List<ChatMessageTagRelationVo>> getTagsByMessageId(@RequestParam Long messageId) {
        return R.ok(chatMessageTagRelationService.getTagsByMessageId(messageId));
    }

    /**
     * 获取标签的所有消息
     *
     * @param tagId 标签ID
     */
    @GetMapping("/getMessagesByTagId")
    public R<List<Long>> getMessagesByTagId(@RequestParam Long tagId) {
        return R.ok(chatMessageTagRelationService.getMessagesByTagId(tagId));
    }
}
