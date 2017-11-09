<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>Test Json</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        #btn{width: 40px; font-size: 18px; height:31px;}
    </style>
    <script src="js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="js/SearchPage.js" type="text/javascript"></script>
</head>
<%--<body onload="OutputHtml();">--%>
<body>
<div class="search_top">
    <div class="search_top_left">
        <a href="index.jsp"><img alt="logo" src="images/LOGO.png"></a>
    </div>
    <div class="search_top_right">
        <div class="search_top_form">
            <form> <!--<form action="searchPage.jsp" method="get">-->
                <input id="strquery" name="query" type="text" placeholder="请输入查询内容" >
                <input type="button" id="btn" name="btn" value="搜索" onclick="OutputHtml();"><br />
            </form>
        </div>
    </div>
</div>


<div class='search_mid'>
    <div id="content"></div>
    <div id="pagelist"></div>
</div>
<br><br><br>

<div class="search_bottom">
    <p>法律信息检索&copy;2017 All Rights Reserved</p>
</div>

</body>

</html>



<%--

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <title>Demo</title>

    <script type="text/javascript" src="easyui/jquery-1.8.0.min.js"></script>

    <script type="text/javascript" src="js/jquery.pagination.js"></script>
    <style type="text/css">
        body {
            width: 100%;
            height: 100%;
            margin: 0 auto;
            padding: 0;
            background-color: #FFF;
        }

        #hen {
            background-color: #000;
            height: 50px;
            margin: 0px;
            padding: 12px 20px 2px 20px;
            border: #CCC double 1px;
        }

        .page {
            width: 1024px;
            margin: 20px auto;
            padding: 0;
        }

        #fm {
            margin: 0;
            padding: 10px 30px;
        }

        .ftitle {
            font-size: 14px;
            font-weight: bold;
            color: #666;
            padding: 5px 0;
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
        }

        .fitem {
            margin-bottom: 5px;
        }

        .fitem label {
            display: inline-block;
            width: 80px;
        }

        A {
            text-decoration: none;
        }

        A:link {
            text－decoration: none;
            color: #000;
        }

        A:visited {
            color: #000;
            text－decoration: none
        }

        A:active {
            color: #000;;
            text－decoration: none
        }

        A:hover {
            text－decoration: none;
            color: red;
        }

        .d_over {
            background-color: #EFEFEF;
        }

        .d_out {
            background-color: #FFFFFF;
        }
    </style>

    <script type="text/javascript">
        $(function(){//页面加载时，进行绑定

            bind(0);
        });

        //点击分页时调用的函数，page_id为当前页的索引
        function pageselectCallback(page_id, jq) {
            bind(page_id);
        }

        function bind(pageIndex)
        {
            var temp="";
            var total=0;
            $.ajax({
                type:"GET",
                url:"sys/news.do?method=findByTopic&page="+(pageIndex+1),
                async:false, ////作用是防止在ajax成功调用之前就调用$("#Pagination").pagination,这个时候数据个数还没有初始化
                dataType:"json",
                data:"pageIndex="+(pageIndex+1),//传递页面索引
//发送请求前，显示加载动画
                beforeSend:function(){$("#divload").show();$("#datas #Pagination").hide()},
//请求完毕后，隐藏加载动画
                complete:function(){$("#divload").hide();$("#datas #Pagination").show()},
                success:function(data){
                    var json=data.rows;//json数据
                    total=data.total;//记录总数
                    $.each(json,function(index,item){
                        temp+="<div id='datas' classdivclass=\"d_out\" onmouseover=\"this.className='d_over'\" "+
                                "onmouseout=\"this.className='d_out'\" style='padding: 10px 15px 12px 15px;'>"+
                                "<strong> <a style='font-size: 20px;' href='"+item.URL+"' target='_blank'>"+
                                item.title+"</a></strong>"+
                                "<div style='font-size: 14px; font-famliy: 宋体; text-indent: 2em; margin-top: 5px;'>"+
                                item.summary+" }</div></div><hr />";
                    });
                    $("#datas").html(temp); //将创建的新行附加在DIV中
                }

            });

            if(total!=0){
//调用分页函数，将分页插件绑定到id为Pagination的div上
                $("#Pagination").pagination(total, { //recordCount在后台定义的一个公有变量，通过从数据库查询记录进行赋值，返回记录的总数
                    callback: pageselectCallback, //点击分页时，调用的回调函数
                    prev_text: '« 上一页', //显示上一页按钮的文本
                    next_text: '下一页 »', //显示下一页按钮的文本
                    items_per_page:10, //每页显示的项数
                    num_display_entries:6, //分页插件中间显示的按钮数目
                    current_page:pageIndex, //当前页索引
                    num_edge_entries:2 //分页插件左右两边显示的按钮数目

                });
            }

        }
    </script>
</head>
<body style="">
<!-- start header -->
<div id="hen">
    <div style="color: #FFF;">
        <h1 style="font-size: 20px;">
            实时动态
        </h1>
    </div>
    <div style="text-align: right;">
        <a
                style="color: #FFF; margin: 5px; text－decoration: none; cursor: pointer;"
                href="index.jsp">返回首页</a>
    </div>
</div>
<div class="page">
    <div style="margin: 10px 0;"></div>
    <div id="datas">
    </div>
    <div id="divload" style="text-align: center">
        <img src="images/wait.gif" />
    </div>
    <div id="Pagination" class="digg"></div>
</div>
<br />
<br />
</body>
</html>P
--%>
