package org.ruoyi.mapper;

import org.ruoyi.domain.ChatSessionShare;
import org.ruoyi.domain.vo.ChatSessionShareVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会话分享Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-20
 */
public interface ChatSessionShareMapper extends BaseMapper<ChatSessionShare> {
    /**
     * 查询会话分享列表
     * 
     * @param page 分页参数
     * @param chatSessionShare 会话分享信息
     * @return 会话分享列表
     */
    IPage<ChatSessionShareVo> selectChatSessionSharePage(@Param("page") Page<ChatSessionShareVo> page, @Param("share") ChatSessionShare chatSessionShare);

    /**
     * 查询会话分享列表
     * 
     * @param chatSessionShare 会话分享信息
     * @return 会话分享列表
     */
    List<ChatSessionShareVo> selectChatSessionShareList(@Param("share") ChatSessionShare chatSessionShare);

    /**
     * 根据分享码查询会话分享信息
     * 
     * @param shareCode 分享码
     * @return 会话分享信息
     */
    ChatSessionShareVo selectChatSessionShareByCode(@Param("shareCode") String shareCode);

    /**
     * 增加查看次数
     * 
     * @param id 会话分享ID
     * @return 影响行数
     */
    int incrementViewCount(@Param("id") Long id);
}
