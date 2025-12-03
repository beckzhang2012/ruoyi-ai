package org.ruoyi.chat.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.core.domain.BaseEntity;

import java.util.Date;

/**
 * AI使用统计业务对象
 *
 * @author ruoyi
 * @date 2025-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AiUsageStatisticsBo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 统计日期开始
     */
    private Date statDateStart;

    /**
     * 统计日期结束
     */
    private Date statDateEnd;

    /**
     * 统计类型（1-日统计，2-周统计，3-月统计）
     */
    private String statType;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 统计日期字符串
     */
    private String statDateStr;

}
