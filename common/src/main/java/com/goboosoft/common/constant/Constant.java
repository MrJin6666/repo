/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.goboosoft.common.constant;

/**
 * 常量
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface Constant {
    /**
     * 成功
     */
    int SUCCESS = 1;
    /**
     * 失败
     */
    int FAIL = 0;
    /**
     * OK
     */
    String OK = "OK";
    /**
     * 用户标识
     */
    String USER_KEY = "userId";
    /**
     * 菜单根节点标识
     */
    Long MENU_ROOT = 0L;
    /**
     * 部门根节点标识
     */
    Long DEPT_ROOT = 0L;
    /**
     * 数据字典根节点标识
     */
    Long DICT_ROOT = 0L;
    /**
     *  升序
     */
    String ASC = "asc";
    /**
     * 降序
     */
    String DESC = "desc";
    /**
     * 创建时间字段名
     */
    String CREATE_DATE = "create_date";

    /**
     * 创建时间字段名
     */
    String ID = "id";

    /**
     * 数据权限过滤
     */
    String SQL_FILTER = "sqlFilter";

    /**
     * 当前页码
     */
    String PAGE = "page";
    /**
     * 每页显示记录数
     */
    String LIMIT = "limit";
    /**
     * 排序字段
     */
    String ORDER_FIELD = "orderField";
    /**
     * 排序方式
     */
    String ORDER = "order";
    /**
     * token header
     */
    String TOKEN_HEADER = "token";

    /**
     * 云存储配置KEY
     */
    String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";
    /**
     * 短信配置KEY
     */
    String SMS_CONFIG_KEY = "SMS_CONFIG_KEY";
    /**
     * 邮件配置KEY
     */
    String MAIL_CONFIG_KEY = "MAIL_CONFIG_KEY";

    /**
     * 定时任务状态
     */
    enum ScheduleStatus {
        /**
         * 暂停
         */
        PAUSE(0),
        /**
         * 正常
         */
        NORMAL(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3),
        /**
         * FASTDFS
         */
        FASTDFS(4),
        /**
         * 本地
         */
        LOCAL(5);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 短信服务商
     */
    enum SmsService {
        /**
         * 阿里云
         */
        ALIYUN(1),
        /**
         * 腾讯云
         */
        QCLOUD(2);

        private int value;

        SmsService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 用户状态
     */
    enum UserService{
        /**
         * 停用
         */
        FAIL(0),
        /**
         * 正常
         */
        NORMAL(1),
        /**
         * 待审核
         */
        CHECK(2),
        /**
         * 审核不通过
         */
        REJECT(3);

        private int value;

        UserService(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 用户类型
     */
    enum CompanyService{
        /**
         * 企业
         */
        COMPANY(0),
        /**
         * 行业
         */
        INDUSTRY(1);

        private int value;

        CompanyService(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 用户状态
     */
    enum MenuService{
        /**
         * 企业信息
         */
        QYGL("qygl"),
        /**
         * 治理计划
         */
        ZLJH("zljh"),
        /**
         * 整改反馈
         */
        ZGFK("zgfk"),
        /**
         * 治理档案
         */
        ZLDA("zlda"),
        /**
         * 人员管理
         */
        RYGL("rygl"),
        /**
         * 管理制度
         */
        GLZD("glzd"),
        /**
         * 危废管理
         */
        WFGL("wfgl"),
        /**
         * 检查结果
         */
        JCJG("jcjg"),
        /**
         * 公告
         */
        GG("gg");

        private String value;

        MenuService(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    /**
     * 治理过程状态
     */
    enum GovernService{
        /**
         * 待处理
         */
        PENDIND("0"),
        /**
         * 处理中
         */
        HAND("1"),
        /**
         * 处理后
         */
        POSTTREAT("2"),
        /**
         * 已完成
         */
        COMPLETE("3"),
        /**
         * 超期
         */
        EXCE("4"),
        /**
         * 延期
         */
        DELAY("5"),
        /**
         * 治理前
         */
        BEFOREGROVERN("6");


        private String value;

        GovernService(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    /**
     * 计划分类
     */
    enum PlanstatusService{
        /**
         * 抽查
         */
        SPOT("1"),
        /**
         * 治理
         */
        grovern("2");


        private String value;

        PlanstatusService(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }

    /**
     * 公司状态
     */
    enum CompanyStatusService{
        /**
         * 待审核
         */
        PENDIND(0),
        /**
         * 审核通过
         */
        PASS(1),
        /**
         * 审核不通过
         */
        FAIL(2);

        private int value;

        CompanyStatusService(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 公司日志状态 0 待审核 1 审核通过 2 审核不通过 3 红牌 4 黄牌 5 一般 6 正常'
     */
    enum CompanyLogService{
        /**
         * 待审核
         */
        PENDIND("0"),
        /**
         * 审核通过
         */
        PASS("1"),
        /**
         * 审核不通过
         */
        FAIL("2"),
        /**
         * 红牌
         */
        RED("3"),
        /**
         * 黄牌
         */
        YELLOW("4"),
        /**
         * 一般
         */
        SAME("5"),
        /**
         * 正常
         */
        NORMAL("6");

        private String value;

        CompanyLogService(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}