package org.ruoyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ruoyi.common.core.mapper.BaseMapperPlus;
import org.ruoyi.domain.ChatMessageTag;
import org.ruoyi.domain.vo.ChatMessageTagVo;

/**
 * 消息标签表Mapper接口
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Mapper
public interface ChatMessageTagMapper extends BaseMapperPlus<ChatMessageTagMapper, ChatMessageTag, ChatMessageTagVo> {

}
