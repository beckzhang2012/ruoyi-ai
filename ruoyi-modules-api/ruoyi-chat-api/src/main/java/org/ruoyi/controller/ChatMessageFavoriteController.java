package org.ruoyi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.ruoyi.core.domain.R;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.domain.bo.ChatMessageFavoriteBo;
import org.ruoyi.domain.vo.ChatMessageFavoriteVo;
import org.ruoyi.service.IChatMessageFavoriteService;

import java.util.Arrays;
import java.util.List;

/**
 * 消息收藏Controller
 *
 * @author ageerle
 * @date 2025-10-20
 */
@Api(value = "消息收藏管理", tags = {"消息收藏管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/favorite")
public class ChatMessageFavoriteController {

    private final IChatMessageFavoriteService chatMessageFavoriteService;

    /**
     * 查询消息收藏列表
     */
    @ApiOperation("查询消息收藏列表")
    @PreAuthorize("@ss.hasPermi('chat:favorite:list')")
    @GetMapping("/list")
    public TableDataInfo<ChatMessageFavoriteVo> list(ChatMessageFavoriteBo bo, PageQuery pageQuery) {
        return chatMessageFavoriteService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询消息收藏详情
     */
    @ApiOperation("查询消息收藏详情")
    @PreAuthorize("@ss.hasPermi('chat:favorite:query')")
    @GetMapping("/{id}")
    public R<ChatMessageFavoriteVo> getInfo(@PathVariable("id") Long id) {
        return R.ok(chatMessageFavoriteService.queryById(id));
    }

    /**
     * 新增消息收藏
     */
    @ApiOperation("新增消息收藏")
    @PreAuthorize("@ss.hasPermi('chat:favorite:add')")
    @PostMapping
    public R<Void> add(@RequestBody ChatMessageFavoriteBo bo) {
        return toAjax(chatMessageFavoriteService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改消息收藏
     */
    @ApiOperation("修改消息收藏")
    @PreAuthorize("@ss.hasPermi('chat:favorite:edit')")
    @PutMapping
    public R<Void> edit(@RequestBody ChatMessageFavoriteBo bo) {
        return toAjax(chatMessageFavoriteService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除消息收藏
     */
    @ApiOperation("删除消息收藏")
    @PreAuthorize("@ss.hasPermi('chat:favorite:remove')")
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(chatMessageFavoriteService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }

    /**
     * 检查消息是否已收藏
     */
    @ApiOperation("检查消息是否已收藏")
    @GetMapping("/isFavorite/{userId}/{messageId}")
    public R<Boolean> isFavorite(@PathVariable Long userId, @PathVariable Long messageId) {
        return R.ok(chatMessageFavoriteService.isFavorite(userId, messageId));
    }

    /**
     * 统计用户收藏数量
     */
    @ApiOperation("统计用户收藏数量")
    @GetMapping("/count/{userId}")
    public R<Integer> countByUserId(@PathVariable Long userId) {
        return R.ok(chatMessageFavoriteService.countByUserId(userId));
    }

    /**
     * 按模型名称筛选收藏
     */
    @ApiOperation("按模型名称筛选收藏")
    @GetMapping("/byModel/{userId}/{modelName}")
    public R<List<ChatMessageFavoriteVo>> queryByModelName(@PathVariable Long userId, @PathVariable String modelName) {
        return R.ok(chatMessageFavoriteService.queryByModelName(userId, modelName));
    }

    /**
     * 按会话筛选收藏
     */
    @ApiOperation("按会话筛选收藏")
    @GetMapping("/bySession/{userId}/{sessionId}")
    public R<List<ChatMessageFavoriteVo>> queryBySessionId(@PathVariable Long userId, @PathVariable Long sessionId) {
        return R.ok(chatMessageFavoriteService.queryBySessionId(userId, sessionId));
    }

    /**
     * 按内容关键词搜索收藏
     */
    @ApiOperation("按内容关键词搜索收藏")
    @GetMapping("/search/{userId}/{content}")
    public R<List<ChatMessageFavoriteVo>> searchByContent(@PathVariable Long userId, @PathVariable String content) {
        return R.ok(chatMessageFavoriteService.searchByContent(userId, content));
    }
}