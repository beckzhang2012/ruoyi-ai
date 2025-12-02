package org.ruoyi.service;

import org.ruoyi.domain.ChatSessionShare;
import org.ruoyi.domain.bo.ChatSessionShareBo;
import org.ruoyi.domain.vo.ChatSessionShareVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 会话分享Service接口
 * 
 * @author ruoyi
 * @date 2025-10-20
 */
public interface IChatSessionShareService extends IService<ChatSessionShare> {
    /**
     * 查询会话分享列表
     * 
     * @param page 分页参数
     * @param chatSessionShareBo 会话分享业务对象
     * @return 会话分享列表
     */
    IPage<ChatSessionShareVo> selectChatSessionSharePage(IPage<ChatSessionShareVo> page, ChatSessionShareBo chatSessionShareBo);

    /**
     * 查询会话分享列表
     * 
     * @param chatSessionShareBo 会话分享业务对象
     * @return 会话分享列表
     */
    List<ChatSessionShareVo> selectChatSessionShareList(ChatSessionShareBo chatSessionShareBo);

    /**
     * 查询会话分享信息
     * 
     * @param id 会话分享ID
     * @return 会话分享信息
     */
    ChatSessionShareVo selectChatSessionShareById(Long id);

    /**
     * 根据分享码查询会话分享信息
     * 
     * @param shareCode 分享码
     * @return 会话分享信息
     */
    ChatSessionShareVo selectChatSessionShareByCode(String shareCode);

    /**
     * 创建会话分享
     * 
     * @param chatSessionShareBo 会话分享业务对象
     * @return 分享链接
     */
    String createChatSessionShare(ChatSessionShareBo chatSessionShareBo);

    /**
     * 更新会话分享
     * 
     * @param chatSessionShareBo 会话分享业务对象
     * @return 结果
     */
    boolean updateChatSessionShare(ChatSessionShareBo chatSessionShareBo);

    /**
     * 取消会话分享
     * 
     * @param id 会话分享ID
     * @return 结果
     */
    boolean cancelChatSessionShare(Long id);

    /**
     * 删除会话分享
     * 
     * @param ids 会话分享ID数组
     * @return 结果
     */
    boolean deleteChatSessionShareByIds(Long[] ids);

    /**
     * 验证分享密码
     * 
     * @param shareCode 分享码
     * @param password 密码
     * @return 验证结果
     */
    boolean verifySharePassword(String shareCode, String password);

    /**
     * 增加查看次数
     * 
     * @param id 会话分享ID
     * @return 结果
     */
    boolean incrementViewCount(Long id);
}
