package org.ruoyi.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.ruoyi.domain.ChatMessageFavorite;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息收藏视图对象 chat_message_favorite
 *
 * @author ruoyi
 * @date 2025-11-10
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ChatMessageFavorite.class)
public class ChatMessageFavoriteVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ExcelProperty(value = "主键")
    private Long id;

    /** 用户ID */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /** 消息ID */
    @ExcelProperty(value = "消息ID")
    private Long messageId;

    /** 会话ID */
    @ExcelProperty(value = "会话ID")
    private Long sessionId;

    /** 消息内容（冗余存储，方便查询） */
    @ExcelProperty(value = "消息内容")
    private String content;

    /** 模型名称 */
    @ExcelProperty(value = "模型名称")
    private String modelName;

    /** 备注 */
    @ExcelProperty(value = "备注")
    private String remark;

    /** 收藏时间 */
    @ExcelProperty(value = "收藏时间")
    private Date createTime;
}