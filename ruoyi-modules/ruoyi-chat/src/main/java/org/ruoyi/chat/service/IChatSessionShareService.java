package org.ruoyi.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ruoyi.chat.domain.ChatSessionShare;
import org.ruoyi.domain.vo.ChatSessionVo;

import java.util.List;

/**
 * 会话分享Service接口
 * 
 * @author ageerle
 * @date 2025-11-10
 */
public interface IChatSessionShareService extends IService<ChatSessionShare> {

    /**
     * 查询会话分享
     * 
     * @param id 会话分享主键
     * @return 会话分享
     */
    ChatSessionShare selectChatSessionShareById(Long id);

    /**
     * 查询会话分享列表
     * 
     * @param chatSessionShare 会话分享
     * @return 会话分享集合
     */
    List<ChatSessionShare> selectChatSessionShareList(ChatSessionShare chatSessionShare);

    /**
     * 新增会话分享
     * 
     * @param chatSessionShare 会话分享
     * @return 结果
     */
    int insertChatSessionShare(ChatSessionShare chatSessionShare);

    /**
     * 修改会话分享
     * 
     * @param chatSessionShare 会话分享
     * @return 结果
     */
    int updateChatSessionShare(ChatSessionShare chatSessionShare);

    /**
     * 批量删除会话分享
     * 
     * @param ids 需要删除的会话分享主键集合
     * @return 结果
     */
    int deleteChatSessionShareByIds(Long[] ids);

    /**
     * 删除会话分享信息
     * 
     * @param id 会话分享主键
     * @return 结果
     */
    int deleteChatSessionShareById(Long id);

    /**
     * 生成会话分享链接
     * 
     * @param sessionId 会话ID
     * @param password 查看密码（可选）
     * @param expireTime 有效期（可选）
     * @return 分享链接
     */
    String generateShareLink(Long sessionId, String password, String expireTime);

    /**
     * 验证分享链接
     * 
     * @param shareToken 分享token
     * @param password 查看密码（可选）
     * @return 会话信息
     */
    ChatSessionVo validateShareLink(String shareToken, String password);

    /**
     * 取消会话分享
     * 
     * @param shareToken 分享token
     * @return 结果
     */
    int cancelShareLink(String shareToken);

    /**
     * 修改会话分享设置
     * 
     * @param shareToken 分享token
     * @param password 查看密码（可选）
     * @param expireTime 有效期（可选）
     * @return 结果
     */
    int updateShareLink(String shareToken, String password, String expireTime);
}
