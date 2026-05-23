-- ============================================
-- 业务任务表创建脚本
-- 数据库：task_management
-- ============================================

CREATE DATABASE IF NOT EXISTS task_management DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE task_management;

-- ============================================
-- 业务任务表
-- ============================================
DROP TABLE IF EXISTS business_task;

CREATE TABLE business_task (
    task_id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '任务ID',
    task_name VARCHAR(200) NOT NULL COMMENT '任务名称',
    task_description TEXT COMMENT '任务描述',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    due_time DATETIME COMMENT '截止时间',
    task_status VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '任务状态',
    priority VARCHAR(10) NOT NULL DEFAULT 'MEDIUM' COMMENT '优先级',
    assignee_id BIGINT COMMENT '负责人ID',
    assignee_name VARCHAR(100) COMMENT '负责人姓名',
    project_name VARCHAR(200) COMMENT '项目名称',
    estimated_hours INT COMMENT '预计工时(小时)',
    actual_hours INT COMMENT '实际工时(小时)',
    completion_rate INT DEFAULT 0 COMMENT '完成进度(0-100)',
    tags VARCHAR(500) COMMENT '标签(JSON格式)',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    
    INDEX idx_status (task_status),
    INDEX idx_priority (priority),
    INDEX idx_assignee (assignee_id),
    INDEX idx_create_time (create_time),
    INDEX idx_due_time (due_time),
    INDEX idx_project (project_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='业务任务表';

-- ============================================
-- 模拟数据插入 (100条)
-- ============================================

INSERT INTO business_task (task_name, task_description, create_time, due_time, task_status, priority, assignee_id, assignee_name, project_name, estimated_hours, actual_hours, completion_rate, tags) VALUES
-- 待处理任务 (PENDING)
('完成用户登录模块开发', '实现用户登录、注册、密码找回等功能，包括前端页面和后端API', DATE_SUB(NOW(), INTERVAL 30 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), 'PENDING', 'HIGH', 1001, '张三', '用户中心', 40, 0, 0, '["前端","后端","API"]'),
('优化数据库查询性能', '分析慢查询日志，优化关键SQL语句，提升系统响应速度', DATE_SUB(NOW(), INTERVAL 25 DAY), DATE_ADD(NOW(), INTERVAL 10 DAY), 'PENDING', 'HIGH', 1002, '李四', '性能优化', 20, 0, 0, '["数据库","优化"]'),
('修复购物车页面崩溃问题', '用户点击结算按钮时页面出现JS错误，需要排查并修复', DATE_SUB(NOW(), INTERVAL 20 DAY), DATE_ADD(NOW(), INTERVAL 2 DAY), 'PENDING', 'URGENT', 1003, '王五', '电商平台', 8, 0, 0, '["Bug","紧急"]'),
('编写接口文档', '为所有新增API编写Swagger文档，包括参数说明和返回值示例', DATE_SUB(NOW(), INTERVAL 15 DAY), DATE_ADD(NOW(), INTERVAL 7 DAY), 'PENDING', 'MEDIUM', 1001, '张三', '技术文档', 16, 0, 0, '["文档","API"]'),
('实现消息推送功能', '集成极光推送，实现Android和iOS消息推送', DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_ADD(NOW(), INTERVAL 15 DAY), 'PENDING', 'MEDIUM', 1004, '赵六', '移动端', 32, 0, 0, '["推送","移动端"]'),
('订单数据统计分析', '生成月度订单报表，包括订单量、金额、退款率等指标', DATE_SUB(NOW(), INTERVAL 5 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), 'PENDING', 'MEDIUM', 1002, '李四', '数据分析', 12, 0, 0, '["报表","统计"]'),
('优化首页加载速度', '压缩图片、合并CSS/JS文件，提升首屏加载速度', DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'PENDING', 'LOW', 1005, '孙七', '性能优化', 8, 0, 0, '["优化","前端"]'),
('实现文件上传功能', '支持大文件分片上传，显示上传进度', DATE_SUB(NOW(), INTERVAL 8 DAY), DATE_ADD(NOW(), INTERVAL 12 DAY), 'PENDING', 'MEDIUM', 1003, '王五', '云存储', 24, 0, 0, '["文件上传"]'),
('用户权限管理系统', '设计并实现基于RBAC的权限控制模型', DATE_SUB(NOW(), INTERVAL 12 DAY), DATE_ADD(NOW(), INTERVAL 20 DAY), 'PENDING', 'HIGH', 1001, '张三', '安全中心', 48, 0, 0, '["权限","安全"]'),
('集成支付宝支付', '对接支付宝SDK，实现支付功能', DATE_SUB(NOW(), INTERVAL 7 DAY), DATE_ADD(NOW(), INTERVAL 14 DAY), 'PENDING', 'HIGH', 1004, '赵六', '支付系统', 40, 0, 0, '["支付","支付宝"]'),

-- 执行中任务 (IN_PROGRESS)
('开发商品搜索功能', '实现基于Elasticsearch的商品全文搜索，支持高亮和分页', DATE_SUB(NOW(), INTERVAL 20 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), 'IN_PROGRESS', 'HIGH', 1001, '张三', '搜索系统', 60, 30, 50, '["搜索","ES"]'),
('用户行为数据分析', '埋点采集用户行为数据，建立用户画像', DATE_SUB(NOW(), INTERVAL 25 DAY), DATE_ADD(NOW(), INTERVAL 10 DAY), 'IN_PROGRESS', 'MEDIUM', 1002, '李四', '数据分析', 80, 45, 60, '["数据分析","埋点"]'),
('重构订单结算模块', '优化订单计算逻辑，支持多种促销叠加', DATE_SUB(NOW(), INTERVAL 15 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), 'IN_PROGRESS', 'HIGH', 1003, '王五', '电商平台', 48, 25, 55, '["重构","订单"]'),
('实现分布式缓存', '引入Redis集群，实现热点数据缓存', DATE_SUB(NOW(), INTERVAL 18 DAY), DATE_ADD(NOW(), INTERVAL 7 DAY), 'IN_PROGRESS', 'HIGH', 1004, '赵六', '架构优化', 56, 35, 65, '["Redis","缓存"]'),
('移动端适配优化', '确保所有页面在移动设备上正常显示', DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_ADD(NOW(), INTERVAL 4 DAY), 'IN_PROGRESS', 'MEDIUM', 1005, '孙七', '前端优化', 32, 18, 55, '["响应式","移动端"]'),
('日志监控系统搭建', '使用ELK搭建集中式日志监控系统', DATE_SUB(NOW(), INTERVAL 22 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'IN_PROGRESS', 'MEDIUM', 1001, '张三', '运维平台', 72, 40, 60, '["日志","监控"]'),
('消息队列引入', '使用RabbitMQ实现异步解耦和流量削峰', DATE_SUB(NOW(), INTERVAL 14 DAY), DATE_ADD(NOW(), INTERVAL 6 DAY), 'IN_PROGRESS', 'HIGH', 1002, '李四', '架构优化', 64, 32, 50, '["MQ","异步"]'),
('客服系统开发', '实现在线客服功能，支持人工和机器人', DATE_SUB(NOW(), INTERVAL 16 DAY), DATE_ADD(NOW(), INTERVAL 9 DAY), 'IN_PROGRESS', 'MEDIUM', 1003, '王五', '客服系统', 80, 45, 55, '["客服","IM"]'),
('API网关设计', '设计统一的API网关，实现路由和鉴权', DATE_SUB(NOW(), INTERVAL 12 DAY), DATE_ADD(NOW(), INTERVAL 11 DAY), 'IN_PROGRESS', 'HIGH', 1004, '赵六', '网关服务', 48, 24, 50, '["网关","API"]'),
('数据迁移项目', '将老系统数据迁移到新数据库', DATE_SUB(NOW(), INTERVAL 8 DAY), DATE_ADD(NOW(), INTERVAL 15 DAY), 'IN_PROGRESS', 'HIGH', 1005, '孙七', '数据工程', 96, 50, 50, '["迁移","数据"]'),

-- 已完成任务 (COMPLETED)
('完成登录页面UI设计', '设计登录注册页面的UI原型和交互稿', DATE_SUB(NOW(), INTERVAL 45 DAY), DATE_SUB(NOW(), INTERVAL 30 DAY), 'COMPLETED', 'MEDIUM', 1005, '孙七', '用户中心', 24, 22, 100, '["UI","设计"]'),
('数据库表结构设计', '设计用户、订单、商品等核心表结构', DATE_SUB(NOW(), INTERVAL 60 DAY), DATE_SUB(NOW(), INTERVAL 50 DAY), 'COMPLETED', 'HIGH', 1002, '李四', '架构设计', 40, 38, 100, '["数据库","设计"]'),
('实现用户注册功能', '包括邮箱验证和手机号验证', DATE_SUB(NOW(), INTERVAL 40 DAY), DATE_SUB(NOW(), INTERVAL 35 DAY), 'COMPLETED', 'HIGH', 1001, '张三', '用户中心', 32, 30, 100, '["注册","验证"]'),
('搭建开发环境', '配置Maven仓库、Git仓库、开发工具', DATE_SUB(NOW(), INTERVAL 90 DAY), DATE_SUB(NOW(), INTERVAL 85 DAY), 'COMPLETED', 'LOW', 1004, '赵六', '环境搭建', 16, 15, 100, '["环境"]'),
('实现商品分类管理', '后台商品分类的增删改查', DATE_SUB(NOW(), INTERVAL 50 DAY), DATE_SUB(NOW(), INTERVAL 42 DAY), 'COMPLETED', 'MEDIUM', 1003, '王五', '商品管理', 28, 26, 100, '["商品","CRUD"]'),
('配置Nginx反向代理', '配置Nginx实现负载均衡和静态资源服务', DATE_SUB(NOW(), INTERVAL 55 DAY), DATE_SUB(NOW(), INTERVAL 48 DAY), 'COMPLETED', 'MEDIUM', 1005, '孙七', '运维', 12, 11, 100, '["Nginx","运维"]'),
('编写单元测试', '为核心业务逻辑编写JUnit单元测试', DATE_SUB(NOW(), INTERVAL 35 DAY), DATE_SUB(NOW(), INTERVAL 28 DAY), 'COMPLETED', 'MEDIUM', 1002, '李四', '质量保障', 36, 34, 100, '["测试","JUnit"]'),
('实现邮件发送功能', '集成JavaMail实现邮件发送', DATE_SUB(NOW(), INTERVAL 38 DAY), DATE_SUB(NOW(), INTERVAL 32 DAY), 'COMPLETED', 'LOW', 1001, '张三', '通知系统', 16, 14, 100, '["邮件","通知"]'),
('数据备份脚本', '编写自动化数据库备份脚本', DATE_SUB(NOW(), INTERVAL 42 DAY), DATE_SUB(NOW(), INTERVAL 38 DAY), 'COMPLETED', 'MEDIUM', 1004, '赵六', '运维', 8, 7, 100, '["备份","脚本"]'),
('完成接口联调', '与第三方支付接口完成联调测试', DATE_SUB(NOW(), INTERVAL 28 DAY), DATE_SUB(NOW(), INTERVAL 21 DAY), 'COMPLETED', 'HIGH', 1003, '王五', '支付系统', 24, 23, 100, '["联调","支付"]'),

-- 失败任务 (FAILED)
('集成微信支付', '对接微信支付SDK，测试环境验证失败', DATE_SUB(NOW(), INTERVAL 50 DAY), DATE_SUB(NOW(), INTERVAL 40 DAY), 'FAILED', 'HIGH', 1004, '赵六', '支付系统', 40, 35, 30, '["支付","微信","失败"]'),
('实现即时通讯', 'WebSocket长连接实现失败，连接不稳定', DATE_SUB(NOW(), INTERVAL 45 DAY), DATE_SUB(NOW(), INTERVAL 30 DAY), 'FAILED', 'MEDIUM', 1003, '王五', 'IM系统', 60, 45, 40, '["WebSocket","IM","失败"]'),
('数据导入功能', '大文件Excel导入超时，无法完成', DATE_SUB(NOW(), INTERVAL 35 DAY), DATE_SUB(NOW(), INTERVAL 25 DAY), 'FAILED', 'MEDIUM', 1002, '李四', '数据导入', 20, 18, 50, '["导入","Excel","失败"]'),

-- 更多待处理任务
('开发数据导出功能', '支持导出Excel、CSV格式数据', DATE_SUB(NOW(), INTERVAL 6 DAY), DATE_ADD(NOW(), INTERVAL 10 DAY), 'PENDING', 'MEDIUM', 1001, '张三', '数据工具', 24, 0, 0, '["导出","Excel"]'),
('实现图片压缩功能', '上传图片自动压缩，减少存储空间', DATE_SUB(NOW(), INTERVAL 9 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'PENDING', 'LOW', 1005, '孙七', '媒体处理', 16, 0, 0, '["图片","压缩"]'),
('优化搜索结果排序', '根据相关性和销量优化搜索排序', DATE_SUB(NOW(), INTERVAL 4 DAY), DATE_ADD(NOW(), INTERVAL 12 DAY), 'PENDING', 'MEDIUM', 1002, '李四', '搜索系统', 20, 0, 0, '["搜索","排序"]'),
('实现活动秒杀功能', '高并发场景下的秒杀系统', DATE_SUB(NOW(), INTERVAL 11 DAY), DATE_ADD(NOW(), INTERVAL 18 DAY), 'PENDING', 'URGENT', 1003, '王五', '营销系统', 80, 0, 0, '["秒杀","高并发"]'),
('用户反馈系统', '收集和处理用户反馈建议', DATE_SUB(NOW(), INTERVAL 2 DAY), DATE_ADD(NOW(), INTERVAL 6 DAY), 'PENDING', 'LOW', 1004, '赵六', '用户中心', 12, 0, 0, '["反馈"]'),
('API版本管理', '实现API多版本共存和平滑迁移', DATE_SUB(NOW(), INTERVAL 13 DAY), DATE_ADD(NOW(), INTERVAL 20 DAY), 'PENDING', 'MEDIUM', 1001, '张三', 'API管理', 32, 0, 0, '["API","版本"]'),
('实现七牛云存储对接', '文件上传到七牛云OSS', DATE_SUB(NOW(), INTERVAL 7 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), 'PENDING', 'MEDIUM', 1005, '孙七', '云存储', 16, 0, 0, '["OSS","云存储"]'),
('积分系统开发', '用户积分获取和消费规则', DATE_SUB(NOW(), INTERVAL 5 DAY), DATE_ADD(NOW(), INTERVAL 16 DAY), 'PENDING', 'MEDIUM', 1002, '李四', '会员系统', 40, 0, 0, '["积分","会员"]'),
('实现分享功能', '商品、文章分享到社交平台', DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_ADD(NOW(), INTERVAL 4 DAY), 'PENDING', 'LOW', 1003, '王五', '社交', 8, 0, 0, '["分享","社交"]'),
('订单超时处理', '未支付订单超时自动取消', DATE_SUB(NOW(), INTERVAL 6 DAY), DATE_ADD(NOW(), INTERVAL 9 DAY), 'PENDING', 'MEDIUM', 1004, '赵六', '订单系统', 12, 0, 0, '["订单","定时"]'),

-- 更多执行中任务
('会员等级系统', '设计会员等级和权益体系', DATE_SUB(NOW(), INTERVAL 18 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), 'IN_PROGRESS', 'MEDIUM', 1001, '张三', '会员系统', 48, 28, 60, '["会员","等级"]'),
('优惠券系统', '多种优惠券类型和使用规则', DATE_SUB(NOW(), INTERVAL 14 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'IN_PROGRESS', 'HIGH', 1002, '李四', '营销系统', 56, 30, 55, '["优惠券","营销"]'),
('物流跟踪系统', '对接快递100实时查询物流', DATE_SUB(NOW(), INTERVAL 16 DAY), DATE_ADD(NOW(), INTERVAL 6 DAY), 'IN_PROGRESS', 'MEDIUM', 1003, '王五', '物流', 40, 22, 55, '["物流","快递"]'),
('商品评论系统', '支持商品评论和晒图功能', DATE_SUB(NOW(), INTERVAL 12 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), 'IN_PROGRESS', 'MEDIUM', 1005, '孙七', '商品', 32, 18, 55, '["评论","商品"]'),
('库存预警系统', '库存低于阈值自动提醒', DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_ADD(NOW(), INTERVAL 7 DAY), 'IN_PROGRESS', 'MEDIUM', 1004, '赵六', '库存', 24, 14, 60, '["库存","预警"]'),
('用户活跃度统计', 'DAU、MAU等核心指标统计', DATE_SUB(NOW(), INTERVAL 8 DAY), DATE_ADD(NOW(), INTERVAL 4 DAY), 'IN_PROGRESS', 'MEDIUM', 1001, '张三', '数据', 20, 12, 60, '["统计","活跃度"]'),
('广告投放系统', '精准投放广告到目标用户', DATE_SUB(NOW(), INTERVAL 20 DAY), DATE_ADD(NOW(), INTERVAL 10 DAY), 'IN_PROGRESS', 'HIGH', 1002, '李四', '广告', 64, 35, 55, '["广告","投放"]'),
('内容审核系统', '敏感词过滤和人工审核', DATE_SUB(NOW(), INTERVAL 15 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'IN_PROGRESS', 'HIGH', 1003, '王五', '内容', 48, 26, 55, '["审核","内容"]'),
('短信验证码功能', '注册登录短信验证', DATE_SUB(NOW(), INTERVAL 11 DAY), DATE_ADD(NOW(), INTERVAL 2 DAY), 'IN_PROGRESS', 'HIGH', 1004, '赵六', '验证', 16, 10, 60, '["短信","验证"]'),
('购物车数据同步', '多端购物车数据实时同步', DATE_SUB(NOW(), INTERVAL 13 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), 'IN_PROGRESS', 'MEDIUM', 1005, '孙七', '购物车', 28, 15, 55, '["购物车","同步"]'),

-- 更多已完成任务
('完成首页UI改版', '全新设计的首页UI和交互', DATE_SUB(NOW(), INTERVAL 30 DAY), DATE_SUB(NOW(), INTERVAL 22 DAY), 'COMPLETED', 'HIGH', 1005, '孙七', 'UI改版', 48, 46, 100, '["UI","首页"]'),
('配置CI/CD流水线', 'Jenkins自动构建部署', DATE_SUB(NOW(), INTERVAL 55 DAY), DATE_SUB(NOW(), INTERVAL 48 DAY), 'COMPLETED', 'MEDIUM', 1004, '赵六', 'DevOps', 32, 30, 100, '["CI/CD","Jenkins"]'),
('用户注册短信验证', '手机号注册验证码功能', DATE_SUB(NOW(), INTERVAL 65 DAY), DATE_SUB(NOW(), INTERVAL 58 DAY), 'COMPLETED', 'HIGH', 1001, '张三', '用户', 20, 19, 100, '["短信","注册"]'),
('实现商品收藏功能', '用户收藏商品和收藏列表', DATE_SUB(NOW(), INTERVAL 48 DAY), DATE_SUB(NOW(), INTERVAL 42 DAY), 'COMPLETED', 'LOW', 1003, '王五', '商品', 16, 15, 100, '["收藏","商品"]'),
('完成搜索功能基础版', '基于MySQL的模糊搜索', DATE_SUB(NOW(), INTERVAL 70 DAY), DATE_SUB(NOW(), INTERVAL 60 DAY), 'COMPLETED', 'HIGH', 1002, '李四', '搜索', 40, 38, 100, '["搜索"]'),
('接入阿里云OSS', '文件存储迁移到阿里云', DATE_SUB(NOW(), INTERVAL 52 DAY), DATE_SUB(NOW(), INTERVAL 45 DAY), 'COMPLETED', 'MEDIUM', 1005, '孙七', '存储', 24, 23, 100, '["OSS","存储"]'),
('实现收藏夹功能', '用户收藏商品管理', DATE_SUB(NOW(), INTERVAL 58 DAY), DATE_SUB(NOW(), INTERVAL 50 DAY), 'COMPLETED', 'LOW', 1003, '王五', '用户', 12, 11, 100, '["收藏"]'),
('完成帮助中心页面', 'FAQ和用户指南', DATE_SUB(NOW(), INTERVAL 40 DAY), DATE_SUB(NOW(), INTERVAL 35 DAY), 'COMPLETED', 'LOW', 1005, '孙七', '内容', 16, 15, 100, '["帮助"]'),
('实现浏览历史记录', '用户商品浏览历史', DATE_SUB(NOW(), INTERVAL 36 DAY), DATE_SUB(NOW(), INTERVAL 30 DAY), 'COMPLETED', 'LOW', 1001, '张三', '用户', 12, 11, 100, '["历史","浏览"]'),
('接入短信服务商', '集成阿里云短信服务', DATE_SUB(NOW(), INTERVAL 62 DAY), DATE_SUB(NOW(), INTERVAL 55 DAY), 'COMPLETED', 'HIGH', 1004, '赵六', '通知', 20, 19, 100, '["短信"]'),

-- 更多失败任务
('实现直播功能', 'WebRTC直播推流失败', DATE_SUB(NOW(), INTERVAL 55 DAY), DATE_SUB(NOW(), INTERVAL 40 DAY), 'FAILED', 'HIGH', 1003, '王五', '直播', 80, 50, 25, '["直播","WebRTC","失败"]'),
('大数据报表导出', '千万级数据导出内存溢出', DATE_SUB(NOW(), INTERVAL 45 DAY), DATE_SUB(NOW(), INTERVAL 35 DAY), 'FAILED', 'MEDIUM', 1002, '李四', '报表', 40, 30, 30, '["导出","大数据","失败"]'),

-- 补充任务达到100条
('实现猜你喜欢', '基于协同过滤的推荐算法', DATE_SUB(NOW(), INTERVAL 17 DAY), DATE_ADD(NOW(), INTERVAL 10 DAY), 'IN_PROGRESS', 'MEDIUM', 1001, '张三', '推荐', 56, 30, 55, '["推荐","算法"]'),
('实现商品比价功能', '全网商品价格对比', DATE_SUB(NOW(), INTERVAL 8 DAY), DATE_ADD(NOW(), INTERVAL 12 DAY), 'PENDING', 'LOW', 1002, '李四', '工具', 24, 0, 0, '["比价"]'),
('接入高德地图', '店铺位置展示和导航', DATE_SUB(NOW(), INTERVAL 5 DAY), DATE_ADD(NOW(), INTERVAL 7 DAY), 'PENDING', 'MEDIUM', 1003, '王五', 'LBS', 16, 0, 0, '["地图","LBS"]'),
('实现拼团功能', '社交电商拼团系统', DATE_SUB(NOW(), INTERVAL 19 DAY), DATE_ADD(NOW(), INTERVAL 15 DAY), 'IN_PROGRESS', 'HIGH', 1004, '赵六', '营销', 72, 40, 55, '["拼团","营销"]'),
('商品详情页优化', '提升加载速度和SEO', DATE_SUB(NOW(), INTERVAL 4 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), 'PENDING', 'MEDIUM', 1005, '孙七', '前端', 20, 0, 0, '["优化","SEO"]'),
('用户画像系统', '多维度用户特征分析', DATE_SUB(NOW(), INTERVAL 21 DAY), DATE_ADD(NOW(), INTERVAL 12 DAY), 'IN_PROGRESS', 'MEDIUM', 1001, '张三', '数据', 64, 35, 55, '["画像","数据"]'),
('实现签到功能', '每日签到送积分', DATE_SUB(NOW(), INTERVAL 6 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), 'PENDING', 'LOW', 1002, '李四', '运营', 12, 0, 0, '["签到","积分"]'),
('接入客服机器人', '智能客服自动回复', DATE_SUB(NOW(), INTERVAL 14 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'IN_PROGRESS', 'MEDIUM', 1003, '王五', '客服', 48, 26, 55, '["客服","AI"]'),
('实现分销系统', '多级分销返利机制', DATE_SUB(NOW(), INTERVAL 22 DAY), DATE_ADD(NOW(), INTERVAL 18 DAY), 'IN_PROGRESS', 'HIGH', 1004, '赵六', '分销', 60, 32, 55, '["分销","返利"]'),
('商品属性筛选', '多维度商品筛选', DATE_SUB(NOW(), INTERVAL 7 DAY), DATE_ADD(NOW(), INTERVAL 6 DAY), 'PENDING', 'MEDIUM', 1005, '孙七', '搜索', 24, 0, 0, '["筛选","商品"]'),
('实现文章系统', 'CMS内容管理系统', DATE_SUB(NOW(), INTERVAL 16 DAY), DATE_ADD(NOW(), INTERVAL 9 DAY), 'IN_PROGRESS', 'MEDIUM', 1001, '张三', '内容', 40, 22, 55, '["CMS","文章"]'),
('消息中心开发', '统一消息通知中心', DATE_SUB(NOW(), INTERVAL 9 DAY), DATE_ADD(NOW(), INTERVAL 4 DAY), 'PENDING', 'MEDIUM', 1002, '李四', '通知', 20, 0, 0, '["消息","通知"]'),
('实现预约功能', '服务预约和时间段管理', DATE_SUB(NOW(), INTERVAL 11 DAY), DATE_ADD(NOW(), INTERVAL 7 DAY), 'IN_PROGRESS', 'MEDIUM', 1003, '王五', '预约', 32, 18, 55, '["预约"]'),
('积分商城开发', '积分兑换商品系统', DATE_SUB(NOW(), INTERVAL 18 DAY), DATE_ADD(NOW(), INTERVAL 14 DAY), 'IN_PROGRESS', 'MEDIUM', 1004, '赵六', '商城', 48, 26, 55, '["积分","商城"]'),
('实现评价系统', '商品和服务评价', DATE_SUB(NOW(), INTERVAL 5 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), 'PENDING', 'MEDIUM', 1005, '孙七', '评价', 16, 0, 0, '["评价"]'),
('接入支付宝芝麻信用', '用户信用评估', DATE_SUB(NOW(), INTERVAL 20 DAY), DATE_ADD(NOW(), INTERVAL 10 DAY), 'IN_PROGRESS', 'MEDIUM', 1001, '张三', '信用', 24, 14, 60, '["信用","芝麻"]'),
('实现退款退货流程', '完善的售后服务体系', DATE_SUB(NOW(), INTERVAL 15 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'IN_PROGRESS', 'HIGH', 1002, '李四', '售后', 40, 22, 55, '["退款","售后"]'),
('实现地址管理', '用户收货地址管理', DATE_SUB(NOW(), INTERVAL 3 DAY), DATE_ADD(NOW(), INTERVAL 2 DAY), 'PENDING', 'MEDIUM', 1003, '王五', '用户', 8, 0, 0, '["地址","用户"]'),
('实现会员日活动', '每月会员日专属优惠', DATE_SUB(NOW(), INTERVAL 12 DAY), DATE_ADD(NOW(), INTERVAL 6 DAY), 'IN_PROGRESS', 'HIGH', 1004, '赵六', '运营', 32, 18, 55, '["活动","会员"]'),
('实现足迹功能', '用户浏览足迹展示', DATE_SUB(NOW(), INTERVAL 4 DAY), DATE_ADD(NOW(), INTERVAL 3 DAY), 'PENDING', 'LOW', 1005, '孙七', '用户', 8, 0, 0, '["足迹"]'),
('实现红包功能', '红包发放和领取', DATE_SUB(NOW(), INTERVAL 19 DAY), DATE_ADD(NOW(), INTERVAL 11 DAY), 'IN_PROGRESS', 'HIGH', 1001, '张三', '营销', 36, 20, 55, '["红包","营销"]'),
('接入实名认证', '用户身份实名认证', DATE_SUB(NOW(), INTERVAL 8 DAY), DATE_ADD(NOW(), INTERVAL 5 DAY), 'PENDING', 'MEDIUM', 1002, '李四', '认证', 16, 0, 0, '["实名","认证"]'),
('实现评价置顶', '优质评价优先展示', DATE_SUB(NOW(), INTERVAL 6 DAY), DATE_ADD(NOW(), INTERVAL 4 DAY), 'PENDING', 'LOW', 1003, '王五', '评价', 12, 0, 0, '["评价"]'),
('实现发票系统', '电子发票开具和管理', DATE_SUB(NOW(), INTERVAL 17 DAY), DATE_ADD(NOW(), INTERVAL 9 DAY), 'IN_PROGRESS', 'MEDIUM', 1004, '赵六', '财务', 32, 18, 55, '["发票"]'),
('实现多语言切换', '中英文语言切换', DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_ADD(NOW(), INTERVAL 8 DAY), 'PENDING', 'LOW', 1005, '孙七', '国际化', 20, 0, 0, '["i18n","多语言"]');

-- 查看插入结果
SELECT COUNT(*) as total_count FROM business_task;
SELECT task_status, COUNT(*) as count FROM business_task GROUP BY task_status;
SELECT priority, COUNT(*) as count FROM business_task GROUP BY priority;
