### 使用说明
配置文件 resource/conf/admin.properties
weixin.properties 微信配置文件
shior 文件 resource/shior.ini
缓存 resource/ehcache.xml 后台账号 admin 密码 123456
目前功能未全部完成，后续进行版本升级补充。

###  项目依赖
ofcms-core 核心
ofcms-model  数据源
ofcms-front  模板
管理台
ofcms-admin  -> ofcms-core
ofcms-admin  -> ofcms-model
ofcms-admin  -> ofcms-front
###  部署说明
建议采用 idea 工具开发
mysql 5.6+
jdk 1.8
tomcat 8