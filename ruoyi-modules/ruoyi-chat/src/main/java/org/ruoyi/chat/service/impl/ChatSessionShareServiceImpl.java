package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.ruoyi.chat.domain.ChatSessionShare;
import org.ruoyi.chat.mapper.ChatSessionShareMapper;
import org.ruoyi.chat.service.IChatSessionShareService;
import org.ruoyi.common.core.exception.ServiceException;
import org.ruoyi.common.core.utils.StringUtils2;
import org.ruoyi.common.core.utils.uuid.IdUtils;
import org.ruoyi.common.security.utils.SecurityUtils;
import org.ruoyi.domain.ChatSession;
import org.ruoyi.domain.vo.ChatSessionVo;
import org.ruoyi.service.IChatSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 会话分享Service业务层处理
 * 
 * @author ageerle
 * @date 2025-11-10
 */
@Service
public class ChatSessionShareServiceImpl extends ServiceImpl<ChatSessionShareMapper, ChatSessionShare> implements IChatSessionShareService {

    @Autowired
    private IChatSessionService chatSessionService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 查询会话分享
     * 
     * @param id 会话分享主键
     * @return 会话分享
     */
    @Override
    public ChatSessionShare selectChatSessionShareById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询会话分享列表
     * 
     * @param chatSessionShare 会话分享
     * @return 会话分享
     */
    @Override
    public List<ChatSessionShare> selectChatSessionShareList(ChatSessionShare chatSessionShare) {
        LambdaQueryWrapper<ChatSessionShare> lqw = new LambdaQueryWrapper<ChatSessionShare>(chatSessionShare);
        lqw.eq(ChatSessionShare::getStatus, 1);
        lqw.orderByDesc(ChatSessionShare::getCreateTime);
        return baseMapper.selectList(lqw);
    }

    /**
     * 新增会话分享
     * 
     * @param chatSessionShare 会话分享
     * @return 结果
     */
    @Override
    public int insertChatSessionShare(ChatSessionShare chatSessionShare) {
        chatSessionShare.setCreateBy(SecurityUtils.getUserId());
        chatSessionShare.setCreateTime(new Date());
        chatSessionShare.setUpdateBy(SecurityUtils.getUserId());
        chatSessionShare.setUpdateTime(new Date());
        return baseMapper.insert(chatSessionShare);
    }

    /**
     * 修改会话分享
     * 
     * @param chatSessionShare 会话分享
     * @return 结果
     */
    @Override
    public int updateChatSessionShare(ChatSessionShare chatSessionShare) {
        chatSessionShare.setUpdateBy(SecurityUtils.getUserId());
        chatSessionShare.setUpdateTime(new Date());
        return baseMapper.updateById(chatSessionShare);
    }

    /**
     * 批量删除会话分享
     * 
     * @param ids 需要删除的会话分享主键
     * @return 结果
     */
    @Override
    public int deleteChatSessionShareByIds(Long[] ids) {
        return baseMapper.deleteBatchIds(List.of(ids));
    }

    /**
     * 删除会话分享信息
     * 
     * @param id 会话分享主键
     * @return 结果
     */
    @Override
    public int deleteChatSessionShareById(Long id) {
        return baseMapper.deleteById(id);
    }

    /**
     * 生成会话分享链接
     * 
     * @param sessionId 会话ID
     * @param password 查看密码（可选）
     * @param expireTime 有效期（可选）
     * @return 分享链接
     */
    @Override
    public String generateShareLink(Long sessionId, String password, String expireTime) {
        // 检查会话是否存在
        ChatSession chatSession = chatSessionService.getById(sessionId);
        if (chatSession == null) {
            throw new ServiceException("会话不存在");
        }

        // 生成唯一的分享token
        String shareToken = UUID.randomUUID().toString().replaceAll("-", "");

        // 加密查看密码（如果提供）
        String encryptedPassword = null;
        if (StringUtils.isNotBlank(password)) {
            encryptedPassword = passwordEncoder.encode(password);
        }

        // 解析有效期（如果提供）
        Date expireDate = null;
        if (StringUtils.isNotBlank(expireTime)) {
            try {
                expireDate = DateUtils.parseDate(expireTime, "yyyy-MM-dd HH:mm:ss");
            } catch (Exception e) {
                throw new ServiceException("有效期格式错误");
            }
        }

        // 保存会话分享信息到数据库
        ChatSessionShare chatSessionShare = new ChatSessionShare();
        chatSessionShare.setSessionId(sessionId);
        chatSessionShare.setShareToken(shareToken);
        chatSessionShare.setPassword(encryptedPassword);
        chatSessionShare.setExpireTime(expireDate);
        chatSessionShare.setStatus(1);
        chatSessionShare.setCreateBy(SecurityUtils.getUserId());
        chatSessionShare.setCreateTime(new Date());
        chatSessionShare.setUpdateBy(SecurityUtils.getUserId());
        chatSessionShare.setUpdateTime(new Date());
        baseMapper.insert(chatSessionShare);

        // 生成分享链接并返回
        String shareLink = "/chat/share/" + shareToken;
        return shareLink;
    }

    /**
     * 验证分享链接
     * 
     * @param shareToken 分享token
     * @param password 查看密码（可选）
     * @return 会话信息
     */
    @Override
    public ChatSessionVo validateShareLink(String shareToken, String password) {
        // 根据分享token查询会话分享信息
        LambdaQueryWrapper<ChatSessionShare> lqw = new LambdaQueryWrapper<ChatSessionShare>();
        lqw.eq(ChatSessionShare::getShareToken, shareToken);
        ChatSessionShare chatSessionShare = baseMapper.selectOne(lqw);

        // 检查会话分享是否存在
        if (chatSessionShare == null) {
            throw new ServiceException("分享链接无效");
        }

        // 检查会话分享是否有效
        if (chatSessionShare.getStatus() != 1) {
            throw new ServiceException("分享链接已失效");
        }

        // 检查会话分享是否过期
        if (chatSessionShare.getExpireTime() != null && new Date().after(chatSessionShare.getExpireTime())) {
            throw new ServiceException("分享链接已过期");
        }

        // 验证查看密码（如果设置）
        if (StringUtils.isNotBlank(chatSessionShare.getPassword())) {
            if (StringUtils.isBlank(password)) {
                throw new ServiceException("请输入查看密码");
            }
            if (!passwordEncoder.matches(password, chatSessionShare.getPassword())) {
                throw new ServiceException("查看密码错误");
            }
        }

        // 查询会话信息并返回
        ChatSessionVo chatSessionVo = chatSessionService.queryById(chatSessionShare.getSessionId());
        if (chatSessionVo == null) {
            throw new ServiceException("会话不存在");
        }
        return chatSessionVo;
    }

    /**
     * 取消会话分享
     * 
     * @param shareToken 分享token
     * @return 结果
     */
    @Override
    public int cancelShareLink(String shareToken) {
        // 根据分享token查询会话分享信息
        LambdaQueryWrapper<ChatSessionShare> lqw = new LambdaQueryWrapper<ChatSessionShare>();
        lqw.eq(ChatSessionShare::getShareToken, shareToken);
        ChatSessionShare chatSessionShare = baseMapper.selectOne(lqw);

        // 检查会话分享是否存在
        if (chatSessionShare == null) {
            throw new ServiceException("分享链接无效");
        }

        // 检查会话分享是否属于当前用户
        if (!chatSessionShare.getCreateBy().equals(SecurityUtils.getUserId())) {
            throw new ServiceException("无权限取消该分享链接");
        }

        // 将会话分享的状态设置为无效
        chatSessionShare.setStatus(0);
        chatSessionShare.setUpdateBy(SecurityUtils.getUserId());
        chatSessionShare.setUpdateTime(new Date());

        // 更新会话分享信息到数据库
        return baseMapper.updateById(chatSessionShare);
    }

    /**
     * 修改会话分享设置
     * 
     * @param shareToken 分享token
     * @param password 查看密码（可选）
     * @param expireTime 有效期（可选）
     * @return 结果
     */
    @Override
    public int updateShareLink(String shareToken, String password, String expireTime) {
        // 根据分享token查询会话分享信息
        LambdaQueryWrapper<ChatSessionShare> lqw = new LambdaQueryWrapper<ChatSessionShare>();
        lqw.eq(ChatSessionShare::getShareToken, shareToken);
        ChatSessionShare chatSessionShare = baseMapper.selectOne(lqw);

        // 检查会话分享是否存在
        if (chatSessionShare == null) {
            throw new ServiceException("分享链接无效");
        }

        // 检查会话分享是否属于当前用户
        if (!chatSessionShare.getCreateBy().equals(SecurityUtils.getUserId())) {
            throw new ServiceException("无权限修改该分享链接");
        }

        // 更新查看密码（如果提供）
        if (StringUtils.isNotBlank(password)) {
            chatSessionShare.setPassword(passwordEncoder.encode(password));
        } else if (password != null) {
            // 如果password为空字符串，则移除密码
            chatSessionShare.setPassword(null);
        }

        // 更新有效期（如果提供）
        if (StringUtils.isNotBlank(expireTime)) {
            try {
                chatSessionShare.setExpireTime(DateUtils.parseDate(expireTime, "yyyy-MM-dd HH:mm:ss"));
            } catch (Exception e) {
                throw new ServiceException("有效期格式错误");
            }
        } else if (expireTime != null) {
            // 如果expireTime为空字符串，则移除有效期
            chatSessionShare.setExpireTime(null);
        }

        // 更新会话分享信息到数据库
        chatSessionShare.setUpdateBy(SecurityUtils.getUserId());
        chatSessionShare.setUpdateTime(new Date());
        return baseMapper.updateById(chatSessionShare);
    }
}
