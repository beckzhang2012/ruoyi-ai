package org.ruoyi.controller;

import lombok.RequiredArgsConstructor;
import org.ruoyi.common.annotation.Log;
import org.ruoyi.common.annotation.RepeatSubmit;
import org.ruoyi.common.core.controller.BaseController;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.page.TableDataInfo;
import org.ruoyi.common.enums.BusinessType;
import org.ruoyi.common.utils.poi.ExcelUtil;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.domain.bo.ChatMessageFavoriteBo;
import org.ruoyi.domain.vo.ChatMessageFavoriteVo;
import org.ruoyi.service.IChatMessageFavoriteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 聊天消息收藏Controller
 *
 * @author ruoyi
 * @date 2025-11-10
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/message/favorite")
public class ChatMessageFavoriteController extends BaseController {

    private final IChatMessageFavoriteService chatMessageFavoriteService;

    /**
     * 查询聊天消息收藏列表
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite:list')")
    @GetMapping("/list")
    public TableDataInfo<ChatMessageFavoriteVo> list(ChatMessageFavoriteBo bo, PageQuery pageQuery) {
        return chatMessageFavoriteService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出聊天消息收藏列表
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite:export')")
    @Log(title = "聊天消息收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChatMessageFavoriteBo bo) {
        List<ChatMessageFavoriteVo> list = chatMessageFavoriteService.queryList(bo);
        ExcelUtil.exportExcel(list, "聊天消息收藏", ChatMessageFavoriteVo.class, response);
    }

    /**
     * 获取聊天消息收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite:query')")
    @GetMapping(value = "/{id}")
    public R<ChatMessageFavoriteVo> getInfo(@PathVariable Long id) {
        return R.ok(chatMessageFavoriteService.queryById(id));
    }

    /**
     * 新增聊天消息收藏
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite:add')")
    @Log(title = "聊天消息收藏", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping
    public R<Void> add(@Validated @RequestBody ChatMessageFavoriteBo bo) {
        return toAjax(chatMessageFavoriteService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改聊天消息收藏
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite:edit')")
    @Log(title = "聊天消息收藏", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping
    public R<Void> edit(@Validated @RequestBody ChatMessageFavoriteBo bo) {
        return toAjax(chatMessageFavoriteService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除聊天消息收藏
     */
    @PreAuthorize("@ss.hasPermi('chat:message:favorite:remove')")
    @Log(title = "聊天消息收藏", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(chatMessageFavoriteService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}