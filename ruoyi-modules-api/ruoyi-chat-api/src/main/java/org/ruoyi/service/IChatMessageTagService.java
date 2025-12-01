package org.ruoyi.service;

import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.page.TableDataInfo;
import org.ruoyi.domain.bo.ChatMessageTagBo;
import org.ruoyi.domain.vo.ChatMessageTagVo;

import java.util.List;

/**
 * 消息标签表Service接口
 *
 * @author ageerle
 * @date 2025-11-11
 */
public interface IChatMessageTagService {

    /**
     * 查询消息标签表
     *
     * @param id 消息标签表主键
     * @return 消息标签表
     */
    ChatMessageTagVo queryById(Long id);

    /**
     * 查询消息标签表列表
     *
     * @param bo 消息标签表
     * @return 消息标签表集合
     */
    TableDataInfo<ChatMessageTagVo> queryPageList(ChatMessageTagBo bo);

    /**
     * 查询消息标签表列表
     *
     * @param bo 消息标签表
     * @return 消息标签表集合
     */
    List<ChatMessageTagVo> queryList(ChatMessageTagBo bo);

    /**
     * 新增消息标签表
     *
     * @param bo 消息标签表
     * @return 结果
     */
    R<Boolean> insertByBo(ChatMessageTagBo bo);

    /**
     * 修改消息标签表
     *
     * @param bo 消息标签表
     * @return 结果
     */
    R<Boolean> updateByBo(ChatMessageTagBo bo);

    /**
     * 批量删除消息标签表
     *
     * @param ids 需要删除的消息标签表主键集合
     * @return 结果
     */
    R<Boolean> deleteWithValidByIds(List<Long> ids, Boolean isValid);
}
