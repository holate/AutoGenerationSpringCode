# AutoGenerationSpringCode
自动生成spring项目基础代码
### 已完成<br>
自动生成controller<br>
自动生成mapper<br>
自动生成mappxml<br>
自动生成pojo<br>
自动生成service<br>
自动生成base类<br>
自动生成siteresponse类<br>
### 未完成<br>
自动分页
全局异常处理
## 其他配置文件
我的blog项目中具有部分，可提供参考

[pom-springboot配置文件](https://github.com/holate/Training/blob/master/%E7%BC%96%E7%A8%8B/%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6/pom-springboot.xml)

[logback配置文件](https://github.com/holate/Training/blob/master/%E7%BC%96%E7%A8%8B/%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6/logback-spring.xml)
### 项目说明
+ 项目fork自[1054624458的site子项目](https://github.com/1054624458/site)
+ 项目规范
    1. 数据库table主键名字需为id
    2. 数据库字段命名需下划线分割
        - 例如:user_name
    3. 下划线后一个单词在实体类中会被大写
        - 例如:user_name在java文件中将被映射为userName
+ 迭代历史
  1. 将可视化界面去掉，改为配置文件形式<br>
  2. 自动生成base类<br>
  3. 自动生成siteresponse类<br>
