package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ruoyi.chat.domain.entity.ChatSessionShare;
import org.ruoyi.chat.domain.dto.ChatSessionShareDTO;
import org.ruoyi.chat.mapper.ChatSessionShareMapper;
import org.ruoyi.chat.service.IChatSessionShareService;
import org.ruoyi.common.core.utils.SecurityUtils;
import org.ruoyi.common.core.utils.StringUtils;
import org.ruoyi.common.core.utils.uuid.IdUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * 会话分享Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-01
 */
@Service
public class ChatSessionShareServiceImpl extends ServiceImpl<ChatSessionShareMapper, ChatSessionShare> implements IChatSessionShareService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHARE_CODE_LENGTH = 16;

    @Override
    public ChatSessionShare createShare(ChatSessionShareDTO shareDTO) {
        // 检查会话是否已被分享
        ChatSessionShare existingShare = baseMapper.selectBySessionId(shareDTO.getSessionId());
        if (existingShare != null) {
            return existingShare;
        }

        ChatSessionShare share = new ChatSessionShare();
        share.setSessionId(shareDTO.getSessionId());
        share.setUserId(SecurityUtils.getUserId());
        share.setShareCode(generateUniqueShareCode());
        
        // 加密密码
        if (StringUtils.isNotBlank(shareDTO.getPassword())) {
            share.setPassword(passwordEncoder.encode(shareDTO.getPassword()));
        }
        share.setExpireTime(shareDTO.getExpireTime());
        share.setStatus(1);
        share.setViewCount(0);

        baseMapper.insert(share);
        return share;
    }

    @Override
    public boolean verifyPassword(String shareCode, String password) {
        ChatSessionShare share = baseMapper.selectByShareCode(shareCode);
        if (share == null || StringUtils.isBlank(share.getPassword())) {
            return true;
        }
        return passwordEncoder.matches(password, share.getPassword());
    }

    @Override
    public ChatSessionShare getShareInfo(String shareCode) {
        return baseMapper.selectByShareCode(shareCode);
    }

    @Override
    public void incrementViewCount(String shareCode) {
        baseMapper.incrementViewCount(shareCode);
    }

    @Override
    public List<ChatSessionShare> selectMyShareList() {
        return baseMapper.selectByUserId(SecurityUtils.getUserId());
    }

    @Override
    public void cancelShare(Long id) {
        ChatSessionShare share = getById(id);
        if (share != null && share.getUserId().equals(SecurityUtils.getUserId())) {
            share.setStatus(0);
            updateById(share);
        }
    }

    @Override
    public void updateShare(ChatSessionShare share) {
        ChatSessionShare existingShare = getById(share.getId());
        if (existingShare != null && existingShare.getUserId().equals(SecurityUtils.getUserId())) {
            // 仅允许更新密码和过期时间
            if (StringUtils.isNotBlank(share.getPassword())) {
                existingShare.setPassword(passwordEncoder.encode(share.getPassword()));
            }
            existingShare.setExpireTime(share.getExpireTime());
            updateById(existingShare);
        }
    }

    @Override
    public boolean isShareValid(String shareCode) {
        ChatSessionShare share = baseMapper.selectByShareCode(shareCode);
        if (share == null || share.getStatus() != 1) {
            return false;
        }
        // 检查过期时间
        if (share.getExpireTime() != null && share.getExpireTime().isBefore(LocalDateTime.now())) {
            return false;
        }
        return true;
    }

    /**
     * 生成唯一分享码
     *
     * @return 分享码
     */
    private String generateUniqueShareCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(SHARE_CODE_LENGTH);
        while (true) {
            for (int i = 0; i < SHARE_CODE_LENGTH; i++) {
                sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            String shareCode = sb.toString();
            // 检查分享码是否唯一
            if (baseMapper.selectByShareCode(shareCode) == null) {
                return shareCode;
            }
            sb.setLength(0);
        }
    }
}
