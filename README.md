# seby
对seby的一些网站进行数据爬取练手

# 内容
+ 主要收集一些网站的m3u8地址
+ 可根据m3u8地址自行下载视频
+ 爬取数据自己下载的好处……
  
# 使用方式
+ 把sql在数据库里执行
```
CREATE TABLE `live` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `live_address` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(2000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `num` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
```
+ 更改jdbc连接
+ 运行项目
+ 调用接口，传入参数，触发爬取（可自由改造适合自己的办法）
+ 接口调用地址: http://127.0.0.1:8081/get?code=2023062201&start=1
