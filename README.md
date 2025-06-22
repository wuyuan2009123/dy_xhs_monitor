# 如何部署
    前提 需要安装java maven 打出jar包 安装添加 anpodman 联系作者拿打包好的jar 然后解压替换 自己的配置文件 application-pro.yml
    找一个代理ip网站 获取一个代理ip提取了解 配置在 
![config.png](config.png)  
图片中的 proxy-url  去 https://wxpusher.zjiecode.com/ 注册一个账户 获取自己的 app-token 配置好着两项就可以了  
注意数据库 url: jdbc:sqlite:/usr/local/ds_xhs/ds.db   一定压迫配置自己的地址

使用参考 IndexController 里面的接口
