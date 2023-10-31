<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="js/vue.js"></script>
</head>
<body>
<div id="app">
    书名:{{name}}</br>
    isbn:{{isbn}}</br>
    价格:{{price}}</br>
    <button value="pick me" @click="commonParam">pick me</button>
</div>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<script>
    //1. 通过js代码获取到按钮对象
    //document代表当前HTML文档
    var vue = new Vue({
        "el":"#app",
        "data":{
            "isbn":"",
            "name":"",
            "price":""
        },
        "methods":{
            commonParam(){
                //使用axios发送异步请求
                axios({
                    "method":"post",
                    "url":"hello-servlet",
                    "params":{
                        "name":"tom",
                        "pwd":"123456"
                    }
                }).then(response => {
                    //then里面是处理请求成功的响应数据
                    //response就是服务器端的响应数据,是json类型的
                    //response里面的data就是响应体的数据
                    this.name = response.data.bookName
                    this.price=response.data.price
                    this.isbn=response.data.isbn
                }).catch(error => {
                    //error是请求失败的错误描述
                    console.log(error)
                })
            }
        }
    })
</script>
</body>
</html>