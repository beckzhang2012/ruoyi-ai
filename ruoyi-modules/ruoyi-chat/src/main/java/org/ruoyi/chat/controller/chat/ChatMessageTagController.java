package org.ruoyi.chat.controller.chat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.chat.service.IChatMessageTagService;
import org.ruoyi.chat.service.IChatMessageTagRelationService;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 聊天消息标签
 *
 * @author ruoyi
 * @date 2025-11-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/message/tag")
public class ChatMessageTagController extends BaseController {

    private final IChatMessageTagService chatMessageTagService;
    private final IChatMessageTagRelationService chatMessageTagRelationService;

    /**
     * 查询聊天消息标签列表
     */
    @GetMapping("/list")
    public TableDataInfo<ChatMessageTag> list(ChatMessageTag tag, PageQuery pageQuery) {
        return chatMessageTagService.queryPageList(tag, pageQuery);
    }

    /**
     * 获取聊天消息标签详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<ChatMessageTag> getInfo(@NotNull(message = "主键不能为空")
                                        @PathVariable Long id) {
        return R.ok(chatMessageTagService.queryById(id));
    }

    /**
     * 新增聊天消息标签
     */
    @Log(title = "聊天消息标签", businessType = BusinessType.INSERT)
    @PostMapping
    public R<Long> add(@Validated(AddGroup.class) @RequestBody ChatMessageTag tag) {
        return R.ok(chatMessageTagService.insertByTag(tag));
    }

    /**
     * 修改聊天消息标签
     */
    @Log(title = "聊天消息标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<Boolean> edit(@Validated(EditGroup.class) @RequestBody ChatMessageTag tag) {
        return R.ok(chatMessageTagService.updateByTag(tag));
    }

    /**
     * 删除聊天消息标签
     *
     * @param ids 主键串
     */
    @Log(title = "聊天消息标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Boolean> remove(@NotEmpty(message = "主键不能为空")
                               @PathVariable Collection<Long> ids) {
        return R.ok(chatMessageTagService.deleteWithValidByIds(ids, true));
    }

    /**
     * 根据用户ID查询标签列表
     */
    @GetMapping("/listByUserId")
    public R<List<ChatMessageTag>> listByUserId() {
        Long userId = getUserId();
        return R.ok(chatMessageTagService.selectTagsByUserId(userId));
    }

    /**
     * 为消息添加标签
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     */
    @PostMapping("/addTagToMessage")
    public R<Long> addTagToMessage(@NotNull(message = "消息ID不能为空")
                                     @RequestParam Long messageId,
                                     @NotNull(message = "标签ID不能为空")
                                     @RequestParam Long tagId) {
        return R.ok(chatMessageTagRelationService.addTagToMessage(messageId, tagId));
    }

    /**
     * 为消息移除标签
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     */
    @DeleteMapping("/removeTagFromMessage")
    public R<Void> removeTagFromMessage(@NotNull(message = "消息ID不能为空")
                                          @RequestParam Long messageId,
                                          @NotNull(message = "标签ID不能为空")
                                          @RequestParam Long tagId) {
        return toAjax(chatMessageTagRelationService.removeTagFromMessage(messageId, tagId));
    }

    /**
     * 为消息批量添加标签
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     */
    @PostMapping("/batchAddTagsToMessage")
    public R<Integer> batchAddTagsToMessage(@NotNull(message = "消息ID不能为空")
                                               @RequestParam Long messageId,
                                               @NotEmpty(message = "标签ID不能为空")
                                               @RequestParam List<Long> tagIds) {
        return R.ok(chatMessageTagRelationService.batchAddTagsToMessage(messageId, tagIds));
    }

    /**
     * 为消息批量移除标签
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     */
    @DeleteMapping("/batchRemoveTagsFromMessage")
    public R<Integer> batchRemoveTagsFromMessage(@NotNull(message = "消息ID不能为空")
                                                  @RequestParam Long messageId,
                                                  @NotEmpty(message = "标签ID不能为空")
                                                  @RequestParam List<Long> tagIds) {
        return R.ok(chatMessageTagRelationService.batchRemoveTagsFromMessage(messageId, tagIds));
    }

    /**
     * 根据标签ID查询消息ID集合
     *
     * @param tagId 标签ID
     */
    @GetMapping("/selectMessageIdsByTagId")
    public R<List<Long>> selectMessageIdsByTagId(@NotNull(message = "标签ID不能为空")
                                                    @RequestParam Long tagId) {
        return R.ok(chatMessageTagRelationService.selectMessageIdsByTagId(tagId));
    }

    /**
     * 根据标签ID集合查询消息ID集合
     *
     * @param tagIds 标签ID集合
     */
    @GetMapping("/selectMessageIdsByTagIds")
    public R<List<Long>> selectMessageIdsByTagIds(@NotEmpty(message = "标签ID不能为空")
                                                   @RequestParam List<Long> tagIds) {
        return R.ok(chatMessageTagRelationService.selectMessageIdsByTagIds(tagIds));
    }

}
