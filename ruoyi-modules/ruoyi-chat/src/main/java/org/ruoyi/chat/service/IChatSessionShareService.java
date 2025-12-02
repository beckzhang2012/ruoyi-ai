package org.ruoyi.chat.service;

import org.ruoyi.chat.domain.entity.ChatSessionShare;
import org.ruoyi.chat.domain.dto.ChatSessionShareDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 会话分享Service接口
 *
 * @author ruoyi
 * @date 2025-12-01
 */
public interface IChatSessionShareService extends IService<ChatSessionShare> {

    /**
     * 创建会话分享
     *
     * @param shareDTO 会话分享DTO
     * @return 分享信息
     */
    ChatSessionShare createShare(ChatSessionShareDTO shareDTO);

    /**
     * 验证分享密码
     *
     * @param shareCode 分享码
     * @param password 密码
     * @return 验证结果
     */
    boolean verifyPassword(String shareCode, String password);

    /**
     * 获取分享的会话信息
     *
     * @param shareCode 分享码
     * @return 会话信息
     */
    ChatSessionShare getShareInfo(String shareCode);

    /**
     * 增加查看次数
     *
     * @param shareCode 分享码
     */
    void incrementViewCount(String shareCode);

    /**
     * 查询用户分享列表
     *
     * @return 分享列表
     */
    List<ChatSessionShare> selectMyShareList();

    /**
     * 取消分享
     *
     * @param id 分享ID
     */
    void cancelShare(Long id);

    /**
     * 更新分享设置
     *
     * @param share 分享信息
     */
    void updateShare(ChatSessionShare share);

    /**
     * 检查分享是否有效
     *
     * @param shareCode 分享码
     * @return 有效返回true，否则false
     */
    boolean isShareValid(String shareCode);
}
