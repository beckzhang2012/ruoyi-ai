package org.ruoyi.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.page.TableDataInfo;
import org.ruoyi.domain.bo.ChatMessageTagBo;
import org.ruoyi.domain.vo.ChatMessageTagVo;
import org.ruoyi.service.IChatMessageTagService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 消息标签表Controller
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/message/tag")
@SaCheckLogin
public class ChatMessageTagController {

    private final IChatMessageTagService chatMessageTagService;

    /**
     * 查询消息标签表列表
     */
    @GetMapping("/list")
    public TableDataInfo<ChatMessageTagVo> list(ChatMessageTagBo bo) {
        return chatMessageTagService.queryPageList(bo);
    }

    /**
     * 获取消息标签表详细信息
     *
     * @param id 消息标签表主键
     */
    @GetMapping("/{id}")
    public R<ChatMessageTagVo> getInfo(@NotNull(message = "主键不能为空")
                                           @PathVariable Long id) {
        return R.ok(chatMessageTagService.queryById(id));
    }

    /**
     * 新增消息标签表
     */
    @PostMapping
    public R<Boolean> add(@Validated @RequestBody ChatMessageTagBo bo) {
        return chatMessageTagService.insertByBo(bo);
    }

    /**
     * 修改消息标签表
     */
    @PutMapping
    public R<Boolean> edit(@Validated @RequestBody ChatMessageTagBo bo) {
        return chatMessageTagService.updateByBo(bo);
    }

    /**
     * 删除消息标签表
     *
     * @param ids 消息标签表主键集合
     */
    @DeleteMapping("/{ids}")
    public R<Boolean> remove(@NotEmpty(message = "主键不能为空")
                               @PathVariable List<Long> ids) {
        return chatMessageTagService.deleteWithValidByIds(ids, true);
    }
}
