package org.ruoyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ruoyi.common.core.mapper.BaseMapperPlus;
import org.ruoyi.domain.ChatMessageTagRelation;
import org.ruoyi.domain.vo.ChatMessageTagRelationVo;

/**
 * 消息标签关联表Mapper接口
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Mapper
public interface ChatMessageTagRelationMapper extends BaseMapperPlus<ChatMessageTagRelationMapper, ChatMessageTagRelation, ChatMessageTagRelationVo> {

}
