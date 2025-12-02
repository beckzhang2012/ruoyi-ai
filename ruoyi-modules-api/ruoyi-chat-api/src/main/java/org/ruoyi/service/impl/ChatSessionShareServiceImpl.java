package org.ruoyi.service.impl;

import org.ruoyi.domain.ChatSessionShare;
import org.ruoyi.domain.bo.ChatSessionShareBo;
import org.ruoyi.domain.vo.ChatSessionShareVo;
import org.ruoyi.mapper.ChatSessionShareMapper;
import org.ruoyi.service.IChatSessionShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 会话分享Service实现类
 * 
 * @author ruoyi
 * @date 2025-10-20
 */
@Service
public class ChatSessionShareServiceImpl extends ServiceImpl<ChatSessionShareMapper, ChatSessionShare> implements IChatSessionShareService {
    /**
     * 查询会话分享列表
     * 
     * @param page 分页参数
     * @param chatSessionShareBo 会话分享业务对象
     * @return 会话分享列表
     */
    @Override
    public IPage<ChatSessionShareVo> selectChatSessionSharePage(IPage<ChatSessionShareVo> page, ChatSessionShareBo chatSessionShareBo) {
        ChatSessionShare chatSessionShare = new ChatSessionShare();
        BeanUtils.copyProperties(chatSessionShareBo, chatSessionShare);
        return baseMapper.selectChatSessionSharePage(page, chatSessionShare);
    }

    /**
     * 查询会话分享列表
     * 
     * @param chatSessionShareBo 会话分享业务对象
     * @return 会话分享列表
     */
    @Override
    public List<ChatSessionShareVo> selectChatSessionShareList(ChatSessionShareBo chatSessionShareBo) {
        ChatSessionShare chatSessionShare = new ChatSessionShare();
        BeanUtils.copyProperties(chatSessionShareBo, chatSessionShare);
        return baseMapper.selectChatSessionShareList(chatSessionShare);
    }

    /**
     * 查询会话分享信息
     * 
     * @param id 会话分享ID
     * @return 会话分享信息
     */
    @Override
    public ChatSessionShareVo selectChatSessionShareById(Long id) {
        ChatSessionShare chatSessionShare = baseMapper.selectById(id);
        ChatSessionShareVo chatSessionShareVo = new ChatSessionShareVo();
        BeanUtils.copyProperties(chatSessionShare, chatSessionShareVo);
        return chatSessionShareVo;
    }

    /**
     * 根据分享码查询会话分享信息
     * 
     * @param shareCode 分享码
     * @return 会话分享信息
     */
    @Override
    public ChatSessionShareVo selectChatSessionShareByCode(String shareCode) {
        ChatSessionShareVo chatSessionShareVo = baseMapper.selectChatSessionShareByCode(shareCode);
        if (chatSessionShareVo != null) {
            // 检查分享是否过期
            if (chatSessionShareVo.getValidHours() > 0) {
                Date expireTime = new Date(chatSessionShareVo.getCreateTime().getTime() + chatSessionShareVo.getValidHours() * 60 * 60 * 1000);
                if (new Date().after(expireTime)) {
                    chatSessionShareVo.setStatus(2); // 已过期
                }
            }
        }
        return chatSessionShareVo;
    }

    /**
     * 创建会话分享
     * 
     * @param chatSessionShareBo 会话分享业务对象
     * @return 分享链接
     */
    @Override
    public String createChatSessionShare(ChatSessionShareBo chatSessionShareBo) {
        ChatSessionShare chatSessionShare = new ChatSessionShare();
        BeanUtils.copyProperties(chatSessionShareBo, chatSessionShare);

        // 生成唯一的分享码
        String shareCode = generateShareCode();
        chatSessionShare.setShareCode(shareCode);

        // 加密密码（如果设置了密码）
        if (StringUtils.hasText(chatSessionShare.getPassword())) {
            chatSessionShare.setPassword(encryptPassword(chatSessionShare.getPassword()));
        }

        // 设置默认值
        chatSessionShare.setStatus(0); // 有效
        chatSessionShare.setViewCount(0);
        chatSessionShare.setDelFlag(0);

        // 保存到数据库
        baseMapper.insert(chatSessionShare);

        // 生成分享链接
        String shareUrl = generateShareUrl(shareCode);
        return shareUrl;
    }

    /**
     * 更新会话分享
     * 
     * @param chatSessionShareBo 会话分享业务对象
     * @return 结果
     */
    @Override
    public boolean updateChatSessionShare(ChatSessionShareBo chatSessionShareBo) {
        ChatSessionShare chatSessionShare = baseMapper.selectById(chatSessionShareBo.getId());
        if (chatSessionShare == null) {
            return false;
        }

        BeanUtils.copyProperties(chatSessionShareBo, chatSessionShare);

        // 加密密码（如果设置了密码）
        if (StringUtils.hasText(chatSessionShare.getPassword())) {
            chatSessionShare.setPassword(encryptPassword(chatSessionShare.getPassword()));
        } else {
            // 如果密码为空，清除密码
            chatSessionShare.setPassword(null);
        }

        // 更新到数据库
        return baseMapper.updateById(chatSessionShare) > 0;
    }

    /**
     * 取消会话分享
     * 
     * @param id 会话分享ID
     * @return 结果
     */
    @Override
    public boolean cancelChatSessionShare(Long id) {
        ChatSessionShare chatSessionShare = baseMapper.selectById(id);
        if (chatSessionShare == null) {
            return false;
        }

        chatSessionShare.setStatus(1); // 已取消
        return baseMapper.updateById(chatSessionShare) > 0;
    }

    /**
     * 删除会话分享
     * 
     * @param ids 会话分享ID数组
     * @return 结果
     */
    @Override
    public boolean deleteChatSessionShareByIds(Long[] ids) {
        for (Long id : ids) {
            ChatSessionShare chatSessionShare = baseMapper.selectById(id);
            if (chatSessionShare != null) {
                chatSessionShare.setDelFlag(1); // 已删除
                baseMapper.updateById(chatSessionShare);
            }
        }
        return true;
    }

    /**
     * 验证分享密码
     * 
     * @param shareCode 分享码
     * @param password 密码
     * @return 验证结果
     */
    @Override
    public boolean verifySharePassword(String shareCode, String password) {
        ChatSessionShareVo chatSessionShareVo = selectChatSessionShareByCode(shareCode);
        if (chatSessionShareVo == null || chatSessionShareVo.getStatus() != 0) {
            return false;
        }

        if (!StringUtils.hasText(chatSessionShareVo.getPassword())) {
            // 没有设置密码，直接验证通过
            return true;
        }

        if (!StringUtils.hasText(password)) {
            // 密码为空，验证失败
            return false;
        }

        // 加密输入的密码并与数据库中的密码比较
        String encryptedPassword = encryptPassword(password);
        return encryptedPassword.equals(chatSessionShareVo.getPassword());
    }

    /**
     * 增加查看次数
     * 
     * @param id 会话分享ID
     * @return 结果
     */
    @Override
    public boolean incrementViewCount(Long id) {
        return baseMapper.incrementViewCount(id) > 0;
    }

    /**
     * 生成唯一的分享码
     * 
     * @return 分享码
     */
    private String generateShareCode() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return DigestUtils.md5DigestAsHex(uuid.getBytes(StandardCharsets.UTF_8)).substring(0, 16);
    }

    /**
     * 加密密码
     * 
     * @param password 原始密码
     * @return 加密后的密码
     */
    private String encryptPassword(String password) {
        // 使用MD5加密密码（可以根据需要使用更安全的加密方式）
        return DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成分享链接
     * 
     * @param shareCode 分享码
     * @return 分享链接
     */
    private String generateShareUrl(String shareCode) {
        // 这里可以根据实际的域名和端口进行配置
        return "http://localhost:8080/chat/share/" + shareCode;
    }
}
