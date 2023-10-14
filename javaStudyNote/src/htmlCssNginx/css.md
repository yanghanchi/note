#1.CSS基本语法
    CSS的引入方式
        1.内联样式
            ·在标签中通过style属性来控制样式。只能影响当前这一行。
            ·格式
                <标签 style="属性名：属性值； 属性名：属性值">内容</标签>
        2.内部样式
            ·在<head>标签中通过<style>标签来控制样式。只能影响当前文件。
            ·格式
                <head>
                    <style>
                        选择器 {
                            属性名： 属性值；
                            属性名： 属性值；
                        }
                    </style>
                </head>  
        3.外部样式
            ·在<head>标签中通过<link>标签来引入独立css文件，可以影响不同的文件。
            ·格式
                <link rel="stylesheet" href="css文件"> 

    CSS的注释
        1.什么是注释
            ·注释是用于解释说明程序的
        2.注释的格式
            ·/*注释的内容*/
        3.注释的特点
            ·被注释的样式，不会被浏览器解析

   