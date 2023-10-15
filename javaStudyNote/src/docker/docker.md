#1.初始Docker</bl>
    我们写的代码会接触到好几个环境：开发环境、测试环境以及生产环境</bl>
    比如:生产环境上你的代码是运行在JDK1.8上的能正常运行，但是测试环境的JDK是1.7导致测试人员部署你的war包后跑程序失败，
    这时你就要将你的JDK环境同war包一起传过去。将来测试人员就不用他的环境了，就在容器中测试就行了，这就规避了因为环境不同而产生的BUG了。
    而这个容器就是Docker。避免了软件跨环境迁移的问题。
 ##1.1.Docker概念
    ·Docker是一个开源的应用引擎
    ·诞生于2013年初，基于Go语言实现，dotCloud公司出品（后改名为Docker Inc）
    · Docker可以让开发者打包他们的应用以及依赖包到一个轻量级、可移植的容器中，然后发布到任何流行的Linux机器上。
    ·容器是完全使用沙箱机制，相互隔离
    ·容器性能开销极低
 小节：docker是一种容器技术，解决软件跨环境迁移的问题。
 
 ##1.2.Docker的安装
    ·①.yum包更新到最新
        yum update
    ·②.安装需要的软件包，yum.util提供yum-config-manager功能，另外两个是devicemapper驱动依赖的
        yum install -y yum-utils device-mapper-persistent-data lvm2
    ·③.设置yum源
        yum-config-manager --add-repo https://download.docker.com
    ·④.安装docker,出现输入的界面都按y
        yum install -y docker-ce
        这里要注意如果你系统本身安装了docker需要先将其卸载，因为docker对centos系统内核有要求
        sudo yum remove docker  docker-common docker-selinux docker-engine
    ·⑤.查看docker版本，验证是否安装成功
        docker -v
        
 ##1.3.Docker架构
  ![表单项标签](../mg/docker/docker架构.jpg)
  ·镜像（Inamge）:Docker镜像就相当于是一个root文件系统。比如官方镜像ubuntu:16.04就包含了完整的一套Ubuntu16.04最小系统的root文件系统。
  ·容器（Container）：镜像和容器的关系，就像是面向对象程序设计中的类和对象一样，镜像是静态的定义，容器是镜像运行时的实体。容器可以被创建、启动、停止、删除、暂停等。
  ·仓库（Repository）:仓库可看成一个代码控制中心，用来保存镜像。
  
#2.Docker服务相关的命令
    ·启动docker服务:systemctl start docker
    ·停止docker服务：systemctl stop docker
    ·重启docker服务：systemctl restart docker
    ·查看docker服务状态：systemctl status docker
    ·开机启动docker服务：systemctl enable docker

#3.Docker镜像相关的命令
    ·查看镜像：docker images
    ·搜索镜像: docker search redis
    ·拉取镜像：docker pull reis:5.0 (如果没有指定版本就会拉取最新的版本。可以通过hub.docker.com来查看版本信息)
    ·删除镜像：docker rmi imggeid / docker rmi redis:latest   
                如果你想删除所有的镜像可以使用docker rmi `docker images -q`
    ·查看镜像：查看本地所有的镜像
        doceker images
        docker images -q # 查看所有镜像的id
    
#4.Docker容器相关命令
    ·查看容器：docker ps #查看正在运行的容器
                dockers ps -a #查看所有容器
    ·创建容器：docker run 参数
                参数说明：
                    ·-i:保持容器运行。通常与-t同时使用。加入it这两个参数后，容器创建后自动进入容器中，退出容器后，容器自动关闭。、
                    ·-t：为容器重新分配一个伪输入终端，通常与-i同时使用。
                    ·-d：以守护（后台）模式运行容器。创建一个容器在后台运行，需要使用docker exec进入容器。退出容器后，容器不会关闭。
                    ·-it创建的容器一般称为交互式容器，-id创建的容器一般称为守护式容器
                    ·--name：为创建的容器命名。
                    docker run -id --name=c1 centos:7 /bin/bash
                    docker exec -it c1 /bin/bash
    ·进入容器：docker exec -it 容器的name /bin/bash
                docker exec 参数 #退出容器，容器不会关闭
    ·启动容器： docker start 容器名称
    ·停止容器：docker stop 容器名称
    ·删除容器：docker rm 容器的名称/容器的ID
    ·查看容器信息：docker inspect 容器名称
    
#5.Docker容器的数据卷
    数据卷暂时的可以理解为是宿主机中的一个文件或者目录
##5.1 数据卷的概念
    思考：<br/>
        ·Docker容器删除后，在容器中产生的数据还在吗？<br/>
        ·Docker容器和外部机器可以直接交换文件吗？<br/>
        ·容器之间想要进行数据交互？<br/>
    数据卷
        ·数据卷是宿主机中的一个目录或文件
        ·当容器目录和数据卷目录绑定后，对方的修改会立即同步
        ·一个数据卷可以被多个容器同时挂载
        ·一个容器也可以挂载多个数据卷
    数据卷的作用
        ·容器数据持久化
        ·外部机器和容器间接通信
        ·容器之间数据交换

##5.2配置数据卷
    ·创建启动容器时，使用-v参数设置数据卷
        docker run ... -v 宿主机目录(文件):容器内目录（文件）...
    ·注意事项：
        1.目录必须时绝对路径
        2.如果目录不存在，会自创建
        3.可以挂载多个数据卷
        docker run -it --name-c1 -v /root/data:/root/data_container centos:7 /bin/bash

#5.3数据卷容器
    多容器进行数据交换
        1.多个容器挂载同一个数据卷
        2.数据卷容器
    配置数据卷容器
        1.创建启动c3数据卷容器，使用-v参数 设置数据卷
            docker run -it --name=c3 -v /volume centos:7 /bin/bash
        2.创建启动c1 c2容器，使用--volumes-from参数 设置数据卷
            docker run it --name=c1 --volumes-from c3 centos:7 /bin/bash
            docker run it --name=c2 --volumes-from c3 centos:7 /bin/bash
        
#6.Docker应用部署
    在Docker容器中部署MySQL,并通过外部mysql客户端操作MySQLServer。
    ①.搜索mysql镜像
    ②.拉取mysql镜像
    ③.创建容器
    ④.操作容器中的mysql
    
        
             